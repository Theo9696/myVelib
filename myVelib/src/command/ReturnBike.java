package command;

import java.util.Map;

import Exceptions.StationFullException;
import Exceptions.StationOfflineException;
import Gameground.Simulation;

public class ReturnBike implements Command {

	String name;
	
	ReturnBike(){
		this.name = "returnBike";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 5)
		{
			try {
			
				int userID = Integer.parseInt(mots[1]);
				int stationID = Integer.parseInt(mots[2]);
				String velibnetworkName = mots[3];
				int time = Integer.parseInt(mots[4]);
				try {
					simulations.get(velibnetworkName).returnABicycle(userID, stationID, time);
				} catch (StationOfflineException | StationFullException e) {
					System.out.println(e);
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("returnBike <userID, stationID, velibnetworkName, timeBicycleGivenBack>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("returnBike <userID, stationID, velibnetworkName, timeBicycleGivenBack>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("returnBike <userID, stationID, velibnetworkName, timeBicycleGivenBack>");
		}
		
	}
	
	
}
