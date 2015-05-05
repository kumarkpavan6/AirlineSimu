package flyPack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Client extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3806549955488017051L;

	/** The user name of Client. */
	private String userClient;

	/** The array list of searched flights sorted by time. */
	protected Flight[] sortedTime;

	/** The array list of searched flights sorted by cost. */
	protected Flight[] sortedCost;

	/** The map of searched flights. */
	protected Map<Flight, Flight[]> sorted;	

	/**
	 * Creates a Client user with user name userName.
	 * 
	 * @param userName
	 *            the user name of client
	 * @throws FileNotFoundException
	 */
	public Client(String email) throws FileNotFoundException {
		super();
		userClient = email;
	}

	public Map<Flight, Flight[]> getSorted(){
		return sorted;
	}
	
	public void setSorted(Map<Flight, Flight[]> map){
		this.sorted = map;
	}
	/**
	 * Creates a empty default client user.
	 */
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Upload all the personal information of clients.
	 * 
	 * @param fileName
	 *            the file containing all the personal information of clients.
	 */
	public void uploadPersonal(String fileName) {
		try {
			pObj.saveToCSVFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Upload all the billing information of clients.
	 * 
	 * @param fileName
	 *            the file containing all the billing information of clients.
	 */
	public void uploadBilling(String fileName) {
		try {
			bObj.saveToCSVFile(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Return a String representation of the searched flights sorted by travel
	 * time. Flights are searched from the data according to departure date
	 * depDate, origin origin and destination dest.
	 * 
	 * @param depDate
	 *            the departure date of the client
	 * @param origin
	 *            the origin of the client
	 * @param dest
	 *            the destination of the client
	 * @return the string representation of the searched flights sorted by
	 *         travel time.
	 */
	public String viewSearchByTime(String depDate, String origin, String dest) {
		sorted = fObj.searchItinearies(origin, dest, depDate);
		ArrayList<Flight> list = new ArrayList<Flight>(Arrays.asList(fObj.flightMapToArray(sorted))); 		
		sortedTime = fObj.sortByTravelTime(list);
		String re = "";
		for (Flight x : sortedTime) {
			re += x + "\n";
		}
		return re;
	}

	public ArrayList<Flight> viewSearchByTimeA(String depDate, String origin,
			String dest) {
		sorted = fObj.searchItinearies(origin, dest, depDate);
		ArrayList<Flight> list = new ArrayList<Flight>(Arrays.asList(fObj.flightMapToArray(sorted))); 		
		sortedTime = fObj.sortByTravelTime(list);
		ArrayList<Flight> output = new ArrayList<Flight>();
		Collections.addAll(output, sortedTime);
		return output;
	}

	/**
	 * Return a String representation of the searched flights sorted by cost.
	 * Flights are searched from the data according to departure date depDate,
	 * origin origin and destination dest.
	 * 
	 * @param depDate
	 *            the departure date of the client
	 * @param origin
	 *            the origin of the client
	 * @param dest
	 *            the destination of the client
	 * @return the string representation of the searched flights sorted by cost.
	 */
	public String viewSearchByCost(String depDate, String origin, String dest) {
		sorted = fObj.searchItinearies(origin, dest, depDate);
		ArrayList<Flight> list = new ArrayList<Flight>(Arrays.asList(fObj.flightMapToArray(sorted))); 		
		sortedTime = fObj.sortByCost(list);
		String re = "";
		for (Flight y : sortedCost) {
			re += y + "\n";
		}
		return re;
	}

	public ArrayList<Flight> viewSearchByCostA(String depDate, String origin,
			String dest) {
		sorted = fObj.searchItinearies(origin, dest, depDate);
		ArrayList<Flight> list = new ArrayList<Flight>(Arrays.asList(fObj.flightMapToArray(sorted))); 		
		sortedTime = fObj.sortByCost(list);
		ArrayList<Flight> output = new ArrayList<Flight>();
		Collections.addAll(output, sortedCost);
		return output;
	}

	/**
	 * Return a String representation of the searched flights. Flights are
	 * searched from the data according to departure date depDate, origin origin
	 * and destination dest.
	 * 
	 * @param depDate
	 *            the departure date of the client
	 * @param origin
	 *            the origin of the client
	 * @param dest
	 *            the destination of the client
	 * @return the string representation of the searched flights.
	 */
	public String searchItin(String origin, String dest, String depDate) {
		sorted = fObj.searchItinearies(origin, dest, depDate);
		Flight[] f;
		f = fObj.flightMapToArray(sorted);
		String re = "";
		for (Flight y : f) {
			re += y + "\n";
		}
		return re;
	}

	public Map<Flight, Flight[]> searchItinA(String origin, String dest,
			String depDate) {
		sorted = fObj.searchItinearies(origin, dest, depDate);
		return sorted;
	}

	/**
	 * Return a String representation of the flights. Flights are searched from
	 * the data according to departure date depDate, origin origin and
	 * destination dest.
	 * 
	 * @param depDate
	 *            the departure date of the client
	 * @param origin
	 *            the origin of the client
	 * @param dest
	 *            the destination of the client
	 * @return the string representation of the flights.
	 */
	public String getItin(String date, String origin, String destination) {
		Map<Flight, Flight[]> s = new HashMap<Flight, Flight[]>();
		s = fObj.searchItinearies(date, origin, destination);
		Flight[] temp;
		String k = "";
		temp = fObj.flightMapToArray(s);
		for (int i = 0; i < temp.length; i++) {
			k = k + temp[i].toString() + "\n";
		}
		return k;
	}
}
