package myVelib;

import java.util.ArrayList;

public interface RidePreferences {
	
	public abstract PlanningRide compute(ArrayList<Station> stations, User user, double[] destination) throws ComputingRideImpossibleException;
}
