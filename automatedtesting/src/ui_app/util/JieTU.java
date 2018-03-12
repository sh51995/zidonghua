package ui_app.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class JieTU {
	public static File snapshot(TakesScreenshot drivername, String filename) {
		     
		        String currentPath = System.getProperty("user.dir"); // get current work
		                                                                 // folder
		         File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		         File file=null;
		         
		         try {
		             System.out.println("save snapshot path is:" + currentPath + "/"
		                     + filename+".jpg");
		             file=new File(currentPath + "\\" + filename+".jpg");
		             FileUtils.copyFile(scrFile, file);
		         } catch (IOException e) {
		             System.out.println("Can't save screenshot");
		             e.printStackTrace();
		         } finally {
		            System.out.println("screen shot finished, it's in " + currentPath
		                     + " folder");
		        }
		         return file;
		     }
	
	public static void main(String[] args) {
		
	}
}
