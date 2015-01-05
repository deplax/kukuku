package com.plannet.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.plannet.clientdb.uuidDAO;
import com.plannet.http.HttpRequest;
import com.plannet.others.PortalTimerHandler;
import com.plannet.others.Utilities;

public class MainActivity extends ActionBarActivity {
	private String response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();///
        actionBar.hide();///

		Utilities.moveToAnotherActivity(this, MyPlanActivity.class);

		// ////////////////////////
		// 여기서부터 uuid로 로그인, 저장된 것이 없거나 실패시 sign in으로 이동
		// ////////////////////////

		final String uuid = new uuidDAO(this).select();
		Log.e("uuid", "현재 uuid : " + uuid);

		// "default"는 DBOpenHelper.onCreate() 참고. 처음 설치될 때 이 값으로 초기화 된다.
		if (uuid.equals("default")) {
			Log.e("uuid", "정보 없음 : SignInActivity로 이동함");
			PortalTimerHandler handler = new PortalTimerHandler(this, SignInActivity.class);
			handler.execute(3000);
		} else {
			// uuid로 http post 요청, 서버에서 응답값 받아와서 result에 저장
			Thread thread = new Thread() {
				public void run() {
					response = HttpRequest.UUIDSignIn(uuid);
				}
			};

			thread.start();
			try {
				thread.join(); // 요청 완료될 때까지 기다려준다
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (response.equals("UUIDNotFound")) {
				// 응답값이 UUIDNotFound인 경우엔 SignIn으로 보내줌
				PortalTimerHandler handler = new PortalTimerHandler(this, SignInActivity.class);
				handler.execute(3000);
			} else if (response.equals("Success")) {
				// 응답값이 "Success"인 경우엔 MyPlan으로 보내줌
				PortalTimerHandler handler = new PortalTimerHandler(this, MyPlanActivity.class);
				handler.execute(3000);
			}
		}
	}
}
