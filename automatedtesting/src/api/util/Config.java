package api.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {
	static Logger log=Logger.getLogger(Config.class);
	public static String getCfronURL(String properties){
		String result=null;
		Properties prop = new Properties();
		 try {
			prop.load(new BufferedInputStream (new FileInputStream("conf/config.properties")));
			result=prop.getProperty(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		log.error(e);
		} 
		 return result;
	}
	
	
	
}
