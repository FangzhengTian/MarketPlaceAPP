package cmu.cap.appactivities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import cmu.cap.marketplaceapp.R;

public class BrowsePlanPage3Activity extends Activity {

	private RadioButton als_esrd_Yes;
	private RadioButton als_esrd_No;
	private RadioButton blind_disabled_Yes;
	private RadioButton blind_disabled_No;
	private RadioButton pregnant_Yes;
	private RadioButton pregnant_No;
	private TextView ckerror;
	private ArrayList<String> mValues;
	private String appid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_plan_page3);
	}
	
	public void nextPage(View view){
		
		ckerror = (TextView) findViewById(R.id.check_error);
		als_esrd_Yes = (RadioButton) findViewById(R.id.ALSorESRDYes);
		als_esrd_No = (RadioButton) findViewById(R.id.ALSorESRDNo);
		blind_disabled_Yes = (RadioButton) findViewById(R.id.BlindDisabledYes);
		blind_disabled_No = (RadioButton) findViewById(R.id.BlindDisabledNo);
		pregnant_Yes = (RadioButton) findViewById(R.id.pregnantYes);
		pregnant_No = (RadioButton) findViewById(R.id.pregnantNo);
		
		mValues = getIntent().getStringArrayListExtra("VALUES");
		
		String error = "";
		
		if((als_esrd_Yes.isChecked() || als_esrd_No.isChecked())
				&& (blind_disabled_Yes.isChecked() || blind_disabled_No.isChecked())
				&& (pregnant_Yes.isChecked() || pregnant_No.isChecked())){
			
			if(als_esrd_Yes.isChecked()){
				mValues.add(als_esrd_Yes.getText().toString());
			}else if(als_esrd_No.isChecked()){
				mValues.add(als_esrd_No.getText().toString());
			}
			
			if(blind_disabled_Yes.isChecked()){
				mValues.add(blind_disabled_Yes.getText().toString());
			}else if(blind_disabled_No.isChecked()){
				mValues.add(blind_disabled_No.getText().toString());
			}
			
			if(pregnant_Yes.isChecked()){
				mValues.add(pregnant_Yes.getText().toString());
			}else if(pregnant_No.isChecked()){
				mValues.add(pregnant_No.getText().toString());
			}
			
			Intent i = new Intent(BrowsePlanPage3Activity.this, BrowsePlanPage4Activity.class);
			appid = getIntent().getStringExtra("USERID");
			i.putExtra("USERID", appid);
			i.putStringArrayListExtra("VALUES", mValues);
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
