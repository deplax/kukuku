package com.plannet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.plannet.clientdb.PlanDAO;
import com.plannet.http.HttpRequest;
import com.plannet.others.CurrentPageCidStore;
import com.plannet.others.Utilities;

public class EditPlanActivity extends Activity implements OnClickListener {
	private EditText titleEdit;
	private EditText summaryEdit;
	private Button button;

	private int targetPid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_plan);

		targetPid = getIntent().getIntExtra("pid", 0);

		titleEdit = (EditText) findViewById(R.id.add_plan_title_edit);
		summaryEdit = (EditText) findViewById(R.id.add_plan_summary_edit);
		// 원래 있던 걸로 내용 초기화해준다
		titleEdit.setText(getIntent().getStringExtra("title"));
		summaryEdit.setText(getIntent().getStringExtra("summary"));

		button = (Button) findViewById(R.id.add_plan_ok_button);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final int cid = CurrentPageCidStore.getCurrentPageCid();
		final String title = titleEdit.getText().toString();
		final String summary = summaryEdit.getText().toString();

		Log.e("edit plan : ", cid + "   " + title + "   " + summary);

		if (title.isEmpty()) {
			Utilities.toastPopUp(this, "제목을 입력해주세요!");
			return;
		}

		new PlanDAO(this).update(targetPid, title, summary);

		new Thread() {
			public void run() {
				HttpRequest.PushPlan(cid, title, summary); // result 받아와서 처리해줘야 한다
			}
		}.start();

		onBackPressed(); // 뒤로 가기 버튼 누른 것과 같은 효과
	}
}