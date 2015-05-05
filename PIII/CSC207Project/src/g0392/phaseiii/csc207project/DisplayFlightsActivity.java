package g0392.phaseiii.csc207project;

import java.util.ArrayList;
import java.util.Collections;

import flyPack.Flight;
import flyPack.User;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayFlightsActivity extends Activity {

	/*The user of the App.*/
	private User user;
	
	private ListView listView;
	private int pos;
	final Context context = this;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_flights);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");		
		listView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                user.getFlightManager().getKeySet());
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			pos = position;			}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	/**
	 * Book the flight selecteed by the user.
	 * @param view
	 */
	public void bookFlight(View view){
		Flight f = (Flight) listView.getItemAtPosition(pos);
		if (f.hasSeats()){
			user.getPersonal().bookFlights(f);
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("Display");
			builder.setMessage("Flight Booked");
			builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		        }});
			builder.create();		
			Intent intent = new Intent(this, ClientActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		} else {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Flight does not have remaining seats");
			builder.create();
		}				
	}
	
	/**
	 * Show the searched flight from the cheapest flight to the most expensive.
	 * @param view
	 */
	public void displayByCost(View view){
		ArrayList<Flight> list = new ArrayList<Flight>();
		ArrayList<Flight> temp = new ArrayList<Flight>();
		list.addAll(user.getFlightManager().getItineraries().keySet());
		Collections.addAll(temp, user.getFlightManager().sortByCost(list));
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                temp);
		listView.setAdapter(arrayAdapter);
	}
	
	/**
	 * Show the searched flight from the shortest travel time to longest travel time.
	 * @param view
	 */
	public void displayByTime(View view){
		ArrayList<Flight> list = new ArrayList<Flight>();
		ArrayList<Flight> temp = new ArrayList<Flight>();
		list.addAll(user.getFlightManager().getItineraries().keySet());
		Collections.addAll(temp, user.getFlightManager().sortByTravelTime(list));
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                temp);
		listView.setAdapter(arrayAdapter);
	}
}
