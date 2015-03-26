package PO;

public class TeamInfoPO {
	/*
	 * 
	 * 数据只可以读，不可以修改
	 * 
	 * */
	private String fullName;			//球队全名
	private String teamAbb;             //abbreviation,球队名缩写
	private String location;			//所在地
	private String  division;			//赛区
	private String partition;			//分区
	private String homeGround;			//主场
	private String formedTime;			//成立时间
	
	public TeamInfoPO(String fullName,String abb,String location,String d,
			String partition,String home,String time){
		this.fullName=fullName;
		this.teamAbb=abb;
		this.location=location;
		this.division=d;
		this.partition=partition;
		this.homeGround=home;
		this.formedTime=time;
		
		
	}

	public String getFullName() {
		return fullName;
	}

	public String getTeamAbb() {
		return teamAbb;
	}

	public String getLocation() {
		return location;
	}

	public String getDivision() {
		return division;
	}

	public String getPartition() {
		return partition;
	}

	public String getHomeGround() {
		return homeGround;
	}

	public String getFormedTime() {
		return formedTime;
	}
}
