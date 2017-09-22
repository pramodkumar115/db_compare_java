package com.dbcompare.domain;

public class DBConfig {

	private String name;
	
	private String dbCustomName;
	
    private String host;
    
    private String serviceName;
    
    private String port;
    
    private String sid;
    
    private String userName;
    
    private String password;
    
    private String dbOwner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDbCustomName() {
		return dbCustomName;
	}

	public void setDbCustomName(String dbCustomName) {
		this.dbCustomName = dbCustomName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

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

	/**
	 * @return the dbOwner
	 */
	public String getDbOwner() {
		return dbOwner;
	}

	/**
	 * @param dbOwner the dbOwner to set
	 */
	public void setDbOwner(String dbOwner) {
		this.dbOwner = dbOwner;
	}
    
}
