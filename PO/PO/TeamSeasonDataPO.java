package PO;

public class TeamSeasonDataPO {
	/*
	 * ��������ָ�Ķ�����������ĳ�������
	 * 
	 * */
	String teamName;				//�����������
	double defenseNum;				//������
	double offenseNum;				//������
	double BPG; 					//������ñ
	double blockEfficiency;			//��ñ��
	double efficiency; 				//Ч��
	double reboundNum;				//����������
	double assistNum;				//����������
	double turnoverNum;				//����ʧ����
	double stealNum;				//����������
	double freeThrowPercentage;		//��������������  
	double foulNum;					//����������
	double pointNum;                //�����÷�
	double threePointPercentage;    //��������������
	double shootPercentage; 		//Ͷ��������
	double shotEfficiency;			//��ʵ������
	double assistEfficiency;        //������
	double reboundEfficiency;       //������
	double offensiveReboundEff;     //����������
	double stealEfficiency;			//������
	double turnoverPercenttage;     //ʧ����
	
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
