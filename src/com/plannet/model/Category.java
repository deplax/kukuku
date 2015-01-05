package com.plannet.model;

public class Category {
	private int cid;
	private int uid;
	private String name;

	public Category(int cid, int uid, String name) {
		this.cid = cid;
		this.uid = uid;
		this.name = name;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
