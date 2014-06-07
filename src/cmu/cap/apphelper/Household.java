package cmu.cap.apphelper;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Parcel;
import android.os.Parcelable;

public class Household implements Parcelable {

	String appid = "";
	int num = -1;
	String address1 = "";
	String address2 = "";
	String city = "";
	String state = "";
	int zipcode = 0;

	public Household() {
	}

	public Household(Parcel p) {
		setAppId(p.readString());
		setNum(p.readInt());
		setAddress1(p.readString());
		setAddress2(p.readString());
		setCity(p.readString());
		setState(p.readString());
		setZipcode(p.readInt());
	}

	public String getAppId() {
		return appid;
	}

	public void setAppId(String appid) {
		this.appid = appid;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String toJSONString() {
		JSONObject j = new JSONObject();
		try {
			j.put("appid", appid);
			j.put("num", num);
			j.put("address1", address1);
			j.put("address2", address2);
			j.put("city", city);
			j.put("state", state);
			j.put("zipcode", zipcode);
			return j.toString();
		} catch (JSONException e) {
			return null;
		}
	}

	public String toString() {
		return "Number of Family Member: "
				+ num + "\nAddress:\n" + address1 + ", " + address2 + "\n"
				+ city + ", " + state + ", " + zipcode;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(appid);
		dest.writeInt(num);
		dest.writeString(address1);
		dest.writeString(address2);
		dest.writeString(city);
		dest.writeString(state);
		dest.writeInt(zipcode);
	}

	public static final Parcelable.Creator<Household> CREATOR = new Creator<Household>() {

		public Household createFromParcel(Parcel source) {

			return new Household(source);
		}

		public Household[] newArray(int size) {

			return new Household[size];
		}

	};
}
