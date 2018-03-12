package util.mysql.server.memberdata;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.mysql.entity.model.User;

public class UserImpl {
	static Logger log=Logger.getLogger(UserImpl.class);
	public static User getUserByMobile(String mobile){
		log.info("=====进入getUserDevice");
		log.info("=====mobile为: "+mobile);
		SqlSession session=MemberSession.getMemberSession();
		
		User user=session.selectOne("util.mysql.entity.mapping.userMapper.getUserByMobile",mobile);
		return user;
		
	}
}
