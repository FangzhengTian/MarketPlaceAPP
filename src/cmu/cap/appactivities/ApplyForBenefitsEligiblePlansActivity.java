package cmu.cap.appactivities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cmu.cap.appnetwork.FinishApplicationNetwork;
import cmu.cap.marketplaceapp.R;

public class ApplyForBenefitsEligiblePlansActivity extends Activity {
	private String appid;
	private String planid;
	private String result;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apply_for_benefits_eligible_plans);
		try {
			createContent();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		appid = getIntent().getStringExtra("APPID");
		final ApplyForBenefitsEligiblePlansActivity ap = this;
		Button applybtn = (Button) findViewById(R.id.apply_button);
		applybtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				FinishApplicationNetwork help = new FinishApplicationNetwork();
				help.finishApplication(appid, planid, result, ap);
			}
		});
	}

	public void createContent() throws JSONException {
		result = getIntent().getStringExtra("RESULT");
		JSONObject js = new JSONObject(result);
		planid = js.getString("planid");
		JSONArray results = new JSONArray(js.getString("result"));
		result = results.toString();
		int len = results.length();
		
		TextView plan_result = (TextView) findViewById(R.id.plan_result);
		plan_result.setMovementMethod(new ScrollingMovementMethod());
		StringBuilder content = new StringBuilder();
		
		for(int i=0;i<len;i++){
			JSONObject j = results.getJSONObject(i);
			
			content.append("Name: ");
			content.append(j.getString("name"));
			content.append("\nPlan Name: ");
			content.append(j.getString("plan"));
			content.append("\nCategory: ");
			content.append(j.getString("category"));
			content.append("\n\n");
		}
		plan_result.setText(content.toString());
	}
	
	public void goback(View view) {
		finish();
	}
	
	public void applyReady(String result){
		title = (TextView) findViewById(R.id.title);
		title.setText(result);
		Intent i = new Intent(ApplyForBenefitsEligiblePlansActivity.this, SuccessfulLoginActivity.class);
		i.putExtra("USERID", appid);
		startActivity(i);
	}
}
