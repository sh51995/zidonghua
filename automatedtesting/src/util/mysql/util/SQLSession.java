package util.mysql.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import util.mysql.entity.model.User;


public class SQLSession {
	static Logger log=Logger.getLogger(SQLSession.class);
	public static SqlSession getSqlSession(String config){
		SqlSession session=null;
		try {
			 InputStream is = SQLSession.class.getClassLoader().getResourceAsStream(config);
		        

		        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		        session = sessionFactory.openSession();
		} catch (Exception e) {
		log.error(e);
		}
		return session;
	}
	

}
