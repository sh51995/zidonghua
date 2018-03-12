package api.testcase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.server.Card;
import api.server.Logic;
import net.sf.json.JSONObject;
import util.mysql.entity.model.User;
import util.mysql.entity.model.UserDevice;
import util.mysql.server.memberdata.UserDeviceImpl;
import util.mysql.server.memberdata.UserImpl;
@Listeners({testng.GenerateReporter.class})
public class TestCard {
	static Logger log=Logger.getLogger(TestCard.class);
	static String mobile="13682506853";
	static User user=UserImpl.getUserByMobile(mobile);
	static String token;
	static UserDevice userdevice= UserDeviceImpl.getUserDeviceByMobile(mobile);
	
	@Test(description="查询绑卡")
	public void testCard(){
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
		  
		  
		  String resultbody=Card.getCard(token, Cookie, userdevice);
		  
		  
		  log.info("=====查询绑卡返回为:"+resultbody);
		  
		  JSONObject jo=JSONObject.fromObject(resultbody);
		  
		  String result=jo.getString("result");
		  
		  if(result.equals("000000")){
			  assertTrue(true);
		  }else{
			  log.error("=====响应为："+result);
			  assertFalse(true);
		  }
	}
}
