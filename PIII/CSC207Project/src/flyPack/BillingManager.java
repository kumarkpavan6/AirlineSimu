package flyPack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BillingManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8558305706503479284L;
	/**A map of billing information with String Keys and Billing values*/
	private Map<String, Billing> billingInfo;
	
	/**
	 * Creates a billing manager that reads from file fileFilePath.
	 * @param filePath the csv file contains all the billing information
	 * @throws ClassNotFoundException 
	 * @throws IOException
	 */
	public BillingManager(String filePath) throws ClassNotFoundException, IOException{
		billingInfo = new HashMap<String, Billing>();
		File file = new File(filePath);
	    if (file.exists()) {
	    	readFromCSVFile(filePath);
	    } else {
	        file.createNewFile();
	    }
		
	}
	
	/**
	 * Creates a billing manager that reads from file fileFilePath.
	 * @param filePath the csv file contains all the billing information
	 * @return BillingInfo
	 */
	public Map<String, Billing> getBilling(){
		return billingInfo;
		
	}
	
	/**
	 * Creates a empty default Billing Manager.
	 */
	public BillingManager() {
		billingInfo = new HashMap<String, Billing>();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Read from filePath and set it to new billing class.
	 * @param filePath the csv file contains all the billing information
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public void readFromCSVFile(String filePath) throws ClassNotFoundException, FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream(filePath));
		String [] record;
		Billing billinfo;
		
		while(sc.hasNextLine()){
			record = sc.nextLine().split(",");
			String lastName = record[0];
			String firstName = record[1];
			String email = record[2];
			String address = record[3];
			String creditCardNumber = record[4];
			String expireDate = record[5];
			billinfo = new Billing(firstName, lastName, email, address, creditCardNumber, expireDate);
			billingInfo.put(email, billinfo);
		}
		sc.close();
	}
	
	/**
	 * Prints the billing information of Name email and address.
	 */
	public void printbillingInfo(){
		for(Billing b : billingInfo.values()){
			System.out.println(b.getFirstName() +" " + b.getLastName() +" " 
		+  b.getEmail() +" " + b.getAddress());
		}
	}
	
	/**
	 * Add the billing information of the person to the data map.
	 * Make email the key of the map, and billing class the value.
	 * @param b the billing class, which contains all information of the person.
	 */
	public void add(Billing b){
		billingInfo.put(b.getEmail(), b);
	}
	
	/**
	 * Save the billing information of the person to csv file.
	 * @param filePath the file contains all the billing information of the users.
	 * @throws IOException
	 */
	public void saveToCSVFile(String filePath) throws IOException {
		File file = new File(filePath);
		if(file.exists()){
			FileWriter fileWriter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			for(Map.Entry<String, Billing> billing : billingInfo.entrySet()){
				String firstName = billing.getValue().getFirstName();
				String lastName = billing.getValue().getLastName();
				String address = billing.getValue().getAddress();
				String email = billing.getValue().getEmail();
				String creditCardNumber = billing.getValue().getcreditCardNumber();
				String expireDate = billing.getValue().getExpireDate();
				String toWrite = lastName + "," + firstName + "," + email + "," + address + "," + creditCardNumber + "," + expireDate;
				bufferWriter.write("\n");
				bufferWriter.write(toWrite);
			}
			bufferWriter.close();
		}

    }
	
	/**
	 * Return the billing information of the person with email email.
	 * @param email the email of the person
	 * @return
	 */
    public Billing getbillingInfo(String email){
		return billingInfo.get(email);
	}
    
}



