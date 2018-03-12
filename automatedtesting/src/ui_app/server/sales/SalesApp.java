package ui_app.server.sales;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SalesApp {
	static Logger log=Logger.getLogger(SalesApp.class);
	public static AppiumDriver<WebElement> getSalesDriver(AppiumDriver<WebElement> driver){
	     DesiredCapabilities capabilities = new DesiredCapabilities();  
	    //    capabilities.setCapability("deviceName","0123456789ABCDEF");  
	   //  	capabilities.setCapability("deviceName","JJRDU16B08015928");  
	     	capabilities.setCapability("deviceName","A10ABN5GQRHS"); 
	        capabilities.setCapability("automationName","Appium");  
	        capabilities.setCapability("platformName","Android");  
	        capabilities.setCapability("platformVersion","23");  
	          
	       //配置测试apk  
	        capabilities.setCapability("appPackage", "com.gl365.android.sale");  
	        capabilities.setCapability("appActivity", "com.gl365.android.sale.MainActivity");  
	        capabilities.setCapability("sessionOverride", true);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session  
	        capabilities.setCapability("unicodeKeyboard", true);    //设置键盘  
	        capabilities.setCapability("resetKeyboard", false);     //设置默认键盘为appium的键盘  
	        try {
				driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities) ;
			} catch (MalformedURLException e) {
				log.error(e.getStackTrace());
			}
	        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);  
	       return driver; 
	}
}
