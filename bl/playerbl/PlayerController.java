package playerbl;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import matchbl.MatchController;
import playerblservice.PlayerBLService;
import playerdata.PlayerData;
import playerdataservice.PlayerDataService;
import teambl.PlayerInfo_team;
import teambl.TeamController;
import PO.*;
import VO.*;

public class PlayerController extends UnicastRemoteObject implements PlayerBLService,PlayerInfo_team{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String url="jdbc:mysql://localhost/mysql";
    String user="ghl";
    String pwd="ghl13";
    
   
     ArrayList<PlayerVO> pList=new ArrayList<>();
     
     public PlayerController() throws RemoteException{
    	 super();
    	// writePlayerToDataBase();
    	readPlayerFromDBS("pointNum");
    	 
     }
	
	private  ArrayList<PlayerVO> sort_division(ArrayList<PlayerVO>list,
			String division) throws RemoteException{
		TeamInfo_player tp=new TeamController();
		for(int i=0;i<list.size();i++){
			if(!tp.getTeam_info(list.get(i).getData().getTeamName()).getDivision().equals(division)){
				list.remove(i);
				if(i>0)
					i--;
			}
		}
		
		return list;
	}
	private ArrayList<PlayerVO> sort_partition(ArrayList<PlayerVO>list,
			String partition)throws RemoteException{
		TeamInfo_player tp=new TeamController();
		for(int i=0;i<list.size();i++){
			if(!tp.getTeam_info(list.get(i).getData().getTeamName()).getPartition().equals(partition)){
				list.remove(i);
				if(i>0)
					i--;
			}
		}
		
		return list;
	}
	
	private ArrayList<PlayerVO> sort_position(ArrayList<PlayerVO>list,String position){
		
		for(int i=0;i<list.size();i++){
			if(!list.get(i).getInfo().getPosition().equals(position)){
				list.remove(i);
				if(i>0)
					i--;
			}
		}
		
		return list;
	}
	

	
	@Override
	public ArrayList<PlayerVO> sort(String position,
			String partition,String item) throws RemoteException {
		     
		readPlayerFromDBS(item);
			ArrayList<PlayerVO> list=pList;
			
			
			if(position!=null&&position.trim().length()!=0){
				
				list=sort_position(list,position);
			}
			
			if(partition!=null&&partition.trim().length()!=0){
				
				if(partition.equals("E")||partition.equals("W")){
					list=sort_division(list,partition);
				}
				
				else {
				list=sort_partition(list,partition);
				}
			}
			return list;	
	}
	
	
	
	
	/*public static void main(String args[]) throws RemoteException{
		System.out.println("-----:"+"进入main函数进行测验！");
		 PlayerController c=new PlayerController();
		 c.writePlayerToDataBase();
		
		
           MatchInfo_player mInfo=new MatchController();
		
		
		
		
 
		
	PlayerDataService dataServer=new PlayerData();
		ArrayList<PlayerInfoPO> list=dataServer.getAllPlayer();
		
		for(int i=0;i<list.size();i++){
			System.out.println("打印球员信息："+list.get(i).getName()+" ; "+(list.get(i).getSchool())+";"+list.get(i).getHeight()+";"+list.get(i).getWeight());
			} 
		
	}*/

	
	private void readPlayerFromDBS(String item){
		
		/***
		 * 类内部的私有方法
		 * 用于从数据库中读取
		 * 球员的全部数据
		 * item 是需要排序的依据
		 * */
		 
         
		try
	    {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
	           Connection conn = DriverManager.getConnection(url,user, pwd);
			
	         Statement stmt = conn.createStatement();//
	      
	      // ResultSet  rs=stmt.executeQuery("select * from player order by height desc");
	       //desc鏄寜鐓ч檷搴忔帓搴忕殑锛屽鏋滃幓鎺夛紝鍒欐槸榛樿鎸夌収鍗囧簭鎺掑簭
	      ResultSet  rs=stmt.executeQuery("select * from player order by '"+item+"'");
	   
	      int i=1;
	      char chr=39;
	      while (rs.next())
	      {
	    	PlayerVO  vo=new PlayerVO(new PlayerInfoVO(rs.getString("name").replace('’', chr),rs.getString("number"),
	    			  rs.getString("position"),rs.getString("height"),
	    			  rs.getDouble("weight"),rs.getString("birth"),
	    			  rs.getInt("age"),rs.getInt("exp"),rs.getString("school")),
	    			 new PlayerSeasonDataVO(rs.getString("name"),rs.getString("teamName"),rs.getInt("matchNum"),
	    			  rs.getInt("startingNum"),rs.getDouble("defenseNum"),
	    			  rs.getDouble("offenseNum"),rs.getDouble("time"),
	    			  rs.getDouble("BPG"),rs.getDouble("blockEfficiency")
	    			  ,rs.getDouble("efficiency"),rs.getDouble("reboundNum"),
	    			  rs.getDouble("assistNum"),rs.getDouble("turnoverNum"),
	    			  rs.getDouble("stealNum") ,rs.getDouble("freeThrowPercentage"),
	    			  rs.getDouble("foulNum"),rs.getDouble("pointNum"),
	    			  rs.getDouble("threePointPercentage"),
	    			  rs.getDouble("shootPercentage"),rs.getDouble("shootEfficiency"),
	    			  rs.getDouble("assistEfficiency"),rs.getDouble("reboundEfficiency"),
	    			  rs.getDouble("offensiveReboundEff"),rs.getDouble("stealEfficiency"),
	    			  rs.getDouble("turnoverPercentage"),rs.getDouble("usingPercentage"),
	    			  rs.getDouble("GmSc"),rs.getInt("doubleNum"),rs.getInt("threeNum")));
	       pList.add(vo);
	      }
	            
	      stmt.close();
	      conn.close();
	}catch (Exception ex)
	    {
	      System.out.println("Error : " + ex.toString());
	    }
		
}
	
	
	
	
	private void writePlayerToDataBase() throws RemoteException{
		
		
		MatchInfo_player mInfo=new MatchController();
		
		ArrayList<PlayerSeasonDataVO> list=mInfo.getAllPlayerSeasonMatchData();
		
		DecimalFormat df = new DecimalFormat( "0.00");
		
		System.out.println("打印list的大小："+list.size());
		
		try
	    {
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
	         Connection conn = DriverManager.getConnection(url,user, pwd);
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	         Statement stmt = conn.createStatement();
	
	        PlayerSeasonDataVO vo;
	        String sqrStr;
	        
	       
	        
	       /*String createTableTeam = "CREATE TABLE teambaseinfo " +
	 	    		  "(fullName VARCHAR(32),teamAbb VARCHAR(32),location VARCHAR(32)," +
	 	    		  "division VARCHAR(32),partition VARCHAR(32),homeGround VARCHAR(32),formedTime VARCHAR(32))";
	 	      stmt.executeUpdate(createTableTeam);*/
	 	    //  System.out.println("寤虹珛琛ㄦ牸鎴愬姛锛�");
	 	    // sqrStr="INSERT INTO `teambaseinfo` (`fullName`, `location`, `teamAbb`, `division`, `partition`, `homeGround`, `formedTime`) VALUES ('sddddf', 'sdf', 'sdf', 'sdf', 'ssdf', 'sdf', 'sadf')";
	 	    //stmt.executeUpdate(sqrStr);
	 	    
	 	    System.out.println("进入循环前：");
	 	    PlayerInfoPO info;
	 	    
	 	    /*涓嬮潰鏆備笖涓嶈绠�*/
	 	    int m=0;
	 	    double PBA=0;
	 	     while (list.size()>0)
	      {
	 	    	System.out.println("打印list的大小："+list.size());
	 	    	
	 	     info=getPlayerBaseInfo((list.get(0).getName()));
	 	    	 System.out.println("-------info是否是空的------"+(info==null));
	 	    System.out.println("要插入的球员的姓名："+list.get(0).getName()+"  返回的球员的基本信息是否为空info==0？："+info==null);
	 	    	 
	 	    	 if(info!=null){
	 	    		  
	 	    	System.out.println("球员的毕业学校："+info.getSchool());
	 	    	 vo=list.get(0);
	 	    	 PBA=vo.getAssistNum()+vo.getBPG()+vo.getPointNum();
	    	 sqrStr="INSERT INTO `player` (`name`, `number`, `position`, `height`, `weight`,"
	    	 		+ " `birth`, `age`, `exp`, `school`, "
	    	 		+ "`teamName`, `matchNum`, `startingNum`, `defenseNum`, `offenseNum`, "
	    	 		+ "`time`, `BPG`, `blockEfficiency`, `efficiency`,"
	    	 		+ " `reboundNum`, `assistNum`, `turnoverNum`, `stealNum`, "
	    	 		+ "`freeThrowPercentage`, `foulNum`, `pointNum`, `threePointPercentage`,"
	    	 		+ " `shootPercentage`, `shootEfficiency`, `assistEfficiency`, `reboundEfficiency`, "
	    	 		+ "`offensiveReboundEff`, `stealEfficiency`, `turnoverPercentage`, `usingPercentage`, "
	    	 		+ "`GmSc`, `doubleNum`, `threeNum`,`PBA`) VALUES ('"+info.getName()+"',"
	    	 				+ " '"+info.getNumber()+"', '"+info.getPosition()+"',"
	    	 				+ " '"+info.getHeight()+"', '"+info.getWeight()+"', '"+info.getBirth()+"',"
	    	 						+ " '"+info.getAge()+"', '"+info.getExp()+"', "
	    	 			+ "'"+info.getSchool()+"', '"+vo.getTeamName()+"', '"+vo.getMatchNum()+"', "
	    	 		+ "'"+vo.getStartingNum()+"', '"+df.format(vo.getDefenseNum())+"', '"+df.format(vo.getOffenseNum())+"',"
	    	 		+ "'"+df.format(vo.getTime())+"', '"+df.format(vo.getBPG())+"', '"+df.format(vo.getBlockEfficiency())+"', "
	    	 		+ "'"+df.format(vo.getEfficiency())+"', '"+df.format(vo.getReboundNum())+"', '"+df.format(vo.getAssistNum())+"', "
	    	 		+ "'"+df.format(vo.getTurnoverNum())+"', '"+df.format(vo.getStealNum())+"', "
	    	 				+ "'"+df.format(vo.getFreeThrowPercentage())+"', "
	    	 		+ "'"+df.format(vo.getFoulNum())+"', '"+df.format(vo.getShootEfficiency())+"', "
	    	 				+ "'"+df.format(vo.getThreePointPercentage())+"',"
	    	 		+ "'"+df.format(vo.getShootPercentage())+"', '"+df.format(vo.getShootEfficiency())+"', "
	    	 				+ "'"+df.format(vo.getAssistEfficiency())+"', "
	    	 		+ "'"+df.format(vo.getReboundEfficiency())+"', '"+df.format(vo.getOffensiveReboundEff())+"',"
	    	 		+ " '"+df.format(vo.getStealEfficiency())+"', '"+df.format(vo.getTurnoverPercenttage())+"',"
	    	 				+ " '"+df.format(vo.getUsingPercentage())+"', "
	    	 		+ "'"+df.format(vo.getGmSc())+"', '"+df.format(vo.getDoubleNum())+"', '"+vo.getThreeNum()+"','"+df.format(PBA)+"')";
	    	//  sqrStr="INSERT INTO `teambaseinfo` (`fullName`, `location`, `teamAbb`, `division`, `partition`, `homeGround`, `formedTime`) VALUES('"+fullName+"','"+teamAbb+"','"+location+"','"+division+"','"+partition+"','"+homeGround+"','"+formedTime+"')";
	    	   
	    	 stmt.executeUpdate(sqrStr);
	    	  m++;
	 	    	 }
	 	    	 list.remove(0);
	    	  System.out.println("打印计数---"+m);
	      }
	      
	      stmt.close();
	      conn.close();
	    }
	    catch (Exception ex)
	    {
	      System.out.println("Error : " + ex.toString());
	    }
	}//end writePlayerToDataBase()
	
	
	private PlayerInfoPO getPlayerBaseInfo(String name){
		
		/*
		 * 根据姓名获取一个
		 * 球员的基本信息
		 * 
		 * 
		 * */
		
		System.out.println("-----传入的球员姓名："+name);
		
		PlayerDataService dataServer=new PlayerData();
		ArrayList<PlayerInfoPO> list=dataServer.getAllPlayer();
		System.out.println("难道说球员的信息没有获取到？----"+list.size());
		for(int i=0;i<list.size();i++){
			if(name.equals(list.get(i).getName())){
				return list.get(i);
			}
		}
		return null;
	}

	@Override
	public ArrayList<PlayerInfoVO> getAllPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PlayerInfoVO> list=new  ArrayList<>();
		for(int i=0;i<pList.size();i++){
			list.add(pList.get(i).getInfo());
		}
		
		return list;
	}

	@Override
	public PlayerInfoVO getPlayerInfo(String name) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PlayerInfoVO> list=getAllPlayer();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getName().equals(name)){
				return list.get(i);
			}
		}
		
		return null;
	}

	@Override
	public ArrayList<PlayerVO> getPlayerInTeam(String teamAbb) {
		ArrayList<PlayerVO> list=getAllPlayer("pointNum");
		ArrayList<PlayerVO> result=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			if(teamAbb.equals(list.get(i).getData().getTeamName())){
				result.add(list.get(i));
			}
		}
		
		return result;
	}
	
	public ArrayList<PlayerVO> getAllPlayer(String item){
		readPlayerFromDBS(item);
		return pList;
	}

	@Override
	public PlayerVO getPlayer(String name) throws RemoteException {
		// TODO Auto-generated method stub
		for(int i=0;i<pList.size();i++){
			if(pList.get(i).getInfo().getName().equals(name))
				return pList.get(i);
		}
		return null;
	}
	
}
