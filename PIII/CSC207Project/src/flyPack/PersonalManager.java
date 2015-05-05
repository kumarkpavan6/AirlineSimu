package flyPack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;



public class PersonalManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832041874929936445L;
	
	
	/** Creates a map contains data of people with information.*/
	private Map<String, Personal> people;	
	
	/**
	 * Creats a Personal Manager class that reads from file filePath.
	 * @param filePath the csv file that contains all the information of users
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public PersonalManager(String filePath) throws ClassNotFoundException, IOException{
		people = new HashMap<String, Personal>();
		File file = new File(filePath);
	    if (file.exists()) {
	    	readFromCSVFile(filePath);
	    } else {
	        file.createNewFile();
	    }
		
	}
	
	/**
	 * Creates a empty default Personal Manager.
	 */
	public PersonalManager() {
		// TODO Auto-generated constructor stub
		people = new HashMap<String, Personal>();
	}
		
	
	/**
	 * Read from file filePath and set it to each person object.
	 * @param filePath the csv file that contains all the information of users.
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public void readFromCSVFile(String filePath) throws ClassNotFoundException, FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream(filePath));
		String [] record;
		Personal person;
		
		while(sc.hasNextLine()){
			record = sc.nextLine().split(",");
			String lastName = record[0];
			String firstName = record[1];
			String email = record[2];
			String address = record[3];
			person = new Personal(firstName, lastName, email, address);
			people.put(person.getEmail(), person);
		}
		sc.close();
	}
	
	/**
	 * Print the person with name email and address.
	 */
	public void printPeople(){
		for(Personal p : people.values()){
			System.out.println(p.getFirstName() +" " + p.getLastName() +" " 
		+  p.getEmail() +" " + p.getAddress());
		}
	}
	
	/**
	 * Add the person to the map.
	 * Set the email of this person the key of the map.
	 * Set the information to the value of the map.
	 * @param p the personal object contains all the information of the person
	 */
	public void add(Personal p){
		people.put(p.getEmail(), p);
	}
	
	/**
	 * Save the information of the person the csv file.
	 * @param filePath the file contains all the information of users
	 * @throws IOException
	 */
	public void saveToCSVFile(String filePath) throws IOException {
		File file = new File(filePath);
		if(file.exists()){
			FileWriter fileWriter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			for(Map.Entry<String, Personal> person : people.entrySet()){
				String firstName = person.getValue().getFirstName();
				String lastName = person.getValue().getLastName();
				String email = person.getValue().getEmail();
				String address = person.getValue().getAddress();
				String toWrite = lastName + "," + firstName + "," + email + "," + address;
				bufferWriter.write("\n");
				bufferWriter.write(toWrite);
			}
			bufferWriter.close();
		}else{
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file.getName());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			for(Map.Entry<String, Personal> person : people.entrySet()){
				String firstName = person.getValue().getFirstName();
				String lastName = person.getValue().getLastName();
				String email = person.getValue().getEmail();
				String address = person.getValue().getAddress();
				String toWrite = lastName + "," + firstName + "," + email + "," + address;
				bufferWriter.write("\n");
				bufferWriter.write(toWrite);
			}
			bufferWriter.close();
		}
		
        
    }
	

}

