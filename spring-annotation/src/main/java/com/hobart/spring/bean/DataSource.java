package com.hobart.spring.bean;

/**
 * 数据源类
 * @author hobart
 *
 */
public class DataSource {
	private String userName;
	private String password;
	private String url;
	private String driverClass;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	@Override
	public String toString() {
		return "DataSource [userName=" + userName + ", password=" + password + ", url=" + url + ", driverClass="
				+ driverClass + "]";
	}
}
