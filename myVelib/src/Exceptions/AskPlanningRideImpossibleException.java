package Exceptions;

public class AskPlanningRideImpossibleException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "The user can't ask for another planning ride";
	}

}
