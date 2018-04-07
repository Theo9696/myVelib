package command;

import java.util.Map;

import Gameground.Simulation;

public interface Command {
	
	
	public abstract void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne);

}
