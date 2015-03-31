package teambl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import playerbl.PlayerController;
import playerbl.TeamInfo_player;
import matchbl.MatchController;
import PO.*;
import VO.*;
import teamblservice.TeamBLService;
import teamdata.TeamData;
import teamdataservice.TeamDataService;

public class TeamController extends UnicastRemoteObject implements TeamBLService,TeamInfo_player{
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String url="jdbc:mysql://localhost/mysql";
    String user="ghl";
    String pwd="ghl13";

     Connection conn;
     Statement stmt;//��������������ִ��sql����
     ArrayList<TeamInfoVO> infoList=new ArrayList<>();
     ArrayList<TeamSeasonDataVO> dataList=new ArrayList<>();
     
	
     public TeamController() throws RemoteException {
    	 super();
    	 try{
    	  url="jdbc:mysql://localhost/mysql";
    	     user="ghl";
    	     pwd="ghl13";
    	    Class.forName("com.mysql.jdbc.Driver").newInstance();
    	     conn = DriverManager.getConnection(url,user, pwd);
    	     stmt = conn.createStatement();//��������������ִ��sql����
    	 }catch(Exception e){
    		 System.out.println("数据库连接出错："+e.toString());
    	 }
    	
    	 write_teamInfo_to_dataBase();
    	// write_TeamSeasondata_To_dataBase();
    	  //getAllTeamInfo();
    	 // getAllTeamSeasonDataVO("points");
     }
     
     
	public static void main(String args[]){
		
		
		double x=8;
		int y=9;
		
		System.out.println(x/y);
		
        /*DecimalFormat df = new DecimalFormat( "0.0000 ");  
        double d1 = 0.6;  
        double d2 = 4.56789;  
        System.out.println(df.format(d1));   
        System.out.println(df.format(d2)); 
		
		double x=0.92222222222222222222222222;
		DecimalFormat  f=new DecimalFormat("#.00");
		System.out.println(f.format(x));*/
		try {
		TeamController t=new TeamController();/*
		//t.write_TeamSeasondata_To_dataBase();
		
		t.getTeamInfoVO("rrr");
		
		for(int i=0;i<t.dataList.size();i++){
			System.out.println("打印球队效率："+t.dataList.get(i).getAssistEfficiency());
		}
		
		for(int i=0;i<t.infoList.size();i++){
			System.out.println("打印球队名："+t.infoList.get(i).getFullName());
		}*/
		
		/*TeamController t=new TeamController();
		t.write_teamInfo_to_dataBase();*/
		
		
			//t.getAllTeam();
			
			//System.out.println("---"+t.getAllTeam().size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public ArrayList<TeamVO> getAllTeam() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<TeamVO> list=new ArrayList<TeamVO>();
		for(int i=0;i<dataList.size();i++){
			System.out.println("---"+dataList.get(i).getTeamName()/*getTeamInfoVO(dataList.get(i).getTeamName()).getFullName()*/);
			list.add(new TeamVO(getTeamInfoVO(dataList.get(i).getTeamName()),dataList.get(i)));
		}
		
		return list;
	}

	@Override
	public TeamVO getTeamInfo(String teamAbb) throws RemoteException {
		ArrayList<TeamVO> list=getAllTeam();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getInfo().getTeamAbb().equals(teamAbb))
				return list.get(i);
		}
		return null;
	}

	@Override
	public ArrayList<TeamVO> sort(String sortItem) throws RemoteException {
		// TODO Auto-generated method stub
		getAllTeamSeasonDataVO(sortItem);
		return getAllTeam();
	}

	
	private TeamInfoVO getTeamInfoVO(String teamAbb){
		
		for(int i=0;i<infoList.size();i++){
			if(infoList.get(i).getTeamAbb().equals(teamAbb)){
				return infoList.get(i);
			}
		}
		
		return null;
	}
	
	private void getAllTeamSeasonDataVO(String item){
		try
	    {
	       ResultSet  rs=stmt.executeQuery("select * from teamseasondata order by '"+item+"' desc");
      
	      while (rs.next())
	      {
	    	  //"select * from player order by height desc"
	    	  /*
	    	   * String teamName,int matchNum,int fieldGoal,int shootNum,int T_fieldGoal,int T_shootNum,
	int freeThrowGoalNum,int freeThrowNum,int O_ReboundNum,int D_ReboundNum,int assistNum,int stealNum,int reboundNum,int blockNum,
	int turnoverNum,int foulNum,int points,double shootPercentage,double threePointPercentage,double freeThrowPercentage,
	double winRate,double offenseRound,double offenseEfficiency,double defenseEfficiency,double reboundEfficiency,
	double stealEfficiency ,double assistEfficiency
	    	   * */
	    	TeamSeasonDataVO  vo=new TeamSeasonDataVO(rs.getString("teamName"),rs.getInt("matchNum"),
	    			  rs.getInt("fieldGoal"),rs.getInt("shootNum"),
	    			  rs.getInt("T_fieldGoal"),rs.getInt("T_shootNum"),
	    			  rs.getInt("freeThrowGoalNum"),rs.getInt("freeThrowNum"),rs.getInt("O_Rebound"),
	    			  rs.getInt("D_Rebound"),rs.getInt("assistNum"),
	    			  rs.getInt("stealNum"),rs.getInt("reboundNum"),
	    			  rs.getInt("blockNum"),rs.getInt("turnoverNum"),
	    			  rs.getInt("foulNum"),rs.getInt("points")
	    			  ,rs.getDouble("shootPercentage"),rs.getDouble("threePointPercentage"),
	    			  rs.getDouble("freeThrowPercentage"),rs.getDouble("winRate"),rs.getDouble("offenseRound"),
	    			  rs.getDouble("offenseEfficiency"),rs.getDouble("defenseEfficiency"),
	    			  rs.getDouble("reboundEfficiency"),rs.getDouble("stealEfficiency"),
	    			  rs.getDouble("assistEfficiency"));
	        dataList.add(vo);
	      }
	}catch (Exception ex)
	    {
	      System.out.println("Error : " + ex.toString());
	    }
	}
	
	
	
	
	private TeamSeasonDataVO getTeamSeasonDataVO(String teamNameAbb){
		/*
		 * 按照球队的球队缩写获取球队的
		 * 赛季的比赛信息
		 * 
		 * 为内部私有方法
		 * 
		 * */
		for(int i=0;i<dataList.size();i++){
			if(dataList.get(i).equals(teamNameAbb))
				return dataList.get(i);
		}
		return null;
	}
	
	public void write_TeamSeasondata_To_dataBase() throws RemoteException{
		/*  
		 * 将球队赛季信息录入数据库
		 *从文本文件中读取数据整合后将数据存入数据库
		 *以便以更快的速度获得数据
		 *  */
		/*
		
		/*******************************/
		MatchInfo_team team;
		try {
			team = new MatchController();
		
		
		DecimalFormat df = new DecimalFormat( "0.000");
		ArrayList<TeamSeasonDataVO> list=team.getTeamSeasonData();
		System.out.println("获得人数："+list.size());
		
		try
	    {
	        TeamSeasonDataVO vo;
	        String sqrStr; 
	       /*String createTableTeam = "CREATE TABLE teambaseinfo " +
	 	    		  "(fullName VARCHAR(32),teamAbb VARCHAR(32),location VARCHAR(32)," +
	 	    		  "division VARCHAR(32),partition VARCHAR(32),homeGround VARCHAR(32),formedTime VARCHAR(32))";
	 	      stmt.executeUpdate(createTableTeam);*/
	 	      System.out.println("建立表格成功！");
	 	    // sqrStr="INSERT INTO `teambaseinfo` (`fullName`, `location`, `teamAbb`, `division`, `partition`, `homeGround`, `formedTime`) VALUES ('sddddf', 'sdf', 'sdf', 'sdf', 'ssdf', 'sdf', 'sadf')";
	 	    //stmt.executeUpdate(sqrStr);
	 	    
	 	    System.out.println("未进入循环中");
	 	    PlayerInfoPO info;
	 	    
	 	    /*下面暂且不要管*/
	 	    int m=0;
	 	     while (list.size()>0) {		  
	 	    	 vo=list.get(0);
	 	    	 
	 	    	 sqrStr="INSERT INTO `teamseasondata` (`teamName`, `matchNum`, `fieldGoal`, `shootNum`, "
	 	    	 		+ "`T_fieldGoal`, `T_shootNum`, `freeThrowGoalNum`, `freeThrowNum`,"
	 	    	 		+ " `O_Rebound`, `D_Rebound`, `assistNum`, `stealNum`, `reboundNum`, "
	 	    	 		+ "`blockNum`, `turnoverNum`, `foulNum`, `points`, `shootPercentage`, "
	 	    	 		+ "`threePointPercentage`, `freeThrowPercentage`, `winRate`, `offenseRound`,"
	 	    	 		+ " `offenseEfficiency`, `defenseEfficiency`, `reboundEfficiency`, `stealEfficiency`, "
	 	    	 		+ "`assistEfficiency`) VALUES ('"+vo.getTeamName()+"', '"+vo.getMatchNum()+"', "
	 	    	 		+ "'"+vo.getFieldGoal()+"', '"+vo.getShootNum()+"', "
	 	    	 		+ "'"+vo.getT_fieldGoal()+"', '"+vo.getT_shootNum()+"', "
	 	    	 		+ "'"+vo.getFreeThrowGoalNum()+"', '"+vo.getFreeThrowNum()+"','"+vo.getO_ReboundNum()+"', "
	 	    	 		+ "'"+vo.getD_ReboundNum()+"', '"+vo.getAssistNum()+"', "
	 	    	 		+ "'"+vo.getStealNum()+"', '"+vo.getReboundNum()+"', "
	 	    	 		+ "'"+vo.getBlockNum()+"', '"+vo.getTurnoverNum()+"', "
	 	    	 		+ "'"+vo.getFoulNum()+"','"+vo.getPointNum()+"', "
	 	    	 		
	 	    	 		+ "'"+df.format(vo.getShootPercentage())+"', '"+df.format(vo.getThreePointPercentage())+"', "
	 	    	 		+ "'"+df.format(vo.getFreeThrowPercentage())+"','"+df.format(vo.getWinRate())+"',"
	 	    	 		+ " '"+df.format(vo.getOffenseRound())+"', '"+df.format(vo.getOffenseEfficiency())+"', "
	 	    	 		+ "'"+df.format(vo.getStealEfficiency())+"', '"+df.format(vo.getReboundEfficiency())+"', "
	 	    	 		+ "'"+df.format(vo.getStealEfficiency())+"', '"+df.format(vo.getAssistEfficiency())+"')";
	 	    	 
	    	 stmt.executeUpdate(sqrStr);
	    	 
	    	  list.remove(0);
	   
	    	  m++;
	    	  System.out.println("计数：---"+m);
	 	    	 
	      }
	      
	      stmt.close();
	      conn.close();
	    }
	    catch (Exception ex)
	    {
	      System.out.println("Error : " + ex.toString());
	    }
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/********************************/
		
		
		
		
	}
	
	
	private void write_teamInfo_to_dataBase(){
		/*  
		 * 将球基本信息录入数据库
		 *从文本文件中读取数据整合后将数据存入数据库
		 *以便以更快的速度获得数据
		 *
		 *录入只进行一次
		 *  */
		
		TeamDataService dataServer=new TeamData();
		ArrayList<TeamInfoPO> teamInfoList=dataServer.getTeamInfoList();
		try
	    {
	      String url="jdbc:mysql://localhost/mysql";
	      String user="ghl";
	      String pwd="ghl13";
	     
	     Class.forName("com.mysql.jdbc.Driver").newInstance();
	       Connection conn = DriverManager.getConnection(url,user, pwd);
	       Statement stmt = conn.createStatement();
	
	        TeamInfoPO po;
	        String sqrStr;
	        
	        String fullName;
	        String teamAbb;
	        String location;
	        String division;
	        String partition;
	        String homeGround;
	        String formedTime;
	        
	       /*String createTableTeam = "CREATE TABLE teambaseinfo " +
	 	    		  "(fullName VARCHAR(32),teamAbb VARCHAR(32),location VARCHAR(32)," +
	 	    		  "division VARCHAR(32),partition VARCHAR(32),homeGround VARCHAR(32),formedTime VARCHAR(32))";
	 	      stmt.executeUpdate(createTableTeam);*/
	 	      System.out.println("建立表格成功！");
	 	    // sqrStr="INSERT INTO `teambaseinfo` (`fullName`, `location`, `teamAbb`, `division`, `partition`, `homeGround`, `formedTime`) VALUES ('sddddf', 'sdf', 'sdf', 'sdf', 'ssdf', 'sdf', 'sadf')";
	 	    //stmt.executeUpdate(sqrStr);
	 	    
	 	    System.out.println("未进入循环中");
	 	    
	 	    
	 	    /*下面暂且不要管*/
	 	     while (teamInfoList.size()>0)
	      {
	    	  po=teamInfoList.get(0);
	    	   fullName=po.getFullName();
	    	   teamAbb=po.getTeamAbb();
	    	   location=po.getLocation();
	    	   division=po.getDivision();
	    	   partition=po.getPartition();
	    	   homeGround=po.getHomeGround();
	    	   formedTime=po.getFormedTime();
	    	  
	    	  System.out.println(po.getTeamAbb()+";"+teamAbb);
	    	  sqrStr="INSERT INTO `teambaseinfo` "
	    	  		+ "(`fullName`, `location`,"
	    	  		+ " `teamAbb`, `division`,"
	    	  		+ " `partition`, `homeGround`, "
	    	  		+ "`formedTime`) "
	    	  		+ "VALUES('"+fullName+"','"+location+"','"+teamAbb+"',"
	    	  		+ "'"+division+"','"+partition+"',"
	    	  		+ "'"+homeGround+"','"+formedTime+"')";
	    	    stmt.executeUpdate(sqrStr);
	    	  teamInfoList.remove(0);
	    	
	      }
	      
	      stmt.close();
	      conn.close();
	    }
	    catch (Exception ex)
	    {
	      System.out.println("Error : " + ex.toString());
	    }
	}



	@Override
	public double getTeamWinNum(String teamAbb) throws RemoteException {
		// TODO Auto-generated method stub	
		for(int i=0;i<dataList.size();i++){			
			if(dataList.get(i).getTeamName().equals(teamAbb)){
				return dataList.get(i).getWinNum();
			}
		}
		return 0;
	}



	@Override
	public ArrayList<String> getTeamAllPlayer(String teamAbb)
			throws RemoteException {
		/*这个接口我是认为不妥，有待修改吧*/
		// TODO Auto-generated method stub
		PlayerInfo_team pit=new PlayerController();
		ArrayList<PlayerVO> list=pit.getPlayerInTeam(teamAbb);
		
		ArrayList<String> result=new ArrayList<>();
		for(int i=0;i<list.size();i++){
			result.add(list.get(i).getInfo().getName());
		}
		return result;
	}
	
	
	private void getAllTeamInfo(){
	
		try
	    {
	      // ResultSet  rs=stmt.executeQuery("select * from teambaseinfo where name='" + teamName + "'");
	       ResultSet  rs=stmt.executeQuery("select * from teambaseinfo");
	       
	      while (rs.next())
	      {
	    	 // System.out.println("打印球队基本信息："+rs);
	    	 /* System.out.println("打印球队信息："+rs.getString("fullName")+";"+rs.getString("teamAbb")+";"
	    			 + rs.getString("location")+";"+rs.getString("division")+";"+
	    			  rs.getString("partition")+";"+rs.getString("homeGround")+";"+rs.getString("formedTime"));*/
	    	  infoList.add(new TeamInfoVO(rs.getString("fullName"),rs.getString("teamAbb"),
	    			  rs.getString("location"),rs.getString("division"),
	    			  rs.getString("partition"),rs.getString("homeGround"),rs.getString("formedTime")));
	        //return vo;
	      }
	}catch (Exception ex)
	    {
	      System.out.println("Error : " + ex.toString());
	    }
	
	}


	@Override
	public TeamInfoVO getTeam_info(String teamAbb) {
		// TODO Auto-generated method stub
		return getTeamInfoVO(teamAbb);
	}
	

}
