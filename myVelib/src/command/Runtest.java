package command;

import java.util.Map;

import gameground.Simulation;

public class Runtest implements Command {
	
	String name;
	
	Runtest(){
		this.name = "runtest";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
		if (mots.length == 2) {
			System.out.println("The file " + mots[1] + " is tried to be opened !");
		} else {
			System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		}
		
		
	}

}
