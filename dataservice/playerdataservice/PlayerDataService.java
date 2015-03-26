package playerdataservice;

import java.util.ArrayList;

import PO.PlayerInfoPO;
import PO.PlayerPO;

public interface PlayerDataService {
	ArrayList<PlayerInfoPO> getAllPlayer();  		//获取所有球员
}
