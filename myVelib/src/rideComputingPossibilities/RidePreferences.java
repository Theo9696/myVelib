package rideComputingPossibilities;

import java.util.ArrayList;

import exception.ComputingRideImpossibleException;
import myVelib.PlanningRide;
import myVelib.Station;
import myVelib.User;

public interface RidePreferences {
	
	public final int walkSpeed = 4;
	public final int mechanicalSpeed = 15;
	public final int electricalSpeed = 20;
	
	/*
	 * Function returning the planning ride advises to the user according to his preferences of computing
	 */
	public abstract PlanningRide compute(ArrayList<Station> stations, User user, double[] destination, String typeBicycle) throws ComputingRideImpossibleException;
}
