package playerblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import VO.PlayerInfoVO;
import VO.PlayerVO;

public interface PlayerBLService extends Remote{
	//��ȡ������Ա�Ļ�����Ϣ �����ڼ�����Ϣ��ʾ
		public ArrayList<PlayerInfoVO> getAllPlayer()throws RemoteException;		
		//TODO CC �Ķ� ������Ա���ƻ�ȡ������Ϣ
		public PlayerVO getPlayer(String name)throws RemoteException;
		//����
		public PlayerInfoVO getPlayerInfo(String name)throws RemoteException;		        //
		public ArrayList<PlayerVO> sort(String pisition,String paitition,String item)throws RemoteException;	

}
