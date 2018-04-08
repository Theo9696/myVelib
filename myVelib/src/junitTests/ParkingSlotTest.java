package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;

class ParkingSlotTest {

	/*
	 * rate of occupation for one easy case : always a bicycle ! Then for the all time (3) the parkingslot is occupied (from 1 to 4)
	 */
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
	
	/*
	 * rate of occupation for one easy case : no bicycle ! Then for the all time (0) the parkingslot is not occupied (from 5 to 6)
	 */
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
	/*
	 * rate of occupation for a more complex case : not always a bicycle in the parkingslot 
	 * ! Then the parkingslot is occupied from (0->5) and (7.5->7.7), total time occupied : 5.2
	 */
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
	/*
	 * Never occupied : 0
	 */
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
	/*
	 * if we reverse timeStart and timeEnd : do the same
	 */
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
	/*
	 * Out of order must act like occupied...
	 */
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
	
	/*
	 * Function addBicycle(b) must put the bicycle b into the parkingSlot and the parkingslot must have a uniqueID
	 */
	@Test
	void test() throws ParkingSlotFullException {
		ParkingSlot p = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		Bicycle b1 = new ElectricalBicycle();
		//Bicycle b2 = new MechanicalBicycle();
		p.addBicycle(b1, 0);
		assertTrue(p.getBicycle().equals(b1) && p.getParkingID() == 1 && p2.getParkingID() == 2);
	}
	/*
	 * addBicycle remove it, it must not have bicycle anymore
	 */
	@Test
	void test2() throws ParkingSlotFullException {
		ParkingSlot p = new ParkingSlot();
		Bicycle b1 = new ElectricalBicycle();
		//Bicycle b2 = new MechanicalBicycle();
		p.addBicycle(b1, 0);
		p.removeBicycle(5);
		assertTrue(p.getBicycle() == null);
	}
	
	/*
	 * addBicycle remove it and put an other one without mistake
	 */
	@Test
	void test3() throws ParkingSlotFullException {
		ParkingSlot p = new ParkingSlot();
		Bicycle b1 = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		p.addBicycle(b1, 0);
		p.removeBicycle(5);
		p.addBicycle(b2, 6);
		assertTrue(p.getBicycle().equals(b2));
	}
	

}
