package flyPack;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Flight implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2773482438852058258L;

	/**
	 * 
	 */

	static DecimalFormat format = new DecimalFormat("#,###.00");

	/**The flight number of the Itinerary.*/
	private String flightNum;
	
	/**The Departure Date of the Itinerary.*/
	private String depDate;
	
	/**The Departure Time of the Itinerary.*/
	private String depTime;
	
	/**The Arrival Date of the Itinerary.*/
	private String arrDate;
	
	/**The Arrival Time of the Itinerary.*/
	private String arrTime;
	
	/**The airline number of the Itinerary.*/
	private String airline; 
	
	/**The Origin of the Itinerary.*/
	private String origin; 
	
	/**The destination of the Itinerary.*/
	private String dest;
	
	/**The total travel time of the Itinerary.*/
	private String travelTime;
	
	/**The cost of the Itinerary.*/
	private double Cost;
	
	private int seat; 
	
	//static public DecimalFormat priceFormat = new DecimalFormat("$####.00");
	
	public boolean hasSeats(){
		if (seat != 0){
			return true;
		} else {
			return false;
		}
	}
	
	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * Creates a empty default itinerary. 
	 */
	public Flight(){		
	}
	
	public Flight(String flightNum, String depDate, String arrDate, String arrTime,
			String depTime, String airline, String origin, String dest, String travelTime,
			double Cost, int seat){
		this.flightNum = flightNum;
		this.depDate = depDate;
		this.arrDate = arrDate;
		this.arrTime = arrTime;		
		this.depTime = depTime;
		this.airline = airline;
		this.origin = origin;
		this.dest = dest;
		this.travelTime = travelTime;
		this.Cost = Cost;
		this.seat = seat;
	}
	
	@Override
	/**
	 * Return a string representation of the itinerary
	 * with flight number, departure date and time, arrival date and time,
	 * origin, destination, cost and travel time of the itinerary. 
	 */
	public String toString() {
		return flightNum + "," + depDate + " " + depTime +
				"," + arrDate + " " + arrTime + "," +airline + "," +origin + "," +
				dest + "," + format.format(Cost) + seat;
				
	}

	/**
	 * Return the flight Number of the Itinerary.
	 * @return the flight number of the Itinerary
	 */
	public String getFlightNum() {
		return flightNum;
	}
	
	/**
	 * Sets the flight number of the Itinerary.
	 * @param flightNum the flight number of the Itinerary
	 */
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	
	/**
	 * Return the Departure Date of the Itinerary.
	 * @return the Departure date of the Itinerary
	 */
	public String getDepDate() {
		return depDate;
	}
	
	/**
	 * Sets the Departure date of the Itinerary.
	 * @param depDate the departure date of the itinerary
	 */
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	
	/**
	 * Return the departure time of the itinerary.
	 * @return the departure time of the itinerary
	 */
	public String getDepTime() {
		return depTime;
	}
	
	/**
	 * Sets the Departure time of the itinerary.
	 * @param depTime the departure time of the itinerary
	 */
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	
	/**
	 * Return the arrival date of the itinerary.
	 * @return the arrival date of the itinerary
	 */
	public String getArrDate() {
		return arrDate;
	}
	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
	
	/**
	 * Return the arrival time of the itinerary.
	 * @return the arrival time of the itinerary
	 */
	public String getArrTime() {
		return arrTime;
	}
	
	/**
	 * Sets the arrival time of the itinerary.
	 * @param arrTime the 
	 */
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	
	/**
	 * Return the airline company of the itinerary.
	 * @return the airline of the itinerary
	 */
	public String getAirline() {
		return airline;
	}
	
	/**
	 * Sets the airline number of the itinerary.
	 * @param airline the airline number of the itinerary
	 */
	public void setAirline(String airline) {
		this.airline = airline;
	}
	
	/**
	 * Return the original of the itinerary.
	 * @return the original of the itinerary
	 */
	public String getOrigin() {
		return origin;
	}
	
	/**
	 * Sets the origin of the itinerary.
	 * @param origin the origin of the itinerary
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * Return the destination of the itinerary.
	 * @return return the destination of the itinerary
	 */
	public String getDest() {
		return dest;
	}
	
	/**
	 * Sets the destination of the itinerary. 
	 * @param dest the destination of the itinerary
	 */
	public void setDest(String dest) {
		this.dest = dest;
	}
	
	/**
	 * Return the travel time of the itinerary.
	 * @return the travel time of the itinerary
	 */
	public String getTravelTime() {
		return travelTime;
	}
	
	/**
	 * Sets the travel time of the itinerary. 
	 * @param travelTime the travel time of the itinerary
	 */
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	
	/**
	 * Return the cost of the itinerary.
	 * @return the cost of the itinerary
	 */
	public double getCost() {
		return Cost;
	}
	
	/**
	 * Sets the cost of the itinerary.
	 * @param cost2 the cost of the itinerary
	 */
	public void setCost(double cost2) {
		Cost = cost2;
	} 
	
				
}
