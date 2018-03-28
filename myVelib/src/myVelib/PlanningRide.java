package myVelib;

public class PlanningRide {
	
	// Attributes
	private Station source;
	private Station destination;
	private Bicycle bicycle;
	private User user;
	private double timeBicycleTaken;
	private double timeBicycleGivenBack;
	
	/* *********************************** Creator ********************************* */
	public PlanningRide(Station source, Station destination, User user) {
		this.source = source;
		this.destination = destination;
		this.user = user;
	}
	
	/* ***************************************** Display ****************************************** */
	
	public String toString() {
		return "\n Departure : station n� " +  source.getStationID() + " | Arrival : station n� " + ((destination ==null) ? "No destination specified" :destination.getStationID()) +
				(UserHasNotABicycle() ? "" : " | " + this.bicycle.toString());
		
	}
	
	/* **************************************** Methods ******************************************* */
	
	public void addBicycle(Bicycle bicycle, double time) {
		if (!this.UserHasNotABicycle()) {
			System.out.println("You already got a bicycle in this ride");
		} else {
			this.bicycle = bicycle;
			timeBicycleTaken = time;
		}		
	}
	
	/* ******************************************* Getters/Setters ************************************* */
	
	public Station getStationSource() {
		return this.source;
	}
	
	public Station getStationDestination() {
		return this.destination;
	}
	
	public void setDestination(Station destination) {
		this.destination = destination;
	}
	
	public boolean UserHasNotABicycle() {
		return (this.bicycle == null);
	}
	
	public Bicycle getBicycle() {
		return this.bicycle;
	}
	
	public void setTimeGivenBack(double time) {
		this.timeBicycleGivenBack = time;
	}
	
	public double getTimeGivenBack() {
		return this.timeBicycleGivenBack ;
	}
	
	public double getTimeTaken() {
		return this.timeBicycleTaken ;
	}

}
