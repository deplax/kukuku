package com.plannet.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

import com.plannet.listener.SignUpOnClickListener;

public class SignUpActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		ActionBar actionBar = getActionBar();///
        actionBar.hide();///

		Button signUpButton = (Button) findViewById(R.id.signUpButton);
		signUpButton.setOnClickListener(new SignUpOnClickListener(this, SignInActivity.class));
	}
}
