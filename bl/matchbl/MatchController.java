package matchbl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import playerbl.MatchInfo_player;
import playerbl.PlayerWholeSeasonData;
import matchblservice.MatchBLService;
import matchdata.MatchDataController;
import matchdataservice.MatchDataService;
import teambl.MatchInfo_team;
import PO.MatchPO;
import PO.SingleMatchPersonalDataPO;
import PO.TeamMatchPO;
import VO.MatchVO;
import VO.PlayerSeasonDataVO;
import VO.SingleMatchPersonalDataVO;
import VO.TeamMatchVO;
import VO.TeamSeasonDataVO;

public class MatchController extends UnicastRemoteObject implements MatchInfo_team, MatchInfo_player,MatchBLService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatchController() throws RemoteException  {
		
		super();
		
		// TODO Auto-generated constructor stub
	}

	public static TeamMatchVO hostTeam, tempHost = new TeamMatchVO(null, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, null);
	public static TeamMatchVO guestTeam, tempGuest = new TeamMatchVO(null, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, null); // tempHost �� tempGuest���Դ�ż�����м����
	
	
	ArrayList<PlayerWholeSeasonData> list=new ArrayList<>();			//韦祖策添加的
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("starts here!");
		MatchController c;
		try {
			c = new MatchController();
			ArrayList<PlayerSeasonDataVO> p= c.getAllPlayerSeasonMatchData();
			ArrayList<TeamSeasonDataVO> v=c.getTeamSeasonData();
			
			for(int i=0;i<v.size();i++){
				System.out.println(v.get(i).getOffenseEfficiency()+"   offenseEfficiency!~~~");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private ArrayList<MatchVO> getAllMatchVO() throws RemoteException{
		ArrayList<MatchVO> result=new ArrayList<MatchVO>();
		MatchController c=new MatchController();
		MatchDataService mds = new MatchDataController();
		ArrayList<MatchPO> list= mds.getAllMatch();
		for(int i=0;i<list.size();i++){
		result.add(c.matchPOToVO(list.get(i)));
		}
		return result;
	}

	// ��matchpo ������ϼ��� ��װΪmatchvo
	public MatchVO matchPOToVO(MatchPO po) {
		calculateNum(po.getHostTeam(), "host");
		calculateNum(po.getGuestTeam(), "guest");
		calOffenseRound("host");
		calOffenseRound("guest");
		this.hostTeam = TeamMatchPOtoVO(tempHost, "host");
		this.guestTeam = TeamMatchPOtoVO(tempGuest, "guest");
		MatchVO vo = new MatchVO(po.getDate(), po.getMatchScore(),
				po.getScores(), hostTeam, guestTeam);
		return vo;

	}

	// ��teamMatchPO ת��Ϊ TeamMatchVO
	private TeamMatchVO TeamMatchPOtoVO(TeamMatchVO vo, String role) {

		double offenseRound = vo.getOffenseRound(); // ��غ�
		double defenseRound = 0; // ���ػغ�

		double freeThrowPercentage = 0; // ����������
		double threePointPercentage = 0; // ���������
		double shootPercentage = 0; // Ͷ��������
		double assistEfficiency = 0; // ����
		double O_ReboundEfficiency = 0; // ��������
		double D_ReboundEfficiency = 0; // ����������
		double stealEfficiency = 0; // ������
		double defenseEfficiency = 0; // ����Ч��
		double offenseEfficiency = 0; // ��Ч��

		/*
		 * ������һЩ���Եļ��㹫ʽ 1) ʤ�ʣ�ʤ����/��ʤ����+ʧ�ܳ���
		 * 
		 * 2)
		 * ��غϣ����ӻغ�=Ͷ����+0.4*��ӷ�����-1.07*�����ӽ�����/�����ӽ�����+���ַ������壩*Ͷʧ����+1.07*ʧ����
		 * 
		 * 3) ��Ч�ʣ�ÿ 100 ����غϣ���ӵĵ÷�
		 * 
		 * 4) ����Ч�ʣ�ÿ 100 �����ػغϣ����ֵĵ÷�
		 * 
		 * 5) ������Ч�ʣ�ǰ�����������/��ǰ����������+���ֺ�����������
		 * 
		 * 6) ��������Ч�ʣ������������/������������+����ǰ������������
		 * 
		 * 7) ����Ч�ʣ�ÿ 100 �����ػغϣ���ӵ���������
		 * 
		 * 8) ���ʣ�ÿ 100 ����غϣ���ӵ�������
		 */

		if (role.equals("host")) {
			vo.setDefenseRound(tempGuest.getOffenseRound());
			O_ReboundEfficiency = (double)vo.getO_ReboundNum()
					/ (vo.getO_ReboundNum() + tempGuest.getD_ReboundNum()); // ��������
			D_ReboundEfficiency = (double)vo.getD_ReboundNum()
					/ (vo.getD_ReboundNum() + tempGuest.getO_ReboundNum()); // ����������
		} else if (role.equals("guest")) {
			vo.setDefenseRound(tempHost.getOffenseRound());
			O_ReboundEfficiency = (double)vo.getO_ReboundNum()
					/ (vo.getO_ReboundNum() + tempHost.getD_ReboundNum()); // ��������
			D_ReboundEfficiency = (double)vo.getD_ReboundNum()
					/ (vo.getD_ReboundNum() + tempHost.getO_ReboundNum()); // ����������
		}

		freeThrowPercentage = (double)vo.getFreeThrowGoalNum() / vo.getFreeThrowNum(); // ����������
		threePointPercentage = (double)vo.getT_fieldGoal() / vo.getT_shootNum(); // ���������
		shootPercentage =(double) vo.getFieldGoal() / vo.getShootNum(); // Ͷ��������
		assistEfficiency = (double)vo.getAssistNum() / vo.getOffenseRound() * 100; // ����

		offenseEfficiency = (double)vo.getPointNum() / vo.getOffenseRound() * 100; // ��Ч��
		System.out.println(offenseEfficiency+"   cal offenseRate");
		System.out.println(vo.getPointNum()+"   pointNum");
		System.out.println(vo.getOffenseRound()+"  offenseRound");
		stealEfficiency = (double)vo.getStealNum() / vo.getDefenseRound() * 100; // ������
		defenseEfficiency = (double)vo.getPointNum() / vo.getDefenseRound() * 100; // ����Ч��

		TeamMatchVO matchvo = new TeamMatchVO(vo.getTeamName(),
				vo.getPointNum(), vo.getDefenseNum(), vo.getOffenseNum(),
				vo.getReboundNum(), vo.getO_ReboundNum(), vo.getD_ReboundNum(),
				vo.getAssistNum(), vo.getTurnoverNum(), vo.getStealNum(),
				vo.getFoulNum(), vo.getFieldGoal(), vo.getShootNum(),
				vo.getT_fieldGoal(), vo.getT_shootNum(),
				vo.getFreeThrowGoalNum(), vo.getFreeThrowNum(),
				vo.getBlockNum(), offenseRound, defenseRound,
				freeThrowPercentage, threePointPercentage, shootPercentage,
				assistEfficiency, O_ReboundEfficiency, D_ReboundEfficiency,
				stealEfficiency, defenseEfficiency, offenseEfficiency,
				vo.getIndividualData());
		return matchvo;

	}

	private void calculateNum(TeamMatchPO po, String role) {

		ArrayList<SingleMatchPersonalDataPO> individualData = po
				.getIndividualData();
		ArrayList<SingleMatchPersonalDataVO> singleData = new ArrayList<SingleMatchPersonalDataVO>(
				individualData.size());
		for (int i = 0; i < individualData.size(); i++) {
			singleData.add(new SingleMatchPersonalDataVO(individualData.get(i)
					.getPlayerName(),
					individualData.get(i).getPlayerPosition(), individualData
							.get(i).getTime(), individualData.get(i)
							.getFieldGoal(), individualData.get(i)
							.getShootNum(), individualData.get(i)
							.getT_fieldGoal(), individualData.get(i)
							.getT_shootNum(), individualData.get(i)
							.getFreeThrowGoalNum(), individualData.get(i)
							.getFreeThrowNum(), individualData.get(i)
							.getO_ReboundNum(), individualData.get(i)
							.getD_ReboundNum(), individualData.get(i)
							.getReboundNum(), individualData.get(i)
							.getAssistNum(), individualData.get(i)
							.getStealNum(),
					individualData.get(i).getBlockNum(), individualData.get(i)
							.getTurnoverNum(), individualData.get(i)
							.getFoulNum(), individualData.get(i).getPoints()));
		}// end for
			// ����

		int points = 0; // �÷�
		int defenseNum = 0; // ������
		int offenseNum = 0; // ����
		int reboundNum = 0; // ��������
		int O_ReboundNum = 0; // ��ǰ����������
		int D_ReboundNum = 0; // ���أ��󳡣�������
		int assistNum = 0; // ����
		int turnoverNum = 0; // ʧ����
		int stealNum = 0; // ������
		int foulNum = 0; // ������
		int fieldGoal = 0; // Ͷ��������
		int shootNum = 0; // Ͷ��������
		int T_fieldGoal = 0; // �����������
		int T_shootNum = 0; // ����������
		int freeThrowGoalNum = 0; // ����������
		int freeThrowNum = 0; // ���������
		int blockNum = 0; // ��ñ��

		int playerNum = singleData.size(); // number of players in the team
		for (int i = 0; i < playerNum; i++) {
			// ��ļ���
			points = points + singleData.get(i).getPoints();
			defenseNum = defenseNum + singleData.get(i).getD_ReboundNum();
			offenseNum = offenseNum + singleData.get(i).getO_ReboundNum();
			reboundNum = reboundNum + singleData.get(i).getReboundNum();
			O_ReboundNum = O_ReboundNum + singleData.get(i).getO_ReboundNum();
			D_ReboundNum = D_ReboundNum + singleData.get(i).getD_ReboundNum();
			assistNum = assistNum + singleData.get(i).getAssistNum();
			turnoverNum = turnoverNum + singleData.get(i).getTurnoverNum();
			stealNum = stealNum + singleData.get(i).getStealNum();
			foulNum = foulNum + singleData.get(i).getFoulNum();
			fieldGoal = fieldGoal + singleData.get(i).getFieldGoal();
			shootNum = shootNum + singleData.get(i).getShootNum();
			T_fieldGoal = T_fieldGoal + singleData.get(i).getT_fieldGoal();
			T_shootNum = T_shootNum + singleData.get(i).getT_shootNum();
			freeThrowGoalNum = freeThrowGoalNum
					+ singleData.get(i).getFreeThrowGoalNum();
			freeThrowNum = freeThrowNum + singleData.get(i).getFreeThrowNum();
			blockNum = blockNum + singleData.get(i).getBlockNum();

		}// end for

		if (role.equals("host")) {
			tempHost = new TeamMatchVO(po.getTeamName(), points, defenseNum,
					offenseNum, reboundNum, O_ReboundNum, D_ReboundNum,
					assistNum, turnoverNum, stealNum, foulNum, fieldGoal,
					shootNum, T_fieldGoal, T_shootNum, freeThrowGoalNum,
					freeThrowNum, blockNum, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					singleData);
		} else if (role.equals("guest")) {
			tempGuest = new TeamMatchVO(po.getTeamName(), points, defenseNum,
					offenseNum, reboundNum, O_ReboundNum, D_ReboundNum,
					assistNum, turnoverNum, stealNum, foulNum, fieldGoal,
					shootNum, T_fieldGoal, T_shootNum, freeThrowGoalNum,
					freeThrowNum, blockNum, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					singleData);
		}
	}

	private void calOffenseRound(String role) {
		if (role.equals("host")) {
			
			
			double offenseRound = (double)(tempHost.getShootNum()
					+ 0.4
					* tempHost.getFreeThrowNum()
					- 1.07
					* (tempHost.getO_ReboundNum()
							/ (tempHost.getO_ReboundNum() + tempGuest
									.getD_ReboundNum()) * (tempHost
							.getShootNum() - tempHost.getFieldGoal())) + 1.07
					* tempHost.getTurnoverNum());
			//System.out.println(offenseRound+"   host~~");
			tempHost.setOffenseRound(offenseRound);
			tempGuest.setDefenseRound(offenseRound);
		} else if (role.equals("guest")) {
		
			double offenseRound = (double)(tempGuest.getShootNum()
					+ 0.4
					* tempGuest.getFreeThrowNum()
					- 1.07
					* (tempGuest.getO_ReboundNum()
							/ (tempGuest.getO_ReboundNum() + tempHost
									.getD_ReboundNum()) * (tempGuest
							.getShootNum() - tempGuest.getFieldGoal())) + 1.07
					* tempGuest.getTurnoverNum());
			tempGuest.setOffenseRound(offenseRound);
			tempHost.setDefenseRound(offenseRound);
		
			
			//System.out.println(offenseRound+"   guest~~");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teambl.MatchInfo_team#getTeamSeasonData()
	 */
	@Override
	public ArrayList<TeamSeasonDataVO> getTeamSeasonData() { // ��ȡ������ӵ�����������Ϣ�����VO�Ķ���˵��
		// TODO Auto-generated method stub
		MatchDataService mds = new MatchDataController();
		ArrayList<MatchPO> allMatchInfo = mds.getAllMatch();
		ArrayList<MatchVO> allMatch = new ArrayList<MatchVO>();
		ArrayList<TeamMatchVO> teamSeasonInfo = new ArrayList<TeamMatchVO>();
		ArrayList<Integer> matchNum = new ArrayList<Integer>();

		for (int i = 0; i < allMatchInfo.size(); i++) {
			allMatch.add(matchPOToVO(allMatchInfo.get(i)));
		}

		ArrayList<TeamMatchVO> teamMatch = new ArrayList<TeamMatchVO>();
		for (int i = 0; i < allMatch.size(); i++) {
			teamMatch.add(allMatch.get(i).getHostTeam());
			teamMatch.add(allMatch.get(i).getGuestTeam());
		}

		boolean isExisted = false;
		for (int i = 0; i < teamMatch.size(); i++) {
			isExisted = false;

			for (int j = 0; j < teamSeasonInfo.size(); j++) {
				if (teamMatch.get(i).getTeamName()
						.equals(teamSeasonInfo.get(j).getTeamName())) {
					teamSeasonInfo.get(j).setFieldGoal(
							teamSeasonInfo.get(j).getFieldGoal()
									+ teamMatch.get(i).getFieldGoal()); // Ͷ��������
					teamSeasonInfo.get(j).setShootNum(
							teamSeasonInfo.get(j).getShootNum()
									+ teamMatch.get(i).getShootNum()); // Ͷ��������
					teamSeasonInfo.get(j).setT_fieldGoal(
							teamSeasonInfo.get(j).getT_fieldGoal()
									+ teamMatch.get(i).getT_fieldGoal());// �����������
					teamSeasonInfo.get(j).setT_shootNum(
							teamSeasonInfo.get(j).getT_shootNum()
									+ teamMatch.get(i).getT_shootNum());// ����������
					teamSeasonInfo.get(j).setFreeThrowGoalNum(
							teamSeasonInfo.get(j).getFreeThrowGoalNum()
									+ teamMatch.get(i).getFreeThrowGoalNum());// ����������
					teamSeasonInfo.get(j).setFreeThrowNum(
							teamSeasonInfo.get(j).getFreeThrowNum()
									+ teamMatch.get(i).getFreeThrowNum());// ���������
					teamSeasonInfo.get(j).setO_ReboundNum(
							teamSeasonInfo.get(j).getO_ReboundNum()
									+ teamMatch.get(i).getO_ReboundNum());// ��ǰ����������
					teamSeasonInfo.get(j).setD_ReboundNum(
							teamSeasonInfo.get(j).getD_ReboundNum()
									+ teamMatch.get(i).getD_ReboundNum());// ���أ��󳡣�������
					teamSeasonInfo.get(j).setAssistNum(
							teamSeasonInfo.get(j).getAssistNum()
									+ teamMatch.get(i).getAssistNum()); // ����
					teamSeasonInfo.get(j).setStealNum(
							teamSeasonInfo.get(j).getStealNum()
									+ teamMatch.get(i).getStealNum());// ������
					teamSeasonInfo.get(j).setReboundNum(
							teamSeasonInfo.get(j).getReboundNum()
									+ teamMatch.get(i).getReboundNum());// ��������
					teamSeasonInfo.get(j).setBlockNum(
							teamSeasonInfo.get(j).getBlockNum()
									+ teamMatch.get(i).getBlockNum());// ��ñ��
					teamSeasonInfo.get(j).setTurnoverNum(
							teamSeasonInfo.get(j).getTurnoverNum()
									+ teamMatch.get(i).getTurnoverNum());// ʧ����
					teamSeasonInfo.get(j).setFoulNum(
							teamSeasonInfo.get(j).getFoulNum()
									+ teamMatch.get(i).getFoulNum());// ������
					teamSeasonInfo.get(j).setPointNum(
							teamSeasonInfo.get(j).getPointNum()
									+ teamMatch.get(i).getPointNum());// �÷�
					teamSeasonInfo.get(j).setShootPercentage(
							teamSeasonInfo.get(j).getShootPercentage()
									+ teamMatch.get(i).getShootPercentage());// Ͷ��������
					teamSeasonInfo.get(j).setThreePointPercentage(
							teamSeasonInfo.get(j).getThreePointPercentage()
									+ teamMatch.get(i)
											.getThreePointPercentage());// �������������
					teamSeasonInfo.get(j)
							.setFreeThrowPercentage(
									teamSeasonInfo.get(j)
											.getFreeThrowPercentage()
											+ teamMatch.get(i)
													.getFreeThrowPercentage()); // ������������
					teamSeasonInfo.get(j).setOffenseRound(
							teamSeasonInfo.get(j).getOffenseRound()
									+ teamMatch.get(i).getOffenseRound()); // ��غ�
					teamSeasonInfo.get(j).setOffenseEfficiency(
							teamSeasonInfo.get(j).getOffenseEfficiency()
									+ teamMatch.get(i).getOffenseEfficiency()); // ��Ч��
					teamSeasonInfo.get(j).setDefenseEfficiency(
							teamSeasonInfo.get(j).getDefenseEfficiency()
									+ teamMatch.get(i).getDefenseEfficiency()); // ����Ч��
					teamSeasonInfo.get(j).setReboundEfficiency(
							teamSeasonInfo.get(j).getReboundEfficiency()
									+ teamMatch.get(i).getReboundEfficiency()); // ����Ч��
					teamSeasonInfo.get(j).setStealEfficiency(
							teamSeasonInfo.get(j).getStealEfficiency()
									+ teamMatch.get(i).getFieldGoal()); // ������
					teamSeasonInfo.get(j).setAssistEfficiency(
							teamSeasonInfo.get(j).getAssistEfficiency()
									+ teamMatch.get(i).getAssistEfficiency()); // ����

					matchNum.set(j, matchNum.get(j) + 1);
					isExisted = true;

					/*
					 * double winRate; // ʤ�� double reboundEfficiency //������
					 */

				}
			}
			if (!isExisted) {
				teamSeasonInfo.add(teamMatch.get(i));
				matchNum.add(1);
			}
		}

		ArrayList<Integer> winNum = new ArrayList<Integer>();
		
		
		for (int count = 0; count < teamSeasonInfo.size(); count++) {
			winNum.add(0);
		}
		/* ÿ������ʤ�����ͳ�� */
		String[][] matchCompare = new String[allMatchInfo.size()][2];
		for (int k = 0; k < allMatchInfo.size(); k++) {
			matchCompare[k] = allMatchInfo.get(k).getMatchScore().split("-|-");
			if (Integer.parseInt(matchCompare[k][0])>Integer.parseInt(matchCompare[k][1])) {
				for (int m = 0; m < teamSeasonInfo.size(); m++) {
					if (teamSeasonInfo
							.get(m)
							.getTeamName()
							.equals(allMatchInfo.get(k).getHostTeam()
									.getTeamName())) {
						winNum.set(m, winNum.get(m) + 1);
						
					}
				}

			} else if (Integer.parseInt(matchCompare[k][0])<Integer.parseInt(matchCompare[k][1])) {
				for (int m = 0; m < teamSeasonInfo.size(); m++) {
					if (teamSeasonInfo
							.get(m)
							.getTeamName()
							.equals(allMatchInfo.get(k).getGuestTeam()
									.getTeamName())) {
						winNum.set(m, winNum.get(m) + 1);
					
					}
				}
			}
		}

		ArrayList<TeamSeasonDataVO> seasonDataList = new ArrayList<TeamSeasonDataVO>();
		for (int i = 0; i < teamSeasonInfo.size(); i++) {
			int matchTimes = matchNum.get(i);
			double shootRate = (double)teamSeasonInfo.get(i).getShootPercentage()
					/ matchTimes;
			double threePointRate = (double)teamSeasonInfo.get(i)
					.getThreePointPercentage() / matchTimes;
			double freeThrowRate = (double)teamSeasonInfo.get(i)
					.getFreeThrowPercentage() / matchTimes;
			double offenseRate = (double)teamSeasonInfo.get(i).getOffenseEfficiency()
					/ matchTimes;
			double defenseRate = (double)teamSeasonInfo.get(i).getDefenseEfficiency()
					/ matchTimes;

			double stealRate = (double)teamSeasonInfo.get(i).getStealEfficiency()
					/ matchTimes;
			double assistRate = (double)teamSeasonInfo.get(i).getAssistEfficiency()
					/ matchTimes;
	

			double winRate = (double)winNum.get(i) / matchTimes; // ʤ��
			double reboundRate = 0; // ������
			/*
			 * ���㹫ʽ�� ����ֵΪ0
			 */

			TeamSeasonDataVO singleSeasonData = new TeamSeasonDataVO(
					teamSeasonInfo.get(i).getTeamName(), matchTimes,
					winNum.get(i),teamSeasonInfo.get(i).getFieldGoal(), teamSeasonInfo.get(i)
							.getShootNum(), teamSeasonInfo.get(i)
							.getT_fieldGoal(), teamSeasonInfo.get(i)
							.getT_shootNum(), teamSeasonInfo.get(i)
							.getFreeThrowGoalNum(), teamSeasonInfo.get(i)
							.getFreeThrowNum(), teamSeasonInfo.get(i)
							.getO_ReboundNum(), teamSeasonInfo.get(i)
							.getD_ReboundNum(), teamSeasonInfo.get(i)
							.getAssistNum(), teamSeasonInfo.get(i)
							.getStealNum(), teamSeasonInfo.get(i)
							.getReboundNum(), teamSeasonInfo.get(i)
							.getBlockNum(), teamSeasonInfo.get(i)
							.getTurnoverNum(), teamSeasonInfo.get(i)
							.getFoulNum(), teamSeasonInfo.get(i).getPointNum(),
					shootRate, threePointRate, freeThrowRate, winRate,
					teamSeasonInfo.get(i).getOffenseRound() / matchTimes,
					offenseRate, defenseRate, reboundRate, stealRate,
					assistRate);
			
			seasonDataList.add(singleSeasonData);
		
		}

		return seasonDataList;
	}

	
	/*下面的代码是韦祖策添加的*/
	@Override
	public PlayerSeasonDataVO getPlayerSeasonMatchData(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PlayerSeasonDataVO> getAllPlayerSeasonMatchData(){
		ArrayList<MatchVO> matchList;
		ArrayList<PlayerSeasonDataVO> result=new ArrayList<>();
		try {
			matchList = getAllMatchVO();
		
		
		ArrayList<SingleMatchPersonalDataVO> poList;
		String teamNameA;
		String teamNameB;
		MatchVO vo;
		
		int h_two_shoot_num;
		int g_two_shoot_num;
		
		double h_Offensive_round;//进攻回合
		double g_Offensive_round;
		
		while(matchList.size()>0){
			
			vo=matchList.get(0);
			teamNameA=vo.getHostTeam().getTeamName();
			teamNameB=vo.getGuestTeam().getTeamName();
			
			h_two_shoot_num=vo.getHostTeam().getShootNum()-vo.getHostTeam().getT_shootNum();
			g_two_shoot_num=vo.getGuestTeam().getShootNum()-vo.getGuestTeam().getT_shootNum();
			
			
			/*进攻回合：本队回合=投篮数+0.4*球队罚球数-1.07*（本队进攻篮板/（本队进攻篮
					板+对手防守篮板）*投失球数）+1.07*失误数*/
			h_Offensive_round=vo.getHostTeam().getShootNum()+0.4*vo.getHostTeam().getFreeThrowNum()-
					1.07*((double)vo.getHostTeam().getO_ReboundNum()/(vo.getHostTeam().getO_ReboundNum()+
							vo.getGuestTeam().getD_ReboundEfficiency())*(vo.getHostTeam().getShootNum()-
									vo.getHostTeam().getFieldGoal()))+1.07*vo.getHostTeam().getTurnoverNum();
			
			g_Offensive_round=vo.getGuestTeam().getShootNum()+0.4*vo.getGuestTeam().getFreeThrowNum()-
					1.07*((double)(vo.getGuestTeam().getO_ReboundNum()/(vo.getGuestTeam().getO_ReboundNum()+
							vo.getHostTeam().getD_ReboundEfficiency())*(vo.getGuestTeam().getShootNum()-
							vo.getGuestTeam().getFieldGoal())))+1.07*vo.getGuestTeam().getTurnoverNum();
			
			poList=matchList.get(0).getHostTeam().getIndividualData();
			
			
			
			while(poList.size()>0){
				/*offense_rebound,int two_shoot_num,int rebound_num*/
				dealData(poList.get(0),teamNameA,vo.getHostTeam(),vo.getGuestTeam().getOffenseRound()
						,g_two_shoot_num,g_Offensive_round);
				poList.remove(0);
			}
			poList=matchList.get(0).getGuestTeam().getIndividualData();
			
			while(poList.size()>0){
				dealData(poList.get(0),teamNameB,vo.getGuestTeam(),vo.getHostTeam().getOffenseRound(),
						h_two_shoot_num,h_Offensive_round);
				poList.remove(0);
			}
			matchList.remove(0);
		}
		
		PlayerWholeSeasonData p;
		/*此处按照TSS上的说法，进攻数就是进攻篮板*/
		
		/*String name,String teamName,
		 * int matchNum, int startingNum,
		 * double defenseNum,double offenseNum,
		 * double time,double BPG,
		 * 
		 * double blockEfficiency,double efficiency,
			double reboundNum,double assistNum,
			double turnoverNum,double stealNum,
			double freeThrowPercentage,  double foulNum
			,double pointNum,double threePointPercentage,
			double shootPercentage,double shootEfficiency,
			double assistEfficiency,double reboundEfficiency,
			double offensiveReboundEff,double stealEfficiency,
			double turnoverPercenttage,double usingPercentage,
			double GmSc,int doubleNum,int threeNum*/
		
		for(int i=0;i<list.size();i++){
			list.get(i).calculateAverageData();
			p=list.get(i);
			result.add(new PlayerSeasonDataVO(p.getPlayerName(),p.getTeamName(),
					p.getMatchNum(),p.getStartingNum(),
					p.getD_ReboundNum(),p.getO_ReboundNum(),
					p.getTime(),p.getBlockNum(),
					p.getBlockEfficiency(),p.getEfficiency(),
					 p.getReboundNum(),p.getAssistNum(),
					 p.getTurnoverNum(),p.getStealNum(),
					 p.getFreeThrowPercentage(),p.getFoulNum(),
					 p.getPoints(),p.getThreePointPercentage(),
					 p.getShootPercentage(),p.getShootEfficiency(),
					 p.getAssistEfficiency(),p.getReboundEfficiency(),
					 p.getOffensiveReboundEff(),p.getStealEfficiency(),
					 p.getTurnoverPercentage(), p.getUsingPercentage(),
					 p.getGmSc(),p.getdoubleNum(),p.getThreeNum()));
		}
		
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	private void dealData(SingleMatchPersonalDataVO vo,String team,TeamMatchVO tvo,
			double offense_rebound,int two_shoot_num,double rebound_num){
		String t[]=vo.getTime().split(":");
		int doubleNum=0;
		int threeNum=0;
		int startingNum=0;
		int num=0;
		if(vo.getPoints()>=10){
			num++;
		}
		if(vo.getBlockNum()>=10){
			num++;
		}
		if(vo.getAssistNum()>=10){
			num++;
		}
		if(vo.getReboundNum()>=10){
			num++;
		}
		
		if(num==3){
			threeNum=1;
		}else if(num==2){
			doubleNum=1;
		}else{
			threeNum=doubleNum=0;
		}
		if(!vo.getPlayerPosition().equals("")){
			startingNum=1;
		}
		
		double time;
		try{
		time=Double.parseDouble(t[0])+((double)Double.parseDouble(t[1]))/60;
		}catch(Exception e){
			time=0;
		}
		
		double assistEFf=0;
		  double reboundEff=0;
		  double O_ReboundEff=0;
		   double D_reboundEff=0;
		   double stealEff=0;
		   double usingP=0;
		  double  blockEff=0;
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).getPlayerName().equals(vo.getPlayerName())){
				
				/*double assistEFf,double reboundEff,double O_ReboundEff,
			 double D_reboundEff,double stealEff,double usingP*/
				
				/*助攻率：球员助攻数÷(球员上场时间÷(球队所有球员上场时间÷5)×球队总进
						球数-球员进球数)*/
				/*10）  篮板率：球员篮板数×(球队所有球员上场时间÷5)÷球员上场时间÷(球队总篮
					板+对手总篮板)
							11）  进攻篮板率：公式同上，只是将篮板数改为进攻篮板数
							12）  防守篮板率：公式同上，只是将篮板数改为防守篮板数*/
				if(time!=0){
					if(time/48*tvo.getFieldGoal()-vo.getFieldGoal()!=0)
				  assistEFf=(double)vo.getAssistNum()/(time/48*tvo.getFieldGoal()-vo.getFieldGoal());
				  
				  reboundEff=(double)vo.getReboundNum()*48/time/(tvo.getReboundNum()+ rebound_num);
				  D_reboundEff=(double)vo.getD_ReboundNum()*48/time/(tvo.getReboundNum()+rebound_num );
				  O_ReboundEff=(double)vo.getO_ReboundNum()*48/time/(tvo.getReboundNum()+ rebound_num);
				  
				  /*抢断率： 球员抢断数×(球队所有球员上场时间÷5)÷球员上场时间÷对手进攻次
				数)
					15）  盖帽率：球员盖帽数×(球队所有球员上场时间÷5)÷球员上场时间÷对手两分球
					出手次数
					16）  失误率：球员失误数÷(球员两分球出手次数+0.44×球员罚球次数+球员失误
						数)
					17）  使用率： (球员出手次数+0.44×球员罚球次数+球员失误次数)×(球队所有球员
						上场时间÷5)÷球员上场时间÷(球队所有总球员出手次数+0.44×球队所有球员罚球
						次数+球队所有球员失误次数) */
				  			
				  stealEff=vo.getStealNum()*48/time/offense_rebound;
				  
				  if(( tvo.getShootNum()+tvo.getTurnoverNum())!=0)
				  usingP=(vo.getShootNum()+0.44*vo.getFreeThrowNum()+
				         vo.getTurnoverNum())*48/time/( tvo.getShootNum()+tvo.getTurnoverNum());
					/*盖帽率：球员盖帽数×(球队所有球员上场时间÷5)÷球员上场时间÷对手两分球
						出手次数*/	  
				  blockEff=vo.getBlockNum()*48/time/two_shoot_num;
				  			}
				  /*/*double assistEFf,double reboundEff,double O_ReboundEff,
			 double D_reboundEff,double stealEff,double usingP*/
				list.get(i).addData(time, vo.getFieldGoal(),
						vo.getShootNum(),vo.getT_fieldGoal(),
						vo.getT_shootNum(), vo.getFreeThrowGoalNum(),
						vo.getFreeThrowNum(), vo.getO_ReboundNum(),
						vo.getD_ReboundNum(), vo.getReboundNum(), 
						vo.getAssistNum(),vo.getStealNum(),vo.getBlockNum(),
						vo.getTurnoverNum(), vo.getFoulNum(), 
						vo.getPoints(),doubleNum, threeNum, startingNum,
						assistEFf, reboundEff,O_ReboundEff,
						D_reboundEff,stealEff, usingP,blockEff);
				return;
			}
		}
		
		list.add(new PlayerWholeSeasonData(vo.getPlayerName(),
				vo.getPlayerPosition(),team,time, 
				vo.getFieldGoal(), vo.getShootNum(),vo.getT_fieldGoal(),
				vo.getT_shootNum(), vo.getFreeThrowGoalNum(),
				vo.getFreeThrowNum(), vo.getO_ReboundNum(),
				vo.getD_ReboundNum(), vo.getReboundNum(), 
				vo.getAssistNum(),vo.getStealNum(),vo.getBlockNum(),
				vo.getTurnoverNum(), vo.getFoulNum(), 
				vo.getPoints(),doubleNum, threeNum, startingNum,
				1,assistEFf, reboundEff,O_ReboundEff,
				D_reboundEff,stealEff, usingP,blockEff));
	}

	@Override
	public ArrayList<String> getMatchByTeamTime(String time, String team)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchVO getMatchByTeam(String time,String teamA, String teamB)
			throws RemoteException {
		// TODO Auto-generated method stub
			MatchDataService con=new MatchDataController();
		   MatchPO po=con.getMatch(time, teamA+"-"+teamB);
		   
		   return matchPOToVO( po);

	}
}
