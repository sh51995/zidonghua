package ui_app.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import ui_app.util.AppiumUtil;

public class GetAppiumDriver {
	static Logger log=Logger.getLogger(GetAppiumDriver.class);
	static String deviceName=AppiumUtil.getDevices();
	public static AppiumDriver<WebElement> getDriver(AppiumDriver<WebElement> driver,String appPackage,String appActivity){
		DesiredCapabilities capabilities = new DesiredCapabilities();  
		     capabilities.setCapability("deviceName",deviceName); 
		       
		        capabilities.setCapability("automationName","Appium");  
		        capabilities.setCapability("platformName","Android");  
		        capabilities.setCapability("platformVersion","23");  
		          
		       //配置测试apk  
		        capabilities.setCapability("appPackage", appPackage);  
		        capabilities.setCapability("appActivity", appActivity);  
		        capabilities.setCapability("sessionOverride", true);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session  
		        capabilities.setCapability("unicodeKeyboard", true);    //设置键盘  
		        capabilities.setCapability("resetKeyboard", false);     //设置默认键盘为appium的键盘  
		        try {
					driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities) ;
				} catch (MalformedURLException e) {
					log.error(e.getStackTrace());
				}
		        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);  
		       return driver; 
	}
}
