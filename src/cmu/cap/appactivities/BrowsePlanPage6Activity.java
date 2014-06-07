package cmu.cap.appactivities;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cmu.cap.marketplaceapp.R;

public class BrowsePlanPage6Activity extends Activity {

	private ArrayList<String> mValues;
	private String appid;
	private TextView result;
	private TextView result1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_plan_page6);

		getResults();
	}

	public void getResults(){
		
		mValues = getIntent().getStringArrayListExtra("VALUES");
		result = (TextView) findViewById(R.id.browsePlanResult);
		result1 = (TextView) findViewById(R.id.browsePlanResult1);
		
		Random rand = new Random();
		int r = rand.nextInt(2);
		
		//ArrayList<Boolean> age = new ArrayList<Boolean>();
		String[] age = mValues.get(1).split(",");
		if(age[0].equalsIgnoreCase("[true")){
			result.setText("Based on the initial information provided, one or more members in "
					+ " your family might be eligible for medicare/medicaid");
			if(r == 0){
				result1.setText("Also, other members of your family might be eligible"
						+ " for commercial plans. Please log-in and apply for benefits to get a list"
						+ " of plans your family is eligible for.");
			}else{
				result1.setText("Also, other members of your family might not be eligible"
						+ " for commercial plans. However, please log-in and apply for benefits"
						+ " to get the exact eligibility results.");
			}
			
		}else{
			
			result.setText("Based on the information provided, no one in "
					+ " your family is eligible for medicare/medicaid");
			if(r == 0){
				result1.setText("But, other members in your family might be eligible"
						+ " for commercial plans. Please log-in and apply for benefits to get a list"
						+ " of plans your family is eligible for.");
			}else{
				result1.setText("Also, other members of your family might not be eligible"
						+ " for commercial plans. However, please log-in and apply for benefits"
						+ " to get the exact eligibility results.");
			}
		}
		
		
		

	}
	
	public void goback(View view) {
		finish();
	}
	
	public void finish(View view) {
		appid = getIntent().getStringExtra("USERID");
		if(appid == null){
			Intent i = new Intent(BrowsePlanPage6Activity.this,	FirstScreenActivity.class);
			startActivity(i);
		}else{
			Intent i = new Intent(BrowsePlanPage6Activity.this,	SuccessfulLoginActivity.class);
			i.putExtra("USERID", appid);
			startActivity(i);
		}
	}
}
