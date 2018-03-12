package ui_app.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SeleniumUtil {
	static Logger log=Logger.getLogger(SeleniumUtil.class);
	
	
	public static boolean findByAccessibilityId(AppiumDriver<WebElement> driver,String className){
		try {
			driver.findElementByAccessibilityId(className);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean findById(AppiumDriver<WebElement> driver,String className){
		try {
			driver.findElementById(className);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean findByName(AppiumDriver<WebElement> driver,String using){
		try {
		driver.findElementByName(using);
		return true;
		} catch (Exception e) {
			return false;
		}
	}
	
/*
	public static WebElement findByClassName(AppiumDriver<WebElement> driver,String className){
		WebElement we=null;
		try {
		we=driver.findElementByClassName(className);
		} catch (Exception e) {
			log.error("获取"+className+"失败"+e.getStackTrace());
			JieTU.snapshot(driver, className+System.currentTimeMillis());
		}
		return we;
	}
	
	public static WebElement findByAccessibilityId(AppiumDriver<WebElement> driver,String using){
		WebElement we=null;
		try {
		we=driver.findElementByAccessibilityId(using);
		} catch (Exception e) {
			log.error("获取"+using+"失败"+e.getStackTrace());
			JieTU.snapshot(driver, using+System.currentTimeMillis());
		}
		return we;
	}
	
	public static WebElement findById(AppiumDriver<WebElement> driver,String id){
		WebElement we=null;
		try {
		we=driver.findElementById(id);
		} catch (Exception e) {
			log.error("获取"+id+"失败"+e.getStackTrace());
			JieTU.snapshot(driver, id+System.currentTimeMillis());
		}
		return we;
	}
	

	
	
	public static WebElement findByTagName(AppiumDriver<WebElement> driver,String using){
		WebElement we=null;
		try {
		we=driver.findElementByTagName(using);
		} catch (Exception e) {
			log.error("获取"+using+"失败"+e.getStackTrace());
			JieTU.snapshot(driver, using+System.currentTimeMillis());
		}
		return we;
	}
	*/
}
