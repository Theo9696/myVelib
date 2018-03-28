package myVelib;

import java.util.ArrayList;

import Exceptions.ComputingRideImpossibleException;

public class UniformityBicycleConservation implements RidePreferences {

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
								Math.pow(s1.getStationLong()-s2.getStationLong(),2)) / ((typeBicycle == "Electrical") ? electricalSpeed : mechanicalSpeed)
								
								// walk between the station and the destination
								+ Math.sqrt(Math.pow(destination[0]-s2.getStationLat(),2)
										+ Math.pow(destination[1]-s2.getStationLong(),2)/walkSpeed ); 
						
						//station of destination can't be  plus station
						if (!s1.isOffline() && !s2.isOffline() && rideTimeNew < rideTime) {
							if (s1.hasStationBicycle(typeBicycle) && !s2.isFull()) {
								//Departure has a bicycle of the wished type //Arrival has a free slot
								departure = s1;											
								arrival = s2;
								rideTime = rideTimeNew;
								
						}
								
					}
				}
				
			}
			}
			// We check if a plus station is at less than 110% from the destination than the station find with a fastest computing
			Station arrivalnew = arrival;
			Station departurenew = departure;
			System.out.println(arrival);
			System.out.println(departure);
			for (Station s1 : stations) {
				double distances1destination = Math.sqrt(Math.pow(destination[1] - s1.getStationLong(),2) + Math.pow(destination[0] - s1.getStationLat(),2));
				double distances1source = Math.sqrt(Math.pow(user.getUserLong() - s1.getStationLong(),2) + Math.pow(user.getUserLat() - s1.getStationLat(),2));
				
				double distancearrival = Math.sqrt(Math.pow(destination[1] - arrival.getStationLong(),2) + Math.pow(destination[0] - arrival.getStationLat(),2));
				double distancesource = Math.sqrt(Math.pow(user.getUserLong() - departure.getStationLong(),2) + Math.pow(user.getUserLong() - departure.getStationLat(),2));
				
				double distancearrivalnew = Math.sqrt(Math.pow(destination[1] - arrivalnew.getStationLong(),2) + Math.pow(destination[0] - arrivalnew.getStationLat(),2));
				double distancesourcenew = Math.sqrt(Math.pow(user.getUserLong() - departurenew.getStationLong(),2) + Math.pow(user.getUserLat() - departurenew.getStationLat(),2));
				// If a station with more free parkingslot of the wished type is at less than 5% more distance of previous arrival, the station will be prefered
				if ((s1.getNumberOfFreeParkingslot()>arrivalnew.getNumberOfFreeParkingslot() || distancearrivalnew>distances1destination) && 1.050000001*distancearrival >= distances1destination ) {
					arrivalnew = s1;
				}
				// If a station with more bicycles of the wished type is at less than 5% more distance of previous departure, the station will be prefered
				if ((s1.getNumberofBikeAvailable(typeBicycle)>departurenew.getNumberofBikeAvailable(typeBicycle) || distancesourcenew>distances1source) && 1.05*distancesource >= distances1source) {
					departurenew = s1;
				}
				
			}
			
			
			if (rideTime == Double.POSITIVE_INFINITY) {
				throw new ComputingRideImpossibleException();
			} else {
			return new PlanningRide(departurenew, arrivalnew, user);
			}
		}
		catch (NullPointerException e) {
			System.out.println("No combinaison of stations found ! ");
		}
		catch (ComputingRideImpossibleException e) {
			System.err.println("No combinaison of stations found ! ");
		}
		throw new ComputingRideImpossibleException(); 
	
	
	

	}
}
