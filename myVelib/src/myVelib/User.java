package myVelib;

import java.util.ArrayList;

import exception.AskPlanningRideImpossibleException;
import exception.ComputingRideImpossibleException;
import rideComputingPossibilities.AvoidPlusStations;
import rideComputingPossibilities.FastestPath;
import rideComputingPossibilities.PreferPlusStation;
import rideComputingPossibilities.RidePreferences;
import rideComputingPossibilities.ShortestPath;
import rideComputingPossibilities.UniformityBicycleConservation;

public class User {
	
	/* Attributes */
	private String name;
	private static int nextnumericalID;
	private int UserID;
	private float timecreditbalance;
	SubscriptionPossibility subscription;
	private double[] GPScoordinate = new double[2];
	private ArrayList<PlanningRide> pastRide = new ArrayList<PlanningRide>();
	private PlanningRide currentRide;
	private float timeSpentOfABike;
	private int totalAmountOfCharges;
	private float timeCreditEarned;
	
	
	/****************************************** Creators ***************************************/
	public User(String name) {
		
		try {
			this.name = name;
			timecreditbalance = 0;
			this.UserID = nextnumericalID;
			nextnumericalID++;
			this.subscription = new WithoutSubscription();
			this.GPScoordinate[0] = 0;
			this.GPScoordinate[1] = 0;
			this.currentRide = null;
			
		} catch (NullPointerException e) {
		System.err.println("You tried to enter a null name !");
	}
	}
	
	public User(String name, String cartType) {
		
		try {
			this.name = name;
			timecreditbalance = 0;
			this.UserID = nextnumericalID;
			nextnumericalID++;
			if (cartType.equals("Vlibre")) {
				this.subscription = new Vlibre();
			} else if (cartType.equals( "Vmax")) {
				this.subscription = new Vmax();
			} else if (cartType.equals("No subscription")) {
				this.subscription = new WithoutSubscription();
			} else {
				System.out.println("The subscription hasn't been recognized, the user created has then no subscription");
				this.subscription = new WithoutSubscription();
			}

			this.GPScoordinate[0] = 0;
			this.GPScoordinate[1] = 0;
			this.currentRide = null;
			
		} catch (NullPointerException e) {
		System.err.println("You tried to enter a null name !");
	}
	}
	
public User(String name, String cartType, double latitude, double longitude) {
		
		try {
			this.name = name;
			timecreditbalance = 0;
			this.UserID = nextnumericalID;
			nextnumericalID++;
			if (cartType.equals("Vlibre")) {
				this.subscription = new Vlibre();
			} else if (cartType.equals( "Vmax")) {
				this.subscription = new Vmax();
			} else if (cartType.equals("NoSubscription")) {
				this.subscription = new WithoutSubscription();
			} else {
				System.out.println("The subscription hasn't been recognized, the user created has then no subscription");
				this.subscription = new WithoutSubscription();
			}

			this.GPScoordinate[0] = latitude;
			this.GPScoordinate[1] = longitude;
			this.currentRide = null;
			
		} catch (NullPointerException e) {
		System.err.println("You tried to enter a null name !");
	}
	}
	
	public User(String name, float timecreditbalance, SubscriptionPossibility subscription, double latitude, double longitude) {
		
		try {
			this.name = name;
			this.timecreditbalance = timecreditbalance;
			this.UserID = nextnumericalID;
			nextnumericalID++;
			this.subscription = subscription;
			this.GPScoordinate[0] = latitude;
			this.GPScoordinate[1] = longitude;
			this.currentRide = null;
		} catch (NullPointerException e) {
			System.err.println("You tried to enter a null name or subscription !");
		}
	}
	
	/************************************** Getters / Setters ****************************************/
	
	public double getUserLat() {
		return this.GPScoordinate[0];
	}
	
	public double getUserLong() {
		return this.GPScoordinate[1];
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public float getTimecreditbalance() {
		return timecreditbalance;
	}

	public void setTimecreditbalance(float f) {
		this.timecreditbalance = f;
	}
	
	public void setSubscription(SubscriptionPossibility sub) {
		this.subscription = sub;
		
	}
	public SubscriptionPossibility getSubscription() {
		return this.subscription ;
		
	}
	
	/*private void setActualRide(PlanningRide ride) {
		this.currentRide = ride;
	}*/
	
	public PlanningRide getActualRide() {
		return this.currentRide;
	}
	
	public ArrayList<PlanningRide> getPastRide (){
		return this.pastRide;
	}
	
	public float getTimeSpentOnABike() {
		return this.timeSpentOfABike;
	}
	
	
	public int getTotalAmountOfCharges() {
		return this.totalAmountOfCharges;
	}
	
	public float getTimeCreditEarned() {
		return this.timeCreditEarned;
	}
	
	public void addTimeSpentOnABike(float time) {
		this.timeSpentOfABike += time;
	}
	
	
	public void addTotalAmountOfCharges(int charge) {
		this.totalAmountOfCharges += charge;
	}
	
	public void addTimeCreditEarned(float timecredit) {
		this.timeCreditEarned += timecredit;
	}
	
	/*********************************** Methods *************************************/
	
	/*
	 * Return the total cost of a ride according to the subscription of the user, the @time of the ride, the @bicycle and the @typeStation
	 */
	public int payement(double time, Bicycle bicycle, TypeStation typeStation) {
		int totalCost = subscription.cost(time, bicycle, this, typeStation);
		addTotalAmountOfCharges(totalCost);
		return totalCost;
		
	}
	
	/*
	 * Save the planning ride advises by the system
	 */
	public void receiveRide(PlanningRide planningRide) {
		
			this.currentRide = planningRide;
			
			if (planningRide.getStationDestination() != null) {  //if a user is not following a planning ride
			planningRide.getStationDestination().aNewUserComing(this);
			}
			planningRide.getStationSource().aNewUserComing(this);
		
	}
	
	/*
	 * Function to enable the user to be given a planning ride to reach its destination (@latitude, @longitude) with the @type of bicycle wished 
	 */
	public void askNewRide(double latitude, double longitude, String type, String ridePref, ArrayList<Station> stations) throws AskPlanningRideImpossibleException {
		RidePreferences ridePreference;
		if (ridePref.equals("avoidplus")) {
			ridePreference = new AvoidPlusStations();
		} else if (ridePref.equals("preferplus")) {
			ridePreference = new PreferPlusStation();
		} else if (ridePref.equals("shortest")) {
			ridePreference = new ShortestPath();
		} else if (ridePref.equals("fastest")) {
			ridePreference = new FastestPath();
		} else if (ridePref.equals("uniformity")) {
			ridePreference = new UniformityBicycleConservation();
		} else {
			System.out.println("The type of ride preference has not been recognized ! It would be by default uniformity");
			ridePreference = new UniformityBicycleConservation();
		}
		ComputingRide computingRide = new ComputingRide(this, stations, latitude, longitude, type, ridePreference);
		try {
			PlanningRide ride = computingRide.computeWay();
			this.receiveRide(ride);
			System.out.println("Hello user "+ this.UserID + " ! Here is your ride : " + ride.toString());
		}
		catch (ComputingRideImpossibleException e) {
			System.out.println(e);
		}
	}
	
	/*
	 * To archive the ride the user just completes
	 */
	public void completeARide(double timeBicycleGivenBack) {
		if (this.currentRide != null) {
			pastRide.add(currentRide);
			currentRide.setTimeGivenBack(timeBicycleGivenBack);
			System.out.println("Hello user n�" + this.UserID +"!");
			System.out.println("The cost of the ride is : " + payement(currentRide.getTimeGivenBack() - currentRide.getTimeTaken(), currentRide.getBicycle(), currentRide.getStationDestination().getTypeStation()) + " �"
					+ "\n We hope to see you another time !\n ");
			this.addTimeSpentOnABike((float)(currentRide.getTimeGivenBack() - currentRide.getTimeTaken()));
			this.currentRide = null;
		} else {
			System.out.println("You can't complete a ride without having one, does it sound logical? :)");
		}
	}
	/*
	 * Send a notification to the user when a problem in the destination station or source happens
	 */
	public void update(Station station) {
		//Ma station d'arriv�e n'est plus disponible
		if (station.equals(currentRide.getStationDestination())) {
			if (currentRide.getStationDestination().isFull() || currentRide.getStationDestination().isOffline()) {
				System.out.println("\nHi " +this.getName() +" (user " + this.getUserID()+") "+"\nThe destination station is full or offline ! Do you want to recalculate the ride? (y/n)\n");
			}
		} else if (station.equals(currentRide.getStationSource())) {
			if (currentRide.getBicycle() == null) {
				System.out.println("\nHi " +this.getName() +" (user " + this.getUserID()+")" +" \nThe source station where you should take a bicycle is no more available"
						+ "\n Do you want to recalculate the ride? (y/n)\n");
			}
		}
	}
	
	public String toString() {
		return "-------------------------"+ "User " + this.getUserID()+ " "+ this.getName()+ "----------------- " + this.getUserLat() +
				"\"\" " + this.getUserLong() + "\"\" " + "---------------\n" + "time credit balance : " + this.getTimecreditbalance() +
				" | subscription : " + this.getSubscription().toString() +	"\n"+
				((this.currentRide == null) ? "Not asking for a ride" : ((this.currentRide.getBicycle() == null) ? "Currently walking to or from a station " + this.currentRide.toString() : "Doing bicycle" + this.currentRide.toString())) +
				"\n"; 
	}
	
	public String returnUserStatistics() {
		return "******************** Statistics User n� "+ this.getUserID() +" ******************************\n" +
	"Number of rides: " + this.pastRide.size() + " | total time spent on a bike: " + this.timeSpentOfABike +
	"\ntotal amount of charges: " + this.totalAmountOfCharges + " | time-credit earned: " + this.timeCreditEarned +"\n"; 
	}

	

}
