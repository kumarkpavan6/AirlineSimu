package g0392.phaseiii.csc207project;

import java.util.ArrayList;
import java.util.Arrays;

import flyPack.Flight;
import flyPack.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DisplayItineraryActivity extends Activity {
	
	/*The user using the App.*/
	private User user;
	
	private ListView lv1; 
	private ListView lv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_itinerary);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		lv1 = (ListView)findViewById(R.id.listView1);	
		lv2 = (ListView)findViewById(R.id.listView2);	
		displayFlights();
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			displayTransfers(position);
			}
			});
	}
		
	/**
	 * Book the selected flight for user client.
	 * @param view
	 */
	public void bookFlight(View view){
		Flight f1 = (Flight) lv1.getItemAtPosition(lv1.getPositionForView(view));
		Flight f2 = (Flight) lv2.getItemAtPosition(lv2.getPositionForView(view));
		if (f1 == null && f2 == null){			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("No Flights Avialable");
			builder.create();
		}
		else if (f1 != null && f2 == null){
			if (f1.hasSeats()){
			user.getPersonal().bookFlights(f1);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Flight Booked");
			builder.create();
			Intent intent = new Intent(this, ClientActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
			} else {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Flight does not have remaining seats");
				builder.create();
			}			
		}
		else{
		if (f1.hasSeats() && f2.hasSeats()){
			user.getPersonal().bookFlights(f1, f2);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Flight Booked");
			builder.create();
			Intent intent = new Intent(this, ClientActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		} else { 
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Flight does not have remaining seats");
			builder.create();
		}		
	}
	}
	
	/**
	 * Show the itineraries and flight transfers of the flight for user.
	 * @param position
	 */
	public void displayTransfers(int position){		
		Flight f = (Flight) lv1.getItemAtPosition(position);
		ArrayList<Flight> arry = new ArrayList<Flight>(Arrays.asList
				(user.getFlightManager().getItineraries().get(f)));
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                arry);
		lv2.setAdapter(arrayAdapter);	
	}
	
	/**
	 * Show the flight for the user.
	 */
	public void displayFlights(){
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                user.getFlightManager().getKeySet());
		lv1.setAdapter(arrayAdapter);
	}
	
}
