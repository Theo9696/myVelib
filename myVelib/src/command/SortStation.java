package command;

//import java.util.ArrayList;
import java.util.Map;

import gameground.Simulation;
//import myVelib.Station;

public class SortStation implements Command {
	
	String name;
	
	SortStation(){
		this.name = "sortStation";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 3)
		{
			try {
				String velibnetworkName = mots[1];
				String sortpolicy = mots[2];
				if (sortpolicy.contentEquals( "mostusedStations")) {
					simulations.get(velibnetworkName).getMostUsedStations();
				} else if (sortpolicy.contentEquals( "leastoccupiedStations")) {
					// By default we look for the first 100 hours
					System.out.println("By default here is the list of station sorted by average time of occupation : by default between (0->100)");
					simulations.get(velibnetworkName).getLeastOccupiedStations(0, 100);
				} else {
					System.out.println("We do not know this sorting policy :" + sortpolicy);
				}
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("sortStation <velibnetworkName, sortpolicy> with sortpolicy : mostusedStations or leastoccupiedStations");
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("sortStation <velibnetworkName, sortpolicy> with sortpolicy : mostusedStations or leastoccupiedStations");
			}
		} else if (mots.length == 5) {
			try {
				String velibnetworkName = mots[1];
				String sortpolicy = mots[2];
				Double timeStart = Double.parseDouble(mots[3]);
				Double timeEnd = Double.parseDouble(mots[4]);
				if (sortpolicy.contentEquals( "leastoccupiedStations")) {
					simulations.get(velibnetworkName).getLeastOccupiedStations(timeStart, timeEnd);
				} else {
					System.out.println("We do not know this sorting policy :" + sortpolicy);
				}
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("sortStation <velibnetworkName, sortpolicy, timeStart, timeEnd> with sortpolicy : leastoccupiedStations");
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("sortStation <velibnetworkName, sortpolicy,timeStart, timeEnd> with sortpolicy : leastoccupiedStations");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments pour la commande sortStation");
			System.out.println("sortStation <velibnetworkName, sortpolicy> with sortpolicy : mostusedStations or leastoccupiedStations" +
			" | or | "+ "sortStation <velibnetworkName, sortpolicy,timeStart, timeEnd> with sortpolicy : leastoccupiedStations");
		}
		
	}

}
