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

class AvoidPlusStationsTest {

	@Test
	void testAvoidPlusStation() throws ParkingSlotFullException, AskPlanningRideImpossibleException {
		User u1 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s3 = new Station(1,1,true,new StandardStation());
		Station s4 = new Station(10,9,true,new PlusStation());
		Station s5 = new Station(10,8.91,true,new StandardStation());
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
		u1.askNewRide(10, 10, "Electrical", "avoidplus", stations);
		assertTrue(u1.getActualRide().getStationDestination().getStationID() == 5);
	}
	
	@Test
	void testAvoidPlusStationWithoutOther() throws ParkingSlotFullException, AskPlanningRideImpossibleException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s0 = new Station(1,1,true,new PlusStation());
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
		u0.askNewRide(10, 10, "Electrical", "avoidplus", stations);
		assertTrue(u0.getActualRide() == null);
	}

}
