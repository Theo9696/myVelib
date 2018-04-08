package command;


import java.util.Map;

import exception.ParkingSlotFullException;
import exception.StationOfflineException;
import gameground.Simulation;

public class Setup implements Command {
	
	String name;
	
	public Setup(){
		
		this.name = "setup";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 2)
		{
			String velibnetworkName = mots[1];
			try {
				simulations.put(velibnetworkName, new Simulation(10, 10, 0, 4, 75, velibnetworkName));
			} catch (StationOfflineException | ParkingSlotFullException e) {
				System.out.println(e);
			}
			System.out.println("\n setup done !");	  
		}
		else if(mots.length == 6)
		{
			try {
				String name = mots[1];
				int nstations = Integer.parseInt(mots[2]);
				int nslots = Integer.parseInt(mots[3]);
				int sidearea = Integer.parseInt(mots[4]);
				int nbikes = Integer.parseInt(mots[5]);
				try {
					simulations.put(name, new Simulation(nstations, nslots, 0, sidearea, nbikes, name));
				} catch (StationOfflineException | ParkingSlotFullException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("\n setup done ! \n");
				}
				catch(NumberFormatException e)
				{
					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
					System.out.println("setup <velibnetworkName> or setup <velibnetworkName, nbStations, nbParkingSlotByStation, sizeArea, nbBikes>");
				}
		}
		else
		{
			System.out.println("\n\n" +"\nPas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("setup <velibnetworkName> or setup <velibnetworkName, nbStations, nbParkingSlotByStation, sizeArea, nbBikes>");
		}
		
	}

}
