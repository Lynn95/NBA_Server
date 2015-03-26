package PO;

public class PlayerSeasonDataPO {
	
	private String teamName;				//所属球队，球员的基本信息时不变的，但其数据是和球队绑定的，因此我决定把这个属性防在这
	private double matchNum;				//参赛场数
	private double startingNum;				//先发场数
	private double defenseNum;				//防守数
	private double offenseNum;				//进攻数
	private double time;					//场均上场时间
	private double BPG; 					//场均盖帽
	private double blockEfficiency;			//盖帽率
	private double efficiency; 				//效率
	private double reboundNum;				//场均篮板数
	private double assistNum;				//场均助攻数
	private double turnoverNum;				//场均失误数
	private double stealNum;				//场均抢断数
	private double freeThrowPercentage;		//场均罚篮命中率  
	private double foulNum;					//场均犯规数
	private double pointNum;                //场均得分
	private double threePointPercentage;    //场均三分命中率
	private double shootPercentage; 		//投篮命中率
	private double shotEfficiency;			//真实命中率
	private double assistEfficiency;        //助攻率
	private double reboundEfficiency;       //篮板率
	private double offensiveReboundEff;     //进攻篮板率
	private double stealEfficiency;			//抢断率
	private double turnoverPercenttage;     //失误率
	private double usingPercentage;         //使用率
	private double GmSc;					//GmSc效率值

	private int seasonDobleNum;				//赛季两双数
	private int seasonThreeNum;  			//赛季三双数
	
	
	
	public PlayerSeasonDataPO(String teamName,double matchNum, double startingNum,double defenseNum,
	double offenseNum,double time,double BPG,double blockEfficiency,double efficiency,
	double reboundNum,double assistNum,double turnoverNum,
	double stealNum,double freeThrowPercentage,  double foulNum,double pointNum,
	double threePointPercentage,double shootPercentage,double shotEfficiency,
	double assistEfficiency,double reboundEfficiency,double offensiveReboundEff,
	double stealEfficiency,double turnoverPercenttage,double usingPercentage,
	double GmSc,int DoubleNum,int seasonThreeNum){
		
				
	}
	
	
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public double getMatchNum() {
		return matchNum;
	}
	public void addMatchNum() {
		 matchNum ++;
	}
	public double getStartingNum() {
		return startingNum;
	}
	public void addStartingNum(double startingNum) {
		 startingNum ++;
	}
	public double getDefenseNum() {
		return defenseNum;
	}
	public void setDefenseNum(int Num) {
		if(matchNum!=0)
			 assistNum = (Num+defenseNum*(matchNum-1))/matchNum;
			else
				defenseNum=Num;
	}
	public double getOffenseNum() {
		return offenseNum;
	}
	public void setOffenseNum(int Num) {
		if(matchNum!=0)
			 assistNum = (Num+offenseNum*(matchNum-1))/matchNum;
			else
				offenseNum=Num;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double Num) {
		if(matchNum!=0)
			 time = (Num+time*(matchNum-1))/matchNum;
			else
				time=Num;
	}
	public double getBlockEfficiency(){
		return blockEfficiency;
	}
	public void setBlockEfficiency(double blockEfficiency) {
		this.blockEfficiency = blockEfficiency;
	}
	public double getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(double efficiency) {
		/*待研究*/
	}
	public double getReboundNum() {
		return reboundNum;
	}
	public void setReboundNum(double Num) {
		if(matchNum!=0)
			 reboundNum = (Num+stealNum*(matchNum-1))/matchNum;
			else
				reboundNum=Num;
	}
	public double getAssistNum() {
		return assistNum;
	}
	public void setAssistNum(double  Num){
		if(matchNum!=0)
			 assistNum = (Num+stealNum*(matchNum-1))/matchNum;
			else
				assistNum=Num;
	}
	public double getTurnoverNum() {
		return turnoverNum;
	}
	public void setTurnoverNum(double Num) {
		if(matchNum!=0)
			turnoverNum =  (Num+turnoverNum*(matchNum-1))/matchNum;
			else
				turnoverNum=Num;
	}
	public double getStealNum(){
		return stealNum;
	}
	public void setStealNum(double  Num) {
		if(matchNum!=0)
		 stealNum =  (Num+stealNum*(matchNum-1))/matchNum;
		else
			stealNum=Num;
	}
	public double getFreeThrowPercentage() {
		return freeThrowPercentage;
	}
	public void setFreeThrowPercentage(int Num) {
		if(matchNum!=0)
			 freeThrowPercentage = (Num+freeThrowPercentage*(matchNum-1))/matchNum;
			else
				freeThrowPercentage=Num;
	}
	public double getFoulNum() {
		return foulNum;
	}
	public void setFoulNum(int Num) {
		if(matchNum!=0)
			 stealNum =  (Num+foulNum*(matchNum-1))/matchNum;
			else
				foulNum=Num;
	}
	public double getPointNum() {
		return pointNum;
	}
	public void setPointNum(int Num) {
		if(matchNum!=0)
			 pointNum =  (Num+pointNum*(matchNum-1))/matchNum;
			else
				pointNum=Num;
	}
	public double getThreePointPercentage() {
		return threePointPercentage;
	}
	public void setThreePointPercentage(double threePointPercentage) {
		/*此处有待细看*/
		this.threePointPercentage = threePointPercentage;
	}
	public double getShootPercentage() {
		return shootPercentage;
	}
	public void setShootPercentage(double shootPercentage) {
		this.shootPercentage = shootPercentage;
	}
	public double getShotEfficiency() {
		return shotEfficiency;
	}
	public void setShotEfficiency(double shotEfficiency) {
		this.shotEfficiency = shotEfficiency;
	}
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	public void setAssistEfficiency(double assistEfficiency) {
		this.assistEfficiency = assistEfficiency;
	}
	public double getReboundEfficiency() {
		return reboundEfficiency;
	}
	public void setReboundEfficiency(double reboundEfficiency) {
		this.reboundEfficiency = reboundEfficiency;
	}
	public double getOffensiveReboundEff() {
		return offensiveReboundEff;
	}
	public void setOffensiveReboundEff(double offensiveReboundEff) {
		this.offensiveReboundEff = offensiveReboundEff;
	}
	public double getStealEfficiency() {
		return stealEfficiency;
	}
	public void setStealEfficiency(double stealEfficiency) {
		this.stealEfficiency = stealEfficiency;
	}
	public double getTurnoverPercenttage() {
		return turnoverPercenttage;
	}
	public void setTurnoverPercenttage(double turnoverPercenttage) {
		this.turnoverPercenttage = turnoverPercenttage;
	}
	public double getUsingPercentage(){
		return usingPercentage;
	}
	public void setUsingPercentage(double usingPercentage) {
		this.usingPercentage = usingPercentage;
	}
	public double getBPG() {
		return BPG;
	}
	public double getGmSc() {
		return GmSc;
	}



	public int getSeasonDobleNum() {
		return seasonDobleNum;
	}



	public int getSeasonThreeNum() {
		return seasonThreeNum;
	}
}
