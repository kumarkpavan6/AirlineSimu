package flyPack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User{
	
	/** The user name of Admin.*/
	private String userAdmin;
	
	/**The array list of searched flights sorted by time.*/
	protected Flight[] sortedTime;
	
	/**The array list of searched flights sorted by cost.*/
	protected Flight[] sortedCost;
		
	/**The map of searched flights.*/
	protected Map<Flight, Flight[]> sorted;
	
	
	/**
	 * Creates a Administrative user with user name userName.
	 * @param userName the user name of admin
	 * @throws FileNotFoundException
	 */
	public Admin(String userName) throws FileNotFoundException {
		super("admin");
		userAdmin = userName;
	}
	
	/**
	 * Creates a empty default admin user.
	 */
	public Admin() {
		super("admin");
	}

	/**
	 * Upload the flights from file fileName.
	 * @param fileName the file containing all information about flight.
	 */
	public void uploadFlight(String fileName){
		fObj.readFileCSVFileString(fileName);
	}
	
	/**
	 * Upload all the personal information of clients. 
	 * @param fileName the file containing all the personal
	 * information of clients.
	 */
	public void uploadPersonal(String fileName){
		try {
			pObj.saveToCSVFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Upload all the billing information of clients. 
	 * @param fileName the file containing all the billing 
	 * information of clients.
	 */
	public void uploadBilling(String fileName){
		try {
			bObj.saveToCSVFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Return a String representation of the searched flights sorted by travel time.
	 * Flights are searched from the data according to departure date depDate, 
	 * origin origin and destination dest.
	 * @param depDate the departure date of the client
	 * @param origin the origin of the client
	 * @param dest the destination of the client
	 * @return the string representation of the searched flights sorted by travel time.
	 */
	public String viewSearchByTime(String depDate, String origin, String dest ){
		sorted = fObj.searchItinearies(depDate, origin, dest);
		ArrayList<Flight> list = new ArrayList<Flight>(Arrays.asList(fObj.flightMapToArray(sorted))); 		
		sortedTime = fObj.sortByTravelTime(list);
		String k = "";
		for(Flight y : sortedTime){
			if (y.equals(null)){
				k = k + "\n";
			}
			k = y + "\n";
		}		
	return k;
	}
	
	/**
	 * Return a String representation of the searched flights sorted by cost.
	 * Flights are searched from the data according to departure date depDate, 
	 * origin origin and destination dest.
	 * @param depDate the departure date of the client
	 * @param origin the origin of the client
	 * @param dest the destination of the client
	 * @return the string representation of the searched flights sorted by cost.
	 */
	public String viewSearchByCost(String depDate, String origin, String dest){
		sorted = fObj.searchItinearies(depDate, origin, dest);
		ArrayList<Flight> list = new ArrayList<Flight>(Arrays.asList(fObj.flightMapToArray(sorted))); 		
		sortedTime = fObj.sortByCost(list);
		String k = "";
		for(Flight y : sortedTime){
			if (y.equals(null)){
				k = k + "\n";
			}
			k = y + "\n";
		}		
	return k;
	}
	
	/**
	 * Return a String representation of the searched flights.
	 * Flights are searched from the data according to departure date depDate, 
	 * origin origin and destination dest.
	 * @param depDate the departure date of the client
	 * @param origin the origin of the client
	 * @param dest the destination of the client
	 * @return the string representation of the searched flights.
	 */
	public String searchItin(String origin,String dest,String depDate){
		Flight[] f; 
		f = fObj.getFlights(depDate, origin, dest);
		String re = "";
		for(Flight y : f){
			re = y + "\n";
		}
		return re;
	}
	
	/**
	 * Return a String representation of the flights.
	 * Flights are searched from the data according to departure date depDate, 
	 * origin origin and destination dest.
	 * @param depDate the departure date of the client
	 * @param origin the origin of the client
	 * @param dest the destination of the client
	 * @return the string representation of the flights.
	 */
	public String getItin(String date, String origin, String destination){
		Map<Flight, Flight[]> s = new HashMap<Flight, Flight[]>();
		s = fObj.searchItinearies(date, origin, destination);
		Flight[] temp;
		String k = "";
		temp = fObj.flightMapToArray(s);
		for(int i = 0; i < temp.length; i ++){				
				k = k + temp[i].toString() + "\n";
			}		
		return k;
}
}
	
