package myVelib;

public class ParkingSlotFullException extends Exception {
	
	private ParkingSlot parkingslot;
	
	public ParkingSlotFullException(ParkingSlot parkingslot) {
		// TODO Auto-generated constructor stub
		this.parkingslot = parkingslot;
	}
	
	public String toString() {
		return "The parkingslot n° " + this.parkingslot.getParkingID()+" is already occupied by a bicycle";
	}

}
