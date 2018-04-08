package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.ParkingSlot;
import myVelib.PlanningRide;
import myVelib.Station;
import myVelib.User;

class PlanningRideTest {

	/*
	 * addBicycle must add a bicycle if there is not an other one in it
	 * UserHasNotABicycle must be false in this case
	 * and TimeTaken is 0 
	 */
	@Test
	void test() {
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Station s1 = new Station();
		Station s2 = new Station();
		s1.addParkingSlot(p1);
		s2.addParkingSlot(p2);
		User user = new User("Théo");
		PlanningRide planning = new PlanningRide(s1, s2, user);
		planning.addBicycle(b, 0);
		assertTrue(planning.getBicycle().equals(b) && planning.UserHasNotABicycle()==false && planning.getTimeTaken()==0);
	}

}
