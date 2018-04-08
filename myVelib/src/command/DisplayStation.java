package command;


import java.util.Map;

import gameground.Simulation;
import myVelib.Station;

public class DisplayStation implements Command {
	
	String name;
	
	DisplayStation(){
		this.name = "displayStation";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 3)
		{
			try {
				String velibnetworkName = mots[1];
				int stationID = Integer.parseInt(mots[2]);
				Map<Integer, Station> stations = simulations.get(velibnetworkName).getStationsOrdered();
				
				try {
				Station station = stations.get(stationID);
				String str = station.returnStationStatistics();
				System.out.println(str);
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("the station " + stationID +" is not registered in the velibnetwork \""+ velibnetworkName +"\"" );
				}
				
				
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("displayStation <velibnetworkName, stationID>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("displayStation <velibnetworkName, stationID>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("displayStation <velibnetworkName, stationID>");
		}
		
	}
	

}
