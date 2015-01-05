package com.plannet.model;

public class User {
	private int uid;
	private String email;
	private String name;
	private String pw;

	public User(String email, String name, String pw) {
		this.email = email;
		this.name = name;
		this.pw = pw;
	}

	public User(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
}