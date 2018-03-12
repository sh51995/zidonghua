package api.server;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public final class LogicB {
	static Logger log=Logger.getLogger(LogicB.class);
	static String GL_CLIENT_VER=Config.getCfronURL("GL_CLIENT_VER");
	static String GL_TIMESTAMP = "1496726854";//时间戳
	static String GL_CLIENT_ID="glLive.app.ios";
	static String ContentType="application/json;charset=UTF-8";
//	private static String loginurl=Config.getCfronURL("Bfronurl")+"/merchantAPI/login";
//	private static String infourl=Config.getCfronURL("Bfronurl")+"/lifeAPI/user/info";
	private static String orderurl="https://bapp.365gl.com//merchantAPI/order/query/list";
	private static String loginurl="https://bapp.365gl.com/merchantAPI/login";
	
	private static String refund="https://bapp.365gl.com/merchantAPI/order/refund";

	
	
	
	public static String refund(String cookie,String token, UserDevice userdevice,String orderId,String payId){
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
		  
		  JSONObject  body=new JSONObject();
		  body.put("orderId", orderId);
		  body.put("payId", payId);
		  body.put("password", "983692abba1437188b734e566386ad6c");
		  body.put("apiVersion", "V1.1.0");
		  String resultmap=HTTPClient.dopost(refund,header,body.toString());
		  return resultmap;
	}
	
	
	
	/**
	 * C端登录，获取get-cookie和响应体
	 * @param mobile
	 * @param password
	 * @return
	 */
	
	
	public static Map<String,String> cLogic(String mobile,String password,UserDevice userdevice){
	//	 User user=UserImpl.getUserByMobile(mobile);
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
	
	
	public static String orderurl(String cookie,String token, UserDevice userdevice){
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
		  JSONObject  body=new JSONObject();
		  body.element("curPage", 1);
		  body.element("pageSize", 10);
		  body.element("status", 0);
		  
		  body.element("totalFlag", 1);
		  
		  Date now = new Date(); 
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		  String date = dateFormat.format( now ); 
		  
		  body.element("endDate", date);
		  body.element("beginDate", date);
		  
		
		  String resultmap=HTTPClient.dopost(orderurl, header, body.toString());
		  return resultmap;
	}
}
