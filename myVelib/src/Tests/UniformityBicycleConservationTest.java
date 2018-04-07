package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Exceptions.AskPlanningRideImpossibleException;
import Exceptions.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.ParkingSlot;
import myVelib.PlusStation;
import myVelib.StandardStation;
import myVelib.Station;
import myVelib.User;
import myVelib.WithoutSubscription;

class UniformityBicycleConservationTest {

	@Test
	void testStationUniformityFurther104() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s0 = new Station(0,1,true,new StandardStation());
		Station s1 = new Station(10,9,true,new StandardStation());
		Station s2 = new Station(10,8.96,true,new PlusStation());
		Station s3 = new Station(0,1.04,true,new PlusStation());
		Station s4 = new Station(0,1.06,true,new PlusStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		ParkingSlot p5 = new ParkingSlot();
		ParkingSlot p6 = new ParkingSlot();
		ParkingSlot p7 = new ParkingSlot();
		ParkingSlot p8 = new ParkingSlot();
		ParkingSlot p9 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new ElectricalBicycle();
		Bicycle b3 = new ElectricalBicycle();
		Bicycle b4 = new ElectricalBicycle();
		Bicycle b5 = new ElectricalBicycle();
		p0.addBicycle(b,0);
		p1.addBicycle(b5,0);
		p2.addBicycle(b2,0);
		p3.addBicycle(b3,0);
		p4.addBicycle(b4,0);
		s0.addParkingSlot(p0);
		s3.addParkingSlot(p1);
		s3.addParkingSlot(p2);
		s4.addParkingSlot(p3);
		s4.addParkingSlot(p4);
		s1.addParkingSlot(p5);
		s2.addParkingSlot(p6);
		s2.addParkingSlot(p7);
		s2.addParkingSlot(p8);
		s2.addParkingSlot(p9);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s0);
		stations.add(s1);
		stations.add(s2);
		stations.add(s3);
		stations.add(s4);
		System.out.println(stations);
		u0.askNewRide(10, 10, "Electrical", "uniformity", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 2 && u0.getActualRide().getStationSource().getStationID() == 3);
	}
	
	@Test
	void testStationUniformityFurther105() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s0 = new Station(0,1,true,new StandardStation());
		Station s1 = new Station(10,9,true,new StandardStation());
		Station s2 = new Station(10,8.95,true,new PlusStation());
		Station s3 = new Station(0,1.05,true,new PlusStation());
		Station s4 = new Station(0,1.06,true,new PlusStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		ParkingSlot p5 = new ParkingSlot();
		ParkingSlot p6 = new ParkingSlot();
		ParkingSlot p7 = new ParkingSlot();
		ParkingSlot p8 = new ParkingSlot();
		ParkingSlot p9 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new ElectricalBicycle();
		Bicycle b3 = new ElectricalBicycle();
		Bicycle b4 = new ElectricalBicycle();
		Bicycle b5 = new ElectricalBicycle();
		p0.addBicycle(b,0);
		p1.addBicycle(b5,0);
		p2.addBicycle(b2,0);
		p3.addBicycle(b3,0);
		p4.addBicycle(b4,0);
		s0.addParkingSlot(p0);
		s3.addParkingSlot(p1);
		s3.addParkingSlot(p2);
		s4.addParkingSlot(p3);
		s4.addParkingSlot(p4);
		s1.addParkingSlot(p5);
		s2.addParkingSlot(p6);
		s2.addParkingSlot(p7);
		s2.addParkingSlot(p8);
		s2.addParkingSlot(p9);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s0);
		stations.add(s1);
		stations.add(s2);
		stations.add(s3);
		stations.add(s4);
		System.out.println(stations);
		u0.askNewRide(10, 10, "Electrical", "uniformity", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 7 && u0.getActualRide().getStationSource().getStationID() == 8);
	}
	
	@Test
	void testStationUniformityFurther106() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s0 = new Station(0,1,true,new StandardStation());
		Station s1 = new Station(10,9,true,new StandardStation());
		Station s2 = new Station(10,8.94,true,new PlusStation());
		Station s3 = new Station(0,1.059,true,new PlusStation());
		Station s4 = new Station(0,1.06,true,new PlusStation());
		ParkingSlot p0 = new ParkingSlot();
		ParkingSlot p1 = new ParkingSlot();
		ParkingSlot p2 = new ParkingSlot();
		ParkingSlot p3 = new ParkingSlot();
		ParkingSlot p4 = new ParkingSlot();
		ParkingSlot p5 = new ParkingSlot();
		ParkingSlot p6 = new ParkingSlot();
		ParkingSlot p7 = new ParkingSlot();
		ParkingSlot p8 = new ParkingSlot();
		ParkingSlot p9 = new ParkingSlot();
		Bicycle b = new ElectricalBicycle();
		Bicycle b2 = new ElectricalBicycle();
		Bicycle b3 = new ElectricalBicycle();
		Bicycle b4 = new ElectricalBicycle();
		Bicycle b5 = new ElectricalBicycle();
		p0.addBicycle(b,0);
		p1.addBicycle(b5,0);
		p2.addBicycle(b2,0);
		p3.addBicycle(b3,0);
		p4.addBicycle(b4,0);
		s0.addParkingSlot(p0);
		s3.addParkingSlot(p1);
		s3.addParkingSlot(p2);
		s4.addParkingSlot(p3);
		s4.addParkingSlot(p4);
		s1.addParkingSlot(p5);
		s2.addParkingSlot(p6);
		s2.addParkingSlot(p7);
		s2.addParkingSlot(p8);
		s2.addParkingSlot(p9);
		
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s0);
		stations.add(s1);
		stations.add(s2);
		stations.add(s3);
		stations.add(s4);
		System.out.println(stations);
		u0.askNewRide(10, 10, "Electrical", "uniformity", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 11 && u0.getActualRide().getStationSource().getStationID() == 10);
	}
	
	
	


}
