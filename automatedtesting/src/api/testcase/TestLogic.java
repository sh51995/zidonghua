package api.testcase;

import static org.junit.Assert.assertFalse;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.server.Logic;
import net.sf.json.JSONObject;
import util.httpclient.HTTPClient;
import util.jiami.RSAUtils;
import util.mysql.entity.model.User;
import util.mysql.entity.model.UserDevice;
import util.mysql.server.memberdata.UserDeviceImpl;
import util.mysql.server.memberdata.UserImpl;
@Listeners({testng.GenerateReporter.class})
public class TestLogic {
	static Logger log=Logger.getLogger(TestLogic.class);
	static String mobile="13682506853";
	static User user=UserImpl.getUserByMobile(mobile);
	static String token;
	static UserDevice userdevice= UserDeviceImpl.getUserDeviceByMobile(mobile);
	
	
  @Test(description="正常登陆")
  public void testLogic(){
	
	  Map<String, String> resultmap=Logic.cLogic(mobile,user.getPassword(),userdevice);
	  
	  String Cookie=resultmap.get("Set-Cookie");
	  log.info("=====cookie:"+Cookie);
	  String body=resultmap.get("body");
	  log.info("=====获取请求体为："+body.toString());
	  JSONObject jb1=JSONObject.fromObject(body);
	  String resultlogic=jb1.getString("result");
	  if(!("000000").equals(resultlogic)){
		  log.error("=====返回状态为："+resultlogic);
		  assertFalse(true);
	  }else{
		  log.info("=====登录成功");
	  }
	  
	  String data=jb1.getString("data");
	  
	  JSONObject jb2=JSONObject.fromObject(data);
	  
	  String userId=jb2.getString("userId");
	  
	  if(!userId.equals(user.getUser_id())){
		  log.error("=====userid与数据库不匹配：");
		  log.error("=====返回userid为:"+userId+",数据库userid为:"+user.getUser_id());
		  assertFalse(true);
	  }
	  
	  token=jb2.getString("token");
	  
	  log.info("=====token为:" +token);
	  
	  
	 String infobody= Logic.info(mobile, Cookie, token);
	 
	 log.info("=====info返回："+infobody);
	 
	 JSONObject jb3=JSONObject.fromObject(infobody);
	 
	 String inforesult=jb3.getString("result");
	 
	 if(!("000000").equals(inforesult)){
		 log.error("=====响应为:"+inforesult);
		 assertFalse(true);
	 }
  }
  
  
  
  
  @Test(description="登陆失败，密码错误")
  public void testLogicWrongWithPassWord(){
	
	  Map<String, String> resultmap=Logic.cLogic(mobile,user.getPassword()+"a",userdevice);
	  
	  String body=resultmap.get("body");
	  log.info("=====获取请求体为："+body.toString());
	  JSONObject jb1=JSONObject.fromObject(body);
	  String resultlogic=jb1.getString("result");
	  if(!("100001").equals(resultlogic)){
		  log.error("=====返回状态为："+resultlogic);
		  assertFalse(true);
	  }else{
		  log.info("=====登陆失败，密码错误,验证成功");
	  }
	  
  }
  
  
  @Test(description="登陆失败，手机号未注册")
  public void testLogicWrongWithMobile(){
	
	  Map<String, String> resultmap=Logic.cLogic("147111111",user.getPassword()+"a",userdevice);
	  
	  String body=resultmap.get("body");
	  log.info("=====获取请求体为："+body.toString());
	  JSONObject jb1=JSONObject.fromObject(body); 
	  String resultlogic=jb1.getString("result");
	  if(!("100011").equals(resultlogic)){
		  log.error("=====返回状态为："+resultlogic);
		  assertFalse(true);
	  }else{
		  log.info("=====登陆失败，手机号未注册,验证成功");
	  }  
  }
  
  
}
