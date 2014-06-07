package cmu.cap.appnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.ForgetPasswordPage1Activity;
import cmu.cap.apphelper.UserFunctions;

public class PickQuestionNetwork {
	ForgetPasswordPage1Activity fp = null;

	public void pick(String appid, String dob, ForgetPasswordPage1Activity fp){
		this.fp = fp;
		new AsyncPick().execute(appid, dob);
	}

	private class AsyncPick extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {
			return pick(params[0], params[1]);
		}

		protected void onPostExecute(String result) {
			fp.questionReady(result);
		}
		
		private String pick(String appid, String dob){
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.pickQuestion(appid, dob);
			String result = null;
			try {
				result = json.getString("question");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return result;
		}
	}
}
