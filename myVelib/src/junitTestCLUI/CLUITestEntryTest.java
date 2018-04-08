package junitTestCLUI;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import gameground.CLUI;
import gameground.Simulation;

import org.junit.jupiter.api.Test;

import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import exception.StationEmptyException;
import exception.StationFullException;
import exception.StationOfflineException;

class CLUITestEntryTest {

	/*
	 * Initialisation of a simulation with 3 stations, 10 parkingslot by stations, of size area of 10, with 15 bicycles randomly distributed
	 */
	@Test
	void test() throws StationOfflineException, ParkingSlotFullException, IOException, StationEmptyException, StationFullException, AskPlanningRideImpossibleException {
		
		Map<String, Simulation> simulations = (Map<String, Simulation>) new HashMap<String, Simulation>();
		String[] lignes = new String[1];
		lignes[0] = "setup myVelibSystem 3 10 10 15";
		simulations = CLUI.testEntry(lignes, simulations);
		Simulation simu = simulations.get("myVelibSystem");
		assertTrue(simu.getStations().size() == 3 && simu.getStationsOrdered().get(1).getNumberOfParkingslot() == 10 && 
				simu.getUsers().size() == 0 && 
				(simu.getStations().get(0).getNumberofBikeAvailable() + simu.getStations().get(1).getNumberofBikeAvailable() + simu.getStations().get(2).getNumberofBikeAvailable() == 15 ));
	}
	

}
