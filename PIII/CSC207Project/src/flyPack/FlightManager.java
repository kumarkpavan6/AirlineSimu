package flyPack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class FlightManager implements Serializable {
	/** The Flight routes. */
	ArrayList<Flight> flights;
	Map<Flight, Flight[]> itineraries;

	private static final long serialVersionUID = -7526472295622776147L;

	/**
	 * Creates a empty default itinerary.
	 */

	Flight flight = new Flight();

	public FlightManager() {
		flights = new ArrayList<Flight>();
		itineraries = new HashMap<Flight, Flight[]>();
	}

	public Map<Flight, Flight[]> getItineraries() {
		return itineraries;
	}

	public void setItineraries(Map<Flight, Flight[]> itineraries) {
		this.itineraries = itineraries;
	}

	public ArrayList<Flight> getKeySet() {
		ArrayList<Flight> temp = new ArrayList<Flight>();
		temp.addAll(itineraries.keySet());
		return temp;
	}

	/**
	 * Return a map of searched itineraries according to clients origin origin,
	 * destination, and departure date date.
	 * 
	 * @param origin
	 *            the origin of the client
	 * @param destination
	 *            the destination of the client
	 * @param date
	 *            the departure date of the client
	 * @return a map of searched itineraries
	 */
	public Map<Flight, Flight[]> searchItinearies(String date, String origin,
			String destination) {
		Map<Flight, Flight[]> itineraries = new HashMap<Flight, Flight[]>();
		ArrayList<Flight> tempFlights = new ArrayList<Flight>();
		ArrayList<Flight> tempOrigin = new ArrayList<Flight>();
		ArrayList<Flight> tempDestination = new ArrayList<Flight>();
		/*
		 * Minimize the set of flights
		 */
		for (int i = 0; i < flights.size(); i++) {
			String[] splited = date.split("-");
			String[] splited1 = flights.get(i).getDepDate().split("-");
			if (Integer.parseInt(splited[0]) == (Integer.parseInt(splited1[0]))) {
				if (Integer.parseInt(splited[1]) == (Integer
						.parseInt(splited1[1]))) {
					if (Integer.parseInt(splited[2]) - 1 < (Integer
							.parseInt(splited1[2]))
							&& Integer.parseInt(splited1[2]) < Integer
									.parseInt(splited[2]) + 3) {
						tempFlights.add(flights.get(i));
					}
				}
			}
		}
		/*
		 * get flights with the same Origins
		 */
		for (int i = 0; i < tempFlights.size(); i++) {
			if (tempFlights.get(i).getOrigin().equals(origin)) {
				tempOrigin.add(tempFlights.get(i));
			}
		}
		/*
		 * get flights with the same Destinations
		 */
		for (int i = 0; i < tempFlights.size(); i++) {
			if (tempFlights.get(i).getDest().equals(destination)) {
				tempDestination.add(tempFlights.get(i));
			}
		}
		/*
		 * Add itineraries when the destination and the origin matches the
		 * argument
		 */
		for (int i = 0; i < tempFlights.size(); i++) {
			if (tempFlights.get(i).getOrigin().equals(origin)
					&& tempFlights.get(i).getDest().equals(destination)) {
				itineraries.put(tempFlights.get(i), null);
				Flight f = new Flight();
				f = tempFlights.get(i);
				tempOrigin.remove(f);
				tempDestination.remove(f);
			}
		}
		/*
		 * Find transfer flights within 6 hours
		 */
		for (int i = 0; i < tempOrigin.size(); i++) {
			ArrayList<Flight> arry = new ArrayList<Flight>();
			for (int n = 0; n < tempDestination.size(); n++) {
				/*
				 * 0 = month 1 = day 2 = hour 3 = minute
				 */
				int[] flight1 = dateTimeToInt(tempOrigin.get(i).getArrDate(),
						tempOrigin.get(i).getArrTime());
				int time1 = (flight1[2] * 60) + flight1[3];
				int[] flight2 = dateTimeToInt(tempDestination.get(n)
						.getDepDate(), tempDestination.get(n).getDepTime());
				int time2 = (flight2[2] * 60) + flight2[3];
				/*
				 * Destination of flight 1 is the same as the origin of flight 2
				 */
				if (tempOrigin.get(i).getDest()
						.equals(tempDestination.get(n).getOrigin())) {
					/*
					 * If both flights are flying on the same day
					 */
					if (tempOrigin.get(i).getArrDate()
							.equals(tempDestination.get(n).getDepDate())) {
						/*
						 * If both flights are less than 6 hours apart
						 */
						if ((time2 - time1) < 6 * 60) {
							arry.add(tempDestination.get(n));
						}
					}
				}
			}
			Flight[] temp = new Flight[arry.size()];
			itineraries.put(tempOrigin.get(i), arry.toArray(temp));
		}
		return itineraries;
	}

	/**
	 * Return the total travel time of a flight.
	 * 
	 * @param temp
	 *            the route of a flight
	 * @return the total travel time of a flight.
	 */
	public String getTravelTime(Flight temp) {
		/*
		 * 0 = month 1 = day 2 = hour 3 = minute
		 */
		int[] arrival = dateTimeToInt(temp.getArrDate(), temp.getArrTime());
		int[] departure = dateTimeToInt(temp.getDepDate(), temp.getDepTime());
		if (departure[1] == arrival[1]) {
			int tempArrival = (arrival[2] * 60) + arrival[3];
			int tempDeparture = (departure[2] * 60) + departure[3];
			int time = tempArrival - tempDeparture;
			int hours = time / 60;
			int minutes = time % 60;
			return "" + hours + ":" + "" + minutes;
		} else {
			int tempArrival = (arrival[2] * 60) + arrival[3];
			int tempDeparture = ((24 * 60) - ((departure[2] * 60) + departure[3]));
			int time = tempArrival + tempDeparture;
			int hours = time / 60;
			int minutes = time % 60;
			return "" + hours + ":" + "" + minutes;
		}
	}

	/**
	 * Return an integer representation of the travel time
	 * 
	 * @param the
	 *            travel time as a string
	 * @return the travel time an an integer
	 */
	public int getTravelTimeToInt(String s) {
		String[] splited1 = s.split(":");
		int hour = Integer.parseInt(splited1[0]);
		int minute = Integer.parseInt(splited1[1]);
		return ((hour * 60) + minute);
	}

	/**
	 * Return a String representation of the travel time
	 * 
	 * @param the
	 *            travel time as an integer
	 * @return the travel time an a string
	 */
	public String getTravelTimeToString(int time) {
		int hours = time / 60;
		int minutes = time % 60;
		return "" + hours + ":" + "" + minutes;
	}

	/**
	 * Return an array of Flights representing the flights
	 * 
	 * @param a
	 *            map of Flights
	 * @return an array of Flights
	 */
	public Flight[] flightMapToArray(Map<Flight, Flight[]> flights) {
		ArrayList<Flight> tempiti = new ArrayList<Flight>();
		Set<Flight> set = new TreeSet<Flight>();
		set = flights.keySet();
		int size = set.size();
		// loop through the keys
		for (int i = 0; i < size; i++) {
			Flight m = set.iterator().next();
			if (flights.get(m) == (null)) {
				tempiti.add(m);
			} else {
				Flight[] f = new Flight[flights.get(m).length];
				f = flights.get(m);
				for (int n = 0; n < f.length; n++) {
					Flight fn = f[n];
					String flightNum = m.getFlightNum() + " -> "
							+ fn.getFlightNum();
					String depDate = m.getDepDate();
					String depTime = m.getDepTime();
					String arrDate = fn.getArrDate();
					String arrTime = fn.getArrTime();
					String airline = m.getAirline() + " <-> flight "
							+ fn.getAirline();
					String travelTime = getTravelTimeToString(getTravelTimeToInt(m
							.getTravelTime())
							+ getTravelTimeToInt(fn.getTravelTime()));
					String dest = fn.getDest();
					String origin = m.getOrigin();
					double cost = m.getCost() + fn.getCost();
					int seat = m.getSeat() + fn.getSeat();
					tempiti.add(new Flight(flightNum, depDate, arrDate,
							arrTime, depTime, airline, origin, dest,
							travelTime, cost, seat));
				}
			}
			set.remove(m);
		}
		Flight[] temp = new Flight[tempiti.size()];
		return tempiti.toArray(temp);
	}

	/**
	 * Return the sorted list of itineraries from the cheapest to the most
	 * expensive flights.
	 * 
	 * @param a
	 *            map of Flights
	 * @return the list of flights from the cheapest to expensive.
	 */
	public Flight[] sortByCost(ArrayList<Flight> flights) {
		Flight[] tempiti = new Flight[flights.size()];
		tempiti = flights.toArray(tempiti);
		boolean flag = true;
		Flight temp;
		while (flag) {
			flag = false;
			for (int i = 0; i < tempiti.length - 1; i++) {
				if (tempiti[i].getCost() > tempiti[i + 1].getCost()) {
					temp = tempiti[i];
					tempiti[i] = tempiti[i + 1];
					tempiti[i + 1] = temp;
					flag = true;
				}
			}
		}
		return tempiti;
	}

	/**
	 * Return an array of flights given the departure date, origin, and
	 * destination
	 * 
	 * @param departure
	 *            date
	 * @param origin
	 * @param destination
	 * @return the list of flight from the least travel time to most travel
	 *         time.
	 */
	public Flight[] getFlights(String date, String origin, String destination) {
		ArrayList<Flight> temp = new ArrayList<Flight>();
		ArrayList<Flight> tempFlights = new ArrayList<Flight>(flights);
		for (int i = 0; i < flights.size(); i++) {
			Flight f = new Flight();
			f = tempFlights.iterator().next();
			if (f.getDepDate().equals(date) && f.getOrigin().equals(origin)
					&& f.getDest().equals(destination)) {
				temp.add(f);
			}
			tempFlights.remove(f);
		}
		Flight[] n = new Flight[temp.size()];
		return temp.toArray(n);
	}

	/**
	 * Return the sorted list of itineraries from the least travel time to most
	 * travel time.
	 * 
	 * @param a
	 *            map of Flights
	 * @return the list of flight from the least travel time to most travel
	 *         time.
	 */
	public Flight[] sortByTravelTime(ArrayList<Flight> flights) {
		Flight[] tempiti = new Flight[flights.size()];
		tempiti = flights.toArray(tempiti);
		boolean flag = true;
		Flight temp;
		while (flag) {
			flag = false;
			for (int i = 0; i < tempiti.length - 1; i++) {
				String[] splited1 = tempiti[i].getTravelTime().split(":");
				String[] splited2 = tempiti[i + 1].getTravelTime().split(":");
				int[] t1 = new int[splited1.length];
				int[] t2 = new int[splited2.length];
				t1[0] = Integer.parseInt(splited1[0]);
				t1[1] = Integer.parseInt(splited1[1]);
				t2[0] = Integer.parseInt(splited2[0]);
				t2[1] = Integer.parseInt(splited2[1]);
				int time1 = (t1[0] * 60) + t1[1];
				int time2 = (t2[0] * 60) + t2[1];
				if (time1 > time2) {
					temp = tempiti[i];
					tempiti[i] = tempiti[i + 1];
					tempiti[i + 1] = temp;
					flag = true;
				}
			}
		}
		return tempiti;
	}

	/**
	 * Save all the searched itineraries to a text to display to client.
	 */
	public void saveItineraryToText() {
		try {
			FileWriter writer = new FileWriter("Flights.txt");
			BufferedWriter out = new BufferedWriter(writer);
			for (int i = 0; i < flights.size(); i++) {
				out.write(flights.get(i).getFlightNum() + " ,"
						+ flights.get(i).getDepDate() + " "
						+ flights.get(i).getDepTime() + " ,"
						+ flights.get(i).getArrDate() + " "
						+ flights.get(i).getArrTime() + " ,"
						+ flights.get(i).getAirline() + " ,"
						+ flights.get(i).getOrigin() + " ,"
						+ flights.get(i).getDest() + " ,"
						+ flights.get(i).getCost() + " ," 
						+ flights.get(i).getSeat() + "\n");
			}
			out.close();
		} catch (Exception e) {
			System.err.println("Error! Type: " + e.getMessage());
			{
			}
		}
	}

	/**
	 * Read the file that contain all the itineraries and set them to flight
	 * object.
	 * 
	 * @param fileName
	 *            the file contain all the information of flight.
	 */
	public void readFileCSVFile(File path) {
		Scanner sc;
		try {
			sc = new Scanner(path);
			while (sc.hasNextLine()) {
				String nextLine = sc.nextLine();
				String[] split = nextLine.split(",");
				setFlights(split);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFileCSVFileString(String fileName) {
		try {
			String nextLine;
			FileReader file = new FileReader(fileName);
			BufferedReader in = new BufferedReader(file);
			for (int x = 0; x < getFileLineNum(fileName); x++) {
				nextLine = in.readLine();
				String[] split = nextLine.split(",");
				setFlights(split);
			}
		} catch (Exception e) {
			System.err.println("Error! Type: " + e.getMessage());
		}
	}

	/**
	 * Add flight object to the array list flights containing all itineraries.
	 * 
	 * @param s
	 *            the string representation of a flight.
	 */
	public void setFlights(String[] s) {
		Flight temp = new Flight();
		double cost = Double.parseDouble(s[6].trim());
		int seat = Integer.parseInt(s[7].trim());
		temp.setFlightNum(s[0]);
		temp.setAirline(s[3]);
		temp.setOrigin(s[4]);
		temp.setDest(s[5]);
		temp.setCost(cost);
		String[] dep = getDateTime(s[1]);
		temp.setDepDate(dep[0]);
		temp.setDepTime(dep[1]);
		String[] dep1 = getDateTime(s[2]);
		temp.setArrDate(dep1[0]);
		temp.setArrTime(dep1[1]);
		temp.setTravelTime(getTravelTime(temp));
		temp.setSeat(seat);
		flights.add(temp);
	}

	/**
	 * Return a array list of string with separated date and time. Separate Date
	 * and time form one string.
	 * 
	 * @param s
	 *            the string representation of date and time
	 * @return a list of separated date and time
	 */
	public String[] getDateTime(String s) {
		String[] split = s.split("\\s+");
		String date = split[0];
		String time = split[1];
		String[] dateTime = { date, time };
		return dateTime;
	}

	/**
	 * Return an integer representation of date and time.
	 * 
	 * @param date
	 *            the string representation of date
	 * @param time
	 *            the string representation of time
	 * @return an integer representation of date and time
	 */
	public int[] dateTimeToInt(String date, String time) {
		/*
		 * 0 = month 1 = day 2 = hour 3 = minute
		 */
		String[] splited = date.split("-");
		String[] splited1 = time.split(":");
		int month = Integer.parseInt(splited[1]);
		int day = Integer.parseInt(splited[2]);
		int hour = Integer.parseInt(splited1[0]);
		int minute = Integer.parseInt(splited1[1]);
		int[] temp = { month, day, hour, minute };
		return temp;
	}

	/**
	 * Return a string representation of flight information with flight number,
	 * departure date, departure time arrival date and time destination origin,
	 * and cost.
	 * 
	 * @param iti
	 *            the flight object of a itinerary.
	 * @return a string representation of the flight.
	 */
	public String getFlightInfo(Flight iti) {
		String s = "";
		s = iti.getFlightNum() + iti.getDepDate() + " " + iti.getDepTime()
				+ iti.getArrDate() + " " + iti.getArrTime() + iti.getAirline()
				+ iti.getOrigin() + iti.getDest() + iti.getCost();
		return s;
	}

	/**
	 * Return the number of flights in the file fileName.
	 * 
	 * @param fileName
	 *            the file containing all the information of flights.
	 * @return the number of flights in the file fileName.
	 */
	public int getFileLineNum(String fileName) {

		int lnNum = 0;
		try {
			FileReader reader = new FileReader(fileName);
			BufferedReader line = new BufferedReader(reader);
			while (line.readLine() != null) {
				lnNum++;
			}

		} catch (Exception e) {
			System.err.println("Error! Type: " + e.getMessage());
			e.printStackTrace();
		}
		return lnNum;

	}
}
