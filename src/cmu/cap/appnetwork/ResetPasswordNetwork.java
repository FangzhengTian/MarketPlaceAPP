package cmu.cap.appnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.ForgetPasswordPage3Activity;
import cmu.cap.apphelper.UserFunctions;

public class ResetPasswordNetwork {

	ForgetPasswordPage3Activity fp = null;

	public void reset(String appid, String password, ForgetPasswordPage3Activity fp) {
		this.fp = fp;
		new AsyncReset().execute(appid, password);
	}

	private class AsyncReset extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {
			return resetPassword(params[0], params[1]);
		}

		protected void onPostExecute(String result) {
			fp.resetReady(result);
		}

		private String resetPassword(String appid, String password) {
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.resetPassword(appid, password);
			String result = null;
			try {
				result = json.getString("success");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}
