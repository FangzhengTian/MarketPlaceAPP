package cmu.cap.appactivities;

import cmu.cap.appnetwork.VerifyApplicationNetwork;
import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class SuccessfulLoginActivity extends Activity {
	private String appid;
	private TextView msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_successful_login);
		
		appid = getIntent().getStringExtra("USERID");
		
		final SuccessfulLoginActivity ap = this;
		Button applybtn = (Button) findViewById(R.id.apply_button);
		applybtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				VerifyApplicationNetwork help = new VerifyApplicationNetwork();
				help.apply(appid, ap);
			}
		});
		
		Button checkbtn = (Button) findViewById(R.id.checkStatus_button);
		checkbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				VerifyApplicationNetwork help = new VerifyApplicationNetwork();
				help.apply(appid, ap);
			}
		});
	}
	
	public void browse(View view){
		Intent i = new Intent(SuccessfulLoginActivity.this, BrowsePlanPage1Activity.class);
		i.putExtra("USERID", appid);
		startActivity(i);
	}
	
	public void signout(View view) {
		Intent i = new Intent(SuccessfulLoginActivity.this, FirstScreenActivity.class);
		startActivity(i);
	}
	
	public void verifyReady(String result){
		msg = (TextView) findViewById(R.id.error_msg);
		if(result.equals("No application found")){
			msg.setText(result);
			Intent i = new Intent(SuccessfulLoginActivity.this, ApplyForBenefitsPage1Activity.class);
			i.putExtra("USERID", appid);
			startActivity(i);
		}else{
			msg.setText("You have an application!");
			Intent i = new Intent(SuccessfulLoginActivity.this, CheckStatusActivity.class);
			i.putExtra("CONTENT", result);
			startActivity(i);
		}
	}
	
	public void onResume(){
		super.onResume();
		msg = (TextView) findViewById(R.id.error_msg);
		msg.setText("");
	}

}
