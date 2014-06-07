package cmu.cap.appnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.LoginActivity;
import cmu.cap.apphelper.UserFunctions;


public class LoginNetwork {

	LoginActivity lg = null;

	public void login(String appid, String password, LoginActivity lg){
		this.lg = lg;
		new AsyncLogin().execute(appid, password);
	}

	private class AsyncLogin extends AsyncTask<String, Void, String> {

		protected String doInBackground(String... params) {
			return login(params[0], params[1]);
		}

		protected void onPostExecute(String result) {
			lg.loginReady(result);
		}
		
		private String login(String appid, String password){
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.loginUser(appid, password);
			String result = null;
			try{
				if (json.getString("success").equals("true")){
					result = "";
				}else{
					result = "Invalid id or password!";
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			return result;
		}
	}
}
