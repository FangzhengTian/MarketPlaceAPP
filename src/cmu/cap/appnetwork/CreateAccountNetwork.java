package cmu.cap.appnetwork;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import cmu.cap.appactivities.CreateAccountPage3Activity;
import cmu.cap.apphelper.UserFunctions;

public class CreateAccountNetwork {

	CreateAccountPage3Activity ca = null;
	
	public void create(ArrayList<String> values, CreateAccountPage3Activity ca){
		this.ca = ca;
		new AsyncCreateAccount().execute(values);
	}

	private class AsyncCreateAccount extends AsyncTask<ArrayList<String>, Void, String> {

		protected String doInBackground(ArrayList<String>... params) {
			return createAccount(params[0]);
		}

		protected void onPostExecute(String result) {
			ca.createAccountReady(result);
		}
		
		private String createAccount(ArrayList<String> values){
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.createAccount(values);
			String result = null;
			try{
				if (json.getString("success").equals("success")){
					result = "";
				}else {
					result = json.getString("success");
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			return result;
		}
	}
	
}
