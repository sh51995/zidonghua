package api.testcase;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import api.server.Happycoin;
import api.server.Logic;
import net.sf.json.JSONObject;
import util.mysql.entity.model.User;
import util.mysql.entity.model.UserDevice;
import util.mysql.server.accountdata.AccountImpl;
import util.mysql.server.memberdata.UserDeviceImpl;
import util.mysql.server.memberdata.UserImpl;
@Listeners({testng.GenerateReporter.class})
public class TestHappycoin {
	static Logger log=Logger.getLogger(TestHappycoin.class);
	static String mobile="13682506855";
	static User user=UserImpl.getUserByMobile(mobile);
	static String token;
	static UserDevice userdevice= UserDeviceImpl.getUserDeviceByMobile(mobile);
	
	@Test(description="查询乐豆余额")
	public void testHappycoin(){
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
		  
		  String resultbody=Happycoin.getHappycoin(token, Cookie, userdevice);
		  
		  log.info("=====获取乐豆返回为:"+resultbody);
		  
		  JSONObject jo=JSONObject.fromObject(resultbody);
		  
		  String result=jo.getString("result");
		  
		  if(result.equals("000000")){
			  assertTrue(true);
			  JSONObject data1=JSONObject.fromObject(jo.getString("data"));
			  System.out.println(data1);
			  double amount=0.0;
			  String amount1=data1.get("amount").toString();
			  
			  if(amount1.equals("0")){
				  amount=0.0;
			  }else{
			 amount=(double) data1.get("amount");
			 }
			  //	  String amount=data1.get("amount").toString();
			  System.out.println(amount);
			  log.info("=====接口返回乐豆余额为："+amount);
			  double balance=AccountImpl.getBalanceByMobile(mobile);
			  log.info("=====数据库乐豆余额为:" +balance);
			  if(amount!=balance){
				  assertFalse(true);
			  }else{
				  assertTrue(true);
				  log.info("=====接口返回与数据库值匹配");
			  }
			  
		  }else{
			  assertFalse(true);
		  }
		 
	}
}
