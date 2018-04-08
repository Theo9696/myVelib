package exception;

import myVelib.ParkingSlot;

public class ParkingSlotFullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ParkingSlot parkingslot;
	
	public ParkingSlotFullException(ParkingSlot parkingslot) {
		this.parkingslot = parkingslot;
	}
	
	public String toString() {
		return "The parkingslot n° " + this.parkingslot.getParkingID()+" is already occupied by a bicycle";
	}

}
