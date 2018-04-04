package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;

class BicycleTest {

	
	@Test
	void testGet() {
		Bicycle bicycle = new MechanicalBicycle();
		assertTrue(bicycle.getID()==0);
	}
	
	@Test
	void testDisplay() {
		Bicycle bicycle = new ElectricalBicycle();
		assertTrue(("[bicycle n° " + 1 + " | type : " + "Electrical" + "]").equals( bicycle.toString()));
	}
	
	

}
