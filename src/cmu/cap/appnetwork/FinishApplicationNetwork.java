package cmu.cap.appnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.ApplyForBenefitsEligiblePlansActivity;
import cmu.cap.apphelper.UserFunctions;

public class FinishApplicationNetwork {

	ApplyForBenefitsEligiblePlansActivity ap = null;

	public void finishApplication(String appid, String planid, String result, ApplyForBenefitsEligiblePlansActivity ap) {
		this.ap = ap;
		new AsyncFinish().execute(appid, planid, result);
	}

	private class AsyncFinish extends
			AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {
			return applyForBenefits(params[0], params[1], params[2]);
		}

		protected void onPostExecute(String result) {
			ap.applyReady(result);
		}

		private String applyForBenefits(String appid, String planid, String result) {
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.finishApplication(appid, planid, result);
			String re = "";
			try {
				re = json.getString("result");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return re;
		}
	}

}
