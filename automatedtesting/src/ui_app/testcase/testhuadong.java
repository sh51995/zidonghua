package ui_app.testcase;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import ui_app.server.cfront.CfrontApp;

public class testhuadong {
	static AppiumDriver<WebElement> driver;
	 @Test
	  public void testCforntLogin() {
		 try {
			 driver=CfrontApp.getCfrontDriver(driver);
			 Thread.sleep(5000);
			 int width = driver.manage().window().getSize().width;  
			  int height = driver.manage().window().getSize().height;
			 driver.swipe(width / 2,height * 3 / 4, width / 2, height / 4, 500);
			 Thread.sleep(5000);
			 driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
				 
	}
}
