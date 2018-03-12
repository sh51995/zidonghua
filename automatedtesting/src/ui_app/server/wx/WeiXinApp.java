package ui_app.server.wx;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import ui_app.server.cfront.CfrontApp;
import ui_app.util.DingDing;
import ui_app.util.SeleniumUtil;

public class WeiXinApp {
	static AppiumDriver<WebElement> driver;
	
	static Logger log=Logger.getLogger(WeiXinApp.class);
	public static AppiumDriver<WebElement> getWxDriver(AppiumDriver<WebElement> driver){
	     DesiredCapabilities capabilities = new DesiredCapabilities();  
	   //     capabilities.setCapability("deviceName","0123456789ABCDEF"); 
	     capabilities.setCapability("deviceName","A10ABN5GQRHS"); 
	  //   	capabilities.setCapability("deviceName","b2bcc292"); 
	        capabilities.setCapability("automationName","Appium");  
	        capabilities.setCapability("platformName","Android");  
	        capabilities.setCapability("platformVersion","23");  
	          
	       //配置测试apk  
	        capabilities.setCapability("appPackage", "com.tencent.mm");  
	        capabilities.setCapability("appActivity", ".ui.LauncherUI");  
	        capabilities.setCapability("sessionOverride", true);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session  
	        capabilities.setCapability("unicodeKeyboard", true);    //设置键盘  
	        capabilities.setCapability("resetKeyboard", false);     //设置默认键盘为appium的键盘  
	        try {
				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities) ;
			} catch (MalformedURLException e) {
				log.error(e.getStackTrace());
			}
	        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);  
	       return driver; 
	}
	
	
	
	
	
	
	public static boolean pay(AppiumDriver<WebElement> driver){
		try {
			boolean bool=false;
			 driver.findElementByAccessibilityId("更多功能按钮").click();
			 driver.findElementByXPath("//android.widget.TextView[contains(@text, \"扫一扫\")]").click();
			 if(SeleniumUtil.findByAccessibilityId(driver, "当前所在页面,扫码支付")){
				 Thread.sleep(3000);
				// driver.tap(1, 360, 850, 0); 
				 driver.tap(1, 600, 1275, 0); 
//				 if(SeleniumUtil.findByAccessibilityId(driver, "收起键盘")){
//					 System.out.println("===========");
//					 Thread.sleep(3000);
//					 driver.tap(1, 200, 900, 0); 
//					 Thread.sleep(1000);
//					 driver.tap(1, 200, 1000, 0); 
//					 Thread.sleep(1000);
//					 driver.tap(1, 200, 1100, 0); 
//					 Thread.sleep(1000);
//					 driver.tap(1, 400, 1100, 0);
//					 Thread.sleep(1000);
//					 driver.tap(1, 400, 1000, 0);
//					 Thread.sleep(1000);
//					 driver.tap(1, 400, 900, 0);
//					bool=driver.findElementById("com.tencent.mm:id/dg6").getText().equals("支付成功");
//				 }
				 
				 
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
					 driver.findElementById("com.tencent.mm:id/drs").click();
					 bool=SeleniumUtil.findById(driver, "android:id/text1");
					 driver.findElementById("com.tencent.mm:id/hy").click();
				 }
			 }	
			return bool;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
