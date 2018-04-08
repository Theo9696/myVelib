package command;

import java.util.Map;

import exception.AskPlanningRideImpossibleException;
import gameground.Simulation;

public class NewRide implements Command {

	String name;
	
	NewRide(){
		this.name = "newRide";
	}

	@Override
	public void test(String[] mots, Map<String, Simulation> simulations, String[] lignes, int ligne) {
		
			if(mots.length == 7)
			{
				// We had an information of the simulation we placed us
    			// newRide <userID, latitude, longitude, bicycleType, RidePref, velibnetworkName>
				try {
	    			int userID = Integer.parseInt(mots[1]);
	    			double latitude = Double.parseDouble(mots[2]);
	    			double longitude = Double.parseDouble(mots[3]);
	    			String bicycleType = mots[4];
	    			String pref = mots[5];
	    			String velibnetworkName = mots[6];
	    			try {
						simulations.get(velibnetworkName).newRide(userID, latitude, longitude, bicycleType, pref);
					} catch (AskPlanningRideImpossibleException e) {
						System.out.println(e);
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
					System.out.println("newRide <userID, latitudeArrival, longitudeArrival, bicycleType, computingPreference, velibnetworkName> with computingPreference : fastest, shortest, preferplus, avoidplus, uniformity");
				}
				catch (NullPointerException e) {
					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
					System.out.println("newRide <userID, latitudeArrival, longitudeArrival, bicycleType, computingPreference, velibnetworkName> with computingPreference : fastest, shortest, preferplus, avoidplus, uniformity");
				}
			}
			else
			{
				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
				System.out.println("newRide <userID, latitudeArrival, longitudeArrival, bicycleType, computingPreference, velibnetworkName> with computingPreference : fastest, shortest, preferplus, avoidplus, uniformity");
			}
		
	}
}
