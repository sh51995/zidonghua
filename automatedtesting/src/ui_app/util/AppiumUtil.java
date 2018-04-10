package ui_app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.exec.*;

import util.CmdUtil;


public class AppiumUtil {
	static Process p;	
	 public static void appiumStop() throws IOException {
		 Runtime.getRuntime().exec("taskkill /F /IM node.exe"); 
		 System.out.println("Appium server stop");
	    }
	 
	 
	 
	 public static void appiumStart() throws IOException, InterruptedException {
		 Runtime.getRuntime().exec("taskkill /F /IM node.exe"); 
         Thread.sleep(3000);
         // 处理外部命令执行的结果，释放当前线程，不会阻塞线程 
         DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler(); 
         CommandLine commandLine = CommandLine.parse("cmd.exe /c node C:/\"Program Files (x86)\"/Appium/node_modules/appium/bin/appium.js"); 
        
        // 创建监控时间60s,超过60s则中断执行 
        ExecuteWatchdog dog = new ExecuteWatchdog(60 * 1000);
        Executor executor = new DefaultExecutor(); 

        // 设置命令执行退出值为1，如果命令成功执行并且没有错误，则返回1 
        executor.setExitValue(1);
        executor.setWatchdog(dog);
        executor.execute(commandLine, resultHandler);
        resultHandler.waitFor(5000); 
        System.out.println("Appium server start");
	    }
	 
//	 public static boolean appiumStart() throws IOException, InterruptedException {
//		 boolean bool=false;
//		 Runtime.getRuntime().exec("taskkill /F /IM node.exe"); 
//         Thread.sleep(3000);
// 
//         p = Runtime.getRuntime().exec("cmd.exe /c node C:/\"Program Files (x86)\"/Appium/node_modules/appium/bin/appium.js");
//         BufferedReader br = null;
//         br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//         String line = null;
//         String s=null;
//         while ((line = br.readLine()) != null) {
//       	  s=s+line;
//             System.out.println(line);
//             if(s.contains("Appium REST http interface listener started")){
//            	 bool=true;
//            	 break;
//             }
//             
//         }
//        System.out.println("Appium server start"); 
//		return bool;
//	    }
	 
	 
	 public static String getDevices(){
		 String de=CmdUtil.CMD("adb devices");
		 System.out.println(de.indexOf("attached")+8);
		 String devices=de.substring(de.indexOf("attached")+8);
		 devices=devices.substring(0,devices.indexOf("device"));
		 devices=devices.trim();
		 return devices;
	 }
	 public static void main(String[] args) throws IOException, InterruptedException {
		 getDevices();
	//	 appiumStart();
	//	 appiumStop();
	}
}
