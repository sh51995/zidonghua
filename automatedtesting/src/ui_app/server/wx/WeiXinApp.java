package ui_app.server.wx;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;
import ui_app.server.GetAppiumDriver;
import ui_app.util.SeleniumUtil;

public class WeiXinApp {
	static AppiumDriver<WebElement> driver;
	
	static Logger log=Logger.getLogger(WeiXinApp.class);
	public static AppiumDriver<WebElement> getWxDriver(AppiumDriver<WebElement> driver){
		return GetAppiumDriver.getDriver(driver, "com.tencent.mm", ".ui.LauncherUI"); 
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
