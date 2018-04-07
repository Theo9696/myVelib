package myVelib;

import java.util.ArrayList;


import Exceptions.ParkingSlotFullException;

public class ParkingSlot {
	
	// Attributes
	private static int nextnumericalID;
	private Bicycle bicycle [] = new Bicycle[1];
	private int parkingID;
	private boolean free;
	private boolean usable;
	private ArrayList<double[]> timeOccupied = new ArrayList<double[]>();
	
	/* ********************************** Creator ******************************* */
	public ParkingSlot() {
		this.parkingID = nextnumericalID;
		nextnumericalID++;
		this.usable = true;
		this.free = true;
	}
	
	
	/* ********************************* Methods ********************************* */
	
	/*
	 * Use this function to put a bicycle @bi in the parkingslot if it's available
	 */
	public void addBicycle(Bicycle bi, double timeBicycleGivenBack) throws ParkingSlotFullException {
		if (free && usable) {
			bicycle[0] = bi;
			free = false;
			// while the slot is occupied : we consider that it's for an infinite time
			double [] time = {timeBicycleGivenBack, Double.POSITIVE_INFINITY};
			timeOccupied.add(time);
	}
		else if (!free) {
			throw new ParkingSlotFullException(this);
		}
	}
	
	/*
	 * Use this function to remove the bicycle for this parkingslot if it's not free
	 */
	public Bicycle removeBicycle(double timeBicycleTaken) {
		if (!free) {
			free = true;	
			Bicycle b = (Bicycle)bicycle[0];
			bicycle[0] = null;
			
			if (this.timeOccupied.size() > 0) {
				
				double[] time = this.timeOccupied.remove(this.timeOccupied.size()-1);
				time[1] = timeBicycleTaken;
				timeOccupied.add(time);
				
			}
			
			return b;
			
	}
		else 
			System.out.println("you can't put a bicycle there");
			return null;
	}
	
	/* ************************************ Display *********************************************** */
		
	public String toString() {
		try {
			return "parkingslot " + this.getParkingID()+" : " + ((!free) ? "[bicycle " +this.getBicycle().getID()+"]" : (!usable)? "[OutofOrder]" : "[free]");
		}
		catch (NullPointerException e) {
			System.err.println("The parkingslot is empty ! ");
			return "Problem !";
		}
	}
	
	/* *************************************** Getters / Setters ***************************************** */
	
	public void setInOrder(boolean bool, double timesetInOrder) {
		if (this.free) {
			if (this.usable && !bool) {// was usable and becomes not usable ~ occupied for statistics
				double[] time = {timesetInOrder, Double.POSITIVE_INFINITY};
				timeOccupied.add(time);
			}
			if (!this.usable && bool) { // was not usable and becomes usable ~ free for statistics
				double[] time = timeOccupied.remove(timeOccupied.size()-1);
				time[1] = timesetInOrder;
				timeOccupied.add(time);
			}
			this.usable = bool;
			
		}
		else {
			System.out.println("The place is occupied and can't become outoforder");
		}
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
	
	/* *************************************** Methods ***************************************** */
	
	public double rateOfOccupationSlot(double timeStart, double timeEnd) {
		
		if (timeStart>timeEnd) {
			double t1 = timeStart;
			double t2 = timeEnd;
			timeStart = t2;
			timeEnd = t1;
		}
	
		double timeSlotOccupied = 0;
	
		for (double[] time : timeOccupied) {
			if (timeStart >= time[0] && timeStart<= time[1]) {
				if (timeEnd <= time[1]) { // the slot was occupied the all time
					return timeEnd - timeStart;
				} else { // the slot was at occupied at least until time[1]
					
					timeSlotOccupied += time[1] - timeStart;
				}
			}
			else if (timeStart < time[0] && timeEnd > time[0]) {
				if (timeEnd <= time[1]) { //the slot was maybe occupied before but at least between time[0] and timeEnd
					return timeSlotOccupied + timeEnd - time[0];
				} else { // the slot was  at least between time[0] and time[1]
						
					timeSlotOccupied += time[1] - time [0];
				}
			}
		}
		
		return timeSlotOccupied;
	}

}
