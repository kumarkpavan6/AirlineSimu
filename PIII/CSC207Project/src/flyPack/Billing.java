package flyPack;

import java.io.Serializable;

public class Billing extends BillingManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8238581375273786215L;

	/**This Person's Last Name.*/
	private String lastName;
	
	/**This Person's First Name.*/
	private String firstName;
	
	/**This Person's Billing email.*/
	private String email;
	
	/**This Person's Billing Address.*/
	private String address;
	
	/**This Person's Credit Card Number.*/
	private String creditCardNumber;
	
	/**This Creditcard's Expire Date*/
	private String expireDate;
	
	/**
	 * Creates a Billing profile of a person named
	 * FirstName LastName, with Billing Address address,
	 * email email, and Credit Card creditCardNumber with 
	 * expire date expireDate.
	 * @param lastName the last name of this person
	 * @param firstName the first name of this person
	 * @param address the billing address of this person
	 * @param email the email of this person
	 * @param creditCardNumber the Credit Card Number of this Person
	 * @param expireDate the expire date of the credit card
	 */
	public Billing(String lastName, String firstName, String address, 
			String email, String creditCardNumber, String expireDate){
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address = address;
		this.creditCardNumber = creditCardNumber;
		this.expireDate = expireDate;	
	}
	
	
    /**
     * Creates a default empty billing profile.
     */
	public Billing() {
		super();
		this.lastName = "";
		this.firstName = "";
		this.address = "";
		this.email = "";
		this.creditCardNumber = "";
		this.expireDate = "";
	}


    /**
     * Returns a string representation of the billing information 
     * of this person.
     */
	@Override
	public String toString(){
		
		return this.firstName +","+ this.lastName 
				+ ","+ this.address + ","+ this.email
				+ "," +this.creditCardNumber
				+ "," +this.expireDate;
 	}

    /**
     * Returns the billing address of this person. 
     * @return the billing address of this person.
     */
	public String getAddress(){
		return this.address;
	}
	
	/**
	 * Sets the billing address of this person.
	 * @param address the billing address of this person.
	 */
	public void setAddress(String address){
		this.address = address;
	}
	
	/**
	 * Return the LastName of this person.
	 * @return the last name of this person. 
	 */
	public String getLastName() {
		return lastName;
	}

    /**
     * Sets the Last Name of this person.
     * @param lastName the last name of this person
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    /**
     * Return the FirstName of this person.
     * @return the first name of this person
     */
	public String getFirstName() {
		return firstName;
	}

    /**
     * Sets the first name of this person.
     * @param firstName the first name of this person
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    /**
     * Return the email of this person
     * @return the email of this person
     */
	public String getEmail() {
		return email;
	}

    /**
     * Sets the email of this person. 
     * @param email the email of this person.
     */
	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * Return the Credit Card Number of this person.
     * @return the credit card number of this person
     */
	public String getcreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * Sets the credit card number of this person.
	 * @param creditCardNumber the credit card number of this person
	 */
	public void setcreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

    /**
     * Return the Expire date of the person's credit card.
     * @return the expire date of the person's credit card
     * in the format YY-MM-DD
     */
	public String getExpireDate() {
		return expireDate;
	}

    /**
     * Sets the expire date of this Person's credit card.
     * @param expireDate the expire date of of this person's credit card
     * in the format YY-MM-DD
     */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
}
