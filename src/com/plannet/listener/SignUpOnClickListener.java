package com.plannet.listener;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.plannet.activity.R;
import com.plannet.http.HttpRequest;
import com.plannet.others.PortalTimerHandler;
import com.plannet.others.Utilities;

public class SignUpOnClickListener implements OnClickListener {
	private Activity currentActivity;
	private Class<?> targetActivity;
	private String result;

	public SignUpOnClickListener(Activity currentActivity, Class<?> targetActivity) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		final String email = ((EditText) currentActivity.findViewById(R.id.email)).getText().toString();
		final String name = ((EditText) currentActivity.findViewById(R.id.name)).getText().toString();
		final String password = ((EditText) currentActivity.findViewById(R.id.password)).getText().toString();
		String passwordCheck = ((EditText) currentActivity.findViewById(R.id.passwordCheck)).getText().toString();

		if (!Utilities.isValidEmail(email)) {
			Utilities.toastPopUp(currentActivity, "이메일 형식이 올바르지 않습니다!");
			return;
		}

		if (!password.equals(passwordCheck)) {
			Utilities.toastPopUp(currentActivity, "패스워드가 일치하지 않습니다!");
			return;
		}

		if (email.equals("") || name.equals("") || password.equals("") || passwordCheck.equals("")) {
			Utilities.toastPopUp(currentActivity, "입력을 완성해주세요!");
			return;
		}

		Thread thread = new Thread() {
			public void run() {
				result = HttpRequest.SignUp(email, name, password);
				Log.e("SignUp", result);
			};
		};
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (result.equals("EmailOverlap")) {
			Utilities.toastPopUp(currentActivity, "이미 존재하는 이메일입니다!");
			return;
		}

		if (result.equals("Success")) {
			Utilities.toastPopUp(currentActivity, "회원가입을 축하합니다!");
			PortalTimerHandler handler = new PortalTimerHandler(currentActivity, targetActivity);
			handler.execute(3000);
			return;
		}
	}
}