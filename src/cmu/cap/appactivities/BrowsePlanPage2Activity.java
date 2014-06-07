package cmu.cap.appactivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import cmu.cap.marketplaceapp.R;

public class BrowsePlanPage2Activity extends Activity {

	private String appid;
	private ArrayList<String> mValues;
	EditText householdMembers;
	CheckBox ageRange1;
	CheckBox ageRange2;
	CheckBox ageRange3;
	CheckBox ageRange4;
	CheckBox ageRange5;
	
	private TextView ckerror;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_plan_page2);

	}
	
	public void nextPage(View view) {
		
		householdMembers = (EditText) findViewById(R.id.householdText);
		ageRange1 = (CheckBox) findViewById(R.id.age_checkBox1);
		ageRange2 = (CheckBox) findViewById(R.id.age_checkBox2);
		ageRange3 = (CheckBox) findViewById(R.id.age_checkBox3);
		ageRange4 = (CheckBox) findViewById(R.id.age_checkBox4);
		ageRange5 = (CheckBox) findViewById(R.id.age_checkBox5);
		ckerror = (TextView) findViewById(R.id.check_error);
		
		String members = householdMembers.getText().toString();
		ArrayList<Boolean> age = new ArrayList<Boolean>();
		
		if(ageRange1.isChecked()){
			age.add(true);
		}else{
			age.add(false);
		}
		if(ageRange2.isChecked()){
			age.add(true);
		}else{
			age.add(false);
		}
		if(ageRange3.isChecked()){
			age.add(true);
		}else{
			age.add(false);
		}
		if(ageRange4.isChecked()){
			age.add(true);
		}else{
			age.add(false);
		}
		if(ageRange5.isChecked()){
			age.add(true);
		}else{
			age.add(false);
		}
		
		String error="";
		if(members.isEmpty()){
			error="Please enter the number of members in the family";
		}
		if(!(ageRange1.isChecked() || ageRange2.isChecked() || ageRange3.isChecked()
				|| ageRange4.isChecked() || ageRange5.isChecked())){
			error="Please check at least one age range";
		}
		
		mValues = new ArrayList<String>();
		
		if(error.equalsIgnoreCase("")){
			mValues.add(members);
			mValues.add(age.toString());
			appid = getIntent().getStringExtra("USERID");
			Intent i = new Intent(BrowsePlanPage2Activity.this,	BrowsePlanPage3Activity.class);
			i.putExtra("USERID", appid);
			i.putStringArrayListExtra("VALUES",mValues);
			startActivity(i);
		}else{
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
