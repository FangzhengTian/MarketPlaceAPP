package cmu.cap.appnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.SuccessfulLoginActivity;
import cmu.cap.apphelper.UserFunctions;

public class VerifyApplicationNetwork {

	SuccessfulLoginActivity ap = null;

	public void apply(String appid, SuccessfulLoginActivity ap) {
		this.ap = ap;
		new AsyncVerify().execute(appid);
	}

	private class AsyncVerify extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {
			return verifyApplication(params[0]);
		}

		protected void onPostExecute(String result) {
			ap.verifyReady(result);
		}

		private String verifyApplication(String appid) {
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.verifyApplication(appid);
			String result = "";
			try {
				result = json.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result.equals("No application found")) {
				return result;
			} else {
				try {
					result = json.getString("result");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}
		}
	}
}
