package ui_app.testcase;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import ui_app.server.cfront.CfrontApp;
import ui_app.util.DingDing;
import ui_app.util.GetTime;
import ui_app.util.JieTU;
import ui_app.util.SeleniumUtil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class TestLoginCfrontAPP {
	static Logger log=Logger.getLogger(TestLoginCfrontAPP.class);
	static AppiumDriver<WebElement> driver;
	
	
	public static boolean login(){
	
			boolean bool=false;
			for(int i=0;i<3;i++){
				try {
				driver=CfrontApp.getCfrontDriver(driver);
				driver.findElementByAccessibilityId("我的").click();
				for(int s=0;s<5;s++){
			  		if(SeleniumUtil.findByAccessibilityId(driver, "登 录")){
			  			break;
			  		}if(SeleniumUtil.findByAccessibilityId(driver, "我的账单")){
			  			CfrontApp.logout(driver);
			  			driver.findElementByAccessibilityId("我的").click();
			  			break;
			  		}
			  		driver.findElementByAccessibilityId("我的").click();
			  	}
				
				bool=CfrontApp.login(driver);
				if(bool){
					break;
				}else{
					JieTU.snapshot(driver, "C端登录失败"+GetTime.getTime());
					log.error("C端登录第"+i+"失败");
					driver.quit();		
				}
				} catch (Exception e) {
					JieTU.snapshot(driver, "C端登录失败"+GetTime.getTime());
					log.error("Clogin"+e.toString());
				}
			}
			return bool;
		
		
	}
	public static boolean cPay(){
		boolean bool=false;
		for(int i=0;i<3;i++){
		 bool=CfrontApp.cfrontPay(driver);
		  if(bool){
			 break;
		  }else{
			  JieTU.snapshot(driver, "C端支付"+GetTime.getTime());
			  log.error("B端退款"+i+"失败");
			  driver.quit(); 
			  driver=CfrontApp.getCfrontDriver(driver);
		  }
		}
		  return bool;
	}
	
	
	
	
	public static boolean logout(){
		boolean bool=false;
		for(int i=0;i<3;i++){
			try {
				driver.findElementByAccessibilityId("我的").click();
				if(SeleniumUtil.findByAccessibilityId(driver, "我的账单")){
		  			bool=CfrontApp.logout(driver);
		  			System.out.println(bool+"sssss:"+i);
		  			if(bool){
		  				break;
		  			}else{
		  				JieTU.snapshot(driver, "C端注销失败"+GetTime.getTime());
		  				driver.quit();
		  				driver=CfrontApp.getCfrontDriver(driver);	
		  			}
		  		}if(SeleniumUtil.findByAccessibilityId(driver, "登 录")){
		  			bool=true;
		  			log.info("C端已注销");
		  			break;
		  		}
			} catch (Exception e) {
				JieTU.snapshot(driver, "C端注销失败"+GetTime.getTime());
				log.error("Clogout"+e.toString());
			}
			
		}
		return bool;
	}
	
	
	
	
	public static void testCforntLoginAndOut(){

		boolean boologin=login();
		if(boologin){
			boolean boologout=logout();
			if(!boologout){
				 DingDing.send("C端注销失败");
			}
		}else{
			 DingDing.send("C端登录失败");
		}
			driver.quit();
	}
	
  @Test
  public void testCforntLogin() {
	  testCforntLoginAndOut();
		 
  }

}
