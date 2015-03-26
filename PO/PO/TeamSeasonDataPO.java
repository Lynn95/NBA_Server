package PO;

public class TeamSeasonDataPO {
	/*
	 * 赛季数据指的都是球队赛季的场均数据
	 * 
	 * */
	String teamName;				//所属球队名称
	double defenseNum;				//防守数
	double offenseNum;				//进攻数
	double BPG; 					//场均盖帽
	double blockEfficiency;			//盖帽率
	double efficiency; 				//效率
	double reboundNum;				//场均篮板数
	double assistNum;				//场均助攻数
	double turnoverNum;				//场均失误数
	double stealNum;				//场均抢断数
	double freeThrowPercentage;		//场均罚篮命中率  
	double foulNum;					//场均犯规数
	double pointNum;                //场均得分
	double threePointPercentage;    //场均三分命中率
	double shootPercentage; 		//投篮命中率
	double shotEfficiency;			//真实命中率
	double assistEfficiency;        //助攻率
	double reboundEfficiency;       //篮板率
	double offensiveReboundEff;     //进攻篮板率
	double stealEfficiency;			//抢断率
	double turnoverPercenttage;     //失误率
	
	public TeamSeasonDataPO(String teamName,double defenseNum,
	double offenseNum,double BPG,double blockEfficiency,
	double efficiency,double reboundNum,
	double assistNum,double turnoverNum,double stealNum,
	double freeThrowPercentage,double foulNum,
	double pointNum,double threePointPercentage,double shootPercentage,
	double shotEfficiency,double assistEfficiency,double reboundEfficiency,
	double offensiveReboundEff,double stealEfficiency,double turnoverPercenttage){
		this.teamName=teamName;
		this.defenseNum=defenseNum;
		this.offenseNum=offenseNum;
		this.BPG=BPG;
		this.blockEfficiency=blockEfficiency;
		this.efficiency=efficiency;
		this.reboundNum=reboundNum;
		this.assistNum=assistNum;
		this.turnoverNum=turnoverNum;
		this.stealNum=stealNum;
		
		this.freeThrowPercentage=freeThrowPercentage;
		this.foulNum=foulNum;
	     this.pointNum=pointNum;
		this.threePointPercentage=threePointPercentage;
		this.shootPercentage=shootPercentage;
		this.shotEfficiency=shotEfficiency;
		this.assistEfficiency=assistEfficiency;
		this.reboundEfficiency=reboundEfficiency;
		this.reboundEfficiency=offensiveReboundEff;
		this.stealEfficiency=stealEfficiency;
		this.turnoverPercenttage=turnoverPercenttage;
		
		
	}
	
	
}
