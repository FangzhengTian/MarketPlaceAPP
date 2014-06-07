package cmu.cap.appactivities;

import java.util.ArrayList;

import cmu.cap.appnetwork.ResetPasswordNetwork;
import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPasswordPage3Activity extends Activity {

	private TextView pwd_error;
	private EditText inputpwd1;
	private EditText inputpwd2;
	private ArrayList<String> mValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password_page3);

		final ForgetPasswordPage3Activity fp = this;
		Button submitbtn = (Button) findViewById(R.id.submit_button);
		submitbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				inputpwd1 = (EditText) findViewById(R.id.pwd1_reset);
				inputpwd2 = (EditText) findViewById(R.id.pwd2_reset);
				String pwd1 = inputpwd1.getText().toString();
				String pwd2 = inputpwd2.getText().toString();
				if (pwd1.equals(pwd2)) {
					mValues = getIntent().getStringArrayListExtra("VALUES");
					ResetPasswordNetwork help = new ResetPasswordNetwork();
					help.reset(mValues.get(0), pwd1, fp);
				} else {
					pwd_error = (TextView) findViewById(R.id.pwd_error);
					pwd_error.setText("The passwords are not the same!");
				}
			}
		});
	}

	public void resetReady(String result){
		pwd_error = (TextView) findViewById(R.id.pwd_error);
		if (result.equals("false")) {
			pwd_error.setText("reset failed!");
		} else {
			pwd_error.setText("reset finished!");
			Intent i = new Intent(ForgetPasswordPage3Activity.this, LoginActivity.class);
			startActivity(i);
		}
	}
	
	public void goback(View view) {
		finish();
	}
}
