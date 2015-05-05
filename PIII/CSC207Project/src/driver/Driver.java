package driver;

import java.io.IOException;

import flyPack.*;

/** A Driver used for autotesting the project backend. */
public class Driver {
	static User aTester;
	static BillingManager bm;

	/**
	 * Uploads client information to the application from the file at the given
	 * path.
	 * 
	 * @param path
	 *            the path to an input csv file of client information with lines
	 *            in the format:
	 *            LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
	 *            (the ExpiryDate is stored in the format YYYY-MM-DD)
	 */
	public static void uploadClientInfo(String path) {
		// TODO: complete this method body

		try {
			bm = new BillingManager(path);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Uploads flight information to the application from the file at the given
	 * path.
	 * 
	 * @param path
	 *            the path to an input csv file of flight information with lines
	 *            in the format:
	 *            Number,DepartureDateTime,ArrivalDateTime,Airline
	 *            ,Origin,Destination,Price,NumSeats (the dates are in the
	 *            format YYYY-MM-DD HH:MM; the price has exactly two decimal
	 *            places; the number of seats is a non-negative integer)
	 */
	public static void uploadFlightInfo(String path) {
		// TODO: complete this method body
		aTester = new Admin();
		((Admin) aTester).uploadFlight(path);
	}

	/**
	 * Returns the information stored for the client with the given email.
	 * 
	 * @param email
	 *            the email address of a client
	 * @return the information stored for the client with the given email in
	 *         this format:
	 *         LastName,FirstNames,Email,Address,CreditCardNumber,ExpiryDate
	 *         (the ExpiryDate is stored in the format YYYY-MM-DD)
	 */
	public static String getClient(String email) {
		// TODO: complete this method body
		return bm.getbillingInfo(email).toString();
	}

	/**
	 * Returns all flights that depart from origin and arrive at destination on
	 * the given date.
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight origin
	 * @param destination
	 *            a flight destination
	 * @return the flights that depart from origin and arrive at destination on
	 *         the given date formatted with one flight per line in exactly this
	 *         format:
	 *         Number,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
	 *         ,Price (the departure and arrival date and time are in the format
	 *         YYYY-MM-DD HH:MM; the price has exactly two decimal places)
	 */
	public static String getFlights(String date, String origin,
			String destination) {
		// TODO: complete this method body
		return ((Admin) aTester).searchItin(origin, destination, date);
	}

	/**
	 * Returns all itineraries that depart from origin and arrive at destination
	 * on the given date. If an itinerary contains two consecutive flights F1
	 * and F2, then the destination of F1 should match the origin of F2. To
	 * simplify our task, if there are more than 6 hours between the arrival of
	 * F1 and the departure of F2, then we do not consider this sequence for a
	 * possible itinerary (we judge that the stopover is too long).
	 *
	 * Every flight in an itinerary must have at least one seat available for
	 * sale. That is, the itinerary must be bookable.
	 *
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight original
	 * @param destination
	 *            a flight destination
	 * @return itineraries that depart from origin and arrive at destination on
	 *         the given date with stopovers at or under 6 hours. Each itinerary
	 *         in the output should contain one line per flight, in the format:
	 *         Number
	 *         ,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
	 *         followed by total price (on its own line, exactly two decimal
	 *         places) followed by total duration (on its own line, in format
	 *         HH:MM).
	 */
	public static String getItineraries(String date, String origin,
			String destination) {
		// TODO: complete this method body
		return ((Admin) aTester).getItin(date, origin, destination);
	}

	/**
	 * Returns the same itineraries as getItineraries produces, but sorted
	 * according to total itinerary cost, in non-decreasing order.
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight original
	 * @param destination
	 *            a flight destination
	 * @return itineraries (sorted in non-decreasing order of total itinerary
	 *         cost) that depart from origin and arrive at destination on the
	 *         given date with stopovers at or under 6 hours. Each itinerary in
	 *         the output should contain one line per flight, in the format:
	 *         Number
	 *         ,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
	 *         followed by total price (on its own line, exactly two decimal
	 *         places) followed by total duration (on its own line, in format
	 *         HH:MM).
	 */
	public static String getItinerariesSortedByCost(String date, String origin,
			String destination) {
		// TODO: complete this method body
		return ((Admin) aTester).viewSearchByCost(date, origin, destination);
	}

	/**
	 * Returns the same itineraries as getItineraries produces, but sorted
	 * according to total itinerary travel time, in non-decreasing order.
	 * 
	 * @param date
	 *            a departure date (in the format YYYY-MM-DD)
	 * @param origin
	 *            a flight original
	 * @param destination
	 *            a flight destination
	 * @return itineraries (sorted in non-decreasing order of travel itinerary
	 *         travel time) that depart from origin and arrive at destination on
	 *         the given date with stopovers at or under 6 hours. Each itinerary
	 *         in the output should contain one line per flight, in the format:
	 *         Number
	 *         ,DepartureDateTime,ArrivalDateTime,Airline,Origin,Destination
	 *         followed by total price (on its own line, exactly two decimal
	 *         places), followed by total duration (on its own line, in format
	 *         HH:MM).
	 */
	public static String getItinerariesSortedByTime(String date, String origin,
			String destination) {
		// TODO: complete this method body
		return ((Admin) aTester).viewSearchByTime(date, origin, destination);
	}

}
