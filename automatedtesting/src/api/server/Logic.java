package api.server;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import api.util.Config;
import net.sf.json.JSONObject;
import util.httpclient.HTTPClient;
import util.jiami.RSAUtils;
import util.mysql.entity.model.User;
import util.mysql.entity.model.UserDevice;
import util.mysql.server.memberdata.UserDeviceImpl;
import util.mysql.server.memberdata.UserImpl;

public class Logic {
	
	static Logger log=Logger.getLogger(Logic.class);
	static String GL_CLIENT_VER=Config.getCfronURL("GL_CLIENT_VER");
	static String GL_TIMESTAMP = "1496726854";//时间戳
	static String GL_CLIENT_ID="glLive.app.ios";
	static String ContentType="application/json;charset=UTF-8";
	private static String loginurl=Config.getCfronURL("cfronurl")+"/lifeAPI/login";
	private static String infourl=Config.getCfronURL("cfronurl")+"/lifeAPI/user/info";

	
	/**
	 * C端登录，获取get-cookie和响应体
	 * @param mobile
	 * @param password
	 * @return
	 */
	public static Map<String,String> cLogic(String mobile,String password,UserDevice userdevice){
		 User user=UserImpl.getUserByMobile(mobile);
		 String  GL_SALT_MD5_KEY = userdevice.getDevice_id() +GL_CLIENT_ID + GL_CLIENT_VER + GL_TIMESTAMP+mobile+password;
		  String sign=null;
		  try {
			sign = RSAUtils.sign(GL_SALT_MD5_KEY.getBytes());
			sign=URLEncoder.encode(sign, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  Map<String, String> header=new HashMap<String,String>();
		  header.put("GL_CLIENT_ID",GL_CLIENT_ID);
		  header.put("GL_DEVICE_ID", userdevice.getDevice_id());
		  header.put("GL_CLIENT_VER", GL_CLIENT_VER);
		  header.put("GL_TIMESTAMP", GL_TIMESTAMP);
		  header.put("Content-Type", ContentType);
		  header.put("GL_REQ_SIGN",sign);
		  
		  JSONObject jb=new JSONObject();
		  jb.element("deviceName", userdevice.getDevice_name());
		  jb.element("deviceVersion", userdevice.getDevice_version());
		  jb.element("username", mobile);
		  jb.element("password", password);
		  log.info("=====拼接请求体："+jb.toString());
		  
		  List<String> list=new ArrayList<String>();
		  list.add("Set-Cookie");
		  Map<String, String> resultmap=HTTPClient.post(loginurl,header, jb.toString(), list);
		  return resultmap;
	}
	
	

	
	
	
	
	
	
	
	public static String info(String mobile,String cookie,String token){
		 UserDevice userdevice= UserDeviceImpl.getUserDeviceByMobile(mobile);
		 User user=UserImpl.getUserByMobile(mobile);
		 String  GL_SALT_MD5_KEY = token+ userdevice.getDevice_id() +GL_CLIENT_ID + GL_CLIENT_VER + GL_TIMESTAMP;
		  String sign=null;
		  try {
			sign = RSAUtils.sign(GL_SALT_MD5_KEY.getBytes());
			sign=URLEncoder.encode(sign, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  Map<String, String> header=new HashMap<String,String>();
		  header.put("GL_CLIENT_ID",GL_CLIENT_ID);
		  header.put("GL_DEVICE_ID", userdevice.getDevice_id());
		  header.put("GL_CLIENT_VER", GL_CLIENT_VER);
		  header.put("GL_TIMESTAMP", GL_TIMESTAMP);
		  header.put("Content-Type", ContentType);
		  header.put("GL_TOKEN",token);
		  header.put("Cookie",cookie);
		  header.put("GL_REQ_SIGN",sign);
		  
		
		  String resultmap=HTTPClient.doget(infourl,header);
		  return resultmap;
	}
}
