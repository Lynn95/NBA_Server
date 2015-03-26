package matchbl;

import java.util.ArrayList;

public class TeamMatch {
	
	String teamName;
	int pointNum;					//总得分
	int defenseNum;				//防守数
	int offenseNum;				//进攻数
	int  reboundNum;				//篮板数
	int assistNum;				//场均助攻数
	int turnoverNum;				//场均失误数
	int stealNum;				//场均抢断数
	int freeThrowPercentage;		//场均罚篮命中率  
	int foulNum;					//场均犯规数
	double threePointPercentage;    //场均三分命中率
	double shootPercentage; 		//投篮命中率
	double shotEfficiency;			//真实命中率
	double assistEfficiency;        //助攻率
	double reboundEfficiency;       //篮板率
	double offensiveReboundEff;     //进攻篮板率
	double stealEfficiency;			//抢断率
	double turnoverPercenttage;     //失误率
	double usingPercentage;         //使用率
	double GmSc;					//GmSc效率值
	ArrayList<SingleMatchPersonalData> individualData;		//该场比赛的所有球员的数据信息
	
}
