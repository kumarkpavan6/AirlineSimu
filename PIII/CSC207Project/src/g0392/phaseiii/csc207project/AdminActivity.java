package g0392.phaseiii.csc207project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import flyPack.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends Activity {

	/*The user that uses this App.*/
	private User user;

	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		Intent intent = getIntent();

		user = (User) intent.getSerializableExtra("admin");
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
	 * Search itineraries when user press the button search itinerary.
	 * @param view
	 */
	
	public void editClint(View view){
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra("user", user);
		startActivity(intent);
	}
	
	public void startSearchItinerary(View view) {
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra("mode", "searchItinerary");
		intent.putExtra("user", user);
		startActivity(intent);
	}

}
