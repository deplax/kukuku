package com.plannet.others;

public class CurrentPageCidStore {
	private static int currentPageCid;

	private CurrentPageCidStore() {
	}

	public static int getCurrentPageCid() {
		return currentPageCid;
	}

	public static void setCurrentPageCid(int cid) {
		currentPageCid = cid;
	}
}