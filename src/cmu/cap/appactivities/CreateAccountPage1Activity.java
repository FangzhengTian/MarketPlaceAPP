package cmu.cap.appactivities;

import java.util.ArrayList;
import java.util.Calendar;

import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccountPage1Activity extends Activity {
	
	private EditText inputfirstname;
	private EditText inputmiddlename;
	private EditText inputlastname;
	private TextView inputdob;
	private TextView error;
	private ArrayList<String> mValues;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account_page1);
	}
	
	public void nextPage(View view){
		error = (TextView) findViewById(R.id.error_msg);
		inputfirstname = (EditText) findViewById(R.id.firstnameText);
		inputmiddlename = (EditText) findViewById(R.id.middlenameText);
		inputlastname = (EditText) findViewById(R.id.lastnameText);
		inputdob = (TextView) findViewById(R.id.dob);
		String firstname = inputfirstname.getText().toString();
		String lastname = inputlastname.getText().toString();
		String dob = inputdob.getText().toString();
		if(firstname.equals("") || lastname.equals("") || dob.equals("")) error.setText("You must input your first name, last name and dob!");
		else{
			mValues = new ArrayList<String>();
			mValues.add(firstname);
			mValues.add(inputmiddlename.getText().toString());
			mValues.add(lastname);
			mValues.add(dob);
			Intent i = new Intent(CreateAccountPage1Activity.this, CreateAccountPage2Activity.class);
			i.putStringArrayListExtra("VALUES", mValues);
			startActivity(i);
		}
	}

	public void goback(View view) {
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
						TextView dob = (TextView) findViewById(R.id.dob);
						dob.setText(month + "/" + day + "/" + year);
					}
				}, mYear, mMonth, mDay);
		mDatePicker.setTitle("Select date");
		mDatePicker.show();
	}
	
	public void onResume(){
		super.onResume();
		error = (TextView) findViewById(R.id.error_msg);
		error.setText("");
	}
}
