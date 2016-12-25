package org.takastudy.tech.sample.model;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
	
	private String userId;
	private String name;
	
	private String passwordDigest;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordDigest() {
		return passwordDigest;
	}

	public void setPasswordDigest(String password) {
		
		this.passwordDigest = convertPasswordDigest(password);
	}
	
	public static String convertPasswordDigest(String password){
		
		return DigestUtils.sha256Hex(password);
		
	}
	
	

}
