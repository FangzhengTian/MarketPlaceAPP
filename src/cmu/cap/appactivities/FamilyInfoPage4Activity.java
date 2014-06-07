package cmu.cap.appactivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import cmu.cap.apphelper.Family;
import cmu.cap.apphelper.Household;
import cmu.cap.apphelper.Job;
import cmu.cap.marketplaceapp.R;

public class FamilyInfoPage4Activity extends Activity {

	private Spinner incomeTypeList;
	private EditText jobName;
	private EditText income;
	private Button add;
	private TextView ckerror;

	private Household house;
	private ArrayList<Family> familymembers;
	private ArrayList<Job> jobs;
	private int members;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_info_page4);
	}

	public void add(View view) {
		house = getIntent().getParcelableExtra("HOUSEHOLD");
		familymembers = getIntent().getParcelableArrayListExtra("FAMILY");
		jobs = getIntent().getParcelableArrayListExtra("JOB");
		Job j = new Job();
		j.setAppid(house.getAppId());
		j.setPersonid(familymembers.get(familymembers.size() - 1).getPersonid());
		members = getIntent().getIntExtra("MEMBERS", 0);

		ckerror = (TextView) findViewById(R.id.check_error);
		incomeTypeList = (Spinner) findViewById(R.id.incomeTypeList);
		jobName = (EditText) findViewById(R.id.jobName);
		income = (EditText) findViewById(R.id.Income);
		add = (Button) findViewById(R.id.add);

		String incomeType = String.valueOf(incomeTypeList.getSelectedItem());
		String job_Name = jobName.getText().toString();
		String incomeVal = income.getText().toString();

		String error = "";
		if (!(incomeType.equalsIgnoreCase("Choose Income Type")
				&& job_Name.isEmpty() && incomeVal.isEmpty())) {
			if (!(incomeType.equalsIgnoreCase("N/A"))) {
				j.setIncometype(incomeType);
				j.setJobname(job_Name);
				j.setIncome(Integer.valueOf(incomeVal));
				j.setIncomeid(jobs.size() + 1);
				jobs.add(j);
				Intent i = new Intent(FamilyInfoPage4Activity.this,
						FamilyInfoPage4Activity.class);
				i.putExtra("HOUSEHOLD", house);
				i.putExtra("MEMBERS", members);
				i.putParcelableArrayListExtra("FAMILY", familymembers);
				i.putParcelableArrayListExtra("JOB", jobs);
				startActivity(i);
			}
		} else {
			error = "Please enter all values";
			ckerror.setText(error);
		}

	}

	public void nextPage(View view) {
		// Needs code of next screens
		house = getIntent().getParcelableExtra("HOUSEHOLD");
		familymembers = getIntent().getParcelableArrayListExtra("FAMILY");
		jobs = getIntent().getParcelableArrayListExtra("JOB");
		Job j = new Job();
		j.setAppid(house.getAppId());
		j.setPersonid(familymembers.get(familymembers.size() - 1).getPersonid());
		members = getIntent().getIntExtra("MEMBERS", 0);

		ckerror = (TextView) findViewById(R.id.check_error);
		incomeTypeList = (Spinner) findViewById(R.id.incomeTypeList);
		jobName = (EditText) findViewById(R.id.jobName);
		income = (EditText) findViewById(R.id.Income);
		add = (Button) findViewById(R.id.add);

		String incomeType = String.valueOf(incomeTypeList.getSelectedItem());
		String job_Name = jobName.getText().toString();
		String incomeVal = income.getText().toString();

		String error = "";

		if (incomeType.equalsIgnoreCase("Choose Income Type")) {
			error = "Please select an income type";
		}
		if (!incomeType.equalsIgnoreCase("N/A")) {
			if (job_Name.isEmpty()) {
				error = "Please enter job/company name";
			}
			if (incomeVal.isEmpty()) {
				error = "Please enter your income";
			}
		}

		if (error.equalsIgnoreCase("")) {
			if (incomeType.equalsIgnoreCase("N/A")) {
				j.setIncometype(incomeType);
			} else {
				j.setIncometype(incomeType);
				j.setJobname(job_Name);
				j.setIncome(Integer.valueOf(incomeVal));
			}
			j.setIncomeid(jobs.size() + 1);
			jobs.add(j);
			if (members > 1) {
				Intent i = new Intent(FamilyInfoPage4Activity.this,
						FamilyInfoPage1Activity.class);
				i.putExtra("HOUSEHOLD", house);
				i.putExtra("MEMBERS", members - 1);
				i.putParcelableArrayListExtra("FAMILY", familymembers);
				i.putParcelableArrayListExtra("JOB", jobs);
				startActivity(i);
			} else {
				Intent i = new Intent(FamilyInfoPage4Activity.this,
						SummaryActivity.class);
				i.putExtra("HOUSEHOLD", house);
				i.putParcelableArrayListExtra("FAMILY", familymembers);
				i.putParcelableArrayListExtra("JOB", jobs);
				startActivity(i);
			}
		} else {
			ckerror.setText(error);
		}
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
