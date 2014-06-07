package cmu.cap.appactivities;

import cmu.cap.marketplaceapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class FirstScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_screen);

	}
	
	public void signin(View view){
		Intent i = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(i);
	}
	
	public void newUser(View view){
		Intent i = new Intent(getApplicationContext(), CreateAccountPage1Activity.class);
		startActivity(i);
	}
	
	public void browsePlans(View view){
		Intent i = new Intent(getApplicationContext(), BrowsePlanPage1Activity.class);
		startActivity(i);
	}
	
	public void onResume(){
		super.onResume();
	}
}
