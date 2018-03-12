package ui_app.server.merchant;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import api.server.LogicB;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ui_app.util.DingDing;
import ui_app.util.SeleniumUtil;
import util.mysql.entity.model.UserDevice;

public class MerchantApp {
	static Logger log=Logger.getLogger(MerchantApp.class);
	public static AppiumDriver<WebElement> getMerchantDriver(AppiumDriver<WebElement> driver){
	     DesiredCapabilities capabilities = new DesiredCapabilities();  
	 //       capabilities.setCapability("deviceName","0123456789ABCDEF");  
	  //      capabilities.setCapability("deviceName","b2bcc292");  
	   //  	capabilities.setCapability("deviceName","JJRDU16B08015928");  
	     	capabilities.setCapability("deviceName","A10ABN5GQRHS"); 
	        capabilities.setCapability("automationName","Appium");  
	        capabilities.setCapability("platformName","Android");  
	        capabilities.setCapability("platformVersion","23");  
	          
	       //配置测试apk  
	        capabilities.setCapability("appPackage", "com.gl365.android.merchant");  
	        capabilities.setCapability("appActivity", "com.gl365.android.merchant.MainActivity");  
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
	
	
	
	
	public static boolean login(AppiumDriver<WebElement> driver){
		try {
			boolean bool;
		//	 driver.findElementsByClassName("android.widget.EditText").get(0).clear();
		//	  driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("231000143");
			  driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("a12345678");
		//	  driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys("121000031");
		//	  driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys("a12345678");
			  driver.findElementByAccessibilityId("登 录").click();
			  bool=SeleniumUtil.findByAccessibilityId(driver, "众乐科技");
		//	  bool=SeleniumUtil.findByAccessibilityId(driver, "湖南人家8888加长我俺看你发的");
			  return bool;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	public static boolean logout(AppiumDriver<WebElement> driver){
		try {
			boolean bool=false;
			if(SeleniumUtil.findByAccessibilityId(driver, "我的")){
			  driver.findElementByAccessibilityId("我的").click(); 
			  driver.findElementByAccessibilityId("设置").click();
			  Thread.sleep(3000);
			  driver.tap(1, 600, 1850, 0);
			  driver.findElementByAccessibilityId("确定 ").click();
			  bool=SeleniumUtil.findByAccessibilityId(driver, "登 录");
			  
			}else{
				bool=true;
			}
			return bool;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	
	public static boolean tuikuan(){
		
		boolean bool=false;
		try {
			
		
		UserDevice userdevice=new UserDevice();
		userdevice.setDevice_id("863604034797071");
		userdevice.setDevice_name("BLN-AL10");
		userdevice.setDevice_version("6.2.3");
		String token;
		String username ="231000143" ;
		String password ="983692abba1437188b734e566386ad6c";
		Map<String, String> resultmap=LogicB.cLogic(username, password, userdevice);
		  String Cookie=resultmap.get("Set-Cookie");
		  log.info("=====cookie:"+Cookie);
		  String body=resultmap.get("body");
		  log.info("=====获取请求体为："+body.toString());
		  JSONObject jb1=JSONObject.fromObject(body);
		  String resultlogic=jb1.getString("result");
		  if(("000000").equals(resultlogic)){
			  System.out.println("进入===");
			  
			  String data=jb1.getString("data");
			  
			  JSONObject jb2=JSONObject.fromObject(data);
			  
			  token=jb2.getString("token");
			  String infobody=LogicB.orderurl(Cookie, token, userdevice);
			  System.out.println("infobody:"+infobody);
			  JSONObject jb3=JSONObject.fromObject(infobody);
				 
			  String data1=jb3.getString("data");
			  
			  JSONObject jb4=JSONObject.fromObject(data1);
			  
			  JSONArray list=jb4.getJSONArray("list");
			  
			  JSONObject jb5=list.getJSONObject(0);
			  
			  String status=jb5.getString("status");
			  
			  System.out.println("status:"+status);
			  
			  if(status.equals("已付款")){
			  String orderId=jb5.getString("orderId");
			  
			  String payId=jb5.getString("payId");
			  
			  String ss=LogicB.refund(Cookie, token, userdevice, orderId, payId);
			  
			  JSONObject jb6=JSONObject.fromObject(ss);
			  
			  String description=jb6.getString("description");
			  System.out.println("description"+description);
			  
			  if(description.equals("发起撤单成功")){
				  bool=true;
			  }
			  }else{
				  System.out.println("无可撤单订单");
				  bool=true;
			  }
		  }
		  return bool;
		} catch (Exception e) {
			return bool;
		}
		
	}
	
	public static void main(String[] args) {
		 tuikuan();
	}
	public static boolean singleBack(AppiumDriver<WebElement> driver){
		try {
			Thread.sleep(6000);
			boolean bool=false;
			driver.findElementByAccessibilityId("账单").click();
			if(SeleniumUtil.findByAccessibilityId(driver, "微信收款未结算")){
			driver.findElementByAccessibilityId("微信收款未结算").click();
			if(SeleniumUtil.findByAccessibilityId(driver, "撤单")){
				driver.findElementByAccessibilityId("撤单").click();
				for(int i=0;i<5;i++){
					if(SeleniumUtil.findByAccessibilityId(driver, "验证")){
						driver.findElementByClassName("android.widget.EditText").sendKeys("a12345678");
						driver.findElementByAccessibilityId("验证").click();
						if(SeleniumUtil.findByAccessibilityId(driver, "发起撤单成功")){
							bool=true;
							Thread.sleep(5000);
							driver.findElementByAccessibilityId("提示").click();
						}
						break;
					}
				}	
				}else{
					bool=true;
				}
			if(SeleniumUtil.findByAccessibilityId(driver, "账单详情")){
			 Thread.sleep(5000);
			 driver.tap(1, 60, 140, 0);
			}
			}else{
				bool=true;
			}
			return bool;
		} catch (Exception e) {
			return false;
		}
	}
}
