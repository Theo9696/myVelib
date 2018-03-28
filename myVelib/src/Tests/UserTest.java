package Tests;

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

class UserTest {

	@Test
	void testElectricalWithout() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u1 = new User("Patrick", 0, new WithoutSubscription(),25,6);
		assertTrue(u1.payement((float) 2.35, b,new StandardStation())==6);
	}
	@Test
	void testMechanicalWithout() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u1 = new User("Patrick", 0, new WithoutSubscription(),2,5);
		assertTrue(u1.payement((float) 2.35, b2, new StandardStation())==3);
	}
	@Test
	void testElectricalVlibre() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0, new Vlibre(),5,3);
		assertTrue(u2.payement(2.20f, b, new StandardStation())==5);
	}
	@Test
	void testElectricalVlibreCredit() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent2", 0.22f, new Vlibre(),5,6);
		assertTrue(u2.payement(2.20f, b, new StandardStation())==3);
	}
	@Test
	void testMechanicalVlibre() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0, new Vlibre(),2,3);
		assertTrue(u2.payement(2.20f, b2, new StandardStation())==2);
	}
	
	void testMechanicalVlibreCredit() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0.22f, new Vlibre(),2,5);
		assertTrue(u2.payement(1.20f, b2,new StandardStation())==0);
	}
	@Test
	void testElectricalVmax() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0, new Vmax(),2,3);
		assertTrue(u2.payement(2.20f, b,new StandardStation())==2);
	}
	@Test
	void testElectricalVmaxCredit() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0.22f, new Vmax(),5,6);
		assertTrue(u2.payement(2.20f, b,new StandardStation())==1);
	}
	@Test
	void testMechanicalVmax() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0, new Vmax(),5,3);
		assertTrue(u2.payement(2.20f, b2,new StandardStation())==2);
	}
	@Test
	void testMechanicalVmaxCredit() {
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new MechanicalBicycle();
		User u2 = new User("Vincent", 0.22f, new Vmax(),3,3);
		assertTrue(u2.payement(1.20f, b2,new StandardStation())==0);
	}

}