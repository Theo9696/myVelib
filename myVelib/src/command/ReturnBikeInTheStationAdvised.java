package command;

import java.util.Map;

import Exceptions.StationFullException;
import Exceptions.StationOfflineException;
import Gameground.Simulation;

public class ReturnBikeInTheStationAdvised implements Command {
	
	String name;
	
	public ReturnBikeInTheStationAdvised() {
		this.name = "returnBikeInTheStationAdvised";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 4)
		{
			try {
				int userID = Integer.parseInt(mots[1]);
				String velibnetworkName = mots[2];
				int time = Integer.parseInt(mots[3]);
				try {
					simulations.get(velibnetworkName).returnABicycleInTheStationDestination(userID, time);
				} catch (StationOfflineException | StationFullException e) {
					System.out.println(e);
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("returnBikeInTheStationAdvised <userID, velibnetworkName, timeBicycleGivenBack>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("returnBikeInTheStationAdvised <userID, velibnetworkName, timeBicycleGivenBack>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("returnBikeInTheStationAdvised <userID, velibnetworkName, timeBicycleGivenBack>");
		}
		
	}
	

}
