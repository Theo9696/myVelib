package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.StandardStation;
import myVelib.User;
import myVelib.Vlibre;
import myVelib.Vmax;
import myVelib.WithoutSubscription;

class SubscriptionPossibilityTest {

	/*
	 * Without subscription : payement,  2€/hour started, there 2.35 hour -> 3hours so 6€
	 */
	@Test
	void testElectricalWithout() {
		Bicycle b = new ElectricalBicycle();
		User u1 = new User("Patrick", 0, new WithoutSubscription(),25,6);
		assertTrue(u1.payement((float) 2.35, b,new StandardStation())==6);
	}
	
	/*
	 * Without subscription : payement, 1€/hour started, there 2.35 hour -> 3hours so 3€
	 */
	@Test
	void testMechanicalWithout() {
		Bicycle b2 = new MechanicalBicycle();
		User u1 = new User("Patrick", 0, new WithoutSubscription(),2,5);
		assertTrue(u1.payement((float) 2.35, b2, new StandardStation())==3);
	}
	/*
	 *  Vlibre: payement , 1€/first hour + 2€/hour started, there 2.20 hour -> 3hours so 1+4 = 5€
	 */
	@Test
	void testElectricalVlibre() {
		Bicycle b = new ElectricalBicycle();
		User u2 = new User("Vincent", 0, new Vlibre(),5,3);
		assertTrue(u2.payement(2.20f, b, new StandardStation())==5);
	}
	
	/*
	 * Vlibre: payement with 0.22 credit , 1€/first hour + 2€/hour started, there 2.20 hour -> 2 hours and 0.02 credit left so 1+2 = 3€
	 */
	@Test
	void testElectricalVlibreCredit() {
		Bicycle b = new ElectricalBicycle();
		User u2 = new User("Vincent2", 0.22f, new Vlibre(),5,6);
		assertTrue(u2.payement(2.20f, b, new StandardStation())==3);
	}
	
	/*
	 *  Vlibre: payement , 0€/first hour + 1€/hour started, there 2.20 hour -> 3hours so 0+2 = 2€
	 */
	@Test
	void testMechanicalVlibre() {
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0, new Vlibre(),2,3);
		assertTrue(u2.payement(2.20f, b2, new StandardStation())==2);
	}
	/*
	 * Vlibre: payement with 0.22 credit , 0€/first hour + 1€/hour started, there 1.20 hour -> 1 hour and 0.02 credit left so 0= 0€
	 */
	void testMechanicalVlibreCredit() {
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0.22f, new Vlibre(),2,5);
		assertTrue(u2.payement(1.20f, b2,new StandardStation())==0);
	}
	
	/*
	 * Vmax: payement , 0€/first hour + 1€/hour started, there 2.20 hour -> 3 hours so 0+2 = 2€
	 */
	@Test
	void testElectricalVmax() {
		Bicycle b = new ElectricalBicycle();
		User u2 = new User("Vincent", 0, new Vmax(),2,3);
		assertTrue(u2.payement(2.20f, b,new StandardStation())==2);
	}
	
	/*
	 * Vmax: payement with 0.22 credit , 0€/first hour + 1€/hour started, there 2.20 hour -> 2 hours and 0.02 credit left so 0+1 = 1€
	 */
	@Test
	void testElectricalVmaxCredit() {
		Bicycle b = new ElectricalBicycle();

		User u2 = new User("Vincent", 0.22f, new Vmax(),5,6);
		assertTrue(u2.payement(2.20f, b,new StandardStation())==1);
	}
	@Test
	
	/*
	 *  Vmax: payement , 0€/first hour + 1€/hour started, there 2.20 hour -> 3 hours so 0+2 = 2€
	 */
	void testMechanicalVmax() {
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0, new Vmax(),5,3);
		assertTrue(u2.payement(2.20f, b2,new StandardStation())==2);
	}
	
	/*
	 * Vmax: payement with 0.22 credit , 0€/first hour + 1€/hour started, there 1.20 hour -> 1 hour and 0.02 credit left so 0 = 0€
	 */
	@Test
	void testMechanicalVmaxCredit() {
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0.22f, new Vmax(),3,3);
		assertTrue(u2.payement(1.20f, b2,new StandardStation())==0);
	}
}
