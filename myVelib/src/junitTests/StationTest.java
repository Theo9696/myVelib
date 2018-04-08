package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.ParkingSlotFullException;
import exception.StationEmptyException;
import exception.StationFullException;
import exception.StationOfflineException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;
import myVelib.Station;

class StationTest {

	/*
	 * Test the function to obtain a bicycle from the station : there is a bicycle of this type !
	 * Test the functions of analysis of the state of the station (isEmpty(), hasStationBicycle(type), getNumberOfRent()..
	 * There is only one rent there 
	 */
	@Test
	void test() throws ParkingSlotFullException, StationOfflineException, StationEmptyException {
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		//Bicycle b2 = new MechanicalBicycle();
		p1.addBicycle(b, 0);
		Station s = new Station();
		s.addParkingSlot(p1);
		s.addParkingSlot(p2);
		s.addParkingSlot(p3);
		s.addParkingSlot(p4);
		s.needBicycle("Electrical", 0);
		assertTrue(s.isEmpty() == true && s.hasStationBicycle("Electrical") == false && s.getNumberOfRent() == 1);
	}
	
	/*
	 * Test of the functionning of returnBicycle function
	 * and all the analysis of the status of the station...
	 * one drop more than the previous test
	 */
	@Test
	void test2() throws ParkingSlotFullException, StationOfflineException, StationEmptyException, StationFullException {
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		p1.addBicycle(b, 0);
		Station s = new Station();
		s.addParkingSlot(p1);
		s.addParkingSlot(p2);
		s.addParkingSlot(p3);
		s.addParkingSlot(p4);
		s.needBicycle("Electrical", 0);
		s.returnBicycle(b2, 5);
		assertTrue(s.isEmpty() == false && s.hasStationBicycle("Electrical") == false && s.hasStationBicycle("Mechanical") == true && s.getNumberOfRent() == 1
				&& s.getNumberOfDrop() == 1);
	}
	
	/* The test of the average time station is occupied is treated in a JunitTest : AverageTimeOfOccupation */

}
