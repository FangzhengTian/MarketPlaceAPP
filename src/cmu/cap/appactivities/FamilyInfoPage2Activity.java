package cmu.cap.appactivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import cmu.cap.apphelper.Family;
import cmu.cap.apphelper.Household;
import cmu.cap.apphelper.Job;
import cmu.cap.marketplaceapp.R;

public class FamilyInfoPage2Activity extends Activity {

	private RadioButton currentlyInsuredYes;
	private RadioButton currentlyInsuredNo;
	private RadioButton currentlyEnrolledYes;
	private RadioButton currentlyEnrolledNo;
	private RadioButton taxPayerYes;
	private RadioButton taxPayerNo;
	private TextView ckerror;
	private Household house;
	private ArrayList<Family> familymembers;
	private ArrayList<Job> jobs;
	private int members;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_info_page2);
	}

	public void nextPage(View view) {
		house = getIntent().getParcelableExtra("HOUSEHOLD");
		members = getIntent().getIntExtra("MEMBERS", 0);
		familymembers = getIntent().getParcelableArrayListExtra("FAMILY");
		jobs = getIntent().getParcelableArrayListExtra("JOB");
		Family f = familymembers.get(familymembers.size() - 1);

		ckerror = (TextView) findViewById(R.id.check_error);
		currentlyInsuredNo = (RadioButton) findViewById(R.id.currentlyInsuredNo);
		currentlyInsuredYes = (RadioButton) findViewById(R.id.currentlyInsuredYes);
		currentlyEnrolledNo = (RadioButton) findViewById(R.id.currentlyEnrolledNo);
		currentlyEnrolledYes = (RadioButton) findViewById(R.id.currentlyEnrolledYes);
		taxPayerNo = (RadioButton) findViewById(R.id.taxPayerNo);
		taxPayerYes = (RadioButton) findViewById(R.id.taxPayerYes);

		String error = "";

		if ((currentlyEnrolledNo.isChecked() || currentlyEnrolledYes.isChecked())
			&& (currentlyInsuredNo.isChecked() || currentlyInsuredYes.isChecked())
				&& (taxPayerNo.isChecked() || taxPayerYes.isChecked())) {
			
			f.setMedicare_medicaid(currentlyEnrolledYes.isChecked() ? true : false);
			f.setInsured(currentlyInsuredYes.isChecked() ? true : false);
			f.setTaxpayer(taxPayerYes.isChecked() ? true : false);

			Intent i = new Intent(FamilyInfoPage2Activity.this,
					FamilyInfoPage3Activity.class);
			i.putExtra("HOUSEHOLD", house);
			i.putExtra("MEMBERS", members);
			i.putParcelableArrayListExtra("FAMILY", familymembers);
			i.putParcelableArrayListExtra("JOB", jobs);
			startActivity(i);

		} else {
			error = "Please answer all the questions";
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
