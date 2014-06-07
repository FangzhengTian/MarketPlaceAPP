package cmu.cap.appactivities;

import java.util.ArrayList;

import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateAccountPage2Activity extends Activity {

	private EditText inputuserid;
	private EditText inputpassword;
	private EditText inputrepassword;
	private EditText inputemail;
	private TextView error_msg;
	private Spinner inputsq1;
	private EditText inputsa1;
	private Spinner inputsq2;
	private EditText inputsa2;
	private ArrayList<String> mValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account_page2);
	}

	public void nextPage(View view) {
		mValues = getIntent().getStringArrayListExtra("VALUES");
		error_msg = (TextView) findViewById(R.id.create_error);
		inputuserid = (EditText) findViewById(R.id.userID);
		inputpassword = (EditText) findViewById(R.id.firstPassword);
		inputrepassword = (EditText) findViewById(R.id.retypePassword);
		inputemail = (EditText) findViewById(R.id.email);
		inputsq1 = (Spinner) findViewById(R.id.sq1);
		inputsa1 = (EditText) findViewById(R.id.sa1);
		inputsq2 = (Spinner) findViewById(R.id.sq2);
		inputsa2 = (EditText) findViewById(R.id.sa2);
		String userid = inputuserid.getText().toString();
		String password = inputpassword.getText().toString();
		String repassword = inputrepassword.getText().toString();
		String sa1 = inputsa1.getText().toString();
		String sa2 = inputsa2.getText().toString();

		if (!password.equals(repassword)) {
			error_msg.setText("Passwords are not the same!");
			inputpassword.setText("");
			inputrepassword.setText("");
		} else if(userid.equals("") || sa1.equals("") || sa2.equals("") || password.equals("")){
			error_msg.setText("Please input userid or answer the questions!");
		}
		else {
			mValues.add(userid);
			mValues.add(inputpassword.getText().toString());
			mValues.add(inputemail.getText().toString());
			mValues.add(inputsq1.getSelectedItem().toString());
			mValues.add(sa1);
			mValues.add(inputsq2.getSelectedItem().toString());
			mValues.add(sa2);

			Intent i = new Intent(CreateAccountPage2Activity.this,
					CreateAccountPage3Activity.class);
			i.putStringArrayListExtra("VALUES", mValues);
			startActivity(i);
		}
	}

	public void goBack(View view) {
		finish();
	}
	
	public void onResume(){
		super.onResume();
		error_msg = (TextView) findViewById(R.id.create_error);
		error_msg.setText("");
	}
}
