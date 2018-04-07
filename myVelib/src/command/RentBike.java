package command;

import java.util.Map;

import Exceptions.StationEmptyException;
import Exceptions.StationOfflineException;
import Gameground.Simulation;

public class RentBike implements Command {
	
	String name;
	
	RentBike(){
		this.name = "rentBike";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		if(mots.length == 6)
		{
			try {
			// We had an information of the simulation we placed us
			// rentBike <userID, stationID, velibnetworkName, time, bicycleType>
				int userID = Integer.parseInt(mots[1]);
				int stationID = Integer.parseInt(mots[2]);
				String velibnetworkName = mots[3];
				Double timeBicycleTaken = Double.parseDouble(mots[4]);
				String bicycleType = mots[5];
				System.out.println("\nThe user " + userID + " try to take a bicycle in the station " + stationID);
				
				try {
					simulations.get(velibnetworkName).takeABicycle(userID, stationID, bicycleType, timeBicycleTaken);
				} catch (StationOfflineException | StationEmptyException e) {
					System.out.println(e);
				}
			

			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("rentBike <userID, stationID, velibnetworkName, timeBicycleTaken, bicycleType>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("rentBike <userID, stationID, velibnetworkName, timeBicycleTaken, bicycleType>");
			}
		}
		
		else if (mots.length == 5) {
			try {
				// We had an information of the simulation we placed us
				// rentBike <userID, stationID, velibnetworkName, time>
					int userID = Integer.parseInt(mots[1]);
					int stationID = Integer.parseInt(mots[2]);
					String velibnetworkName = mots[3];
					Double timeBicycleTaken = Double.parseDouble(mots[4]);
					System.out.println("\nThe user " + userID + " try to take a bicycle in the station " + stationID);
					
					try {
						simulations.get(velibnetworkName).takeABicycle(userID, stationID, "Mechanical", timeBicycleTaken);
					} catch (StationOfflineException | StationEmptyException e) {
						System.out.println(e);
					}
				

				}
				catch(NumberFormatException e)
				{
					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
					System.out.println("rentBike <userID, stationID, velibnetworkName, timeBicycleTaken>");
				}
				catch (NullPointerException e) {
					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
					System.out.println("rentBike <userID, stationID, velibnetworkName, timeBicycleTaken>");
				}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("rentBike <userID, stationID, velibnetworkName, timeBicycleTaken>");
		}
		
	}
	
	

}
