package playerbl;

public class PlayerWholeSeasonData {
	
	private String name;
	String position;
	String teamName;
	
	private double time;					//上场时间
	 private int fieldGoal;					//进球数
	 private int shootNum;					//投篮数
	 private int T_fieldGoal;				//三分进球数
	 private int T_shootNum;				//三分球投篮数
	 private int freeThrowGoalNum;			//罚球命中数
	 private int freeThrowNum;				//罚球出手数
	 private int O_ReboundNum;				//进攻篮板数
	 private int D_ReboundNum;				//防守篮板数
	 private int reboundNum;				//篮板总数
	 private int assistNum;				    //助攻数
	 private int stealNum;					//抢断数
	 private int blockNum;					//盖帽数
	 private int turnoverNum;				//失误数
	 private int foulNum;					//犯规数
	 private int points;	
	 int doubleNum;							//两双数inf 
	 int threeNum;						//三双数
	 int startingNum;					//先发场数
	 int matchNum;						//参赛的场数
	 
		
		private double assistEfficiency;        //助攻率
		
		private double reboundEfficiency;       //篮板效率
		
		private double offensiveReboundEff;     //进攻篮板效率
		private double deffensiveReboundEff;     //防守篮板效率
		private double stealEfficiency;			//抢断效率
		//private double turnoverPercentage;     //失误率
		private double usingPercentage;         //使用率
		
		private double blockEfficiency;			//盖帽率
	//	private double realShootPercentage;		//真是投篮命中率
	 
	 
	 public PlayerWholeSeasonData(String name,String p,String teamName ,
			 double time,int fieldGoal,
			 int shootNum,int T_fieldGoal,int T_shootNum,
			 int freeThrowGoalNum,int freeThrowNum,
			 int O_R_N,int D_R_N,int reboundNum,int assistNum,int stealNum,
			 int blockNum,int turnoverNum,
			 int foulNum,int points, int doubleNum,int threeNum,
			 int startingNum,int matchNum,
			double assistEFf,double reboundEff,double O_ReboundEff,
			 double D_reboundEff,double stealEff,double usingP,double blockEfficiency){
		
		 this.name=name;
		 this.position=p;
		 this.teamName=teamName;
		 this.time=time;
		 this.fieldGoal=fieldGoal;
		 this.shootNum=shootNum;
		 this.T_fieldGoal=T_fieldGoal;
		 this.T_shootNum=T_shootNum;
		 this.freeThrowGoalNum=freeThrowGoalNum;
		 this.freeThrowNum=freeThrowNum;
		 this.O_ReboundNum=O_R_N;
		 this.D_ReboundNum=D_R_N;
		 this.reboundNum=reboundNum;
		 this.stealNum=stealNum;
		 this.assistNum=assistNum;
		 this.blockNum=blockNum;
		 this.turnoverNum=turnoverNum;
		 this.foulNum=foulNum;
		 this.points=points;
		 this.doubleNum=doubleNum;
		 this.threeNum=threeNum;
		 this.startingNum=startingNum;
		 this.matchNum=1;
		 
		 
		
		 assistEfficiency= assistEFf;
		  reboundEfficiency= reboundEff;
		  offensiveReboundEff= O_ReboundEff;
		  deffensiveReboundEff= D_reboundEff;
		 stealEfficiency=  stealEff;
		 usingPercentage=  usingP;
		 this.blockEfficiency=blockEfficiency;
		 
	 }
	 
	 
	 public void addData(double t,int fGoal,
			 int sNum,int T_fGoal,int T_sNum,int free_ThrowGoalNum,int free_ThrowNum,
			 int O_r_N,int D_r_N,int r_Num,int assist_Num,int steal_Num,int block_Num,
			 int turnover_Num,int foul_Num,int ps,int dNum,int tNum,int startNum,double assistEFf,
			 double reboundEff,double O_ReboundEff,
			 double D_reboundEff,double stealEff,double usingP,double blockEff){
		 matchNum++;
		 time=time+t;
		 fieldGoal=fieldGoal+fGoal;
		 shootNum=sNum+shootNum;
		 T_fieldGoal=T_fieldGoal+T_fGoal;
		 
		 T_shootNum=T_shootNum+T_sNum;
		 freeThrowGoalNum=freeThrowGoalNum+free_ThrowGoalNum;
		 freeThrowNum=free_ThrowNum;
		 O_ReboundNum=O_ReboundNum+O_r_N;
		 D_ReboundNum=D_ReboundNum+D_r_N;
		 
		 reboundNum=reboundNum+r_Num;
		 assistNum=assistNum+assist_Num;
		 stealNum=stealNum+steal_Num;
		 blockNum=blockNum+block_Num;
		 turnoverNum=turnoverNum+turnover_Num;
		 
		 foulNum=foulNum+foul_Num;
		 points=points+ps;
		 doubleNum=doubleNum+dNum;
		 threeNum=threeNum+tNum;
		 startingNum=startingNum+startNum;
		 
		 
		 assistEfficiency= assistEfficiency+assistEFf;
		  reboundEfficiency=reboundEfficiency+reboundEff;
		  offensiveReboundEff=offensiveReboundEff + O_ReboundEff;
		  deffensiveReboundEff=deffensiveReboundEff + D_reboundEff;
		 stealEfficiency=stealEfficiency+ stealEff;
		 usingPercentage=usingPercentage+ usingP;
		 
		 blockEfficiency=blockEfficiency+blockEff;
	 }
	 
	 public void calculateAverageData(){
		/* 
		 * 计算场均数据
		 * */
		 /*time=time/matchNum;
		 fieldGoal=fieldGoal/matchNum;
		 shootNum=shootNum/matchNum;
		 T_fieldGoal=T_fieldGoal/matchNum;
		 T_shootNum=T_shootNum/matchNum;
		 freeThrowGoalNum=freeThrowGoalNum/matchNum;
		 freeThrowNum=freeThrowNum/matchNum;
		 O_ReboundNum=O_ReboundNum/matchNum;
		 D_ReboundNum=D_ReboundNum/matchNum;
		 reboundNum=reboundNum/matchNum;
		 stealNum=stealNum/matchNum;
		 assistNum=assistNum/matchNum;
		 blockNum=blockNum/matchNum;
		 turnoverNum=turnoverNum/matchNum;
		 foulNum=foulNum/matchNum;
		 points=points/matchNum;*/
		 
		 
		 assistEfficiency= (double)assistEfficiency/matchNum;
		  reboundEfficiency=(double)reboundEfficiency/matchNum;
		  offensiveReboundEff=(double)offensiveReboundEff/matchNum;
		  deffensiveReboundEff=(double)deffensiveReboundEff/matchNum;
		 stealEfficiency=(double)stealEfficiency/matchNum;
		 usingPercentage=(double)usingPercentage/matchNum;
		 
		 blockEfficiency=(double)blockEfficiency/matchNum;
	 }
	 
	 public int getdoubleNum(){
		 return doubleNum;
		 
	 }
	 public int getThreeNum(){
		 
		 return threeNum;
	 }
	 public int getStartingNum(){
		 return startingNum;
	 }
	 public int getMatchNum(){
		 return matchNum;
	 }
	 
	 public String getPlayerName() {
			return name;
		}

		public String getPlayerPosition() {
			return position;
		}

		public double getTime() {
			return (double)time/matchNum;
		}
		
		
		public double getFieldGoal() {
			return (double)fieldGoal/matchNum;
		}
		

		public double getShootNum() {
			return (double)shootNum/matchNum;
		}
		

		public double getT_fieldGoal() {
			return (double)T_fieldGoal/matchNum;
		}
		
		

		public double getT_shootNum() {
			return (double)T_shootNum/matchNum;
		}
		
		public double getFreeThrowNum() {
			return (double)freeThrowNum/matchNum;
		}

		public double getO_ReboundNum() {
			return (double)O_ReboundNum/matchNum;
		}

		public double getD_ReboundNum() {
			return (double)D_ReboundNum/matchNum;
		}

		public double getReboundNum() {
			return (double)reboundNum/matchNum;
		}

		public double getAssistNum() {
			return (double)assistNum/matchNum;
		}

		public double getBlockNum() {
			return (double)blockNum/matchNum;
		}

		public double getTurnoverNum() {
			return (double)turnoverNum/matchNum;
		}

		public double getFoulNum() {
			return (double)foulNum/matchNum;
		}

		public double getPoints() {
			return (double)points/matchNum;
		}
		public double getStealNum() {
			return (double)stealNum/matchNum;
		}
		
		public String getTeamName(){
			return teamName;
		}
	
		public double getFreeThrowPercentage(){
			if(freeThrowNum==0){
				return 0;
			}else{
				return (double)freeThrowGoalNum/freeThrowNum;
			}
		};		  
	               
		public  double getThreePointPercentage(){
			if(T_shootNum==0){
				return 0;
			}else{
				return (double)T_fieldGoal/T_shootNum;
			}
			
		};   
		public  double getShootPercentage(){
			
			if(shootNum==0){
				return 0;
			}else{
				return (double)fieldGoal/shootNum;
			}
		}; 		
		
		public  double getShootEfficiency(){
			/*投篮效率：(投篮命中数+0.5×三分命中数)÷投篮出手数*/
			if(shootNum==0){
				return 0;
			}else{
				return (double)((fieldGoal+0.5*T_fieldGoal)/shootNum);
			}
			
		};		
		
		public  double getAssistEfficiency(){
			return assistEfficiency;
		};       
		
		
		 public  double getReboundEfficiency(){
			return reboundEfficiency;
		};     
		
		
		public  double getOffensiveReboundEff(){
			return offensiveReboundEff;
		};     
		public  double getStealEfficiency(){
			return stealEfficiency;
		};			
		public  double getTurnoverPercentage(){
			return (double)turnoverNum/((shootNum-T_shootNum)+
					0.44*freeThrowNum+turnoverNum);
		};     
		public  double getUsingPercentage(){
			return usingPercentage;
		};         
		public  double getGmSc(){
			/*GmSc 效率值：  得分+0.4×投篮命中数-0.7×投篮出手数-0.4×(罚球出手数-罚球命
				中数)+0.7× 前场篮板数+0.3× 后场篮板数+ 抢断数+0.7× 助攻数 +0.7× 盖帽数
				-0.4×犯规数-失误数*/
			return (double)(points+0.4*fieldGoal-0.7*shootNum-
					.04*(freeThrowNum-freeThrowGoalNum)
					+0.7*O_ReboundNum+0.3*D_ReboundNum+stealNum+0.7*assistNum
					+0.7*blockNum-0.4*foulNum-turnoverNum);
		};		
		
		
		public double getBlockEfficiency(){
			return blockEfficiency;
		}
		public double gerRealShootPercentage(){
			
			/* 真实投篮命中率： 得分÷(2×(投篮出手数+0.44×罚球出手数))*/
			if((2*(shootNum/matchNum+0.4*freeThrowNum/matchNum))!=0)
				return (double)(points/(2*(shootNum+0.4*freeThrowNum)));
			else
				return 0;
		}
	 
		public double getEfficiency(){
			/*5）  效率： (得分+篮板+助攻+抢断+盖帽)-（出手次数-命中次数）-（罚球次数-罚球
                                                   命中次数）-失误次数*/
			double e=(double)(points+blockNum+assistNum+stealNum+blockNum)/matchNum-
					(double)(shootNum-fieldGoal)/matchNum-
					(double)(freeThrowNum-freeThrowGoalNum)/matchNum-(double)turnoverNum/matchNum;
			return e;
		}
}
