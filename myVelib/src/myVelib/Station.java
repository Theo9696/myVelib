package myVelib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import Exceptions.ParkingSlotFullException;
import Exceptions.StationEmptyException;
import Exceptions.StationFullException;
import Exceptions.StationOfflineException;

public class Station {
	
	/* Attributes */
	private ArrayList<ParkingSlot> parkingslot = new ArrayList<ParkingSlot>();
	private ArrayList<ParkingSlot> freeparkingslot = new ArrayList<ParkingSlot>();
	private ArrayList<ParkingSlot> occupiedparkingslot = new ArrayList<ParkingSlot>();
	private ArrayList<ParkingSlot> outOfOrderParkingslot = new ArrayList<ParkingSlot>();
	private Map<String, Integer> NumberBicycle = new HashMap<>();
	private Map<Integer, User> userComing = new HashMap<Integer, User>();
	private int numberOfRent = 0;
	private int numberOfDrop = 0;
	private boolean inorder;
	private TypeStation typestation;
	private double[] GPScoordinate =  new double[2];
	private static int nextnumericalID;
	private int StationID;
	private boolean changed;
	
	
	/* ***************************************** Creators * *************************/
	public Station(){
		this.inorder = true;
		this.GPScoordinate[0] = 0;
		this.GPScoordinate[1] = 0;
		this.StationID = nextnumericalID;
		nextnumericalID++;
		NumberBicycle.put("Electrical",0);
		NumberBicycle.put("Mechanical", 0);
		typestation = new StandardStation();
	}
	
	public Station(double latitude, double longitude){
		this.inorder = true;
		this.GPScoordinate[0] = latitude;
		this.GPScoordinate[1] = longitude;
		this.StationID = nextnumericalID;
		nextnumericalID++;
		NumberBicycle.put("Electrical",0);
		NumberBicycle.put("Mechanical", 0);
		typestation = new StandardStation();
	}
	
	public Station(double latitude, double longitude, boolean offline){
		this.inorder = offline;
		this.GPScoordinate[0] = latitude;
		this.GPScoordinate[1] = longitude;
		this.StationID = nextnumericalID;
		nextnumericalID++;
		NumberBicycle.put("Electrical",0);
		NumberBicycle.put("Mechanical", 0);
		typestation = new StandardStation();
	}
	
	public Station(double latitude, double longitude, boolean offline, TypeStation typestation){
		this.inorder = offline;
		this.GPScoordinate[0] = latitude;
		this.GPScoordinate[1] = longitude;
		this.StationID = nextnumericalID;
		nextnumericalID++;
		NumberBicycle.put("Electrical",0);
		NumberBicycle.put("Mechanical", 0);
		this.typestation = typestation;
	}
	
	
	public Station(ArrayList<ParkingSlot> parkingslot, double latitude, double longitude) {
		
		try {
			this.parkingslot = parkingslot;
			this.inorder = true;
			this.GPScoordinate[0] = latitude;
			this.GPScoordinate[1] = longitude;
			this.StationID = nextnumericalID;
			nextnumericalID++;
			NumberBicycle.put("Electrical", 0);
			NumberBicycle.put("Mechanical", 0);
		
			//initialisation of the free parkingslot list by testing each parkingslot of the station
			for (ParkingSlot p : parkingslot) {
				if (p.isFree() && p.isUsable())
					freeparkingslot.add(p);
				else if (p.isFree() && !p.isUsable())
					outOfOrderParkingslot.add(p);
				else {
					occupiedparkingslot.add(p);
					updateNumberBicyclePlus(p.getBicycle());
				
				
			}
		}
		}
		
		catch (NullPointerException e ) {
			System.err.println("Wrong credits to initialize this station ! You must have entered a null entry");
		}
		typestation = new StandardStation();
	}
	
	
	/* **************************************** Getters / Setters *************************************/
	public int getNumberOfRent() {
		return this.numberOfRent;
	}
	
	public int getNumberOfDrop() {
		return this.numberOfDrop;
	}
		
	public int getNumberofBikeAvailable(String bicycleType) {
		int numberOfBicycle = 0;
		for (ParkingSlot parkingslot : occupiedparkingslot) {
			if (parkingslot.getBicycle() != null && parkingslot.getBicycle().getType() == bicycleType) {
				numberOfBicycle++;
			}
		}
		return numberOfBicycle;
	}
	
	public int getNumberOfFreeParkingslot() {
		return freeparkingslot.size();
	}
	
	
	public TypeStation getTypeStation() {
		return this.typestation;
	}
	
	public boolean hasStationBicycle(String type) {
		try {
			return NumberBicycle.get(type) != 0;
		}
		catch (NullPointerException e) {
			System.err.println("A wrong type of bicycle has been asked !");
		}
		return false;
	}
	public double getStationLat() {
		return this.GPScoordinate[0];
	}
	
	public double getStationLong() {
		return this.GPScoordinate[1];
	}
	
	public int getStationID() {
		return this.StationID;
	}
	
	private void updateNumberBicyclePlus(Bicycle bicycle) {
		if (bicycle instanceof ElectricalBicycle) {
			NumberBicycle.put("Electrical", NumberBicycle.get("Electrical")+1);
		} else if (bicycle instanceof MechanicalBicycle) {
			NumberBicycle.put("Mechanical", NumberBicycle.get("Mechanical")+1);
		}
	}
	
	private void updateNumberBicycleLess(Bicycle bicycle) {
		if (bicycle instanceof ElectricalBicycle) {
			NumberBicycle.put("Electrical", NumberBicycle.get("Electrical")-1);
		} else if (bicycle instanceof MechanicalBicycle) {
			NumberBicycle.put("Mechanical", NumberBicycle.get("Mechanical")-1);
		}
	}
	
	public boolean isFull() {

		for (ParkingSlot p : freeparkingslot) {
			if (p.isUsable()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isOffline() {
		return !this.inorder;
	}
	
	public boolean isEmpty() {
		if (freeparkingslot.size() ==0) {
			return true;
		} else {
			return false;
		}
	}
	
	/* ******************************** Methods *********************************************/
	
	/*
	 * Function to stock the user who had been inform to reach this station
	 * @user could afterwards be notified if the station is no more available
	 */
	public void aNewUserComing(User user) {
		this.userComing.put(user.getUserID(),user);
	}
	
	/*
	 * @user has completed his ride, it's no more significant to notify him of the station status
	 */
	public void aNewUserLeftABicycle(User user) {
		this.userComing.remove(user.getUserID());
	}
	
	/*
	 * To add a parkingslot in the station
	 */
	public void addParkingSlot(ParkingSlot p) {
		this.parkingslot.add(p);
		
		if (p.isFree() && p.isUsable()) {
			freeparkingslot.add(p);
		
		} else if (p.isFree() && !p.isUsable())
			outOfOrderParkingslot.add(p);
		
		else {
			occupiedparkingslot.add(p);
			updateNumberBicyclePlus(p.getBicycle());
		}
	}
	
	
	/*
	 * Change the status inorder of the station in offline
	 */
	public void becomeOffline() {
		this.inorder = false;
		this.changed = true;
		notifyObservers();
	}
	
	/*
	 * Change the status of the @ParkingSlot p in free
	 */
	public void slotisfree(ParkingSlot p) {
		if (!freeparkingslot.contains(p)) {
			freeparkingslot.add(p);
			occupiedparkingslot.remove(p);
		}
	}
	/*
	 * Change the status of the @ParkingSlot p in occupied
	 */
	public void slotisoccupied(ParkingSlot p) {
		if (!occupiedparkingslot.contains(p)) {
			occupiedparkingslot.add(p);
			freeparkingslot.remove(p);
		}
		
	}
	/*
	 * Remove a bicycle from the station if possible
	 */
	public Bicycle needBicycle(String bicycleType, double timeBicycleTaken) throws StationOfflineException, StationEmptyException {
		if (!this.inorder) {
			throw new StationOfflineException(this);
			
		} else {
			boolean igotabicycle = false;
			int n = 0;
			while (!igotabicycle && n < occupiedparkingslot.size()) {
				if (occupiedparkingslot.get(n).isUsable() && (occupiedparkingslot.get(n).getBicycle().getType() == bicycleType)) {
					Bicycle b = occupiedparkingslot.get(n).removeBicycle(timeBicycleTaken);
					this.slotisfree(occupiedparkingslot.get(n));
					igotabicycle = true;
					updateNumberBicycleLess(b);
					numberOfRent++;
					
					if (NumberBicycle.get(bicycleType) == 0) {
						this.changed = true;
						notifyObservers();
					}
					
					//Not taken into account yet
					/*if (occupiedparkingslot.size() + outOfOrderParkingslot.size() == parkingslot.size() -1) {
						this.changed = true;
						notifyObservers();
					}*/
					
					System.out.println("Hello ! You took the bicycle "+ b.toString() + " in the station n° " + this.getStationID());
					return b;
					}
				else {
					n++;
				}
			}
				
		}
		throw new StationEmptyException(this);
			
	}
	
	/*
	 * Return a bicycle in the station if possible
	 */
	public void returnBicycle(Bicycle bicycle, double timeBicycleGaveBack) throws StationOfflineException, StationFullException {
		if (!this.inorder) {
			throw new StationOfflineException(this);
		} else if (freeparkingslot.size() == 0) {
			throw new StationFullException(this);
		} else {
			boolean igotaplace = false;
			int n = 0;
			while (!igotaplace && n < freeparkingslot.size()) {
				try {
					ParkingSlot p = freeparkingslot.get(n);
					if (p.isUsable()) {
						p.addBicycle(bicycle, timeBicycleGaveBack);
						this.slotisoccupied(p);
						igotaplace = true;
						updateNumberBicyclePlus(p.getBicycle());
						numberOfDrop++;
						
						if (occupiedparkingslot.size() + outOfOrderParkingslot.size() == parkingslot.size()) {
							this.changed = true;
							notifyObservers();
						}
					}
					else {
						n++;
					}
				}


				catch (ParkingSlotFullException e) {
					System.err.println(e);
				}
			
		}
		
	}
	}
	
	/*
	 * Notify the users coming towards the station if the status of the station becomes offline, full or empty concerning the cases
	 */
	private void notifyObservers() {
		if (this.changed) {
			for( Entry<Integer, User> entry : this.userComing.entrySet()) {
				Integer userID = entry.getKey();
				User user = entry.getValue();
				user.update();
			}
			this.changed = false;
		}
	}
	
	
	public String toString() {
		return "-----------------"+ this.getTypeStation().getType()+ " Station " + this.getStationID()+ "------------ " + parkingslot.size() + " parkingslot(s)"+ "-------------" + this.getStationLat() +
				"\"\" " + this.getStationLong() + "\"\" " +"--------------- Number of operations : "+ (this.getNumberOfDrop() + this.getNumberOfRent()) +"\n" + 
				((!inorder) ? "\n!!!!!!!!!!!!!!!!Offline!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" : "" ) +
				freeparkingslot.size() + " free slot(s) : " + freeparkingslot.toString()+ "\n" + occupiedparkingslot.size() 
		+ " occupied slot(s) : " + occupiedparkingslot.toString() + "\n" +
		outOfOrderParkingslot.size() + " out-of-order slot(s) : " + outOfOrderParkingslot.toString()
		+ "\nThere is " + NumberBicycle.get("Electrical") + " electrical bicycle(s) and "+
				NumberBicycle.get("Mechanical") + " mechanical bicycle(s)" + "\n\n"; 
	}
	
	public double rateOfOccupation(double timeStart, double timeEnd) {
		
		double averageTimeOfOccupation = 0;
		
		for (ParkingSlot slot : parkingslot) {
			averageTimeOfOccupation += slot.rateOfOccupationSlot(timeStart, timeEnd);
		}
		
		averageTimeOfOccupation /= (parkingslot.size() * (timeEnd - timeStart));
		
		return averageTimeOfOccupation;
	}
	
	public String returnStationStatistics() {
		return "************************** Statistics Station n° " + this.StationID + "********************\n" +
				"Number of rents operation: " + this.numberOfRent +" | Number of return operations: " + this.numberOfDrop + "\n";
	}
	
}
