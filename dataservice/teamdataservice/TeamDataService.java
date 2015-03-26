package teamdataservice;
import java.util.ArrayList;
import PO.TeamInfoPO;

public interface TeamDataService {
	ArrayList<TeamInfoPO>  getTeamInfoList(); 		//返回所有球队的基本信息
}
