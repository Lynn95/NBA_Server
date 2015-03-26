package PO;

public class TeamPO {
	private TeamInfoPO info;
	private TeamSeasonDataPO data;
      public TeamPO(TeamInfoPO info,TeamSeasonDataPO data){

	this.info=info;
	this.data=data;
}
	public TeamInfoPO getTammInfoPO(){
	return info;
}

	public TeamSeasonDataPO getTeamSeasonDataPO(){

		return data;
	}
}
