package util.mysql.server.memberdata;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import util.mysql.entity.model.User;
import util.mysql.entity.model.UserDevice;

public class UserDeviceImpl {
	static Logger log=Logger.getLogger(UserDeviceImpl.class);
	public static UserDevice getUserDeviceByUserId(String user_id){
		log.info("=====进入getUserDevice");
		log.info("=====user_id为: "+user_id);
		SqlSession session=MemberSession.getMemberSession();
		
		UserDevice ud=session.selectOne("util.mysql.entity.mapping.userDeviceMapper.getUserDeviceByuserid",user_id);
		return ud;
		
	}
	
	
	public static UserDevice getUserDeviceByMobile(String mobile){
		log.info("=====进入getUserDevice");
		log.info("=====mobile为: "+mobile);
		try {
		SqlSession session=MemberSession.getMemberSession();
		User user=session.selectOne("util.mysql.entity.mapping.userMapper.getUserByMobile",mobile);
		UserDevice ud=(UserDevice) session.selectList("util.mysql.entity.mapping.userDeviceMapper.getUserDeviceByuserid",user.getUser_id()).get(0);
		return ud;
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	


}
