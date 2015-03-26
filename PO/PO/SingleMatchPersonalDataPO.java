package PO;

public class SingleMatchPersonalDataPO {
	/*
	 * 定义持久化对象
	 * 定义的是一场比赛中某一个球员的单场数据
	 * 只可读，不可修改
	 * 
	 * */
	 private String playerName;				//球员名字
	 private String playerPosition;			//球员位置
	 private String time;					//场均上场时间
	 private int fieldGoal;					//投篮命中数
	 private int shootNum;					//投篮出手数
	 private int T_fieldGoal;				//三分球命中数
	 private int T_shootNum;				//三分球出手数
	 private int freeThrowGoalNum;			//罚球命中数
	 private int freeThrowNum;				//罚球出手数
	 private int O_ReboundNum;				//进攻（前场）篮板数
	 private int D_ReboundNum;				//防守（后场）篮板数
	 private int reboundNum;				//总篮板数
	 private int assistNum;				    //助攻数
	 private int stealNum;					//抢断数
	 private int blockNum;					//盖帽数
	 private int turnoverNum;				//失误数
	 private int foulNum;					//犯规数
	 private int points;					//得分
	 
	 public SingleMatchPersonalDataPO(String name,String p,String time,int fieldGoal,
			 int shootNum,int T_fieldGoal,int T_shootNum,int freeThrowGoalNum,int freeThrowNum,
			 int O_R_N,int D_R_N,int reboundNum,int assistNum,int stealNum,int blockNum,int turnoverNum,
			 int foulNum,int points ){
		 this.playerName=name;
		 this.playerPosition=p;
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
	 }

	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerPosition() {
		return playerPosition;
	}

	public String getTime() {
		return time;
	}

	public int getFieldGoal() {
		return fieldGoal;
	}

	public int getShootNum() {
		return shootNum;
	}

	public int getT_fieldGoal() {
		return T_fieldGoal;
	}

	public int getT_shootNum() {
		return T_shootNum;
	}

	public int getFreeThrowGoalNum() {
		return freeThrowGoalNum;
	}

	public int getFreeThrowNum() {
		return freeThrowNum;
	}

	public int getO_ReboundNum() {
		return O_ReboundNum;
	}

	public int getD_ReboundNum() {
		return D_ReboundNum;
	}

	public int getReboundNum() {
		return reboundNum;
	}

	public int getAssistNum() {
		return assistNum;
	}

	public int getBlockNum() {
		return blockNum;
	}

	public int getTurnoverNum() {
		return turnoverNum;
	}

	public int getFoulNum() {
		return foulNum;
	}

	public int getPoints() {
		return points;
	}
	 
	public int getStealNum() {
		return stealNum;
	}
}
