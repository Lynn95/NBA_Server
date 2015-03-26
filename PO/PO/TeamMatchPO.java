package PO;

import java.util.ArrayList;

import matchbl.SingleMatchPersonalData;

public class TeamMatchPO {
	/*
	 * 
	 * 定义一场比赛中一支球队的持久化对象
	 * 只可读，不可修改 
	 * 
	 * */
	private String teamName;
	private ArrayList<SingleMatchPersonalDataPO> individualData;		//该场比赛的所有球员的数据信息
	
	public TeamMatchPO(String teamName,ArrayList<SingleMatchPersonalDataPO> individualData){
		this.teamName= teamName;
		this.individualData= individualData;
		
	}

	public String getTeamName() {
		return teamName;
	}

	public ArrayList<SingleMatchPersonalDataPO> getIndividualData() {
		return individualData;
	}
}
