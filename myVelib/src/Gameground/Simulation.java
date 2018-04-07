package Gameground;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import Exceptions.AskPlanningRideImpossibleException;
import Exceptions.ParkingSlotFullException;
import Exceptions.StationEmptyException;
import Exceptions.StationFullException;
import Exceptions.StationOfflineException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;
import myVelib.PlanningRide;
import myVelib.PlusStation;
//import myVelib.RidePreferences;
import myVelib.StandardStation;
import myVelib.Station;
import myVelib.User;
import myVelib.Vlibre;
import myVelib.Vmax;
import myVelib.WithoutSubscription;

import java.util.TreeMap;
import java.util.TreeSet;

public class Simulation {
	
	// Base of names
	public final String[] ListOfNameBasic = {"Patrick", "Jean-Louis","Baptiste", "Victor", "Emile","Louise",
			"Camille", "Hajar", "Leïla", "Sarah", "Wiam", "Théo", "Lucas", "Paul", "Anna", "Mahaut", "Odelin", "Morgan", "Paloma", "Antoine"};
	
	public ArrayList<String> ListOfName = initListOfName();
	private ArrayList<Station> stations;
	private Map<Integer, Station> stationsOrdered;
	private Map<Integer, User> users = new HashMap<Integer, User>();
	private String nameOfNetwork;
	
	/* ********************************************* Creators ********************************************** */
	Simulation(int numberOfStations, int numberOfUsers, int lengthOfMap, String nameOfNetwork) throws ParkingSlotFullException{
		this.stations = new ArrayList<Station>();
		this.stationsOrdered = new HashMap<Integer, Station>();
		createAMap(numberOfStations, numberOfUsers, lengthOfMap);
		this.nameOfNetwork = nameOfNetwork;
		
		
	}
	
	public Simulation(int numberOfStations, int numberOfParkingSlot, int numberOfUsers, int lengthOfMap, int numberOfBike, String nameOfNetwork) throws StationOfflineException, ParkingSlotFullException{
		this.stations = new ArrayList<Station>();
		this.stationsOrdered = new HashMap<Integer, Station>();
		createAMap(numberOfStations, numberOfParkingSlot, numberOfUsers, lengthOfMap, numberOfBike);
		this.nameOfNetwork = nameOfNetwork;
		
	}
	
	
	Simulation(int numberOfStations, int lengthOfMap, String nameOfNetwork) throws ParkingSlotFullException{
		this.stations = new ArrayList<Station>();
		this.stationsOrdered = new HashMap<Integer, Station>();
		createAMap(numberOfStations, 0, lengthOfMap);
		this.nameOfNetwork = nameOfNetwork;
		
	}
	
	Simulation(int numberOfStations, String nameOfNetwork) throws ParkingSlotFullException{
		this.stations = new ArrayList<Station>();
		this.stationsOrdered = new HashMap<Integer, Station>();
		createAMap(numberOfStations, 0, 50);
		this.nameOfNetwork = nameOfNetwork;
		
	}
	
	Simulation(String nameOfNetwork) throws ParkingSlotFullException{
		this.stations = new ArrayList<Station>();
		this.stationsOrdered = new HashMap<Integer, Station>();
		this.nameOfNetwork = nameOfNetwork;
		createAMap(20, 0, 50);
		
	}
	
	Simulation() throws ParkingSlotFullException{
		this.stations = new ArrayList<Station>();
		this.stationsOrdered = new HashMap<Integer, Station>();
		this.nameOfNetwork = "Essai";
		createAMap(20, 0, 50);
		
		
	}
	
	/* *************************************** getters/setters ************************************************ */
	public String getNameOfNetwork() {
		return this.nameOfNetwork;
	}
	
	/* ********************************************** Methods : creation of objects **************************************** */
	
	private void createAMap(int numberOfStations, int numberOfParkingSlot, int numberOfUsers, int lengthOfMap, int numberOfBike) throws StationOfflineException, ParkingSlotFullException {
		
		ArrayList<Station> stations = new ArrayList<Station>();
		Map<Integer, Station> stationsOrdered = new HashMap<Integer, Station>();
		
		for (int numstation = 0; numstation<numberOfStations; numstation++) {
			Station station = newStation(numberOfParkingSlot, lengthOfMap, false );
			stations.add(station);
			stationsOrdered.put(station.getStationID(), station);
		}
		this.stations = stations;
		this.stationsOrdered = stationsOrdered;
		
		Map<Integer, User> users = new HashMap<Integer,User>();
		
		for (int numUser = 0; numUser<numberOfUsers; numUser++) {
			User user = newUser(lengthOfMap);
			users.put(user.getUserID(), user);
		}
		this.users = users;
		
		if (numberOfBike>numberOfStations*numberOfParkingSlot) {
			System.out.println("Too much bicycles for the total number of parkingslot");
		}
		else {
			for (int bike =0; bike<numberOfBike; bike++) {
				boolean a = true;
				Integer tries = 0;
				while (a == true) {
					float r = (float) (numberOfStations*Math.random());
					try {
						stations.get((int) Math.floor(r)).returnBicycle(newBicycle() , 0, true);
						a = false;
					} catch (StationFullException e) {
						tries++;
						if (tries > 20) {
							System.out.println("Too much bicycles");
							a = false;
						}
					}
				}
			}
	}
	}
	
	private void createAMap(int numberOfStations, int numberOfUsers, int lengthOfMap) throws ParkingSlotFullException {
		
		ArrayList<Station> stations = new ArrayList<Station>();
		Map<Integer, Station> stationsOrdered = new HashMap<Integer, Station>();
		
		for (int numstation = 0; numstation<numberOfStations; numstation++) {
			Station station = newStation(lengthOfMap, true);
			stations.add(station);
			stationsOrdered.put(station.getStationID(), station);
		}
		this.stations = stations;
		this.stationsOrdered = stationsOrdered;
		
		Map<Integer, User> users = new HashMap<Integer,User>();
		
		for (int numUser = 0; numUser<numberOfUsers; numUser++) {
			User user = newUser(lengthOfMap);
			users.put(user.getUserID(), user);
		}
		this.users = users;
		
	}
	
	
	public ArrayList<String> initListOfName(){
		
		ArrayList<String> ListOfName = new ArrayList<String>();
	
		for (String nom : ListOfNameBasic) {
			ListOfName.add(nom);
			}
		return ListOfName;
	}
	
	private Bicycle newBicycle() {
		if (Math.random() < 0.3) {
			return new ElectricalBicycle();
		} else {
			return new MechanicalBicycle();
		}
	}
	
	private ParkingSlot newParkingSlot(boolean autoGenerationOfRandomBicycle) throws ParkingSlotFullException {
		double proba = Math.random();
		
		if (autoGenerationOfRandomBicycle) {
			if (proba < 0.7) {
				// The parkingSlot will either have a mechanical or a electrical car
				ParkingSlot ps = new ParkingSlot();
				ps.addBicycle(this.newBicycle(),0); 
				return ps ;
			} else if (proba >= 0.7 && proba<0.95 ) {
				// The parkingslot will be empty
				ParkingSlot ps = new ParkingSlot();
				return ps ;
			} 
			else {
				//The parkingslot will be out of order
				ParkingSlot ps = new ParkingSlot();
				ps.setInOrder(false,0);
				return ps ;
			}
				
		}
		else if (proba >0.95)
		{
				//The parkingslot will be out of order
				ParkingSlot ps = new ParkingSlot();
				ps.setInOrder(false,0);
				return ps ;
			}
		
		return new ParkingSlot();
	}
	
	private Station newStation(int nbOfSlots, int lengthOfMap, boolean autoGenerationOfRandomBicycle) throws ParkingSlotFullException {
		int numberOfParkingSlot = nbOfSlots;
		double latitude = Math.ceil(lengthOfMap*Math.random());
		double longitude = Math.ceil(lengthOfMap*Math.random());
		
		double proba = Math.random();
		Station station;
		if (proba<0.75) {
			station = new Station(latitude, longitude, true, new StandardStation());
			
		} else if(proba >=0.75 && proba <0.95) {
			station = new Station(latitude, longitude, true, new PlusStation() );
		
		
		}else {
			station = new Station(latitude, longitude, true);
		}
		
		for (int k=0; k<numberOfParkingSlot; k++) {
			station.addParkingSlot(newParkingSlot(autoGenerationOfRandomBicycle));
		}
		
		return station;
	}
	
	private Station newStation(int lengthOfMap, boolean autoGenerationOfRandomBicycle) throws ParkingSlotFullException {
		int numberOfParkingSlot = (int) Math.ceil(50*Math.random());
		double latitude = Math.ceil(lengthOfMap*Math.random());
		double longitude = Math.ceil(lengthOfMap*Math.random());
		
		double proba = Math.random();
		Station station;
		if (proba<0.75) {
			station = new Station(latitude, longitude, true, new StandardStation());
			
		} else if(proba >=0.75 && proba <0.95) {
			station = new Station(latitude, longitude, true, new PlusStation() );
		
		
		}else {
			station = new Station(latitude, longitude, true);
		}
		
		for (int k=0; k<numberOfParkingSlot; k++) {
			station.addParkingSlot(newParkingSlot(autoGenerationOfRandomBicycle));
		}
		
		return station;
	}
	
	private User newUser(int lengthOfMap) {
		double proba = Math.random();
		double latitude = Math.ceil(lengthOfMap*Math.random());
		double longitude = Math.ceil(lengthOfMap*Math.random());
		String name = ListOfName.get((int) Math.floor(ListOfName.size()*Math.random()));
		
		
		if (proba <0.6) { 
			// The user doesn't subscribe to any card
			return new User(name,0,new WithoutSubscription(),latitude, longitude);
		} 
		// The user has made a Vlibre subscription
		else if (proba >= 0.6 && proba<0.85) {
			return new User(name,0,new Vlibre(),latitude, longitude);
		} 
		// The user has made a Vmax subscription
		else {
			return new User(name,0,new Vmax(),latitude, longitude);
		}
	}
	
	/* ********************************************** Methods : add/display **************************************** */
	
	public void addnewUser (User user) {
		this.users.put(user.getUserID(), user);
	}
	
	
	public ArrayList<Station> getStations() {
		return stations;
	}
	
	public Map<Integer, Station> getStationsOrdered(){
		return stationsOrdered;
	}
	
	/*public void setStations(ArrayList<Station> stations) {
		this.stations = stations;
	}*/
	
	public Map<Integer, User> getUsers() {
		return users;
	}
	public void setUsers(Map<Integer, User> users) {
		this.users = users;
	}
	public String toString() {
		
		return "The gameground is composed of " + this.stations.size() + " stations\n" +
				"*************************Stations*********************\n" +
				stations.toString() + "\n" + "and " + this.users.size() +  " users\n"+
				"*************************Users**************************\n"
				+ users.toString();
	}
	
	
	/* ******************************************* Methods : actions on the simulation **************************************** */
	
	
	public void newRide(int userID, double latitude, double longitude, String bicycleType, String Ridepref) throws AskPlanningRideImpossibleException {
		try {
			(this.users.get(userID)).askNewRide(latitude, longitude, bicycleType, Ridepref, stations);
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e);
			System.err.println("You tried to enter a user ID that is not yet registered in the simulation");
		}
	}
	
	public void newUser(User user) {
		this.addnewUser(user);
	}
	
	public void newUser(String cartType ) {
		int r = (int) Math.floor((int)ListOfNameBasic.length*(Math.random()));
		this.addnewUser(new User(ListOfNameBasic[r], cartType));
	}
	
	public void newUser(String cartType, String name ) {
		this.addnewUser(new User(name, cartType));
	}
	
	public void takeABicycle(int userID, int stationID, String bicycleType, double timeBicycleTaken) throws StationOfflineException, StationEmptyException {
		try {
			// Test if the user exist before taking the bicycle from the station !
			this.users.get(userID).getName();
			if (this.stationsOrdered.get(stationID).hasStationBicycle(bicycleType)) {
				
				if(this.users.get(userID).getActualRide() != null && this.users.get(userID).getActualRide().getBicycle() != null) {
					System.out.println("You can't ask for a bicycle if you received already one...");
				}
				else {
					Bicycle b = stationsOrdered.get(stationID).needBicycle(bicycleType, timeBicycleTaken);
					if (this.users.get(userID).getActualRide() != null) {
						this.users.get(userID).getActualRide().addBicycle(b, timeBicycleTaken);
						 
					} else { // creation of a planning ride but we don't know the station of destination since the user didn't ask for a planning ride
						PlanningRide planning = new PlanningRide(stations.get(stationID), null, users.get(userID) );
						this.users.get(userID).receiveRide(planning);
						this.users.get(userID).getActualRide().addBicycle(b, timeBicycleTaken);
					}
				} 
			}
			else {
				System.out.println("This station doesn't have the type of bicycle you wish !");
			}
		}
		catch (NullPointerException e) {
			System.err.println("This station or this user is not registered in this simulation");
			
		}
	}
	
	public void takeABicycleInTheStationSource(int userID, String bicycleType, double timeBicycleTaken) throws StationOfflineException, StationEmptyException {
		try {
			this.takeABicycle(userID, users.get(userID).getActualRide().getStationSource().getStationID(), bicycleType, timeBicycleTaken);
		} catch (NullPointerException e) {
			System.out.println("You can't take a bicycle in a station if you don't ask for a planning ride or if no planning ride is possible for you!");
		}
	}
	
	public void returnABicycle(int userID, int stationID, double timeBicycleGaveBack) throws StationOfflineException, StationFullException {
		try {
			Station stationTargeted = stations.get(stationID);
			try {
				User user = users.get(userID);
				try {
					PlanningRide RideTargeted = user.getActualRide();
					
					try { 
						
						if (RideTargeted.getStationDestination() == null){
							RideTargeted.setDestination(stationTargeted);
						}
						
						if (RideTargeted != null)
							System.out.println(RideTargeted);
						
						Bicycle bicycle = RideTargeted.getBicycle();
						if (bicycle == null) {
							System.out.println("This user is not on a ride right now ? He doesn't have any bicycle...");
						
						}else {
							stationTargeted.returnBicycle(bicycle,timeBicycleGaveBack);
							users.get(userID).completeARide(timeBicycleGaveBack);
						}
						 
					}
					catch (NullPointerException e) {
						System.out.println("This user is not on a ride right now !? He doesn't have any bicycle...");
					} catch (StationFullException e ) {
						new StationFullException(stationTargeted);
					}
				}
				catch (NullPointerException e) {
					System.err.println("You don't ask for a ride, do you?");
				}
			} catch (NullPointerException e) {
				System.err.println("This user doesn't exist !");
			}
			
		}
		catch (StationOfflineException e) {
			System.err.println("This station is offline, you can't park your bicycle there ! ");
		} catch (NullPointerException e) {
			System.err.println("This station is not registered !");
		}
					
	}
	
	public void returnABicycleInTheStationDestination(int userID, double timeBicycleGivenBack) throws StationOfflineException, StationFullException {
		try {
			this.returnABicycle(userID, users.get(userID).getActualRide().getStationDestination().getStationID(), timeBicycleGivenBack);
		} catch (NullPointerException e ) {
			System.out.println("You can't return a bicycle to the station source if you don't have a planning ride...");
		}
	}
	/*
	 * Do all the work for @getMostUsedStations()
	 * It returns a Map<Integer, Set<Integer>> with as key the number of operations and as object a set of stations with have this number of operations
	 * This Map is sorted wrt to the number of operations
	 */
	private  Map<Integer, Set<Integer>> mostUsedStations() {
		
		Map<Integer, Integer> stationsNotOrdered = new HashMap<Integer, Integer>();
		{ for (Station station : stations) {
			stationsNotOrdered.put(station.getStationID(), station.getNumberOfDrop()+ station.getNumberOfRent());
		}
		
		
		Map<Integer, Set<Integer>> stationSorted = inverser(stationsNotOrdered);
		
		return stationSorted;
		}
	}
	/*
	 * This function aims at returning an arraylist of station ordered considering the number of rent and drop
	 */
	public ArrayList<Station> getMostUsedStations(){
		Map<Integer, Set<Integer>> stationSorted = mostUsedStations();
		ArrayList<Station> stationOrdered = new ArrayList<Station>();
		
		for(Entry<Integer, Set<Integer>> entry : stationSorted.entrySet()) {
			//Integer key = entry.getKey();
			Set<Integer> object = entry.getValue();
			//This is enough to take stations.get(key) because station in position k is the kth station...
			for (Integer integer : object) {
				stationOrdered.add(stations.get(integer));
			}
			
			
		}
		System.out.println("*************************List of Stations sorted by number of operations************************************");
		System.out.println(stationOrdered);
		return stationOrdered;
	}
	
	
	
    Map<Double, Set<Integer>> leastUsedStations(double timeStart, double timeEnd) {
		
		Map<Integer, Double> stationsNotOrdered = new HashMap<Integer, Double>();
		{ for (Station station : stations) {
			stationsNotOrdered.put(station.getStationID(), station.rateOfOccupation(timeStart, timeEnd));
		}
		
		
		Map<Double, Set<Integer>> stationSorted = inverser(stationsNotOrdered);
		
		return stationSorted;
		}
	}
	
	
	
	
	public ArrayList<Station> getLeastOccupiedStations(double timeStart, double timeEnd) {
		Map<Double, Set<Integer>> stationSorted = leastUsedStations(timeStart, timeEnd);
		ArrayList<Station> stationOrdered = new ArrayList<Station>();
		
		for(Entry<Double, Set<Integer>> entry : stationSorted.entrySet()) {
			//Double rateOccupation = entry.getKey();
			Set<Integer> stationIDs = entry.getValue();
			//This is enough to take stations.get(key) because station in position k is the kth station...
			for (Integer integer : stationIDs) {
				stationOrdered.add(stations.get(integer));
			}
			
			
		}
		System.out.println("\n*************************List of Stations sorted by rate of occupations between "+ timeStart + " and " + timeEnd +" ************************************");
		for (Station station : stationOrdered) {
			System.out.println(station.toString() + "Occupation rate ( " + timeStart + " -> " + timeEnd+"): " + station.rateOfOccupation(timeStart, timeEnd) +"\n\n");
		}
		return stationOrdered;
	}
	
	
	
	
	
	
	
	
	/*
	 * Function to act on a Map Non ordered on values. It returns the map ordered by values with eventually key doublons
	 */
	public static <K,V> Map<V,Set<K>> inverser(Map<K,V> map) {
		Map<V,Set<K>> result = new TreeMap<V,Set<K>>();
		for (Entry<K, V> e : map.entrySet()) { 
			if (result.containsKey(e.getValue())) 
				result.get(e.getValue()).add(e.getKey()); 
			else { TreeSet<K> set = new TreeSet<K>(); 
				set.add(e.getKey()); 
				result.put(e.getValue(), set); } 
			} 
		return result; 
		} 
	
}
