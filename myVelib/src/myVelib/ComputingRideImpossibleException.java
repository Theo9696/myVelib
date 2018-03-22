package myVelib;

public class ComputingRideImpossibleException extends Exception {
	
	public String toString() {
		return "There is no combinaison of stations that enables you to do this ride";
	}

}
