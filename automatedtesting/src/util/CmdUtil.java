package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdUtil {
	static Process p;
	  public static String CMD(Process p,String cmd) {
		  String result="";
          BufferedReader br = null;
          try {
              p = Runtime.getRuntime().exec(cmd);
              br = new BufferedReader(new InputStreamReader(p.getInputStream()));
              String line = null;
              String s=null;
              while ((line = br.readLine()) != null) {
            	  s=s+line;
                  System.out.println(line);
              }
              result=s;
              System.out.println(s);
              System.out.println("==========");
          } catch (Exception e) {
              e.printStackTrace();
          } finally {

              if (br != null) {
                  try {
                      br.close();
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
              }
          }
          return result;
      }
	  
	  
	  public static void main(String[] args) {
	//	CMD("adb shell dumpsys meminfo com.gl365.android.member");
		CMD(p,"cmd.exe /c node C:/\"Program Files (x86)\"/Appium/node_modules/appium/bin/appium.js");
	}
}
