package teamblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.TeamInfoVO;
import VO.TeamVO;


public interface TeamBLService extends Remote{
	//个人认为这个不用，和下面的方法重复
		//public ArrayList<TeamInfoVO> getAllTeamInfo()throws RemoteException;	//获取所有球队的基本信息
		public ArrayList<TeamVO> getAllTeam()throws RemoteException;			//获取所有球队的基本信息 
		public TeamVO getTeamInfo(String teamAbb)throws RemoteException;		//根据球队缩写获取球队信息
		//TODO cc加
		
		//获得胜场
		public double getTeamWinNum(String teamAbb) throws RemoteException;
		//获得一个球队所有球员的姓名列表
		public ArrayList<String> getTeamAllPlayer(String teamAbb)throws RemoteException;
		public ArrayList<TeamVO> sort(String sortItem)throws RemoteException;	//根据球队的某一项数据进行球队间的排序				//排序，这个接口有待重新考虑，还没有想好


}
