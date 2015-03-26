package playerbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.PlayerSeasonDataVO;

public interface MatchInfo_player {
	/*
	 * match模块需要实现的player模块的接口
	 * /

			/*获取所有的球员的赛季信息，
		对于有的数据不知道如何计算的，先留着，把会的先睡了
		从比赛场中获得要返回的详见VO包里的定义*/
	
	PlayerSeasonDataVO getPlayerSeasonMatchData(String playerName);//这个可以先不实现，下面的实现了，这个也就顺便实现 了
	ArrayList<PlayerSeasonDataVO> getAllPlayerSeasonMatchData() throws RemoteException; 
}
