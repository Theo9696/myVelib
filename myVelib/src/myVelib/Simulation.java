package myVelib;

import java.util.ArrayList;

public class Simulation {
	
	public final String[] ListOfNameBasic = {"Patrick", "Jean-Louis","Baptiste", "Victor", "Emile","Louise",
			"Camille", "Hajar", "Le�la", "Sarah", "Wiam", "Th�o", "Lucas", "Paul", "Anna", "Mahaut", "Odelin", "Morgan", "Paloma"};
	
	public ArrayList<String> ListOfName = initListOfName();
	private ArrayList<Station> stations;
	private ArrayList<User> users;
	
	Simulation(int numberOfStations, int numberOfUsers, int lengthOfMap){
		this.users = new ArrayList<User>();
		this.stations = new ArrayList<Station>();
		createAMap(numberOfStations, numberOfUsers, lengthOfMap);
		
	}
	
	
	private void createAMap(int numberOfStations, int numberOfUsers, int lengthOfMap) {
		
		ArrayList<Station> stations = new ArrayList<Station>();
		
		for (int numstation = 0; numstation<numberOfStations; numstation++) {
			stations.add(newStation(lengthOfMap));
		}
		this.stations = stations;
		
		ArrayList<User> users = new ArrayList<User>();
		
		for (int numUser = 0; numUser<numberOfUsers; numUser++) {
			users.add(newUser(lengthOfMap));
		}
		this.users = users;
		
	}
	
	public static void main(String[] args) {
		
		Simulation simu = new Simulation(20,25,50);
		System.out.println(simu);
		
		
		
		/*User mika = new User("Tanguy");
		System.out.println(mika);
		
		ComputingRide computingRide = new ComputingRide(mika, stations, 10, 10);
		System.out.println(computingRide);
		System.out.println(computingRide.computeWay());
		*/
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
	
	private ParkingSlot newParkingSlot() {
		double proba = Math.random();
		try {
			if (proba < 0.5) {
				// The parkingSlot will either have a mechanical or a electrical car
				ParkingSlot ps = new ParkingSlot();
				ps.addBicycle(this.newBicycle()); 
				return ps ;
			} else if (proba >= 0.5 && proba<0.9 ) {
				// The parkingslot will be empty
				ParkingSlot ps = new ParkingSlot();
				return ps ;
			} else {
				//The parkingslot will be out of order
				ParkingSlot ps = new ParkingSlot();
				ps.setInOrder(false);
				return ps ;
			}
		} catch (ParkingSlotFullException e) {
			e.printStackTrace();
		}
		
		return new ParkingSlot();
	}
	
	private Station newStation(int lengthOfMap) {
		//need to be changed when station will include "plus" possibility
		int numberOfParkingSlot = (int) Math.ceil(50*Math.random());
		double latitude = Math.ceil(lengthOfMap*Math.random());
		double longitude = Math.ceil(lengthOfMap*Math.random());
		
		Station station = new Station(latitude, longitude);
		
		for (int k=0; k<numberOfParkingSlot; k++) {
			station.addParkingSlot(newParkingSlot());
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
	
	public String toString() {
		
		return "The gameground is composed of " + this.stations.size() + " stations\n" +
				"*************************Stations*********************\n" +
				stations.toString() + "\n" + "and " + this.users.size() +  " users\n"+
				"*************************Users**************************\n"
				+ users.toString();
	}

}
