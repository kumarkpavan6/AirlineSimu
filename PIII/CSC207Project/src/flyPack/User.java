package flyPack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2287378488661821848L;

	/**The user name and password of the user.*/
	private String userName, passWord;
	
	/**The list of users in 2D grid.*/
	private String userList[][];
	
	/**The number of users in the system.*/
	private int[] dim;
	
	/**The boolean representation if the user is real.*/
	private boolean userReal;
	
	/**The type of the user.*/
	private String userType; //Is he an Admin(A) or Client(C)
	
	/**The Map that contains all information of users.*/
	protected PersonalManager pObj;
	
	/**The map that contains all the billing information of users.*/
	protected BillingManager bObj;
	
	/**The flight information.*/
	protected FlightManager fObj;
	 
	/**
	 * The empty default user.	
	 */
	
	public FlightManager getFlightManager(){
		return fObj;
	}
	
	public Billing getBilling(){
		return (Billing) bObj;
	}
	public BillingManager getBillingManager(){
		return (BillingManager) bObj;
	}
	public void setBillingManager(BillingManager billingManager){
		bObj = billingManager;
	}
	
	public PersonalManager getPersonalManager(){
		return (PersonalManager) pObj;
	}
	public void setPersonalManager(PersonalManager personalManager){
		pObj = personalManager;
	}
	
	public void setBilling(Billing billing){
		bObj = billing;
	}
	
	public Personal getPersonal(){
		return (Personal) pObj;
	}
	
	public void setPersonal(Personal personal){
		pObj = personal;
	}
	
	public User(){
		pObj = new Personal();
		bObj = new Billing();
		fObj = new FlightManager();
	}
	
	public User(String admin){
		pObj = new PersonalManager();
		bObj = new BillingManager();
		fObj = new FlightManager();
	}
	
	
	/**
	 * Check if the user has the correct user name and password.
	 * @param user the user name of the user
	 * @param pass the password of the user
	 * @throws Exception
	 */
	
	public User(String email, String password){
		pObj = new Personal();
		bObj = new Billing();
		fObj = new FlightManager();
		userName = email;
		passWord = password;
	}
	
	public void userAuthenticate(String user, String pass)throws Exception{
		loadList();
		int c, r;
		for(r = 0; r < dim[0]; r++){
			if(userList[r][0] == user){//checking the username
				if(userList[r][1] == pass){//checking password
					userReal = true; //user is registered and password is correct
					userType = userList[r][2];
				}
				else{
					throw new InvalidPasswordException(); //password does not match, throws an exception.
				}
				
			}
			else{
				throw new UserNotRegisteredException("Invalid or unregistered user name.");
			}
			
		}
		
	}
	
	/**
	 *  Load all USERNAMES and associated PASSWORDS and TYPES into a 2D Array.
	 */
	private void loadList() throws Exception { 
		
		BufferedReader read = new BufferedReader(new FileReader("passwords.txt"));   
	    String line = " ";
	    String [] temp;

	    while ((line = read.readLine())!= null){
	    	temp = line.split(" ");

	        for(int i = 0; i < userList.length; i++) {
	        	for (int j = 0; j < userList.length; j++) {   //MIGHT HAVE TO ADD THE COL for .length 
	        		userList[i][j] = temp[i];

	            }
	        }
	        read.close();
	    }
	}
	
	/**
	 * Return the size of the file fileName.
	 * @param fileName the file contains all the information of the user
	 * @return the size of the file with format [number of rows, number of colms].
	 * @throws FileNotFoundException
	 */
	private int[] getDimensions(String fileName) throws FileNotFoundException {       
        
        Scanner keyboard = new Scanner(new File(fileName));
        int[] x = new int[2];

        // find the number of columns
        String nextLine = keyboard.nextLine();
        int numCols = nextLine.length();
        int numRows = 1;

        // find the number of rows
        while (keyboard.hasNext()) {
            numRows++;
            nextLine = keyboard.nextLine();
        }
        x[0] = numRows;
        x[1] = numCols;
        keyboard.close();
        
        return x;
    }
	
	/**
	 * Return the password of the User.
	 * @return the password of the user
	 */
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * Sets the password of the user.
	 * @param passWord the password of the user
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * Return the user name of the user.
	 * @return the user name of the user
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name of the user.
	 * @param userName the user name of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Return the 2D grid of the user list.
	 * @return a 2D grid of the user list
	 */
	public String[][] getUserList() {
		return userList;
	}
	
	/**
	 * Sets the 2D grid of the user list.
	 * @param userList the list contains all the users.
	 */
	public void setUserList(String[][] userList) {
	    this.userList = userList;
	}
	
	/**
	 * Return whether the user exists.
	 * @return true if the user is exists, false if the user is not real.
	 */
	public boolean isUserReal() {
		return userReal;
	}
	
	/**
	 * Sets whether the user exists.
	 * @param userReal true if the user exists, false if the user is not real
	 */
	public void setUserReal(boolean userReal) {
		this.userReal = userReal;
	}
	
	/**
	 * Return the type of the user.
	 * @return return the type of the user (client or admin)
	 */
	public String getUserType() {
		return userType;
	}
	
	/**
	 * Sets the type of user.
	 * @param userType the type of user (client or admin)
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
