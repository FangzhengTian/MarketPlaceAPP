package cmu.cap.appactivities;

import cmu.cap.marketplaceapp.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class CreateAccountPage4Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account_page4);
	}
	
	public void finish(View view){
		Intent i = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(i);
	}
}
