package command;

import java.util.Map;

import gameground.Simulation;

public class AddUser implements Command {
	
	String name;
	
	AddUser(){
		this.name = "addUser";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 4)
		{
			try {
				String userName = mots[1];
				String cardType = mots[2];
				String velibnetworkName = mots[3];
				simulations.get(velibnetworkName).newUser(cardType, userName);
				System.out.println("The user "+ userName + " has been added to the simulation " + velibnetworkName+"\n");
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("addUser <userName, cardType, velibnetworkName> with cardType : Vmax, Vlibre or NoSubscription");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("addUser <userName, cardType, velibnetworkName> with cardType : Vmax, Vlibre or NoSubscription");
			}
		}
		else if (mots.length == 6) {
			try {
				String userName = mots[1];
				String cardType = mots[2];
				String velibnetworkName = mots[3];
				Double latitude = Double.parseDouble(mots[4]);
				Double longitude = Double.parseDouble(mots[5]);
				
				simulations.get(velibnetworkName).newUser(cardType, userName, latitude, longitude);
				System.out.println("The user "+ userName + " has been added to the simulation " + velibnetworkName+"\n");
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("addUser <userName, cardType, velibnetworkName> with cardType : Vmax, Vlibre or NoSubscription");
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("addUser <userName, cardType, velibnetworkName> with cardType : Vmax, Vlibre or NoSubscription");
			}
		}
		else 
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("addUser <userName, cardType, velibnetworkName> with cardType : Vmax, Vlibre or NoSubscription");
		}
		
	}

}
