package cmu.cap.appnetwork;

import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.SummaryActivity;
import cmu.cap.apphelper.UserFunctions;

public class ApplyNetwork {

	SummaryActivity ap = null;

	public void apply(String h, String fa, String ja, SummaryActivity ap) {
		this.ap = ap;
		new AsyncApply().execute(h, fa, ja);
	}

	private class AsyncApply extends
			AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {
			return applyForBenefits(params[0], params[1], params[2]);
		}

		protected void onPostExecute(String result) {
			ap.applyReady(result);
		}

		private String applyForBenefits(String h, String fa, String ja) {
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.applyForBenefits(h, fa, ja);
			String result = "";
			result = json.toString();
			return result;
		}
	}

}
