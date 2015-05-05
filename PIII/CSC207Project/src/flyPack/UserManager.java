package flyPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map;



public class UserManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4528844201772531822L;

	/**The list of users in 2D grid.*/
	private String userList[][];
	
	/**The number of users in the system.*/
	private int[] dim;
	
	private Map<String, User> users;

	public UserManager() {
		users = new HashMap<String, User>();
	}
	
	public User userAuthenticate(String user, String pass, File fileName, String filePath) throws Exception {
		loadList(fileName, filePath);
		int c;
		for (int r = 0; r < dim[0]; r++) {
			if (userList[r][0] == user) {// checking the username
				if (userList[r][1] == pass) {// checking password
					String[] s = user.split("@");
					if (s[1] == "admin.com"){
						User userAdmin = new Admin("admin");
						return userAdmin;
					} else { 
						User userClient = new Client();
						return userClient;
					}
				} else {
					throw new InvalidPasswordException(); // password does not
															// match, throws an
															// exception.
				}

			} else {
				throw new UserNotRegisteredException(
						"Invalid or unregistered user name.");
			}

		}
		return null;
	}
	

	private void loadList(File fileName, String filePath) throws Exception {
		
		 Scanner keyboard = new Scanner(new File(filePath));

	        // find the number of columns
	        String nextLine = keyboard.nextLine();
	        int numCols = nextLine.length();
	        int numRows = 1;

	        // find the number of rows
	        while (keyboard.hasNext()) {
	            numRows++;
	            nextLine = keyboard.nextLine();
	        }
	        dim[0] = numRows;
	        dim[1] = numCols;
	        keyboard.close();
	        
	        
	        Scanner sc = new Scanner(new File(filePath));
	        List<String> lines = new ArrayList<String>();
	        while (sc.hasNextLine()) {
	          lines.add(sc.nextLine());
	        }

	        String[] arr = lines.toArray(new String[0]);
	        for (int i = 0; i < dim[0]; i++){ //move through rows
	        	
	        	String[] temp;
	        	temp = arr[i].split(",");
		    	userList[i][0] = temp[0];
		    	userList[i][1] = temp[1];
		    	
		    }
	}
	


	public void readFromCSVFile(String filePath) throws ClassNotFoundException,
			FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream(filePath));
		String[] record;

		while (sc.hasNextLine()) {
			record = sc.nextLine().split(",");
			String email = record[0];
			String password = record[1];
			User user = new User(email, password);
			users.put(user.getUserName(), user);
		}
		sc.close();
	}

	public void printUsers() {
		for (User u : users.values()) {
			System.out.println(u.getUserName() + " " + u.getPassWord());
		}
	}

	public void add(User u) {
		users.put(u.getUserName(), u);
	}

	public Map<String, User> getUsers() {
		return this.users;
	}

	public void saveToCSVFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			FileWriter fileWriter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			for (Map.Entry<String, User> user : users.entrySet()) {
				String email = user.getValue().getUserName();
				String password = user.getValue().getPassWord();
				String toWrite = email + " " + password;
				bufferWriter.write("/n");
				bufferWriter.write(toWrite);
			}
			bufferWriter.close();
		}
	}

}
