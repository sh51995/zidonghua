package util.mysql.server.accountdata;

import org.apache.ibatis.session.SqlSession;

import util.mysql.util.SQLSession;

public class AccountSession {
	public static SqlSession getAccountSession(){
		SqlSession session=SQLSession.getSqlSession("util/mysql/config/account_data.xml");
		return session;
	}
}
