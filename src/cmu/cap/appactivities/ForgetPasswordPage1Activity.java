package cmu.cap.appactivities;

import java.util.ArrayList;
import java.util.Calendar;

import cmu.cap.appnetwork.PickQuestionNetwork;
import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPasswordPage1Activity extends Activity {
	
	private TextView id_error;
	private EditText inputUserID;
	private TextView inputdob;
	private ArrayList<String> mValues = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_password_page1);
		
		final ForgetPasswordPage1Activity fp = this;
		Button nextbtn = (Button) findViewById(R.id.next_button);
		nextbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				inputUserID = (EditText) findViewById(R.id.userid_reset);
				inputdob = (TextView) findViewById(R.id.dob_reset);
				String userid = inputUserID.getText().toString();
				String dob = inputdob.getText().toString();
				PickQuestionNetwork help = new PickQuestionNetwork();
				help.pick(userid, dob, fp);
			}
		});
	}
	
	public void questionReady(String result){
		id_error = (TextView) findViewById(R.id.id_error);
		if (result.equals("incorrect user id or dob")) {
			id_error.setText(result);
		} else {
			id_error.setText("");
			inputUserID = (EditText) findViewById(R.id.userid_reset);
			String userid = inputUserID.getText().toString();
			mValues.add(userid);
			String[] results = result.split("\\?");
			mValues.add(results[0]);
			mValues.add(results[1]);
			Intent i = new Intent(ForgetPasswordPage1Activity.this, ForgetPasswordPage2Activity.class);
			i.putStringArrayListExtra("VALUES", mValues);
			startActivity(i);
		}
	}

	public void goback(View view){
		finish();
	}
	
	public void showCalendar(View view) {
		Calendar mcurrentDate = Calendar.getInstance();
		int mYear = mcurrentDate.get(Calendar.YEAR);
		int mMonth = mcurrentDate.get(Calendar.MONTH);
		int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog mDatePicker = new DatePickerDialog(this,
				new OnDateSetListener() {
					public void onDateSet(DatePicker datepicker,
							int selectedYear, int selectedMonth, int selectedDay) {
						String year = String.valueOf(selectedYear);
						String month = String.valueOf(selectedMonth + 1);
						String day = String.valueOf(selectedDay);
						TextView dob = (TextView) findViewById(R.id.dob_reset);
						dob.setText(month + "/" + day + "/" + year);
					}
				}, mYear, mMonth, mDay);
		mDatePicker.setTitle("Select date");
		mDatePicker.show();
	}
	
	public void onResume(){
		super.onResume();
		id_error = (TextView) findViewById(R.id.id_error);
		id_error.setText("");
	}
}
