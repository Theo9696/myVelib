package exception;

import myVelib.Station;

public class StationEmptyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Station station;
	
	
	public StationEmptyException(Station station) {
		// TODO Auto-generated constructor stub
		this.station = station;
	}
	
	public String toString() {
		return "The station n° " + station.getStationID() + " is empty !";
	}

}
