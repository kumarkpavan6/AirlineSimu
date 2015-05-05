package g0392.phaseiii.csc207project;

import java.util.ArrayList;

import flyPack.Flight;
import flyPack.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayClientsActivity extends Activity {

	private User user;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_clients);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");		
		listView = (ListView) findViewById(R.id.listView1);
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(user.getBillingManager().getBilling().keySet());
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                list);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			displayClient(position);			}
			});
		listView.setAdapter(arrayAdapter);
	}
	
	public void displayClient(int position){
		String s = (String) listView.getItemAtPosition(position);
		String u =  user.getBillingManager().getbillingInfo(s).toString();
		EditText lastName = (EditText) findViewById(R.id.editText1);
		EditText firstName = (EditText) findViewById(R.id.firstName);
		EditText email = (EditText) findViewById(R.id.editText2);
		EditText address = (EditText) findViewById(R.id.editText4);
		EditText creditCard = (EditText) findViewById(R.id.editText3);
		EditText expiryDate = (EditText) findViewById(R.id.editText5);
		String[] split = u.split(",");
		lastName.setText(split[1]);
		firstName.setText(split[0]);
		email.setText(split[2]);
		address.setText(split[3]);
		creditCard.setText(split[4]);
		expiryDate.setText(split[5]);
	}
}
