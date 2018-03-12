package util.mysql.entity.model;

import java.sql.Time;

public class UserDevice {

	private String user_id;
	
	private String device_name;
	
	private String device_version;
	
	private String device_id;
	
	private Time last_login_time;
	
	private String count;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_version() {
		return device_version;
	}

	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public Time getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Time last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}
