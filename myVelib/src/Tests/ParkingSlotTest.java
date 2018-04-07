package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exceptions.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;

class ParkingSlotTest {

	@Test
	void testStatistics() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
			
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		assertTrue(p1.rateOfOccupationSlot(1, 4) == 3);
		
	}
	
	@Test
	void testStatistics2() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
			
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		assertTrue(p1.rateOfOccupationSlot(5, 6) == 0);
		
	}
	
	@Test
	void testStatistics3() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
			
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		assertTrue(p1.rateOfOccupationSlot(8, 0) == 5.2);
		
	}
	
	@Test
	void testStatistics4() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
			
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		p1.removeBicycle(7.7);
		
		assertTrue(p1.rateOfOccupationSlot(8, 10) == 0);
		
	}
	
	@Test
	void testStatistics5() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
			
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.addBicycle(b2, 7.5);
		
		assertTrue(p1.rateOfOccupationSlot(8, 10) == 2);
		
	}
	
	@Test
	void testStatistics6() throws ParkingSlotFullException {
		ParkingSlot p1 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
			
		p1.addBicycle(b, 0);
		p1.removeBicycle(5);
		p1.setInOrder(false, 6); // becomes out of order ~ occupied for statistics
		p1.setInOrder(true, 8);
		
		assertTrue(p1.rateOfOccupationSlot(5, 10) == 2);
		
	}

}
