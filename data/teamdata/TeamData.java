package teamdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import PO.TeamInfoPO;
import teamdataservice.TeamDataService;

public class TeamData implements TeamDataService{
	/*
	 * ������ӵĻ�����Ϣ�ĳ־û�����
	 * һ������㲻���޸�
	 * 
	 * */
      private  ArrayList<TeamInfoPO> teamInfoList;
      public TeamData(){
    	  teamInfoList=new ArrayList<TeamInfoPO>();
    	  readObject("NBAdata/teams/teams");
      }
      
      public static void main(String args[]){
    	  TeamData t=new TeamData();
    	  
    	  ArrayList<TeamInfoPO> list=t.getTeamInfoList(); 
    	  TeamInfoPO po;
    	  for(int i=0;i<list.size();i++){
    		  po=list.get(i);
    		  System.out.println("打印信息："+po.getDivision()+";"+po.getFormedTime()+";"+
    		  po.getFullName()+";"+po.getHomeGround()+";"+po.getLocation()+";"+po.getPartition()+";"+
    		  po.getTeamAbb());
    	  }
    	 
      }
	@Override
	public ArrayList<TeamInfoPO> getTeamInfoList() {
		// TODO Auto-generated method stub
		return teamInfoList;
	}

	private void readObject(String filePath){
		
        try {
                String encoding="UTF-8";
               File file=new File(filePath);
                    InputStreamReader read = new InputStreamReader(
    	                    new FileInputStream(file),encoding);
    	        	BufferedReader br = new BufferedReader(read);
                    String line = null;
                    int num=1;
                    while((line = br.readLine()) != null){
                    		System.out.println("----"+line);
                    	if(num==1||num==32){
                    		 num++;
                    		 continue;
                    	}
                   
                    	String str[]=line.split("║");
                    	String info[]=str[1].split("│");
                    	teamInfoList.add(new TeamInfoPO(info[0].trim(),info[1].trim(),info[2].trim(),info[3].trim(),
                    			info[4].trim(),info[5].trim(),info[6].trim()));
                       // System.out.println(line);
                        num++;
                    }
                    read.close();
        
        } catch (Exception e) {
            System.out.println("文件读写异常");
            e.printStackTrace();
        }
	}
}
