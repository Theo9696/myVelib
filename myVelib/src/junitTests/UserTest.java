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

class UserTest {

	/*
	 * askNewRide enables to have a planningRide to follow
	 * completeARide stores the past planningRide and cleans the actualPlanningRide
	 */
	@Test
	void test() throws ParkingSlotFullException, AskPlanningRideImpossibleException {
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
		u1.askNewRide(10, 10, "Electrical", "fastest", stations);
		u1.completeARide(5);
		System.out.println(u1.getPastRide());
		assertTrue(u1.getActualRide() == null);
	}

}
