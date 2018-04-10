package ui_app.server.sales;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import ui_app.server.GetAppiumDriver;

public class SalesApp {
	static Logger log=Logger.getLogger(SalesApp.class);
	public static AppiumDriver<WebElement> getSalesDriver(AppiumDriver<WebElement> driver){
		return GetAppiumDriver.getDriver(driver, "com.gl365.android.sale", "com.gl365.android.sale.MainActivity");
	}
}
