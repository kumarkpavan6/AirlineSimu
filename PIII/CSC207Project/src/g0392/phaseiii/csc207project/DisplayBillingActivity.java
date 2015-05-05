package g0392.phaseiii.csc207project;

import java.io.IOException;
import java.io.OutputStreamWriter;

import flyPack.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DisplayBillingActivity extends Activity {

	/*The user of the App.*/
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_billing);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		populate();
	}
	
	/**
	 * Save all the billing information that the user inputed.
	 */
	public void populate(){
		EditText lastName = (EditText) findViewById(R.id.editText1);
		EditText firstName = (EditText) findViewById(R.id.firstName);
		EditText email = (EditText) findViewById(R.id.editText2);
		EditText address = (EditText) findViewById(R.id.editText3);
		EditText creditCard = (EditText) findViewById(R.id.editText4);
		EditText expiryDate = (EditText) findViewById(R.id.editText5);
		
		lastName.setText(user.getBilling().getLastName());
		firstName.setText(user.getBilling().getFirstName());
		email.setText(user.getBilling().getEmail());
		address.setText(user.getBilling().getAddress());
		creditCard.setText(user.getBilling().getcreditCardNumber());
		expiryDate.setText(user.getBilling().getExpireDate());
	}
	
	/**
	 * Save all billing and personal inforamtion that the user inputed.
	 * @param view
	 * @throws IOException 
	 */
	public void saveInformation(View view) throws IOException{
		EditText lastName = (EditText) findViewById(R.id.editText1);
		EditText firstName = (EditText) findViewById(R.id.firstName);
		EditText email = (EditText) findViewById(R.id.editText2);
		EditText address = (EditText) findViewById(R.id.editText3);
		EditText creditCard = (EditText) findViewById(R.id.editText4);
		EditText expiryDate = (EditText) findViewById(R.id.editText5);
		String lastNameText = lastName.getText().toString();
		String firstNameText = firstName.getText().toString();
		String emailText = email.getText().toString();
		String addressText = address.getText().toString();
		String creditCardText = creditCard.getText().toString();
		String expiryDateText = expiryDate.getText().toString();
		user.getBilling().setFirstName(firstNameText);
		user.getBilling().setLastName(lastNameText);
		user.getBilling().setEmail(emailText);
		user.getBilling().setAddress(addressText);
		user.getBilling().setcreditCardNumber(creditCardText);
		user.getBilling().setExpireDate(expiryDateText);
		user.getPersonal().setFirstName(firstNameText);
		user.getPersonal().setLastName(lastNameText);
		user.getPersonal().setEmail(emailText);
		user.getPersonal().setAddress(addressText);
		 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("client.csv", Context.MODE_WORLD_WRITEABLE));
	     outputStreamWriter.write(user.getBilling().toString());
	     outputStreamWriter.close();
		Intent intent = new Intent(this, ClientActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
	
}
