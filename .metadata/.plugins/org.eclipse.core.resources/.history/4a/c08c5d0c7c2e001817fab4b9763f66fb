package myVelib;

public class ParkingSlot {
	
	private static int nextnumericalID;
	private Bicycle bicycle [] = new Bicycle[1];
	private int parkingID;
	private boolean free;
	private boolean usable;

	ParkingSlot() {
		this.parkingID = nextnumericalID;
		nextnumericalID++;
		this.usable = true;
		this.free = true;
	}
	
	
	public void addBicycle(Bicycle bi) throws ParkingSlotFullException {
		if (free && usable) {
			bicycle[0] = bi;
			free = false;		
	}
		else if (!free) {
			throw new ParkingSlotFullException(this);
		}
	}
	
	public Bicycle removeBicycle() {
		if (!free) {
			free = true;	
			Bicycle b = (Bicycle)bicycle[0];
			bicycle[0] = null;
			return b;
	}
		else 
			System.out.println("you can't put a bicycle there");
			return null;
	}

	public Bicycle getBicycle() {
		return bicycle[0];
	}

	public int getParkingID() {
		return parkingID;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}
	
	public String toString() {
		return "parkingslot " + this.getParkingID()+" : " + ((!free) ? "[bicycle " +this.getBicycle().getID()+"]" : "[free]");
	}
}
