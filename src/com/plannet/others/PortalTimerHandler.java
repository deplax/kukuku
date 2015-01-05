package com.plannet.others;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

public class PortalTimerHandler extends Handler {

	private Activity currentActivity;
	private Class<?> targetActivity;

	public PortalTimerHandler(Activity currentActivity, Class<?> targetActivity) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void handleMessage(Message msg) {
		Utilities.moveToAnotherActivity(currentActivity, targetActivity);
	}

	public void execute(int DelayTime) {
		sendEmptyMessageDelayed(0, DelayTime);
	}
}