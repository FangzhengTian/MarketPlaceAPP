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

public class FamilyInfoPage3Activity extends Activity {

	private RadioButton als_esrd_Yes;
	private RadioButton als_esrd_No;
	private RadioButton blind_disabled_Yes;
	private RadioButton blind_disabled_No;
	private RadioButton pregnant_Yes;
	private RadioButton pregnant_No;
	private TextView ckerror;
	private Household house;
	private ArrayList<Family> familymembers;
	private ArrayList<Job> jobs;
	private int members;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_info_page3);

	}
	
	public void nextPage(View view){
		house = getIntent().getParcelableExtra("HOUSEHOLD");
		familymembers = getIntent().getParcelableArrayListExtra("FAMILY");
		members = getIntent().getIntExtra("MEMBERS",0);
		jobs = getIntent().getParcelableArrayListExtra("JOB");
		Family f = familymembers.get(familymembers.size()-1);
		
		ckerror = (TextView) findViewById(R.id.check_error);
		als_esrd_Yes = (RadioButton) findViewById(R.id.ALSorESRDYes);
		als_esrd_No = (RadioButton) findViewById(R.id.ALSorESRDNo);
		blind_disabled_Yes = (RadioButton) findViewById(R.id.BlindDisabledYes);
		blind_disabled_No = (RadioButton) findViewById(R.id.BlindDisabledNo);
		pregnant_Yes = (RadioButton) findViewById(R.id.pregnantYes);
		pregnant_No = (RadioButton) findViewById(R.id.pregnantNo);
		
		String error = "";
		
		if((als_esrd_Yes.isChecked() || als_esrd_No.isChecked())
				&& (blind_disabled_Yes.isChecked() || blind_disabled_No.isChecked())
				&& (pregnant_Yes.isChecked() || pregnant_No.isChecked())){
			
			f.setEsrd_als(als_esrd_Yes.isChecked() ? true : false);
			f.setDisability(blind_disabled_Yes.isChecked() ? true : false);
			f.setPregnant(pregnant_Yes.isChecked() ? true : false);
			
			Intent i = new Intent(FamilyInfoPage3Activity.this, FamilyInfoPage4Activity.class);
			i.putExtra("HOUSEHOLD", house);
			i.putExtra("MEMBERS", members);
			i.putParcelableArrayListExtra("FAMILY", familymembers);
			i.putParcelableArrayListExtra("JOB", jobs);
			startActivity(i);
		}
		else{
			error="Please answer all the questions";
			ckerror.setText(error);
		}
		
	}
	
	public void goback(View view) {
		finish();
	}
	
	public void onResume(){
		super.onResume();
		ckerror = (TextView) findViewById(R.id.check_error);
		ckerror.setText("");
	}

}