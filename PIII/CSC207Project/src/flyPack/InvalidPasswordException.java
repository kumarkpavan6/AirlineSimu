package flyPack;

public class InvalidPasswordException extends Exception {
	
	
	private static final long serialVersionUID = 9067417722233109851L;


	public InvalidPasswordException(){
		super();
	}
	public InvalidPasswordException(String message){
		super(message);
	}
    public InvalidPasswordException(Throwable cause) {
        super(cause);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordException(String message, Throwable cause,
        boolean enableSuppression, boolean writableStackTrace) {
        super();
    }
}


