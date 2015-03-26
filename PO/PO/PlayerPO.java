package PO;

public class PlayerPO {
	/*
	 * 数据只可读，不可修改
	 * 
	 * */
	
	private PlayerInfoPO playerInfo;					//球员的基本信息
    private PlayerSeasonDataPO seasonData;			//球员的赛季信息
    
    public PlayerPO(PlayerInfoPO info,PlayerSeasonDataPO data){
    	this.playerInfo=info;
    	this.seasonData=data;
    }

	public PlayerInfoPO getPlayerInfo() {
		return playerInfo;
	}

	public PlayerSeasonDataPO getSeasonData() {
		return seasonData;
	}
}
