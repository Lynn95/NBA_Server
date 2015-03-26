package RMI;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import playerbl.PlayerController;
import playerblservice.PlayerBLService;
import teambl.TeamController;
import teamblservice.TeamBLService;
import matchbl.MatchController;
import matchblservice.MatchBLService;



public class RMI_Server {
	public static void main(String args[]) throws Exception { 
	       
	    try { 
	        //����һ��Զ�̶��� 
	        MatchBLService matchService = new MatchController(); //����ʵ������ӿ�
	        PlayerBLService playerService=new PlayerController();
	        TeamBLService  teamService=new TeamController();
	  
	     
	        //���������ϵ�Զ�̶���ע���Registry��ʵ������ָ���˿�Ϊ8888����һ���ز����٣�JavaĬ�϶˿���1099�����ز���ȱ��һ����ȱ��ע����������޷��󶨶���Զ��ע����� 
	        LocateRegistry.createRegistry(8888); 
	       // LocateRegistry.createRegistry(8888);
	        //��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪRHello 
	        
	     
	        //�󶨵�URL��׼��ʽΪ��rmi://host:port/name(����Э��������ʡ�ԣ���������д��������ȷ�ģ� 
	        Naming.bind("rmi://localhost:8888/matchService",matchService); 
	        Naming.bind("rmi://localhost:8888/playerService", playerService); 
	        Naming.bind("rmi://localhost:8888/teamService", teamService);
	        


	        System.out.println(">>>>>INFO:RMI连接成功！"); 
	    } catch (RemoteException e) { 
	        System.out.println("����Զ�̶������쳣��"); 
	        e.printStackTrace(); 
	    } catch (AlreadyBoundException e) { 
	        System.out.println("�����ظ��󶨶����쳣��"); 
	        e.printStackTrace(); 
	    } catch (MalformedURLException e) { 
	        System.out.println("����URL�����쳣��"); 
	        e.printStackTrace(); 
	    } 
	} 
}
