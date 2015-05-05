package flyPack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Personal extends PersonalManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6493923256060181378L;

	/** This person's first name. */
	private String firstName;

	/** This person's last name. */
	private String lastName;

	/** This person's email. */
	private String email;

	/** This prson's address. */
	private String address;

	private Map<Flight, Flight[]> booking;
	
	public Map<Flight, Flight[]> getBooking(){
		return booking;
	}
	
	public ArrayList<Flight> getTransfers(Flight f){
		ArrayList<Flight> temp = new ArrayList<Flight>();
		Flight[] temp1 = new Flight[booking.get(f).length];
		temp1 = temp.toArray(temp1);
		return temp;
	}
	
	public void bookFlights(Flight flight){
		if(booking.containsKey(flight)){			
		} else {
			if (!flight.hasSeats()){				
			} else {
			flight.setSeat(flight.getSeat() - 1);
			booking.put(flight, null);
			}
		}		
	}
	
	public void bookFlights(Flight flight1, Flight flight2){
		if(booking.containsKey(flight1)){	
			if (!flight1.hasSeats() & !flight2.hasSeats()){				
			} else {
			ArrayList<Flight> temp = new ArrayList<Flight>
			(Arrays.asList(booking.get(flight1)));
			temp.add(flight2);
			Flight[] temp1 = new Flight[temp.size()];
			temp1 = temp.toArray(temp1);
			booking.put(flight1, temp1);
			}
		} else { 
			if (!flight1.hasSeats() & !flight2.hasSeats()){				
			} else {
				ArrayList<Flight> temp = new ArrayList<Flight>();
				temp.add(flight2);
				Flight[] temp1 = new Flight[temp.size()];
				temp1 = temp.toArray(temp1);
				booking.put(flight1, temp1);
			}
		}
	}
	
	/**
	 * Creates a default empty person.
	 */
	public Personal() {
		firstName = "";
		lastName = "";
		email = "";
		address = "";
		booking = new HashMap<Flight, Flight[]>();
	}

	/**
	 * Creates a person named firstName LastName, with email email and address
	 * address.
	 * 
	 * @param firstName
	 *            the first name of this person
	 * @param lastName
	 *            the last name of this person
	 * @param email
	 *            the email of this person
	 * @param address
	 *            the address of this person
	 */
	public Personal(String firstName, String lastName, String email,
			String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}

	/**
	 * Return the First Name of This person.
	 * 
	 * @return the first name of this person
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name of this person.
	 * 
	 * @param firstName
	 *            the first name of this person
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Sets the address of this person.
	 * 
	 * @param address
	 *            the address of this person
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Return the last name of this person.
	 * 
	 * @return the last name of this person
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name of this person.
	 * 
	 * @param lastName
	 *            the last name of this person
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Return the email of this person.
	 * 
	 * @return the email of this person
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email of this person.
	 * 
	 * @param email
	 *            the email of this person
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Return the address of this person.
	 * 
	 * @return the last name of this person
	 */
	public String getAddress() {
		return this.address;
	}
	
	@Override
	public String toString(){
		return (getFirstName() + " " + getLastName() + ", " + getAddress() + ", " + getEmail() + ".");
	}

}
