package g0392.phaseiii.csc207project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import flyPack.Admin;
import flyPack.Client;
import flyPack.User;
import flyPack.UserManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private UserManager uManager;
	public User user;
	public String cat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		uManager = new UserManager();
	}

	public void userAuthenticate() throws Exception {
		EditText emailText = (EditText) findViewById(R.id.editText1);
		EditText passwordText = (EditText) findViewById(R.id.editText2);
		
		String email = emailText.getText().toString();
		String password = passwordText.getText().toString();

		String path = (getFilesDir()
				.getAbsolutePath()) + "/" + "passwords.txt";

		Scanner keyboard = new Scanner(new File(path));
		int[] x = new int[2];

		// find the number of columns
		String nextLine = keyboard.nextLine();
		int numCols = nextLine.length();
		int numRows = 1;

		// find the number of rows
		while (keyboard.hasNext()) {
			numRows++;
			nextLine = keyboard.nextLine();
		}
		x[0] = numRows;
		x[1] = numCols;
		keyboard.close();

		String[][] userList = new String[numRows][2];

		// ///////////move
		Scanner sc = new Scanner(new File(path));
		int i = 0;
		while (sc.hasNextLine()) {			
			String string = sc.nextLine();
			String[] temp = string.split(",");;			
			userList[i][0] = temp[0].trim();
			userList[i][1] = temp[1].trim();
			i += 1;
		}				
		for (int r = 0; r < x[0]; r++) {
			if (userList[r][0].equals(email)) {// checking the username
				if (userList[r][1].equals(password)) {// checking password
					String[] s = email.split("@");					
					if (s[1] .equals("admin.com")) {
						User userAdmin = new Admin("admin");
						user = userAdmin;						
						cat = "admin";
						//return "admin";
					} else {
						User userClient = new Client();
						user = userClient;
						user.getBilling().setEmail(email);
						cat = "client";
						//return "client";
					}
				}
			}
		}
		//return "none";
	}

	/**
	 * Starts the program with user Admin.
	 * @param view
	 * @param user the user using the program.
	 * @throws Exception 
	 */
	public void start(View view) throws Exception {
		userAuthenticate();
		
		//String cat = userAuthenticate();
		if (cat == "admin"){
			Intent intent = new Intent(this, AdminActivity.class);
			intent.putExtra("admin", user);
			startActivity(intent);
		} else if (cat == "client"){
			Intent intent = new Intent(this, ClientActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("uManager", uManager);
			startActivity(intent);
		}		
	}		

	/**
	 * Starts the program with creating a new user.
	 * @param view.
	 */
	public void startSignUp(View view) {
		Intent intent = new Intent(this, ClientActivity.class);
		intent.putExtra("uManager", uManager);
		startActivity(intent);
	}

}
