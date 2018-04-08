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

class ReturnBikeInTheStationAdvisedTest {

	/*
	 * Simulation of an initialisation of a velib network, adding of a user and adding a planning ride to this user
	 * The user take then a bicycle in the station advised and return it to the station of arrival advised
	 * we check that the user has a pastRide but no more actual.
	 */
	@Test
	void test() throws StationOfflineException, ParkingSlotFullException, IOException, StationEmptyException, StationFullException, AskPlanningRideImpossibleException {
		
		Map<String, Simulation> simulations = (Map<String, Simulation>) new HashMap<String, Simulation>();
		String[] lignes = new String[5];
		lignes[0] = "setup myVelibSystem 3 10 10 15";
		lignes[1] = "addUser Théo Vmax myVelibSystem";
		lignes[2] = "newRide 0 5 5 Electrical fastest myVelibSystem";
		lignes[3] = "rentBikeInTheStationAdvised 0 myVelibSystem 0 Electrical";
		lignes[4] = "returnBikeInTheStationAdvised 0 myVelibSystem 5";
		simulations = CLUI.testEntry(lignes, simulations);
		Simulation simu = simulations.get("myVelibSystem");
		assertTrue(simu.getUsers().get(0).getPastRide().size() == 1 && simu.getUsers().get(0).getActualRide() == null );
	}

}
