package playerbl;

import java.util.ArrayList;

import PO.MatchPO;
import PO.SingleMatchPersonalDataPO;
import VO.MatchVO;
import VO.PlayerSeasonDataVO;
import VO.SingleMatchPersonalDataVO;
import VO.TeamMatchVO;

public class Teamp {
	
	ArrayList<PlayerWholeSeasonData> list=new ArrayList<>();
	
	
	public ArrayList<PlayerSeasonDataVO> getAllPlayersDataInfo(){
		ArrayList<MatchVO> matchList=new ArrayList<>();
		
		ArrayList<PlayerSeasonDataVO> result=new ArrayList<>();
		
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
					1.07*(vo.getHostTeam().getO_ReboundNum()/(vo.getHostTeam().getO_ReboundNum()+
							vo.getGuestTeam().getD_ReboundEfficiency())*(vo.getHostTeam().getShootNum()-
									vo.getHostTeam().getFieldGoal()))+1.07*vo.getHostTeam().getTurnoverNum();
			g_Offensive_round=vo.getGuestTeam().getShootNum()+0.4*vo.getGuestTeam().getFreeThrowNum()-
					1.07*(vo.getGuestTeam().getO_ReboundNum()/(vo.getGuestTeam().getO_ReboundNum()+
							vo.getGuestTeam().getD_ReboundEfficiency())*(vo.getGuestTeam().getShootNum()-
							vo.getGuestTeam().getFieldGoal()))+1.07*vo.getGuestTeam().getTurnoverNum();
			
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
		/*String name,String teamName,int matchNum, int startingNum,double defenseNum,
	double offenseNum,double time,double BPG,double blockEfficiency,double efficiency,
	double reboundNum,double assistNum,double turnoverNum,
	double stealNum,double freeThrowPercentage,  double foulNum,double pointNum,
	double threePointPercentage,double shootPercentage,double shootEfficiency,
	double assistEfficiency,double reboundEfficiency,double offensiveReboundEff,
	double stealEfficiency,double turnoverPercenttage,double usingPercentage,
	double GmSc,int doubleNum,int threeNum*/
		
		
		for(int i=0;i<list.size();i++){
			list.get(i).calculateAverageData();
			p=list.get(i);
			result.add(new PlayerSeasonDataVO(p.getPlayerName(),p.getTeamName(),
					p.getMatchNum(),p.getStartingNum(),
					p.getEfficiency(),p.getD_ReboundNum(),
					p.getO_ReboundNum(),p.getTime(),
					p.getReboundNum(),p.getBlockEfficiency(),
					 p.getReboundNum(),p.getAssistNum(),p.getTurnoverNum(),p.getStealNum(),
					 p.getFreeThrowPercentage(),p.getFoulNum(),
					 p.getPoints(),p.getThreePointPercentage(),
					 p.getShootPercentage(),p.getShootEfficiency(),
					 p.getAssistEfficiency(),p.getReboundEfficiency(),
					 p.getOffensiveReboundEff(),p.getStealEfficiency(),p.getTurnoverPercentage(),
					 p.getUsingPercentage(),p.getGmSc(),p.getdoubleNum(),p.getThreeNum()));
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
		if(vo.getAssistNum()>=0){
			num++;
		}
		if(vo.getD_ReboundNum()>=10){
			num++;
		}
		
		if(num>=3){
			threeNum=1;
		}else if(num==2){
			doubleNum=1;
		}else{
			threeNum=doubleNum=0;
		}
		if(!vo.getPlayerPosition().equals("")){
			startingNum=1;
		}
		
		double time=Double.parseDouble(t[0])+Double.parseDouble(t[1])/60;
		
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
				 assistEFf=vo.getAssistNum()/(time/48*tvo.getFieldGoal()-vo.getFieldGoal());
				  reboundEff=vo.getReboundNum()*48/time/(tvo.getReboundNum()+ rebound_num);
				  D_reboundEff=vo.getD_ReboundNum()*48/time/(tvo.getReboundNum()+rebound_num );
				  O_ReboundEff=vo.getO_ReboundNum()*48/time/(tvo.getReboundNum()+ rebound_num);
				  
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
				  usingP=(vo.getShootNum()+0.44*vo.getFreeThrowNum()+
				         vo.getTurnoverNum())*48/time/( tvo.getShootNum()+tvo.getTurnoverNum());
					/*盖帽率：球员盖帽数×(球队所有球员上场时间÷5)÷球员上场时间÷对手两分球
						出手次数*/	  
				  blockEff=vo.getBlockNum()*48/time/two_shoot_num;
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

}
