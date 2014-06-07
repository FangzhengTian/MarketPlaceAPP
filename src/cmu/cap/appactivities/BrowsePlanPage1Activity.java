package cmu.cap.appactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cmu.cap.marketplaceapp.R;

public class BrowsePlanPage1Activity extends Activity {

	private String appid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_plan_page1);

	}

	public void nextPage(View view){
		appid = getIntent().getStringExtra("USERID");
		Intent i = new Intent(BrowsePlanPage1Activity.this, BrowsePlanPage2Activity.class);
		i.putExtra("USERID", appid);
		startActivity(i);
	}
	
	public void goback(View view) {
		finish();
	}
	
}
