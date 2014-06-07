package cmu.cap.apphelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class UserFunctions {

	private JSONParser jparser;
	public static String loginURL = "http://deloittecap.heinz.cmu.edu:8080/Login";
	public static String createAccountURL = "http://deloittecap.heinz.cmu.edu:8080/CreateAccount";
	public static String pickQuestionURL = "http://deloittecap.heinz.cmu.edu:8080/PickSecurityQuestion";
	public static String resetPasswordURL = "http://deloittecap.heinz.cmu.edu:8080/ResetPassword";
	public static String applyForBenefitsURL = "http://deloittecap.heinz.cmu.edu:8080/ApplyForBenefits";
	public static String checkStatusURL = "http://deloittecap.heinz.cmu.edu:8080/CheckStatus";
	public static String finishApplicationURL = "http://deloittecap.heinz.cmu.edu:8080/FinishApplication";
	
	public UserFunctions(){
		jparser = new JSONParser();
	}
	
	public JSONObject loginUser(String appid, String password){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("app_id", appid));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jparser.getJSONFromUrl(loginURL, params);
        return json;
	}
	
	public JSONObject createAccount(ArrayList<String> values){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("firstName", values.get(0)));
		params.add(new BasicNameValuePair("middleName", values.get(1)));
		params.add(new BasicNameValuePair("lastName", values.get(2)));
		params.add(new BasicNameValuePair("dob", values.get(3)));
		params.add(new BasicNameValuePair("app_id", values.get(4)));
		params.add(new BasicNameValuePair("password", values.get(5)));
		params.add(new BasicNameValuePair("email", values.get(6)));
		params.add(new BasicNameValuePair("sq1", values.get(7)));
		params.add(new BasicNameValuePair("sa1", values.get(8)));
		params.add(new BasicNameValuePair("sq2", values.get(9)));
		params.add(new BasicNameValuePair("sa2", values.get(10)));
		JSONObject json = jparser.getJSONFromUrl(createAccountURL, params);
		return json;
	}
	
	public JSONObject pickQuestion(String appid, String dob){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_id", appid));
		params.add(new BasicNameValuePair("dob", dob));
		JSONObject json = jparser.getJSONFromUrl(pickQuestionURL, params);
		return json;
	}
	
	public JSONObject resetPassword(String appid, String password){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_id", appid));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jparser.getJSONFromUrl(resetPasswordURL, params);
		return json;
	}
	
	public JSONObject applyForBenefits(String h, String fa, String ja){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("household", h));
		params.add(new BasicNameValuePair("family", fa));
		params.add(new BasicNameValuePair("job", ja));
		JSONObject json = jparser.getJSONFromUrl(applyForBenefitsURL, params);
		return json;
	}
	
	public JSONObject verifyApplication(String appid){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appid", appid));
		JSONObject json = jparser.getJSONFromUrl(checkStatusURL, params);
		return json;
	}
	
	public JSONObject finishApplication(String appid, String planid, String result){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("appid", appid));
		params.add(new BasicNameValuePair("planid", planid));
		params.add(new BasicNameValuePair("result", result));
		JSONObject json = jparser.getJSONFromUrl(finishApplicationURL, params);
		return json;
	}
}
