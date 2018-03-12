package ui_app.testcase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import ui_app.server.merchant.MerchantApp;
import ui_app.util.DingDing;
import ui_app.util.GetTime;
import ui_app.util.JieTU;
import ui_app.util.SeleniumUtil;

public class TestLogicBfrontAPP {
	static Logger log=Logger.getLogger(TestLoginCfrontAPP.class);
	static AppiumDriver<WebElement> driver;
	
public static boolean login(){
	boolean bool=false;
	for(int i=0;i<3;i++){
	 driver=MerchantApp.getMerchantDriver(driver);
	 if(SeleniumUtil.findByAccessibilityId(driver, "众乐科技")){
		  MerchantApp.logout(driver); 
	  }
	 bool=MerchantApp.login(driver);
	  if(bool){
		 break;
	  }else{
		  JieTU.snapshot(driver, "B端登录失败"+GetTime.getTime());
		  log.error("B端登录"+i+"失败");
		  driver.quit(); 
	  }
	}
	  return bool;
}


public static boolean singleBack(){
	boolean bool=false;
	for(int i=0;i<3;i++){
	 bool=MerchantApp.singleBack(driver);
	  if(bool){
		 break;
	  }else{
		  JieTU.snapshot(driver, "B端退款失败"+GetTime.getTime());
		  log.error("B端退款"+i+"失败");
		  
		  driver.quit(); 
		  driver=MerchantApp.getMerchantDriver(driver);
	  }
	}
	  return bool;
}

public static boolean logout(){
	boolean bool=false;
	for(int i=0;i<3;i++){
	 bool=MerchantApp.logout(driver);
	  if(bool){
		 break;
	  }else{
		  JieTU.snapshot(driver, "B端注销失败"+GetTime.getTime());
		  log.error("B端注销"+i+"失败");
		  driver.quit(); 
		  driver=MerchantApp.getMerchantDriver(driver);
	  }
	}
	  return bool;
}

	
public static void testBfrontLoginAndOut(){
	      boolean boologin=login();
	      if(boologin){
	    	  boolean boolsingleBack=singleBack();
	    	  if(boolsingleBack){
	    		  boolean boollogout=logout();
	    		  if(!boollogout){
	    			  DingDing.send("B端注销失败");
	    		  }
	    	  }else{
	    		  DingDing.send("退款失败");
	    		  boolean boollogout=logout();
	    		  if(!boollogout){
	    			  DingDing.send("B端注销失败");
	    		  }
	    	  }
	      }else{
	    	  DingDing.send("B端登录失败"); 
	      }
		 
		  driver.quit(); 
}


public static void testBfrontLoginAndOut1(){
    boolean boologin=login();
    if(boologin){
  		  boolean boollogout=logout();
  		  if(!boollogout){
  			  DingDing.send("B端注销失败");
  		  }
    }else{
  	  DingDing.send("B端登录失败"); 
    }
	 
	  driver.quit(); 
}
	
  @Test
  public void testBforntLogin() {
	  testBfrontLoginAndOut();
  }
}
