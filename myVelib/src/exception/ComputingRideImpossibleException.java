package exception;

public class ComputingRideImpossibleException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "There is no combinaison of stations that enables you to do this ride";
	}

}
