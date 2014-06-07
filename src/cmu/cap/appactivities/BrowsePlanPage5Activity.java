package cmu.cap.appactivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import cmu.cap.marketplaceapp.R;

public class BrowsePlanPage5Activity extends Activity {

	private RadioButton empCoverageYes;
	private RadioButton empCoverageNo;
	
	private TextView ckerror;
	private ArrayList<String> mValues;
	private String appid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_plan_page5);

	}
	
	public void nextPage(View view){
		
		ckerror = (TextView) findViewById(R.id.check_error);
		mValues = getIntent().getStringArrayListExtra("VALUES");
		
		empCoverageYes = (RadioButton) findViewById(R.id.EmployeeCoverageYes);
		empCoverageNo = (RadioButton) findViewById(R.id.EmployeeCoverageNo);
		
		String error = "";
		
		if(empCoverageYes.isChecked() || empCoverageNo.isChecked()){
			
			if(empCoverageYes.isChecked()){
				mValues.add(empCoverageYes.getText().toString());
			}else if(empCoverageNo.isChecked()){
				mValues.add(empCoverageNo.getText().toString());
			}
			
			Intent i = new Intent(BrowsePlanPage5Activity.this, BrowsePlanPage6Activity.class);
			appid = getIntent().getStringExtra("USERID");
			i.putExtra("USERID", appid);
			i.putStringArrayListExtra("VALUES", mValues);
			startActivity(i);
		}else{
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
