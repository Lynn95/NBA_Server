package matchdataservice;

import java.util.ArrayList;

import PO.MatchPO;

public interface MatchDataService {
	/*
	 * ��Ϊ���ڸ���ֻ��һ����������˲�������ɸѡ����Ϊ������
	 * 
	 * */
	ArrayList<MatchPO> getAllMatch();
	MatchPO getMatch(String date,String teams);  		//team ��������ɣ����Ƕ�����д������HOU
	MatchPO getAMatch(String date,String teamName);		//�����ں�һ���ӻ�ȡһ����������
}
