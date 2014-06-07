package cmu.cap.appactivities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import cmu.cap.marketplaceapp.R;

public class CheckStatusActivity extends Activity {

	private JSONArray jarray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_status);
		try {
			createContent();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createContent() throws JSONException {
		try {
			jarray = new JSONArray(getIntent().getStringExtra("CONTENT"));
		} catch (JSONException e) {
			jarray = new JSONArray();
			e.printStackTrace();
		}

		TextView status_result = (TextView) findViewById(R.id.status_result);
		status_result.setMovementMethod(new ScrollingMovementMethod());
		StringBuilder content = new StringBuilder();
		
		for (int i = 0; i < jarray.length(); i++) {
			JSONObject j = jarray.getJSONObject(i);

			content.append("Name: ");
			content.append(j.getString("name"));
			content.append("\nPlan Name: ");
			content.append(j.getString("plan"));
			content.append("\nStatus: ");
			content.append(j.getString("status"));
			content.append("\n\n");
		}
		
		status_result.setText(content.toString());

	}

	public void goback(View view) {
		finish();
	}

}
