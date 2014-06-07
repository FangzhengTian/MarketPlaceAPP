package cmu.cap.appactivities;

import java.util.ArrayList;

import org.json.JSONArray;

import cmu.cap.apphelper.Family;
import cmu.cap.apphelper.Household;
import cmu.cap.apphelper.Job;
import cmu.cap.appnetwork.ApplyNetwork;
import cmu.cap.marketplaceapp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SummaryActivity extends Activity {
	private TextView msg;
	private TextView hmsg;
	private TextView fmsg;
	private TextView jmsg;

	private Household house;
	private ArrayList<Family> familymembers;
	private ArrayList<Job> jobs;
	private String h;
	private String fa;
	private String ja;
	String appid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		house = getIntent().getParcelableExtra("HOUSEHOLD");
		familymembers = getIntent().getParcelableArrayListExtra("FAMILY");
		jobs = getIntent().getParcelableArrayListExtra("JOB");

		hmsg = (TextView) findViewById(R.id.house);
		hmsg.setMovementMethod(new ScrollingMovementMethod());
		hmsg.setText(house.toString());
		appid = house.getAppId();

		fmsg = (TextView) findViewById(R.id.family);
		fmsg.setMovementMethod(new ScrollingMovementMethod());
		String t = "";
		for (Family f : familymembers) {
			t += f.toString() + "\n";
		}
		fmsg.setText(t);

		jmsg = (TextView) findViewById(R.id.job);
		jmsg.setMovementMethod(new ScrollingMovementMethod());
		t = "";
		for (Job j : jobs) {
			t += j.toString() + "\n";
		}
		jmsg.setText(t);

		h = house.toJSONString();
		JSONArray jarray = new JSONArray();
		for (Family f : familymembers) {
			jarray.put(f.toJSON());
		}
		fa = jarray.toString();
		jarray = new JSONArray();
		for (Job j : jobs) {
			jarray.put(j.toJSON());
		}
		ja = jarray.toString();

		final SummaryActivity ap = this;
		Button submitbtn = (Button) findViewById(R.id.submit_button);
		submitbtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				ApplyNetwork help = new ApplyNetwork();
				help.apply(h, fa, ja, ap);
			}
		});
	}

	public void applyReady(String result) {
		msg = (TextView) findViewById(R.id.msg);
		if (!result.isEmpty()) {
			msg.setText("Your information is stored!");
			Intent i = new Intent(SummaryActivity.this,
					ApplyForBenefitsEligiblePlansActivity.class);
			i.putExtra("RESULT", result);
			i.putExtra("APPID", appid);
			startActivity(i);
		} else {
			msg.setText("Error occured! Please try agian!");
			Intent i = new Intent(SummaryActivity.this,
					SuccessfulLoginActivity.class);
			startActivity(i);
		}

	}

	public void onResume() {
		super.onResume();
		msg = (TextView) findViewById(R.id.msg);
		msg.setText("");
	}
}
