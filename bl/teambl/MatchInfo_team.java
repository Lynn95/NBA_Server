package teambl;

import java.util.ArrayList;

import VO.TeamSeasonDataVO;

public interface MatchInfo_team {
	ArrayList<TeamSeasonDataVO> getTeamSeasonData();	//获取所有球队的整个赛季的赛季信息，详见VO的定义说明

}
