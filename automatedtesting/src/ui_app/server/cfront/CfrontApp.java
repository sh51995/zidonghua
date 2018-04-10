package ui_app.server.cfront;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import ui_app.server.GetAppiumDriver;
import ui_app.util.DingDing;
import ui_app.util.SeleniumUtil;

public class CfrontApp {
	static Logger log=Logger.getLogger(CfrontApp.class);

	public static boolean getCfrontMain(AppiumDriver<WebElement> driver){
		WebElement w=new AndroidElement();
		
	  if(driver.findElementsByAccessibilityId("给乐生活").get(0).isDisplayed()){
      	log.info("加载首页成功");
      	return true;
      }else{
      	log.error("加载超时");
        return false;
      }
	}
	
	
	public static AppiumDriver<WebElement> getCfrontDriver(AppiumDriver<WebElement> driver){
		return GetAppiumDriver.getDriver(driver, "com.gl365.android.member", "com.gl365.android.member.MainActivity");
	}
	
	
	
	
	public static boolean  logout(AppiumDriver<WebElement> driver){
		try {
			boolean bool=false;
			 Thread.sleep(5000);
		//	 driver.tap(1, 600, 56, 0);
			 driver.tap(1, 900, 84, 0);
			 Thread.sleep(2000);
		//	 driver.tap(1, 360, 700, 0);
			 driver.tap(1, 540, 1050, 0);
			 driver.findElementByAccessibilityId("确定 ").click();
			 if(SeleniumUtil.findByAccessibilityId(driver, "扫一扫")){
				  bool=true; 
			 }
			 return bool;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	

	public static boolean login(AppiumDriver<WebElement> driver){
		try {
			boolean bool=false;
		//	driver.findElementsByClassName("android.widget.EditText").get(0).clear();
		//	driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("13682506854");
			driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("www7287062");		 
			 //JieTU.snapshot(driver, "jietu"+System.currentTimeMillis());
			driver.findElementByAccessibilityId("登 录").click();
			 if(SeleniumUtil.findByAccessibilityId(driver, "扫一扫")){
				  bool=true; 
			 }
			 return bool;
		} catch (Exception e) {
			System.out.println("============登录失败");
			return false;
		}
	}
	
	
	public static boolean cfrontPay(AppiumDriver<WebElement> driver){
		try {
			boolean bool=false;
			Thread.sleep(5000);
			
			driver.tap(1, 860, 200, 0);
			
			if(SeleniumUtil.findByAccessibilityId(driver, "支付")){
				driver.findElementByAccessibilityId("确认").click();	
				driver.findElementByName("立即支付").click();	
				if(SeleniumUtil.findByAccessibilityId(driver, "收起键盘")){
					 Thread.sleep(3000);
					 driver.tap(1, 200, 1350, 0); 
					 Thread.sleep(1000);
					 driver.tap(1, 200, 1500, 0); 
					 Thread.sleep(1000);
					 driver.tap(1, 200, 1650, 0); 
					 Thread.sleep(1000);
					 driver.tap(1, 500, 1650, 0);
					 Thread.sleep(1000);
					 driver.tap(1, 500, 1500, 0);
					 Thread.sleep(1000);
					 driver.tap(1, 500, 1350, 0);
					 bool=SeleniumUtil.findById(driver, "com.tencent.mm:id/dmy");
					 driver.findElementById("com.tencent.mm:id/dmy").click();
					 Thread.sleep(7000);
					 driver.tap(1, 90, 140, 0);
				 }
				
			}
			return bool;
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}
}
