
package g0392.phaseiii.csc207project;


import java.io.*;


import flyPack.Billing;

import flyPack.BillingManager;
import flyPack.FlightManager;
import flyPack.Personal;
import flyPack.PersonalManager;
import flyPack.User;
import flyPack.UserManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class UserSignUp extends Activity {

	private UserManager uManager; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_sign_up);
		Intent intent = getIntent();
		//File userData = this.getApplicationContext().getFilesDir();	
		//File pass = new File(userData, "passwords.txt");
		uManager = (UserManager) intent.getSerializableExtra("uManager");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	/**
	 * 
	 * @param view
	 * @throws IOException
	 */
	public void registerUser(View view) throws IOException{
		
		//Gets the last name from the 1st edit text field.
		EditText lastNameField = (EditText)findViewById(R.id.lastname);
		String lastName = lastNameField.getText().toString();
		
		//Gets the first name from the 2nd Edit text field.
		EditText FirstNameField = (EditText)findViewById(R.id.firstname);
		String firstname = FirstNameField.getText().toString();
		
		//Gets email from the Edit text field.
		EditText EmailField = (EditText)findViewById(R.id.email);
		String email = EmailField.getText().toString();
		
		//Gets address from Edit text field.
		EditText AddressField = (EditText)findViewById(R.id.address);
		String address = AddressField.getText().toString();
		
		//Gets password from edit text field.
		EditText PasswordField = (EditText)findViewById(R.id.password);
		String password = PasswordField.getText().toString();
		
		//Gets credit card number from edit text field. 
		EditText creditCardField = (EditText)findViewById(R.id.creditnumber);
		String creditCard = creditCardField.getText().toString();
		
		//Gets credit card expire date from edit text field.
		EditText expiryDateField = (EditText)findViewById(R.id.expirydate);
		String expiryDate = expiryDateField.getText().toString();

		/*
		 * Saving user's email and password to file on internal mem
		 */
		String userInfo = email + "," + password + "\n";

		//Write user info to passwords.txt.
	     OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("passwords.txt", Context.MODE_APPEND));
	     outputStreamWriter.write(userInfo);
	     outputStreamWriter.close();
		
	    //Creates a new user with email and password
		User user = new User(email, password);
		
		//Adds billing information to user.
		user.setBilling(new Billing(lastName, firstname, address, 
			 email,  creditCard,  expiryDate));
		
		//Adds personal information to user.
		user.setPersonal(new Personal(firstname, lastName, email,
				 address));
		
		//Adds user to user manager list.
		uManager.add(user);	
		
		//Pass the user object to Client Activity and start client activity.
		Intent intent = new Intent(this, ClientActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}

}
	
