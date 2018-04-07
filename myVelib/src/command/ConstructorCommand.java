package command;

public class ConstructorCommand {
	
	
	
	public Command create(String name) {
		
		if (name.equals("setup")) {
			return new Setup();
		} else if (name.equals("addUser")) {
			return new AddUser();
			
		} else if (name.equals("offline")) {
			return new Offline();
			
		} else if (name.equals("online")) {
			return new Online();
			
		} else if (name.equals("rentBike")) {
			return new RentBike();
		
		} else if (name.equals("rentBikeInTheStationAdvised")) {
			return new RentBikeInTheStationAdvised();
			
		} else if (name.equals("returnBikeInTheStationAdvised")) {
			return new ReturnBikeInTheStationAdvised();
			
		} else if (name.equals("returnBike")) {
			return new ReturnBike();
			
		} else if (name.equals("newRide")) {
			return new NewRide();
		
		} else if (name.equals("displayStation")) {
			return new DisplayStation();
		
		} else if (name.equals("displayUser")) {
			return new DisplayUser();
		
		} else if (name.equals("sortStation")) {
			return new SortStation();
		
		} else if (name.equals("display")) {
			return new Display();
			
		} else {
			return null;
		}
	}

}
