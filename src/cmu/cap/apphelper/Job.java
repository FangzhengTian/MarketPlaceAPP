package cmu.cap.apphelper;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Job implements Parcelable {

	String appid = "";
	int personid = -1;
	int incomeid = -1;
	String incometype = "";
	int income = 0;
	String jobname = "";
	
	public Job(){
	}
	
	public Job(Parcel p){
	    setAppid(p.readString());
	    setPersonid(p.readInt());
	    setIncomeid(p.readInt());
	    setIncometype(p.readString());
	    setIncome(p.readInt());
	    setJobname(p.readString());
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public void setIncomeid(int incomeid) {
		this.incomeid = incomeid;
	}

	public void setIncometype(String incometype) {
		this.incometype = incometype;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public JSONObject toJSON(){
		JSONObject j = new JSONObject();
		try {
			j.put("appid", appid);
			j.put("personid", personid);
			j.put("incomeid", incomeid);
			j.put("incometype", incometype);
			j.put("income", income);
			j.put("jobname", jobname);
			return j;
		} catch (JSONException e) {
			return null;
		}
	}
	
	public String toString(){
		return "Job name: " + jobname + " \nIncome: " + income + "\n";
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(appid);
		dest.writeInt(personid);
		dest.writeInt(incomeid);
		dest.writeString(incometype);
		dest.writeInt(income);
		dest.writeString(jobname);
	}
	
	public static final Parcelable.Creator<Job> CREATOR = new Creator<Job>() {

	    public Job createFromParcel(Parcel source) {

	        return new Job(source);
	    }

	    public Job[] newArray(int size) {

	        return new Job[size];
	    }

	};
}
