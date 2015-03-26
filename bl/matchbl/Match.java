package matchbl;

import java.util.ArrayList;

public class Match {
									/*比赛数据只可以读，不可以修改*/
	private String season;					//赛季
	private String date;					//日期
	private String matchScore;				//大比分
	private ArrayList<String> scores;		//用于存放四节的比分，为xx-xx的字符串形式
	private TeamMatch  hostTeam;			//主队数据
	private TeamMatch  guestTeam;			//客队数据
	
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
