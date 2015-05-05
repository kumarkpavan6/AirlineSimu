package g0392.phaseiii.csc207project;

import flyPack.Personal;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayPersonalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_personal);
        
		//Gets the Intent passed from MainActivity.
		Intent intent = getIntent();
  
		//Uses key "personalKey" to get personal object.
		Personal pInfo = (Personal) intent.getSerializableExtra("personalKey");

		// Sets TextView to the Person's name.
		TextView newlyRegistered = (TextView) findViewById(R.id.personalAdded_field);
		newlyRegistered.setText("Added Personal Info: " + pInfo.toString());
	
	}
}
