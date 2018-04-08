package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.ParkingSlot;
import myVelib.PlusStation;
import myVelib.StandardStation;
import myVelib.Station;
import myVelib.User;
import myVelib.WithoutSubscription;

class RidePreferencesTest {

	@Test
	void testStationPlusFurther109() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u1 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s3 = new Station(1,1,true,new StandardStation());
		Station s4 = new Station(10,9,true,new StandardStation());
		Station s5 = new Station(10,8.91,true,new PlusStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		p0.addBicycle(b,0);
		s3.addParkingSlot(p0);
		s4.addParkingSlot(p1);
		s5.addParkingSlot(p2);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s3);
		stations.add(s4);
		stations.add(s5);
		u1.askNewRide(10, 10, "Electrical", "preferplus", stations);
		assertTrue(u1.getActualRide().getStationDestination().getStationID() == 5);
	}
	
	@Test
	void testStationPlusFurther110() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u2 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s6 = new Station(1,1,true,new StandardStation());
		Station s7 = new Station(10,9,true,new StandardStation());
		Station s8 = new Station(10,8.9,true,new PlusStation());
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		ParkingSlot p5 = new ParkingSlot();
		Bicycle b2 = new ElectricalBicycle();
		p3.addBicycle(b2,0);
		s6.addParkingSlot(p3);
		s7.addParkingSlot(p4);
		s8.addParkingSlot(p5);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s6);
		stations.add(s7);
		stations.add(s8);
		u2.askNewRide(10, 10, "Electrical", "preferplus", stations);
		assertTrue(u2.getActualRide().getStationDestination().getStationID() == 8);
	}
	
	@Test
	void testStationPlusFurther111() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u3 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s9 = new Station(1,1,true,new StandardStation());
		Station s10 = new Station(10,9,true,new StandardStation());
		Station s11 = new Station(10,8.89,true,new PlusStation());
		ParkingSlot p6 = new ParkingSlot();
		ParkingSlot p7 = new ParkingSlot();
		ParkingSlot p8 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		p6.addBicycle(b,0);
		s9.addParkingSlot(p6);
		s10.addParkingSlot(p7);
		s11.addParkingSlot(p8);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s9);
		stations.add(s10);
		stations.add(s11);
		u3.askNewRide(10, 10, "Electrical", "preferplus", stations);
		assertTrue(u3.getActualRide().getStationDestination().getStationID() == 10);
	}
	
	@Test
	void testStationPlusFurther109WithPlus() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s0 = new Station(1,1,true,new StandardStation());
		Station s1 = new Station(10,9,true,new PlusStation());
		Station s2 = new Station(10,8.91,true,new PlusStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		p0.addBicycle(b,0);
		s0.addParkingSlot(p0);
		s1.addParkingSlot(p1);
		s2.addParkingSlot(p2);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s0);
		stations.add(s1);
		stations.add(s2);
		u0.askNewRide(10, 10, "Electrical", "preferplus", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 1);
	}

}
