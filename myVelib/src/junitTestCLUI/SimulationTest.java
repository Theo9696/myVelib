package junitTestCLUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import exception.StationEmptyException;
import exception.StationFullException;
import exception.StationOfflineException;
import gameground.Simulation;

class SimulationTest {

	/*
	 * A simulation can be created by giving the number of Stations, parkingslot by stations, number of users, size of the area (in km)
	 * number of bicycles randomly distributed between stations
	 */
	@Test
	void test() throws StationOfflineException, ParkingSlotFullException {
		Simulation simu = new Simulation(3,5,2,10,5,"VelibTest");
		assertTrue(simu.getStations().size() == 3 && simu.getStationsOrdered().get(1).getNumberOfParkingslot() == 5 && 
				simu.getUsers().size() == 2 && 
				(simu.getStations().get(0).getNumberofBikeAvailable() + simu.getStations().get(1).getNumberofBikeAvailable() + simu.getStations().get(2).getNumberofBikeAvailable() ==5 ));
	}
	
	/*
	 * All the rest of the functions call for function of the core which have been already tested
	 */

	/*
	 * Testing of the function newRide -> user obtain a planning ride and takeABicycleInTheStationSource which uses the planningRide to go 
	 * on the best station according the computing preference
	 * user 2 ask for a planning ride and take a bicycle in the station advised
	 */
	@Test
	void test2() throws StationOfflineException, ParkingSlotFullException, StationEmptyException, AskPlanningRideImpossibleException, StationFullException {
		Simulation simu2 = new Simulation(3,10,2,10,20,"VelibTest");
		simu2.newRide(2, 5, 5,"Electrical", "fastest");
		simu2.takeABicycleInTheStationSource(2, "Electrical", 0);
		assertTrue(simu2.getUsers().get(2).getActualRide().getBicycle() != null);
	}
	
	/*
	 * Testing of the function newRide -> user obtain a planning ride and takeABicycleInTheStationSource which uses the planningRide to go 
	 * on the best station according the computing preference
	 * and returnABicycleInTheStationDestination -> finish the ride and stores it in the pastRide of the User
	 * user 5 ask for a planning ride, take a bicycle in the station advised and return a bicycle in the station advised
	 */
	@Test
	void test3() throws StationOfflineException, ParkingSlotFullException, StationEmptyException, AskPlanningRideImpossibleException, StationFullException {
		Simulation simu2 = new Simulation(3,10,2,10,20,"VelibTest");
		simu2.newRide(5, 5, 5,"Electrical", "fastest");
		simu2.takeABicycleInTheStationSource(5, "Electrical", 0);
		simu2.returnABicycleInTheStationDestination(5, 9);
		assertTrue(simu2.getUsers().get(5).getActualRide() == null && simu2.getUsers().get(5).getPastRide().size() == 1);
	}
	
	/*
	 * user 7 ask for a planning ride, take a bicycle in the station advised and return a bicycle in the station advised
	 * test of the function which display Stations sorted by the number of operations
	 * There a station will have no operation, one will have a drop operation (1 operation), and one a rent operation (1 operation)
	 * stations are sorted the station with less operation to the station with more operations
	 * 
	 */
	@Test
	void test4() throws StationOfflineException, ParkingSlotFullException, StationEmptyException, AskPlanningRideImpossibleException, StationFullException {
		Simulation simu3 = new Simulation(3,10,2,10,20,"VelibTest");
		simu3.newRide(7, 5, 5,"Electrical", "fastest");
		simu3.takeABicycleInTheStationSource(7, "Electrical", 0);
		simu3.returnABicycleInTheStationDestination(7, 9);
		
		assertTrue(simu3.getMostUsedStations().get(0).getNumberOfOperations() == 0 && simu3.getMostUsedStations().get(1).getNumberOfOperations() == 1 && simu3.getMostUsedStations().get(2).getNumberOfOperations() == 1);
	}
}
