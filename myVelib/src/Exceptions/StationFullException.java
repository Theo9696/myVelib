package Exceptions;

import myVelib.Station;

public class StationFullException extends Exception {
	
	Station station;
	
	
	public StationFullException(Station station, boolean bool) {
		// TODO Auto-generated constructor stub
		this.station = station;
	}
	
	public StationFullException(Station station) {
		// TODO Auto-generated constructor stub
		this.station = station;
		System.out.println("We are sorry...You can't park there because the station is full...");
	}
	
	
	
	public String toString() {
		return "The station n° " + station.getStationID() + " is full !";
	}

}
