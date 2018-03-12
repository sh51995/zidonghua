package util.mysql.server.accountdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.mysql.server.memberdata.MemberSession;

public class AccountImpl {
	static Logger log=Logger.getLogger(AccountImpl.class);
	
	public static String getBalanceByUseId(String user_id){
		log.info("=====进入getBalanceByUseId");
		log.info("=====user_id为："+user_id);
		try {
			SqlSession session=AccountSession.getAccountSession();
			String balance=session.selectOne("util.mysql.entity.mapping.account.getBalance",user_id);
			return balance;
		} catch (Exception e) {
			log.error(e);;
			return null;
		}
		
	}

	public static double getBalanceByMobile(String Mobile){
		log.info("=====进入getBalanceByMobile");
		log.info("===== mobile为："+Mobile);
		try {
			
			SqlSession membersession=MemberSession.getMemberSession();
			String userid=membersession.selectOne("util.mysql.entity.mapping.userMapper.getUserIdByMobile", Mobile);
			SqlSession session=AccountSession.getAccountSession();
			double balance=session.selectOne("util.mysql.entity.mapping.account.getBalance",userid);
			return balance;
		} catch (Exception e) {
			log.error(e);
			return 0;
		}
		
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("fsfs");
		list.add("abc");
		list.add("ffdes");
		System.out.println(list.contains(""));
	}
}
