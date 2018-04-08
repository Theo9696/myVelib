package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import myVelib.Bicycle;
import myVelib.ElectricalBicycle;
import myVelib.MechanicalBicycle;
import myVelib.ParkingSlot;
import myVelib.PlusStation;
import myVelib.StandardStation;
import myVelib.Station;
import myVelib.User;
import myVelib.WithoutSubscription;

class UniformityBicycleConservationTest {

	/*
	 * In a computing preference "uniformity", if a station is  at less than 5% of the closest station to the destination or departure
	 * and has more bicycles (for departure), more slots available (for arrival) it must be chosen
	 * here a station s2 is 4% further from destination than station s1 but with more slots it will be chosen
	 * station s3 is 4% further from departure than station s0 but with more bicycles it will be chosen
	 */
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
	
	/*
	 * In a computing preference "uniformity", if a station is  at less than 5% of the closest station to the destination or departure
	 * and has more bicycles (for departure), more slots available (for arrival) it must be chosen
	 * here a station s7 is 5% further from destination than station s6 but with more slots it will be chosen
	 * station s8 is 5% further from departure than station s5 but with more bicycles it will be chosen
	 */
	@Test
	void testStationUniformityFurther105() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s5 = new Station(0,1,true,new StandardStation());
		Station s6 = new Station(10,9,true,new StandardStation());
		Station s7 = new Station(10,8.95,true,new PlusStation());
		Station s8 = new Station(0,1.05,true,new PlusStation());
		Station s9 = new Station(0,1.06,true,new PlusStation());
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
		s5.addParkingSlot(p0);
		s8.addParkingSlot(p1);
		s8.addParkingSlot(p2);
		s9.addParkingSlot(p3);
		s9.addParkingSlot(p4);
		s6.addParkingSlot(p5);
		s7.addParkingSlot(p6);
		s7.addParkingSlot(p7);
		s7.addParkingSlot(p8);
		s7.addParkingSlot(p9);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s5);
		stations.add(s6);
		stations.add(s7);
		stations.add(s8);
		stations.add(s9);
		System.out.println(stations);
		u0.askNewRide(10, 10, "Electrical", "uniformity", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 7 && u0.getActualRide().getStationSource().getStationID() == 8);
	}
	
	/*
	 * In a computing preference "uniformity", if a station is  at less than 5% of the closest station to the destination or departure
	 * and has more bicycles (for departure), more slots available (for arrival) it must be chosen
	 * here a station s12 is 6% further from destination than station s11 with more slots it can't be chosen
	 * station s13 is 5.9% further from departure than station s10 with more bicycles but it can't be chosen
	 */
	@Test
	void testStationUniformityFurther106() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s10 = new Station(0,1,true,new StandardStation());
		Station s11 = new Station(10,9,true,new StandardStation());
		Station s12 = new Station(10,8.94,true,new PlusStation());
		Station s13 = new Station(0,1.059,true,new PlusStation());
		Station s14 = new Station(0,1.06,true,new PlusStation());
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
		s10.addParkingSlot(p0);
		s13.addParkingSlot(p1);
		s13.addParkingSlot(p2);
		s14.addParkingSlot(p3);
		s14.addParkingSlot(p4);
		s11.addParkingSlot(p5);
		s12.addParkingSlot(p6);
		s12.addParkingSlot(p7);
		s12.addParkingSlot(p8);
		s12.addParkingSlot(p9);
		
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s10);
		stations.add(s11);
		stations.add(s12);
		stations.add(s13);
		stations.add(s14);
		System.out.println(stations);
		u0.askNewRide(10, 10, "Electrical", "uniformity", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 11 && u0.getActualRide().getStationSource().getStationID() == 10);
	}
	
	/*
	 * In a computing preference "uniformity", if a station is  at less than 5% of the closest station to the destination or departure
	 * and has more bicycles (for departure), more slots available (for arrival) it must be chosen
	 * here a station s17 is 4% further from destination than station s16 but with more slots it will be chosen
	 * station s18 is 4% further from departure than station s15 but with no bicycle of the type wished it can't be chosen
	 * station s19 is 4.6% further from departure than station s15 but with more bicycles of the type wished it will be chosen
	 */
	@Test
	void testStationUniformityFurther104ButNoBicycleType() throws AskPlanningRideImpossibleException, ParkingSlotFullException {
		User u0 = new User("Patrick", 0, new WithoutSubscription(),0,0);
		Station s15 = new Station(0,1,true,new StandardStation());
		Station s16 = new Station(10,9,true,new StandardStation());
		Station s17 = new Station(10,8.96,true,new PlusStation());
		Station s18 = new Station(0,1.04,true,new PlusStation());
		Station s19 = new Station(0,1.046,true,new PlusStation());
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
		Bicycle b2 = new MechanicalBicycle();
		Bicycle b3 = new MechanicalBicycle();
		Bicycle b4 = new ElectricalBicycle();
		Bicycle b5 = new ElectricalBicycle();
		p0.addBicycle(b,0);
		p1.addBicycle(b3,0);
		p2.addBicycle(b2,0);
		p3.addBicycle(b5,0);
		p4.addBicycle(b4,0);
		s15.addParkingSlot(p0);
		s18.addParkingSlot(p1);
		s18.addParkingSlot(p2);
		s19.addParkingSlot(p3);
		s19.addParkingSlot(p4);
		s16.addParkingSlot(p5);
		s17.addParkingSlot(p6);
		s17.addParkingSlot(p7);
		s17.addParkingSlot(p8);
		s17.addParkingSlot(p9);
		ArrayList<Station> stations = new ArrayList<Station>();
		stations.add(s15);
		stations.add(s16);
		stations.add(s17);
		stations.add(s18);
		stations.add(s19);
		System.out.println(stations);
		u0.askNewRide(10, 10, "Electrical", "uniformity", stations);
		assertTrue(u0.getActualRide().getStationDestination().getStationID() == 17 && u0.getActualRide().getStationSource().getStationID() == 19);
	}
	
	
	


}
