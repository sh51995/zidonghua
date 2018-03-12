package ui_app.testcase;

import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import ui_app.server.sales.SalesApp;
import ui_app.util.DingDing;
import ui_app.util.SeleniumUtil;

public class TestLoginSalesApp {
	static AppiumDriver<WebElement> driver;
	public boolean login(AppiumDriver<WebElement> driver){
		try {
			boolean bool;
			if(SeleniumUtil.findByAccessibilityId(driver, "登 录")){
				driver.findElementsByClassName("android.widget.EditText").get(0).clear();
				driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("15013756335");
				driver.findElementsByClassName("android.widget.EditText").get(1).clear();
				driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("a12345678");
				driver.findElementByAccessibilityId("登 录").click();
				bool=SeleniumUtil.findByAccessibilityId(driver, "首页");
			}else{
				bool=false;
			};
			return bool;
		} catch (Exception e) {
			DingDing.send("s端登录失败");
			e.printStackTrace();
			return false;
		}
	}
	@Test
	  public void testCforntLogin() {
		try {
			driver=SalesApp.getSalesDriver(driver);	
			boolean bool=login(driver);
			if(!bool){
				DingDing.send("s端登录失败");
				 driver.quit();
				 assertFalse(true);
				
			}
			driver.findElementByAccessibilityId("我的").click();
			 Thread.sleep(3000);
			 driver.tap(1, 360, 1112, 0);
			 Thread.sleep(3000);
			 driver.findElementByAccessibilityId("确定").click();
			 driver.quit();
		} catch (Exception e) {
			driver.quit();
			e.printStackTrace();
		}
	}
}
