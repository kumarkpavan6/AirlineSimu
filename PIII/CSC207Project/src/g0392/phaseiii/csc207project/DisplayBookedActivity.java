package g0392.phaseiii.csc207project;

import java.util.ArrayList;
import java.util.Arrays;

import flyPack.Flight;
import flyPack.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayBookedActivity extends Activity {

	/*The user of this APP.*/
	private User user;
	private ListView lv1;
	private ListView lv2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_booked);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		lv1 = (ListView)findViewById(R.id.listView1);	
		lv2 = (ListView)findViewById(R.id.listView2);	
		ArrayList<Flight> temp = new ArrayList<Flight>();
		temp.addAll(user.getPersonal().getBooking().keySet());
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                temp);
		lv1.setAdapter(arrayAdapter);	
		lv1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			displayTransfers(position);
			}
			});
	}
	
	/**
	 * Display all the transfers of flights booked by the user.
	 * @param position
	 */
	public void displayTransfers(int position){		
		Flight f = (Flight) lv1.getItemAtPosition(position);
		ArrayList<Flight> arry = new ArrayList<Flight>(Arrays.asList
				(user.getPersonal().getBooking().get(f)));
		ArrayAdapter<Flight> arrayAdapter = new ArrayAdapter<Flight>(
                this, 
                android.R.layout.simple_list_item_1,
                arry);
		lv2.setAdapter(arrayAdapter);	
	}
	
}
