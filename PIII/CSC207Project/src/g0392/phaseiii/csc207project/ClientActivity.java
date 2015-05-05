package g0392.phaseiii.csc207project;

import java.io.File;
import java.io.FileNotFoundException;

import flyPack.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ClientActivity extends Activity {

	/*The user of the APP.*/
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		String fileName = (this.getApplicationContext().getFilesDir()
				.getAbsolutePath()) + "/" + "flight.csv";
		File path = new File(fileName);
		user.getFlightManager().readFileCSVFile(path);
		
		//////////////////populate all clients.info
		String fileClient = (this.getApplicationContext().getFilesDir()
				.getAbsolutePath()) + "/" + "client.csv";
		File pathClient = new File(fileClient);
		try {
			user.getBillingManager().readFromCSVFile(fileClient);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = user.getBillingManager().getbillingInfo(user.getBilling().getEmail()).toString();
		String[] split = s.split(",");
		user.getBilling().setFirstName(split[0]);
		user.getBilling().setLastName(split[1]);
		user.getBilling().setAddress(split[3]);
		user.getBilling().setcreditCardNumber(split[4]);
		user.getBilling().setExpireDate(split[5]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client, menu);
		return true;
	}

	/**
	 * Search flights when user press the button search flights.
	 * @param view
	 */
	public void startSearchFlight(View view) {
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra("mode", "searchFlight");
		intent.putExtra("user", user);
		startActivity(intent);
	}

	/**
	 * Search Itineraries when user press the button search Itinerary.
	 * @param view
	 */
	public void startSearchItinerary(View view) {
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra("mode", "searchItinerary");
		intent.putExtra("user", user);
		startActivity(intent);
	}

	/**
	 * Pass the activity to show billing information of the user.
	 * @param view
	 */
	public void startBillingInformation(View view) {
		Intent intent = new Intent(this, DisplayBillingActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}

	/**
	 * Pass the activity to display the flights booked of the user.
	 * @param view
	 */
	public void viewFlights(View view) {
		Intent intent = new Intent(this, DisplayBookedActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
}
