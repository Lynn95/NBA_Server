package PO;

import java.util.ArrayList;

import matchbl.TeamMatch;

public class MatchPO {
	/**
	 * ��������ĳ־û�����
	 * ��ʾһ������
	 * ֻ�ɶ��������޸�
	 * */
	private String date;					//����
	private String matchScore;				//��ȷ�
	private ArrayList<String> scores;		//���ڴ���Ľڣ������м�ʱ�����м�ʱ���Ļ��ں��������ӣ��ıȷ֣�Ϊxx-xx���ַ�����ʽ��
	private TeamMatchPO  hostTeam;			//��������
	private TeamMatchPO  guestTeam;			//�Ͷ�����
	
	public MatchPO(String date,String matchScore,
			ArrayList<String>scores,TeamMatchPO ht,TeamMatchPO gt){

		this.date=date;
		this.matchScore=matchScore;
		this.scores=scores;
		this.hostTeam=ht;
		this.guestTeam=gt;	
	}

	public String getDate() {
		return date;
	}

	public String getMatchScore() {
		return matchScore;
	}

	public ArrayList<String> getScores() {
		return scores;
	}

	public TeamMatchPO getHostTeam() {
		return hostTeam;
	}

	public TeamMatchPO getGuestTeam() {
		return guestTeam;
	}
}
