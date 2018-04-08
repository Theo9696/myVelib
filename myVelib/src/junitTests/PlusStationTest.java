package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.PlusStation;
import myVelib.TypeStation;

class PlusStationTest {

	/*
	 * The type of a plus Station is "Plus"
	 */
	@Test
	void test() {
		
		TypeStation type = new PlusStation();
		
		assertTrue(type.getType().equals("Plus"));
	}

}
