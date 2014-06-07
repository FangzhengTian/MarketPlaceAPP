package cmu.cap.appactivities;

import cmu.cap.apphelper.Household;
import cmu.cap.marketplaceapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;


public class ApplyForBenefitsPage2Activity extends Activity {
	
	private EditText addressLine1;
	private EditText addressLine2;
	private EditText cityInput;
	private Spinner stateInput;
	private EditText zipcodeInput;
	private EditText householdInput;
	private Household house = new Household();
	private TextView ckerror;
	private int members;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apply_for_benefits_page2);
	}
	
	public void nextPage(View view) {
		ckerror = (TextView) findViewById(R.id.check_error);
		addressLine1 = (EditText) findViewById(R.id.address1Text);
		addressLine2 = (EditText) findViewById(R.id.address2Text);
		cityInput = (EditText) findViewById(R.id.cityText);
		stateInput = (Spinner) findViewById(R.id.statesList);
		zipcodeInput = (EditText) findViewById(R.id.zipcodeText);
		householdInput = (EditText) findViewById(R.id.householdText);
		String address1 = addressLine1.getText().toString();
		String address2 = addressLine2.getText().toString();
		String city = cityInput.getText().toString();
		String state = stateInput.getSelectedItem().toString();
		String zipcode = zipcodeInput.getText().toString();
		String household = householdInput.getText().toString();
		String error = "";
		
		if (household.equals("") || household.equals("0")) {
			error = "Invalid value for household!";
		}
		if (zipcode.equals("")) {
			error = "Zip code cannot be empty!";
		}
		if (state.equals("Select State")) {
			error = "You must select a state!";
		}
		if (city.equals("")) {
			error = "City cannot be empty";
		}
		if (address1.equals("")) {
			error = "Address cannot be empty!";
		}
						
		if (error.matches("")) {
			String userid = getIntent().getStringExtra("USERID");
			house.setAppId(userid);
			house.setAddress1(address1);
			house.setAddress2(address2);
			house.setCity(city);
			house.setState(state);
			house.setZipcode(Integer.valueOf(zipcode));
			house.setNum(Integer.valueOf(household));
			members = Integer.valueOf(household);
			Intent i = new Intent(ApplyForBenefitsPage2Activity.this, FamilyInfoPage1Activity.class);
			i.putExtra("HOUSEHOLD", house);
			i.putExtra("MEMBERS", members);
			startActivity(i);
		} else {
			ckerror.setText(error);
		}
	}
		
	public void goback(View view) {
		finish();
	}
	
	public void onResume(){
		super.onResume();
		ckerror = (TextView) findViewById(R.id.check_error);
		ckerror.setText("");
	}

}