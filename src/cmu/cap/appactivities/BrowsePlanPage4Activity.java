package cmu.cap.appactivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cmu.cap.marketplaceapp.R;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class BrowsePlanPage4Activity extends Activity {

	CheckBox income1;
	CheckBox income2;
	CheckBox income3;
	CheckBox otherIncome1;
	CheckBox otherIncome2;
	CheckBox otherIncome3;
	
	private ArrayList<String> mValues;
	private String appid;
	private TextView ckerror;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_plan_page4);

	}
	
	public void nextPage(View view){
		
		mValues = getIntent().getStringArrayListExtra("VALUES");
		ckerror = (TextView) findViewById(R.id.check_error);
		
		income1 = (CheckBox) findViewById(R.id.income_checkBox1);
		income2 = (CheckBox) findViewById(R.id.income_checkBox3);
		income3 = (CheckBox) findViewById(R.id.income_checkBox5);
		otherIncome1 = (CheckBox) findViewById(R.id.income_checkBox2);
		otherIncome2 = (CheckBox) findViewById(R.id.income_checkBox4);
		otherIncome3 = (CheckBox) findViewById(R.id.income_checkBox6);
		
		ArrayList<Boolean> income = new ArrayList<Boolean>();
		ArrayList<Boolean> otherIncome = new ArrayList<Boolean>();
		String error="";
		
		if((income1.isChecked() || income2.isChecked() || income3.isChecked())
				&& (otherIncome1.isChecked() || otherIncome2.isChecked() || otherIncome3.isChecked())){
			
			if(income1.isChecked()){
				income.add(true);
			}else{
				income.add(false);
			}
			if(income2.isChecked()){
				income.add(true);
			}else{
				income.add(false);
			}
			if(income3.isChecked()){
				income.add(true);
			}else{
				income.add(false);
			}
			
			if(otherIncome1.isChecked()){
				otherIncome.add(true);
			}else{
				otherIncome.add(false);
			}
			if(otherIncome2.isChecked()){
				otherIncome.add(true);
			}else{
				otherIncome.add(false);
			}
			if(otherIncome3.isChecked()){
				otherIncome.add(true);
			}else{
				otherIncome.add(false);
			}
			
			mValues.add(income.toString());
			mValues.add(otherIncome.toString());
			Intent i = new Intent(BrowsePlanPage4Activity.this,	BrowsePlanPage5Activity.class);
			appid = getIntent().getStringExtra("USERID");
			i.putExtra("USERID", appid);
			i.putStringArrayListExtra("VALUES",mValues);
			startActivity(i);
			
			
		}else{
			error="Please check at least one check box for both the questions";
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
