package SchedulerTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.text.html.parser.Parser;

public class PropertyConfig {

    private InputStream _inputStream;   

    public Map<String, String> getPropsValue(){
        try{
            Properties prop = new Properties();
            
            String propFileName = ".\\config.props.txt";
            
            this._inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(_inputStream  != null){
                prop.load(_inputStream);
            }
            else{
                throw new FileNotFoundException("Porperty file "+ propFileName + " not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            Map<String, String> props = new HashMap<String, String>();

            props.put("source_directory", prop.getProperty("source_directory"));
            props.put("dest_directory", prop.getProperty("dest_directory"));
            props.put("ip", prop.getProperty("ip"));
            props.put("port", prop.getProperty("port"));
            props.put("file_type", prop.getProperty("file_type"));

            return props;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Map<String, String> getPropsValueFromTxtFile(){
        try{
            File myObj = new File(".\\config.txt");
            String data = "";  
            Map<String, String> propList = new HashMap<String, String>();         
            Scanner myReadeScanner = new Scanner(myObj);

            while(myReadeScanner.hasNextLine()){
                data = myReadeScanner.nextLine();
                System.out.println(data);
            }

            myReadeScanner.close();

            String[] props = data.split("\\|");

            for (int i = 0; i<props.length; i++) {
                String[] keyValue = props[i].split("=");

                propList.put(keyValue[0].trim(), keyValue[1].trim());            
            }            

            return propList;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}

