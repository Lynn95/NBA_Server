package matchdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import PO.MatchPO;
import PO.PlayerInfoPO;
import PO.SingleMatchPersonalDataPO;
import PO.TeamMatchPO;
import ShareClass.FileList;
import matchbl.SingleMatchPersonalData;
import matchbl.TeamMatch;
import matchdataservice.MatchDataService;

public class MatchDataController implements MatchDataService{
		/**
		 * 锟斤拷锟斤拷模锟斤拷锟斤拷锟斤拷锟斤拷模锟斤拷
		 * 
		 * **/
	private ArrayList<MatchPO> matchList;
	char chr=39;
	int info[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	public MatchDataController(){
		matchList=new ArrayList<>();
	}
	
	public static void main(String args[]){
		MatchDataController c=new MatchDataController();
		//ArrayList<MatchPO> m=c.getAllMatch();
		 int i=0;
		 
		 
		 MatchPO po=c.getMatch("01-01","CHA-LAC");
		
			System.out.println(i+"+++:"+po.getDate()+";"+po.getHostTeam().getTeamName()
		+";球员姓名"+
					po.getGuestTeam().getIndividualData().get(0).getPlayerName()+"分数："+
		po.getGuestTeam().getIndividualData().get(0).getPoints());
		
	}
	
	@Override
	public ArrayList<MatchPO> getAllMatch() {
		// TODO Auto-generated method stub
		readObject();
		return matchList;
	}

	@Override
	public MatchPO getMatch(String date, String teams) {
		// TODO Auto-generated method stub
		String file="NBAdata/matches/"+"13-14_"+date+"_"+teams;
		
		System.out.println("---------"+file);
        File f = new File(file);  
        String encoding="UTF-8";
		
		try{ 
        	//BufferedReader br = new BufferedReader(new FileReader(new File(l))); 
        
        	InputStreamReader read = new InputStreamReader(
                    new FileInputStream(f),encoding);
        	BufferedReader br = new BufferedReader(read); 	
        	String  line ;

         					//锟斤拷锟斤拷
         String matchScore="";				//锟斤拷确锟�
         ArrayList<String> scores=new ArrayList<String>();	
        String H_team="";
        String G_team="";
        
        ArrayList<SingleMatchPersonalDataPO> H_teamData=new ArrayList<SingleMatchPersonalDataPO> ();
        ArrayList<SingleMatchPersonalDataPO> G_teamData=new ArrayList<SingleMatchPersonalDataPO> ();
        
        int num=1;
        boolean isHostTeam=true;
        char chr=39;
        //line=br.readLine();
        	while((line=br.readLine())!=null){
        		line=line.trim();
        		if(num==1){
        		String  str1[]=line.split(";");
        		String  teamNames[]=str1[1].split("-");
        		H_team=teamNames[0];
        		G_team=teamNames[1];
        		date=str1[0];
        		matchScore=str1[2];
        		
        		}else if(num==2){
        			String score[]=line.split(";");
        			for(int i=0;i<score.length-1;i++)
        			scores.add(score[i]);
        		}
        		else{
        			
        			if(line.length()==3&&num==3){
        	
        				continue;
        			}
        			if(line.length()==3&&num!=3){
        				isHostTeam=false;
        				continue;
        			}
        			if(isHostTeam){
        				String str[]=line.split(";");
        				changeStrToInt(str);
        				H_teamData.add(new SingleMatchPersonalDataPO(str[0].trim().replace(chr, '’'),str[1].trim(),str[2].trim(),
        						info[3],info[4],info[5],info[6],info[7],info[8],
        						info[9],info[10],info[11],info[12],info[13],info[14]
        						,info[15],info[16],info[17]));
        		
        			}else{
        				String str[]=line.split(";");
        				changeStrToInt(str);
        				G_teamData.add(new SingleMatchPersonalDataPO(str[0],str[1],str[2],
        						info[3],info[4],info[5],info[6],info[7],info[8],
        						info[9],info[10],info[11],info[12],info[13],info[14]
        						,info[15],info[16],info[17]));
        			}
        			
        		}
        		num++;
        }
        	 
			        	return new MatchPO(date,matchScore,scores,
			        			(new TeamMatchPO(H_team,H_teamData)),
			        			(new TeamMatchPO(G_team,G_teamData)));
	        }catch(Exception e){
	        		System.out.println("error:"+e.toString());
	        		} 
		
		return null;
	}

	@Override
	public MatchPO getAMatch(String date, String teamName) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}
	
	

	public ArrayList<MatchPO> getMatchList() {
		return matchList;
	}
	
	private void readObject(){
		 /*
		  * 锟斤拷取一锟斤拷锟斤拷员锟斤拷 
		  * 
		  * */
		       
		        //readTxtFile(filePath);
		    
		    	String FILE_IN = "NBAdata/matches";  
		        File f = new File(FILE_IN);  
		        String encoding="UTF-8";
		        List<String> list = new ArrayList<String>();  
		        list = FileList.getFileList(f);  
		        
		        //System.out.println(list.size());  
		        
		        
		        for (String l:list) {
		        	try{ 
		        	//BufferedReader br = new BufferedReader(new FileReader(new File(l))); 
		        
		        	InputStreamReader read = new InputStreamReader(
		                    new FileInputStream(new File(l)),encoding);
		        	BufferedReader br = new BufferedReader(read);
		        	
		        	
		        	String  line ;
		         
		       
		         String date="";					//锟斤拷锟斤拷
		         String matchScore="";				//锟斤拷确锟�
		         ArrayList<String> scores=new ArrayList<String>();	
		        String H_team="";
		        String G_team="";
		        
		        /*
		         * 锟斤拷锟节憋拷锟斤拷锟斤拷锟酵队革拷锟剿碉拷锟斤拷锟斤拷锟斤拷
		         * */
		        ArrayList<SingleMatchPersonalDataPO> H_teamData=new ArrayList<SingleMatchPersonalDataPO> ();
		        ArrayList<SingleMatchPersonalDataPO> G_teamData=new ArrayList<SingleMatchPersonalDataPO> ();
		        
		        int num=1;
		        boolean isHostTeam=true;
		        //line=br.readLine();
		        	while((line=br.readLine())!=null){
		        	//	System.out.println("--"+line+"----");
		        		line=line.trim();
		        		if(num==1){
		        		String  str1[]=line.split(";");
		        		String  teamNames[]=str1[1].split("-");
		        		H_team=teamNames[0];
		        		G_team=teamNames[1];
		        		date=str1[0];
		        		matchScore=str1[2];
		        		
		        		}else if(num==2){
		        			String score[]=line.split(";");
		        			for(int i=0;i<score.length-1;i++)
		        			scores.add(score[i]);
		        		}
		        		else{
		        			
		        			if(line.length()==3&&num==3){
		        	
		        				continue;
		        			}
		        			if(line.length()==3&&num!=3){
		        				isHostTeam=false;
		        				continue;
		        			}
		        			if(isHostTeam){
		        				String str[]=line.split(";");
		        				changeStrToInt(str);
		        				/*String name0,String p1,String time2,int fieldGoal3,
			 int shootNum4,int T_fieldGoal5,int T_shootNum6,int freeThrowGoalNum7,int freeThrowNum8,
			 int O_R_N9,int D_R_N10,int reboundNum11,int assistNum12,int blockNum13,int turnoverNum14,
			 int foulNum15,int points16 */
		        				H_teamData.add(new SingleMatchPersonalDataPO(str[0].trim().replace(chr, '’'),
		        						str[1].trim(),str[2].trim(),
		        						info[3],info[4],info[5],info[6],info[7],info[8],
		        						info[9],info[10],info[11],info[12],info[13],info[14]
		        						,info[15],info[16],info[17]));
		        		
		        			}else{
		        				String str[]=line.split(";");
		        				changeStrToInt(str);
		        				/*String name0,String p1,String time2,int fieldGoal3,
			 int shootNum4,int T_fieldGoal5,int T_shootNum6,int freeThrowGoalNum7,int freeThrowNum8,
			 int O_R_N9,int D_R_N10,int reboundNum11,int assistNum12,int blockNum13,int turnoverNum14,
			 int foulNum15,int points16 */
		        				G_teamData.add(new SingleMatchPersonalDataPO(str[0].trim().replace(chr, '’'),
		        						str[1],str[2],info[3],info[4],
		        						info[5],info[6],info[7],info[8],
		        						info[9],info[10],info[11],info[12],info[13],info[14]
		        						,info[15],info[16],info[17]));
		        			}
		        			
		        		}
		        		//String str2[]=str1[1].split("锟斤拷");
		        		//strList.add(str2[1].trim());
		           // System.out.println(line+"******"+str[1]);
		        		//line=br.readLine();
		        		num++;
		        }
		        	
		      
		        		
		        		
		        		/*String date,String matchScore,
						ArrayList<String>scores,TeamMatch ht,TeamMatch gt*/  
					        	matchList.add(new MatchPO(date,matchScore,scores,
					        			(new TeamMatchPO(H_team,H_teamData)),
					        			(new TeamMatchPO(G_team,G_teamData))));
			        }catch(Exception e){
			        		continue;
			        		} 
		        		
		        		/*playerList.add(new PlayerInfoPO(strList.get(0),Integer.parseInt(strList.get(1)),
		        				strList.get(2),strList.get(3),Double.parseDouble(strList.get(4)),
		        				strList.get(5),Integer.parseInt(strList.get(6)),exp,strList.get(7)));
		        		strList.clear();*/
		        	}//end for
		  	 
		      
		        
		        
	}
	

    private void changeStrToInt(String list[]){
    	for(int i=3;i<list.length;i++){
    		try{
    		info[i]=Integer.parseInt(list[i]);
    		}catch(Exception e){
    			info[i]=0;
    		}
    	}
    }

}
