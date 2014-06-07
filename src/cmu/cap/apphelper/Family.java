package cmu.cap.apphelper;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Family implements Parcelable{

	String appid = "";
	int personid = -1;
	String fname = "";
	String mname = "";
	String lname = "";
	String dob = "";
	boolean insured = false;
	char gender = 'F';
	boolean pregnant = false;
	String relation = "";
	boolean disability = false;
	boolean esrd_als = false;
	boolean medicare_medicaid = false;
	boolean taxpayer = false;

	public Family(){
	}
	
	public Family(Parcel p){
		setAppid(p.readString());
	    setPersonid(p.readInt());
	    setFname(p.readString());
	    setMname(p.readString());
	    setLname(p.readString());
	    setDob(p.readString());
	    setInsured(Boolean.valueOf(p.readString()));
	    setGender((char)p.readInt());
	    setPregnant(Boolean.valueOf(p.readString()));
	    setRelation(p.readString());
	    setDisability(Boolean.valueOf(p.readString()));
	    setEsrd_als(Boolean.valueOf(p.readString()));
	    setMedicare_medicaid(Boolean.valueOf(p.readString()));
	    setTaxpayer(Boolean.valueOf(p.readString()));
	}

	public String getAppid(){
		return appid;
	}
	
	public int getPersonid(){
		return personid;
	}
	
	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setInsured(boolean insured) {
		this.insured = insured;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setPregnant(boolean pregnant) {
		this.pregnant = pregnant;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setDisability(boolean disability) {
		this.disability = disability;
	}

	public void setEsrd_als(boolean esrd_als) {
		this.esrd_als = esrd_als;
	}

	public void setMedicare_medicaid(boolean medicare_medicaid) {
		this.medicare_medicaid = medicare_medicaid;
	}

	public void setTaxpayer(boolean taxpayer) {
		this.taxpayer = taxpayer;
	}
	
	public JSONObject toJSON(){
		JSONObject j = new JSONObject();
		try {
			j.put("appid", appid);
			j.put("personid", personid);
			j.put("fname", fname);
			j.put("mname", mname);
			j.put("lname", lname);
			j.put("dob", dob);
			j.put("insured", insured);
			j.put("gender", gender);
			j.put("pregnant", pregnant);
			j.put("relation", relation);
			j.put("disability", disability);
			j.put("esrd_als", esrd_als);
			j.put("medicare_medicaid", medicare_medicaid);
			j.put("taxpayer", taxpayer);
			return j;
		} catch (JSONException e) {
			return null;
		}
	}
	
	public String toString(){
		return "Name: " + fname + " " + lname + "\nDate of Birth: " + dob + " \nGender: " + gender + "\n";
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
		dest.writeString(fname);
		dest.writeString(mname);
		dest.writeString(lname);
		dest.writeString(dob);
		dest.writeString(Boolean.toString(insured));
		dest.writeInt(gender);
		dest.writeString(Boolean.toString(pregnant));
		dest.writeString(relation);
		dest.writeString(Boolean.toString(disability));
		dest.writeString(Boolean.toString(esrd_als));
		dest.writeString(Boolean.toString(medicare_medicaid));
		dest.writeString(Boolean.toString(taxpayer));
	}
	
	public static final Parcelable.Creator<Family> CREATOR = new Creator<Family>() {

	    public Family createFromParcel(Parcel source) {

	        return new Family(source);
	    }

	    public Family[] newArray(int size) {

	        return new Family[size];
	    }

	};
}
