package ShareClass;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileList {
	/*
	 * 静态方法
	 * 
	 * 传参：文件夹的路径
	 * 返回：给定文件夹路径下的所有的文件名的list数组
	 *
	 * 
	 * */
	public static List<String> getFileList(File file) {  
		  
        List<String> result = new ArrayList<String>();  
  
        if (!file.isDirectory()) {  
            System.out.println(file.getAbsolutePath());  
            result.add(file.getAbsolutePath());  
        } else {  
            File[] directoryList = file.listFiles(new FileFilter() {  
                public boolean accept(File file) {  
                    if (file.isFile()) {  
                        return true;  
                    } else {  
                        return false;  
                    }  
                }  
            });  
            for (int i = 0; i < directoryList.length; i++) {  
                result.add(directoryList[i].getPath());  
            }  
        }  
  
        return result;  
	}
}
