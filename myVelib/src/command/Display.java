package command;

import java.util.Map;

import gameground.Simulation;

public class Display implements Command {
	
	String name;
	
	Display(){
		this.name = "display";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if(mots.length == 2)
		{
			try {
				String velibnetworkName = mots[1];
				Simulation simu = simulations.get(velibnetworkName);
				simu.getNameOfNetwork(); // if no simulation exist with this name it will call the catch NullPointerException
				System.out.println(simu);
			}
			catch (NullPointerException e) {
				System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
				System.out.println("diplay <velibnetworkName>");
			}
			catch(NumberFormatException e)
			{
				System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
				System.out.println("diplay <velibnetworkName>");
			}
		}
		else
		{
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
			System.out.println("diplay <velibnetworkName>");
		}
		
	}
	

}
