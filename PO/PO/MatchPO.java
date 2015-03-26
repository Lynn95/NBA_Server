package PO;

import java.util.ArrayList;

import matchbl.TeamMatch;

public class MatchPO {
	/**
	 * 定义比赛的持久化对象，
	 * 表示一场比赛
	 * 只可读，不可修改
	 * */
	private String date;					//日期
	private String matchScore;				//大比分
	private ArrayList<String> scores;		//用于存放四节（可能有加时赛，有加时赛的话在后面继续添加）的比分，为xx-xx的字符串形式，
	private TeamMatchPO  hostTeam;			//主队数据
	private TeamMatchPO  guestTeam;			//客队数据
	
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
