package command;


import java.util.Map;

import Gameground.Simulation;
import myVelib.User;

public class DisplayUser implements Command {
	
	String name;
	
	DisplayUser(){
		this.name = "displayUser";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		if(mots.length == 3)
		{
			try {
				String velibnetworkName = mots[1];
				int userID = Integer.parseInt(mots[2]);
				Map<Integer, User> users = simulations.get(velibnetworkName).getUsers();
				
				try {
					
					String str = users.get(userID).returnUserStatistics();
					
					System.out.println(str);
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("the user " + userID +" is not registered in the velibnetwork \""+ velibnetworkName +"\"" );
				}
				
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("displayUser <velibnetworkName, userID>");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("displayUser <velibnetworkName, userID>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("displayUser <velibnetworkName, userID>");
		}
		
	}
	
	

}
