package cmu.cap.appactivities;

import java.util.ArrayList;

import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPasswordPage2Activity extends Activity {
	
	private TextView sa_error;
	private EditText inputsa;
	private TextView inputsq;
	private ArrayList<String> mValues;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password_page2);
		mValues = getIntent().getStringArrayListExtra("VALUES");
		inputsq = (TextView) findViewById(R.id.sq_reset);
		inputsq.setText(mValues.get(1) + "?");
	}
	
	public void next(View view){		
		inputsa = (EditText) findViewById(R.id.sa_reset);
		sa_error = (TextView) findViewById(R.id.ans_error);
		String sa = inputsa.getText().toString();
		if (sa.equals(mValues.get(2))) {
			sa_error.setText("");
			Intent i = new Intent(ForgetPasswordPage2Activity.this, ForgetPasswordPage3Activity.class);
			i.putStringArrayListExtra("VALUES", mValues);
			startActivity(i);
		} else {
			sa_error.setText("Invalid answer!");
			Intent i = new Intent(getApplicationContext(), FirstScreenActivity.class);
			startActivity(i);
		}
	}

	public void goback(View view){
		finish();
	}
	
	public void onResume(){
		super.onResume();
		sa_error = (TextView) findViewById(R.id.ans_error);
		sa_error.setText("");
	}
}
