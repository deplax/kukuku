package com.plannet.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;

import com.plannet.listener.SignInOnClickListener;
import com.plannet.others.Utilities;

public class SignInActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		ActionBar actionBar = getActionBar();///
        actionBar.hide();///

		Button signInButton = (Button) findViewById(R.id.signInButton);
		signInButton.setOnClickListener(new SignInOnClickListener(this, MyPlanActivity.class));
		Utilities.addPortalToButton(findViewById(R.id.signUpPortalButton), this, SignUpActivity.class);
	}
}