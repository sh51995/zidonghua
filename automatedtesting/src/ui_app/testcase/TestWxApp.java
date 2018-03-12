package ui_app.testcase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import ui_app.server.wx.WeiXinApp;
import ui_app.util.AppiumUtil;
import ui_app.util.DingDing;
import ui_app.util.GetTime;
import ui_app.util.JieTU;
import ui_app.util.SeleniumUtil;

public class TestWxApp {
	static Logger log=Logger.getLogger(TestWxApp.class);
	static AppiumDriver<WebElement> driver;
	
	public static boolean testWXPays(){
			boolean bool=false;
			for(int i=0;i<3;i++){
			driver=WeiXinApp.getWxDriver(driver);
			 bool=WeiXinApp.pay(driver);
			if(bool){
				driver.quit();
				break;
			}else{
				JieTU.snapshot(driver, "微信支付失败"+GetTime.getTime());
				log.error("微信支付第"+i+"失败");
				driver.quit();	
				}
			}
			return bool;
	
	}
	 @Test
	  public void testWXPay() throws Exception {
		 AppiumUtil.appiumStart();
		 Thread.sleep(10000);
		boolean bool= testWXPays();
		if(!bool){
			DingDing.send("微信支付失败");
		}
		AppiumUtil.appiumStop();
	 }
}
