package com.plannet.listener;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.plannet.activity.R;
import com.plannet.clientdb.uuidDAO;
import com.plannet.http.HttpRequest;
import com.plannet.others.Utilities;

public class SignInOnClickListener implements OnClickListener {

	private Activity currentActivity;
	private Class<?> targetActivity;
	private String[] response;
	private String uuid;
	private String result;

	public SignInOnClickListener(Activity currentActivity, Class<?> targetActivity) {
		this.currentActivity = currentActivity;
		this.targetActivity = targetActivity;
	}

	@Override
	public void onClick(View v) {
		final String email = ((EditText) currentActivity.findViewById(R.id.emailEdit)).getText().toString();
		final String password = ((EditText) currentActivity.findViewById(R.id.passwordEdit)).getText().toString();

		Thread thread = new Thread() {
			public void run() {
				response = HttpRequest.SignIn(email, password);
			};
		};
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result = response[0];
		uuid = response[1];

		if (result.equals("Success")) {
			new uuidDAO(currentActivity).delete();
			new uuidDAO(currentActivity).insert(uuid);
			Utilities.moveToAnotherActivity(currentActivity, targetActivity);
		} else if (result.equals("EmailNotFound")) {
			Utilities.toastPopUp(currentActivity, "등록되지 않은 이메일입니다!");
		} else if (result.equals("PasswordDismatch")) {
			Utilities.toastPopUp(currentActivity, "비밀번호를 다시 입력해주세요!");
		} else if (result.equals("EmailNotVerified")) {
			Utilities.toastPopUp(currentActivity, "이메일 인증이 완료되지 않았습니다!");
		} else {
			Utilities.toastPopUp(currentActivity, "알 수 없는 오류입니다!");
		}
	}
}