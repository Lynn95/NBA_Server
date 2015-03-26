package PO;

public class PlayerSeasonDataPO {
	
	private String teamName;				//������ӣ���Ա�Ļ�����Ϣʱ����ģ����������Ǻ���Ӱ󶨵ģ�����Ҿ�����������Է�����
	private double matchNum;				//��������
	private double startingNum;				//�ȷ�����
	private double defenseNum;				//������
	private double offenseNum;				//������
	private double time;					//�����ϳ�ʱ��
	private double BPG; 					//������ñ
	private double blockEfficiency;			//��ñ��
	private double efficiency; 				//Ч��
	private double reboundNum;				//����������
	private double assistNum;				//����������
	private double turnoverNum;				//����ʧ����
	private double stealNum;				//����������
	private double freeThrowPercentage;		//��������������  
	private double foulNum;					//����������
	private double pointNum;                //�����÷�
	private double threePointPercentage;    //��������������
	private double shootPercentage; 		//Ͷ��������
	private double shotEfficiency;			//��ʵ������
	private double assistEfficiency;        //������
	private double reboundEfficiency;       //������
	private double offensiveReboundEff;     //����������
	private double stealEfficiency;			//������
	private double turnoverPercenttage;     //ʧ����
	private double usingPercentage;         //ʹ����
	private double GmSc;					//GmScЧ��ֵ

	private int seasonDobleNum;				//������˫��
	private int seasonThreeNum;  			//������˫��
	
	
	
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
		/*���о�*/
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
		/*�˴��д�ϸ��*/
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
