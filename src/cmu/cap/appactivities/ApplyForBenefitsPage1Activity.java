package cmu.cap.appactivities;

import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class ApplyForBenefitsPage1Activity extends Activity {
	private String userid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apply_for_benefits_page1);
	}
	
	public void nextPage(View view){
		userid = getIntent().getStringExtra("USERID");
		Intent i = new Intent(ApplyForBenefitsPage1Activity.this, ApplyForBenefitsPage2Activity.class);
		i.putExtra("USERID", userid);
		startActivity(i);
	}

	public void goback(View view) {
		finish();
	}
}
