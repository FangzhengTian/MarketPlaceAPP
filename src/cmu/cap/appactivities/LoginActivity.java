package cmu.cap.appactivities;

import cmu.cap.marketplaceapp.R;
import cmu.cap.appnetwork.LoginNetwork;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class LoginActivity extends Activity {

	private EditText inputUserID;
	private EditText inputPassword;
	private TextView login_error;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final LoginActivity lg = this;
		Button loginbtn = (Button) findViewById(R.id.login_button);
		loginbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				inputUserID = (EditText) findViewById(R.id.user_id);
				inputPassword = (EditText) findViewById(R.id.loginPassword);
				String userid = inputUserID.getText().toString();
				String password = inputPassword.getText().toString();
				LoginNetwork help = new LoginNetwork();
				help.login(userid, password, lg);
			}
		});
	}

	public void loginReady(String result) {
		login_error = (TextView) findViewById(R.id.login_error);
		if (result == "") {
			login_error.setText(result);
			inputUserID = (EditText) findViewById(R.id.user_id);
			String userid = inputUserID.getText().toString();
			Intent i = new Intent(LoginActivity.this,
					SuccessfulLoginActivity.class);
			i.putExtra("USERID", userid);
			startActivity(i);
		} else {
			login_error.setText(result);
		}
	}

	public void goBack(View view) {
		finish();
	}

	public void resetPassword(View view) {
		Intent i = new Intent(getApplicationContext(),
				ForgetPasswordPage1Activity.class);
		startActivity(i);
	}
	
	public void onResume(){
		super.onResume();
		login_error = (TextView) findViewById(R.id.login_error);
		login_error.setText("");
	}
}
