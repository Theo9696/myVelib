package rideComputingPossibilities;

import java.util.ArrayList;

import exception.ComputingRideImpossibleException;
import myVelib.PlanningRide;
import myVelib.Station;
import myVelib.User;

public class AvoidPlusStations implements RidePreferences {
	
	String name;
	
	public AvoidPlusStations(){
		super();
		this.name = "avoidplus";
	}
	
	
	
	public String getName() {
		return this.name;
	}
	/*
	 * The compute version for AvoidPluStations
	 * It returns a planning ride where the source and the destination station are chosen so as to propose the fastest ride
	 * to the user but without taking a plus station.
	 */
	public PlanningRide compute(ArrayList<Station> stations, User user, double[] destination, String typeBicycle) throws ComputingRideImpossibleException {
		
		
		try {
			//We take one possible value for the distance if there is at least one station
			double rideTime = Double.POSITIVE_INFINITY ;
			Station departure = stations.get(0);
			Station arrival = stations.get(1);
			
			for (Station s1 : stations) {
				for (Station s2 : stations) {
					
					if (!s1.equals(s2)) { //station arrival != departure
						
						double rideTimeNew = 
								//walk between the position of the user and the station
								Math.sqrt(Math.pow(user.getUserLat()-s1.getStationLat(),2) 
								+ Math.pow(user.getUserLong()-s1.getStationLong(),2)) / walkSpeed 
								 
								// between the 2 stations
								+ Math.sqrt(Math.pow(s1.getStationLat()-s2.getStationLat(),2) +
								Math.pow(s1.getStationLong()-s2.getStationLong(),2)) / ((typeBicycle.equals( "Electrical")) ? electricalSpeed : mechanicalSpeed)
								
								// walk between the station and the destination
								+ Math.sqrt(Math.pow(destination[0]-s2.getStationLat(),2)
										+ Math.pow(destination[1]-s2.getStationLong(),2)/walkSpeed ); 
						
						//station of destination can't be  plus station
						if (rideTimeNew < rideTime && !s1.isOffline() && !s2.isOffline() && !s2.getTypeStation().getType().equals("Plus")) {
							if (s1.hasStationBicycle(typeBicycle) && !s2.isFull()) { //Departure has a bicycle of the wished type
								departure = s1;											//Arrival has a free slot 
								arrival = s2;
								rideTime = rideTimeNew;
						}
								
					}
				}
				
			}
			}
			
			if (rideTime == Double.POSITIVE_INFINITY) {
				throw new ComputingRideImpossibleException();
			} else {
			return new PlanningRide(departure, arrival, user);
			}
		}
		catch (NullPointerException e) {
			System.out.println(e);
		}
		catch (ComputingRideImpossibleException e) {
			System.err.println("No combinaison of stations found ! ");
		}
		throw new ComputingRideImpossibleException(); 
	
	
	

	}

}
