package SchedulerTask;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import org.springframework.util.FileCopyUtils;

public class Scheduler extends TimerTask  {

    private PropertyConfig _propConfig;
    private String _IP;
    private Integer _Port;
    private String _Source_Dir;
    private String _Destination_Dir;
    private String _File_Type;

    public Scheduler(){
 
        //Constructor
        _propConfig = new PropertyConfig();
        // Map<String, String> dict =_propConfig.getPropsValue();
        Map<String, String> dict =_propConfig.getPropsValueFromTxtFile();

        // this._IP = "10.12.72.6"; //dict.get("ip");
        // this._Port = 1666; //Integer.parseInt(dict.get("port"));
        // this._Source_Dir = "D:\\Projects\\Java Scheduler Task\\New Folder\\"; //dict.get("source_directory");
        // this._Destination_Dir = "D:\\Projects\\Java Scheduler Task\\Old Folder\\"; //dict.get("dest_directory"); 
        // this._File_Type = ".txt"; //dict.get("file_type");

        this._IP = dict.get("ip");
        this._Port = Integer.parseInt(dict.get("port"));
        this._Source_Dir = dict.get("source_directory");
        this._Destination_Dir = dict.get("dest_directory"); 
        this._File_Type = dict.get("file_type");
    }
 
    public void run() {            
        try{
            List<String> fileNameList = this.GetTextFileList(this._Source_Dir); 

            for (String item : fileNameList) {                            
                BufferedReader br = new BufferedReader(new FileReader(this._Source_Dir + "\\"+ item));
                
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
            
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();

                System.out.println(System.currentTimeMillis() + " : " + everything);    
                
                TCP tcp = new TCP(this._IP, this._Port);
                tcp.SendToServer(everything);
                br.close(); 
                
                CopyAndDestory(item);
            }
        }        
        catch(Exception ex){
            System.out.println("error running thread " + ex.getMessage());
        }
    }   
    
    public List<String> GetTextFileList(String directory){
        try {
            List<String> textFiles = new ArrayList<String>();

            File dir = new File(directory);

            if(dir.listFiles() != null)                
            {
                for(File file : dir.listFiles()){
                    if(file.getName().endsWith((this._File_Type))){
                        textFiles.add(file.getName());
                    }
                }
            }            

            return textFiles;
        }
        catch(Exception ex){
            throw ex;
        }        
    }

    public void CopyAndDestory(String fileName){
        try{            
            File src = new File(this._Source_Dir + fileName);
            File dest = new File(this._Destination_Dir + fileName);

            FileCopyUtils.copy(src , dest);            

            src.delete();
        }
        catch(Exception ex){
            System.out.println("error running thread " + ex.getMessage());
        }
 
    }    
 }

