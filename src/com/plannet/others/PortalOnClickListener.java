package com.plannet.others;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class PortalOnClickListener implements OnClickListener {

	private Activity currentActivity;
	private Class<?> targetActivity;
	private Object[] putExtras;

	public PortalOnClickListener(Activity currentActivity, Class<?> targetActivity, Object ... putExtras) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
		this.putExtras = putExtras;
	}

	@Override
	public void onClick(View v) {
		Utilities.moveToAnotherActivity(currentActivity, targetActivity, putExtras);
	}
}