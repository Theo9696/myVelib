package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.StandardStation;
import myVelib.TypeStation;

class StandardStationTest {

	/*
	 * The type of a plus Station is "Standard"
	 */
	@Test
	void test() {
		
		TypeStation type = new StandardStation();
		
		assertTrue(type.getType().equals("Standard"));
	}

}
