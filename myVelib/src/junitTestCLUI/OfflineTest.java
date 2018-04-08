package junitTestCLUI;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import exception.StationEmptyException;
import exception.StationFullException;
import exception.StationOfflineException;
import gameground.CLUI;
import gameground.Simulation;

class OfflineTest {

	/*
	 * Simulation of an initialisation of a velib network and changing the state of the station 0 to offline
	 */
	@Test
	void test() throws StationOfflineException, ParkingSlotFullException, IOException, StationEmptyException, StationFullException, AskPlanningRideImpossibleException {
		
		Map<String, Simulation> simulations = (Map<String, Simulation>) new HashMap<String, Simulation>();
		String[] lignes = new String[2];
		lignes[0] = "setup myVelibSystem 3 10 10 15";
		lignes[1] = "offline myVelibSystem 0";
		simulations = CLUI.testEntry(lignes, simulations);
		Simulation simu = simulations.get("myVelibSystem");
		assertTrue(simu.getStations().get(0).isOffline() == true);
	}

}
