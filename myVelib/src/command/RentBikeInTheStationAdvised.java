package command;

import java.util.Map;

import exception.StationEmptyException;
import exception.StationOfflineException;
import gameground.Simulation;

public class RentBikeInTheStationAdvised implements Command {
	
	String name;
	
	public RentBikeInTheStationAdvised() {
		this.name = "rentBikeInTheStationAdvised";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		if(mots.length == 5)
		{
			try {
			// We had an information of the simulation we placed us
			// rentBike <userID, velibnetworkName, time, BicycleType>
				int userID = Integer.parseInt(mots[1]);
				String velibnetworkName = mots[2];
				Double timeBicycleTaken = Double.parseDouble(mots[3]);
				String BicycleType = mots[4];
				System.out.println("\nThe user " + userID + " try to take a bicycle in the station advised");
				try {
					simulations.get(velibnetworkName).takeABicycleInTheStationSource(userID, BicycleType, timeBicycleTaken);
				} catch (StationOfflineException | StationEmptyException e) {
					System.out.println(e);
				}
			

			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("rentBikeInTheStationAdvised <userID, velibnetworkName, timeBicycleTaken, BicycleType>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("rentBikeInTheStationAdvised <userID, velibnetworkName, timeBicycleTaken,BicycleType>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("rentBikeInTheStationAdvised <userID, velibnetworkName, timeBicycleTaken, BicycleType>");
		}
		
	}

}
