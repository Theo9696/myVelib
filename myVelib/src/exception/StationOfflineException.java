package exception;

import myVelib.Station;

public class StationOfflineException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Station station;
	
	public StationOfflineException(Station station) {
		// TODO Auto-generated constructor stub
		this.station = station;
	}
	
	public String toString() {
		return "Station n° " + station.getStationID()+" is offline";
	}


}
