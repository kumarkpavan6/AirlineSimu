package g0392.phaseiii.csc207project;

import flyPack.User;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class SearchActivity extends Activity {

	/*The user of this APP.*/
	private User user;

	private String mode; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		mode = intent.getStringExtra("mode");
	}
	
	/**
	 * Determine if app should display flights or itinerary.
	 * @param view
	 */
	public void display(View view){
		if (mode.equals("searchFlight")){
			displayFlights(view);
		} else {
			displayItinerary(view);
		}		
	}

	/**
	 * Return the string containing origin, destination, date that the user entered.
	 * @return a string representation that the user entered to search flight.
	 */
	public String[] getContent(){		
		EditText originText = (EditText) findViewById(R.id.editText3);		
		String origin = originText.getText().toString();
		
		EditText destinationText = (EditText) findViewById(R.id.editText2);		
		String destination = destinationText.getText().toString();
		
		DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker1);
		int day = datepicker.getDayOfMonth();
		int month = datepicker.getMonth();
		month = month + 1;
		int year = datepicker.getYear();
		String month1 = ""; 
		if (month < 10) {
			month1 = "0" + "" + month; 
		}		
		String date = "" + year + "-" + month1 + "-" + "" + day;
		String[] content = {date,origin,destination};
		return content;
	}
	
	/**
	 * Pass the display to search itineraries.
	 * @param view
	 */
	public void displayItinerary(View view){
		Intent intent = new Intent(this, DisplayItineraryActivity.class);
		String[] content = getContent();
		user.getFlightManager().setItineraries(user.getFlightManager().
				searchItinearies(content[0], content[1], content[2]));
		intent.putExtra("user", user);
		startActivity(intent);	
	}
	
	/**
	 * Pass the display to search flights.
	 * @param view
	 */
	public void displayFlights(View view){
		Intent intent = new Intent(this, DisplayFlightsActivity.class);
		String[] content = getContent();
		user.getFlightManager().setItineraries(user.getFlightManager().
				searchItinearies(content[0], content[1], content[2]));
		intent.putExtra("user", user);
		startActivity(intent);	
	}
}
