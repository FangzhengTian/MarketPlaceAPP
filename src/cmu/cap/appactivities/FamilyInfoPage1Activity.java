package cmu.cap.appactivities;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import cmu.cap.apphelper.Family;
import cmu.cap.apphelper.Household;
import cmu.cap.apphelper.Job;
import cmu.cap.marketplaceapp.R;

public class FamilyInfoPage1Activity extends Activity {

	private EditText inputfirstname;
	private EditText inputmiddlename;
	private EditText inputlastname;
	private TextView inputdob;
	private RadioButton male;
	private RadioButton female;
	private TextView ckerror;
	private Household house;
	private ArrayList<Family> familymembers;
	private ArrayList<Job> jobs;
	private int members;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_info_page1);

	}

	public void nextPage(View view) {
		house = getIntent().getParcelableExtra("HOUSEHOLD");
		familymembers = getIntent().getParcelableArrayListExtra("FAMILY");
		if(familymembers == null) familymembers = new ArrayList<Family>();
		members = getIntent().getIntExtra("MEMBERS", 0);
		jobs = getIntent().getParcelableArrayListExtra("JOB");
		if(jobs == null) jobs = new ArrayList<Job>();
		Family f = new Family();
		f.setAppid(house.getAppId());
		f.setPersonid(familymembers.size() + 1);

		ckerror = (TextView) findViewById(R.id.check_error);
		inputfirstname = (EditText) findViewById(R.id.firstnameText);
		inputmiddlename = (EditText) findViewById(R.id.middlenameText);
		inputlastname = (EditText) findViewById(R.id.lastnameText);
		inputdob = (TextView) findViewById(R.id.dob);
		male = (RadioButton) findViewById(R.id.male);
		female = (RadioButton) findViewById(R.id.female);

		String firstName = inputfirstname.getText().toString();
		String lastName = inputlastname.getText().toString();
		String dob = inputdob.getText().toString();
		boolean gender = false;
		String error = "";
		if (male.isChecked() || female.isChecked()) {
			gender = true;
		}
		if (firstName.isEmpty()) {
			error = "First Name cannot be blank";
		}
		if (lastName.isEmpty()) {
			error = "Last Name cannot be blank";
		}
		if (dob.isEmpty()) {
			error = "Date of Birth cannot be blank";
		}
		if (gender == false) {
			error = "Select a gender";
		}

		if (error.equals("")) {
			f.setFname(inputfirstname.getText().toString());
			f.setMname(inputmiddlename.getText().toString());
			f.setLname(inputlastname.getText().toString());
			f.setDob(inputdob.getText().toString());
			f.setGender(male.isChecked() ? 'M' : 'F');

			familymembers.add(f);
			Intent i = new Intent(FamilyInfoPage1Activity.this,
					FamilyInfoPage2Activity.class);
			i.putExtra("HOUSEHOLD", house);
			i.putExtra("MEMBERS", members);
			i.putParcelableArrayListExtra("FAMILY", familymembers);
			i.putParcelableArrayListExtra("JOB", jobs);
			startActivity(i);
		} else {
			ckerror.setText(error);
		}

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

	public void goback(View view) {
		finish();
	}

	public void onResume() {
		super.onResume();
		ckerror = (TextView) findViewById(R.id.check_error);
		ckerror.setText("");
	}

}
