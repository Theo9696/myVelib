package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exceptions.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;
import myVelib.Station;

class AverageTimeOfOccupationTest {

	@Test
	void test() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		Station s1 = new Station();
		s1.addParkingSlot(p1);
		s1.addParkingSlot(p2);
		s1.addParkingSlot(p3);
		
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		p2.addBicycle(b2, 0);
		p2.removeBicycle(4);
		p2.addBicycle(b, 7);
		p2.removeBicycle(9);
		
		p3.addBicycle(b2, 0);
		p3.removeBicycle(6);
		
		assertTrue(s1.rateOfOccupation(0, 5) == (double)14/15);
		
	}
	
	@Test
	void test2() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		Station s1 = new Station();
		s1.addParkingSlot(p1);
		s1.addParkingSlot(p2);
		s1.addParkingSlot(p3);
		
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		p2.addBicycle(b2, 0);
		p2.removeBicycle(4);
		p2.addBicycle(b, 7);
		p2.removeBicycle(9);
		
		p3.addBicycle(b2, 0);
		p3.removeBicycle(6);
		
		assertTrue(s1.rateOfOccupation(4, 7) == (double)3/9);
		
	}
	
	@Test
	void test3() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		Station s1 = new Station();
		s1.addParkingSlot(p1);
		s1.addParkingSlot(p2);
		s1.addParkingSlot(p3);
		
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		p2.addBicycle(b2, 0);
		p2.removeBicycle(4);
		p2.addBicycle(b, 7);
		p2.removeBicycle(9);
		
		p3.addBicycle(b2, 0);
		p3.removeBicycle(6);
		
		assertTrue(s1.rateOfOccupation(11, 9) == 0);
		
	}

}
