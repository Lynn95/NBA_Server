package PO;

import java.util.ArrayList;

import matchbl.SingleMatchPersonalData;

public class TeamMatchPO {
	/*
	 * 
	 * ����һ��������һ֧��ӵĳ־û�����
	 * ֻ�ɶ��������޸� 
	 * 
	 * */
	private String teamName;
	private ArrayList<SingleMatchPersonalDataPO> individualData;		//�ó�������������Ա��������Ϣ
	
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
