package exceptions;

public class GeneralException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3937693747269344853L;
    
	private String description = "";
	
	public GeneralException(String description)
	{
        super(description);
        
        this.description = description;
    }
	
	public String getDescription() {
		return description;
	}
}
