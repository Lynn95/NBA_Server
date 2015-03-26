package playerdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import PO.PlayerInfoPO;
import PO.PlayerPO;
import ShareClass.FileList;
import playerdataservice.PlayerDataService;

public class PlayerData implements PlayerDataService {
	
	private ArrayList<PlayerInfoPO> playerList;
	
	
	public PlayerData(){
		playerList=new ArrayList<PlayerInfoPO>();
		readObject();
	}
	
	public static void main(String args[]) throws NumberFormatException, IOException{
		PlayerData da=new PlayerData();
		ArrayList<PlayerInfoPO> pl=da.getAllPlayer();
		System.out.println("进入main函数进行测试："+pl.size());
		for(int i=0;i<pl.size();i++){
			//System.out.println("---:"+pl.get(i).getName()+pl.get(i).getName().length()+";-----"+i);
		}
	}
	
	@Override
	public ArrayList<PlayerInfoPO> getAllPlayer() {
		// TODO Auto-generated method stub
		return playerList;
	}
	
	
	private void readObject() {
		 /*
		  * 鐢ㄤ簬浠庢枃鏈枃浠朵腑璇诲嚭
		  * 鍏ㄩ儴鐞冨憳鐨勫熀鏈俊鎭� 
		  * 
		  * */
		        System.out.println("-----进入文件读写-----");
		    	ArrayList<String> strList=new ArrayList<>();
		    	String FILE_IN = "NBAdata/players/info";  
		        File f = new File(FILE_IN);  
		        List<String> list = new ArrayList<String>();  
		        list = FileList.getFileList(f);  
		        String encoding="UTF-8";
		        //System.out.println(list.size());  
		        
		        System.out.println("文件的个数："+list.size());
		        int i=0;
		        char chr=39;
		        for (String l : list) {
		        	try{ 
		        	//BufferedReader br = new BufferedReader(new FileReader(new File(l))); 
		        System.out.println("***********:"+l);;
		        	InputStreamReader read = new InputStreamReader(
		                    new FileInputStream(new File(l)),encoding);
		        	BufferedReader br = new BufferedReader(read);
		        	
		        	
		        	String  line ;
		        	int num=1;
		        	while((line=br.readLine())!=null){

		        		if(num%2==0){
		        		String  str1[]=line.split("║");
		        		String str2[]=str1[1].split("│");
		        		strList.add(str2[1].trim());
		           // System.out.println(line+"******"+str[1]);
		        	}
		        		num++;
		        }
		        	
		        		 int exp=0;
		        		if(!strList.get(7).equals("R")){
		        			exp=Integer.parseInt(strList.get(7));
		        		}
		        		/*String name,int num,String p,String height,
			double weight,String birth,int age ,int exp
			,String school*/
		        		//System.out.println(i+"-------"+strList.get(0)+";"+strList.get(1)+";"+strList.get(4)+";"+strList.get(6));
		        		char chr7=40;
		        		char chr8=41;
		        		playerList.add(new PlayerInfoPO(strList.get(0).replace(chr, '’'),strList.get(1),
		        				strList.get(2),strList.get(3),Double.parseDouble(strList.get(4)),
		        				strList.get(5),Integer.parseInt(strList.get(6)),
		        				exp,strList.get(8).replace(chr, '’').replace(chr7, '（').replace(chr8, '）')));
		        		strList.clear();
		        	}//end for
		  
		catch(Exception e){
			System.out.println("读写文件错误原因："+e.toString());
			continue;
		} 
		     i++;   }
	}
		       
	  }


