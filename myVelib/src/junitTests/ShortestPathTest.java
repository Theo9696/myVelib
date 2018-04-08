package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;
import myVelib.PlusStation;
import myVelib.StandardStation;
import myVelib.Station;
import myVelib.User;
import myVelib.WithoutSubscription;

class ShortestPathTest {
	
	
	/*
	 * In a computing preference "shortest", the goal is to make the less distance no matter the speed
	 * here a station s2  is closest to the destination and will be a more evident solution to save time but it will be s1 that will be chosen
	 * The user will make the shortest ride in term of distance
	 * s7 can't be chosen because doesn't have bicycle of this type
	 */
	@Test
	void testStationShortest() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s4 = new Station(1,1.1,true,new StandardStation());
		Station s5 = new Station(5,5,true,new PlusStation());
		Station s6 = new Station(10,9,true,new PlusStation());
		Station s7 = new Station(0.5,0.5, true, new StandardStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		p0.addBicycle(b,0);
		p3.addBicycle(b2, 0);
		s4.addParkingSlot(p0);
		s5.addParkingSlot(p1);
		s6.addParkingSlot(p2);
		s7.addParkingSlot(p3);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s4);
		stations.add(s5);
		stations.add(s6);
		stations.add(s7);
		u0.askNewRide(10, 10, "Electrical", "shortest", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 5 && u0.getActualRide().getStationSource().getStationID() == 4);
	}
	
	/*
	 * In a computing preference "shortest", the goal is to make the less distance no matter the speed
	 * here a station s2  is closest to the destination and will be a more evident solution to save time but it will be s1 that will be chosen
	 * The user will make the diagonal (0,0) -> (10,10) that is to say the shortest ride in term of distance
	 */
	@Test
	void testStationShortest2() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s0 = new Station(1,1.1,true,new StandardStation());
		Station s1 = new Station(5,5,true,new PlusStation());
		Station s2 = new Station(10,9,true,new PlusStation());
		Station s3 = new Station(0.5,0.5, true, new StandardStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new ElectricalBicycle();
		p0.addBicycle(b,0);
		p3.addBicycle(b2, 0);
		s0.addParkingSlot(p0);
		s1.addParkingSlot(p1);
		s2.addParkingSlot(p2);
		s3.addParkingSlot(p3);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s0);
		stations.add(s1);
		stations.add(s2);
		stations.add(s3);
		u0.askNewRide(10, 10, "Electrical", "shortest", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 1 && u0.getActualRide().getStationSource().getStationID() == 3);
	}


}
