package PO;

public class PlayerPO {
	/*
	 * ����ֻ�ɶ��������޸�
	 * 
	 * */
	
	private PlayerInfoPO playerInfo;					//��Ա�Ļ�����Ϣ
    private PlayerSeasonDataPO seasonData;			//��Ա��������Ϣ
    
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
