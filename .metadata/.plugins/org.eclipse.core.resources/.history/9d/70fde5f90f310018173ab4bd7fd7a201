package myVelib;

import java.util.ArrayList;

public interface RidePreferences {
	
	public final int walkSpeed = 4;
	public final int mechanicalSpeed = 15;
	public final int electricalSpeed = 20;
	
	public abstract PlanningRide compute(ArrayList<Station> stations, User user, double[] destination, String typeBicycle) throws ComputingRideImpossibleException;
}
