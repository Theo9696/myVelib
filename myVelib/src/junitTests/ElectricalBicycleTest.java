package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Bicycle;
import myVelib.ElectricalBicycle;

class ElectricalBicycleTest {

	/*
	 * Check if the basic function getType() works
	 */
	@Test
	void test() {
		Bicycle b = new ElectricalBicycle();
		assertTrue(b.getType().contentEquals("Electrical"));
	}

}
