package matchbl;

import java.util.ArrayList;

public class Match {
									/*��������ֻ���Զ����������޸�*/
	private String season;					//����
	private String date;					//����
	private String matchScore;				//��ȷ�
	private ArrayList<String> scores;		//���ڴ���Ľڵıȷ֣�Ϊxx-xx���ַ�����ʽ
	private TeamMatch  hostTeam;			//��������
	private TeamMatch  guestTeam;			//�Ͷ�����
	
	public Match(String season,String date,String matchScore,
			ArrayList<String>scores,TeamMatch ht,TeamMatch gt){
		this.season=season;
		this.date=date;
		this.matchScore=matchScore;
		this.scores=scores;
		this.hostTeam=ht;
		this.guestTeam=gt;	
	}

	public String getSeason() {
		return season;
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

	public TeamMatch getHostTeam() {
		return hostTeam;
	}

	public TeamMatch getGuestTeam() {
		return guestTeam;
	}
}
