package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Bicycle;
import myVelib.MechanicalBicycle;

class MechanicalBicycleTest {

	/*
	 * A mechanical bicycle must return to the function getType() : "Mechanical"
	 */
	@Test
	void test() {
		Bicycle b = new MechanicalBicycle();
		assertTrue(b.getType().equals("Mechanical"));
	}

}
