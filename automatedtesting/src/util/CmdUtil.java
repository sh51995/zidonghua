package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CmdUtil {
	  public static String CMD(String cmd) {
		  String result="";
          BufferedReader br = null;
          try {
        	  Process    p = Runtime.getRuntime().exec(cmd);
              br = new BufferedReader(new InputStreamReader(p.getInputStream()));
              String line = null;
              String s="";
              while ((line = br.readLine()) != null) {
            	  s=s+line;
                  System.out.println(line);
              }
              result=s;
              System.out.println(s);
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
		String s=CMD("adb devices");
		System.out.println(s);
	}
}
