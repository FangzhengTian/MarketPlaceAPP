package cmu.cap.appactivities;

import java.util.ArrayList;

import cmu.cap.appnetwork.CreateAccountNetwork;
import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class CreateAccountPage3Activity extends Activity {

	private CheckBox ckagree;
	private TextView ckerror;
	private ArrayList<String> mValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account_page3);
		TextView agreement = (TextView) findViewById(R.id.agreement);
		agreement.setMovementMethod(new ScrollingMovementMethod());

		ckagree = (CheckBox) findViewById(R.id.agree_checkBox);
		ckerror = (TextView) findViewById(R.id.check_error);
		mValues = getIntent().getStringArrayListExtra("VALUES");

		final CreateAccountPage3Activity ca = this;
		Button createbtn = (Button) findViewById(R.id.create_button);
		createbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				if (ckagree.isChecked()) {
					CreateAccountNetwork help = new CreateAccountNetwork();
					help.create(mValues, ca);
				} else {
					ckerror.setText("Please check agree to create account");
				}
			}
		});
	}

	public void createAccountReady(String result) {
		ckerror = (TextView) findViewById(R.id.check_error);
		if (result == "") {
			ckerror.setText(result);
			Intent i = new Intent(CreateAccountPage3Activity.this,
					CreateAccountPage4Activity.class);
			startActivity(i);
		} else {
			ckerror.setText(result);
			Intent i = new Intent(CreateAccountPage3Activity.this,
					CreateAccountPage1Activity.class);
			startActivity(i);
		}
	}

	public void goBack(View view) {
		finish();
	}
}
