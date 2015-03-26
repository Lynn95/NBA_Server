package ShareClass;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileList {
	/*
	 * ��̬����
	 * 
	 * ���Σ��ļ��е�·��
	 * ���أ������ļ���·���µ����е��ļ�����list����
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
