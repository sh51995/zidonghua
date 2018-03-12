package util.mysql.server.memberdata;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.mysql.entity.model.User;
import util.mysql.util.SQLSession;

public class MemberSession {
	static Logger log=Logger.getLogger(MemberSession.class);
	public static SqlSession getMemberSession(){
		SqlSession session=SQLSession.getSqlSession("util/mysql/config/member_data.xml");
		return session;
	}
	
	
	public static void main(String[] args) {
		SqlSession session=MemberSession.getMemberSession();
		User u=session.selectOne("util.mysql.entity.mapping.userMapper.getUserByMobile","13682506853");
		System.out.println(u.getUser_id());
	}
}
