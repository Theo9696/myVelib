package Gameground;

import java.io.*;
import java.util.*;

import Exceptions.AskPlanningRideImpossibleException;
import Exceptions.ParkingSlotFullException;
import Exceptions.StationEmptyException;
import Exceptions.StationFullException;
import Exceptions.StationOfflineException;
import myVelib.Station;

public class CLUI extends PrintStream {
	
	static OutputStream logfile;
	static PrintStream  oldStdout;
	static PrintStream  oldStderr;
	public static String LogFile = "";
	private static FileOutputStream fis;
 
	CLUI(PrintStream ps)
	{
		super(ps);
	}
	
	public static String readTextFile(String fileName) {
		  
		  String returnValue = "";
		  FileReader file = null;
		  BufferedReader reader = null;
		  
		  try {
			  // open input stream pointing at fileName
			  file = new FileReader(fileName);
			  
			  // open input buffered reader to read file line by line
			  reader = new BufferedReader(file);
			  String line = "";
			  
			  // reading input file line by line
			  while ((line = reader.readLine()) != null) {
				  returnValue += line + "\n";
			  }
		  } 
		  catch (FileNotFoundException e) {
				System.out.println("This file doesn't exist, you can't runtest it !");
		  }
		  catch (RuntimeException e) {
				System.out.println("This file doesn't exist, you can't runtest it !");
		  }
		  catch (Exception e) {
		      throw new RuntimeException(e);
		  } finally {
		    if (file != null) {
		      try {
		        file.close();
		        reader.close();
		       
		      } catch (IOException e) {
		    	  System.out.println("File not found: " + fileName);
		        // Ignore issues during closing 
		      }
		    }
		  }
		  return returnValue;
		} 
	
	 
	public static void main(String[] args) throws IOException, ParkingSlotFullException, StationOfflineException, StationEmptyException, StationFullException, AskPlanningRideImpossibleException {
		
		Map<String, Simulation> simulations = new HashMap<String, Simulation>();
		/*studyFile("testScenarioN.txt",simulations);*/
		
		
		
		Scanner scan = new Scanner(System.in);
		String[] lignes = new String[1];
		start("outputCLUI.txt");
		
		int compteur = 0;
		String stop = "false";
		
		while (compteur <1000 && !stop.equals("stop")) {
			System.out.println("\n ***Enter next command or stop to end****");
			lignes[0] = scan.nextLine();
			System.out.println(lignes[0]);
			
			if (lignes[0].equals("stop")){
				stop = "stop";
				stop();
			} 

			else if (lignes[0].length() == 7 && lignes[0].substring(0,7).contentEquals("runtest")) {
				
				String delims2 = (String) "[ ,,]";
				String[] mots = (String[]) ((java.lang.String) lignes[0]).split((java.lang.String) delims2);
				try {
					stop();
					studyFile(mots[1], simulations);
					start("outputCLUI.txt");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("The number of arguments for the command runtest must be one");
				}
				
			}
			
			else {
				simulations = testEntry(lignes, simulations);
				compteur++;
			}
		}
		scan.close();
	   
	}
	
	public static void studyFile(String nameOfTheInFile, Map<String, Simulation> simulations) throws IOException, StationOfflineException, ParkingSlotFullException, StationEmptyException, StationFullException, AskPlanningRideImpossibleException {
		
		try {
			String [] lignes = lectureFile(nameOfTheInFile);
			start(nameOfTheInFile.substring(0, nameOfTheInFile.length()-4)+"Output.txt");
			simulations = testEntry(lignes, simulations);
			stop();
		}
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("Your file " + nameOfTheInFile +" must end by .txt");
		}
		
		
	}
	
	
	// Starts copying stdout and 
		//stderr to the file f.
		public static void start(String f_OUT) throws IOException
		{
			LogFile = f_OUT;
//			 Save old settings.
			oldStdout = System.out;
			oldStderr = System.err;
			
			fis = new FileOutputStream(f_OUT);
			// Create/Open logfile.
			logfile = new PrintStream(fis);
	 
			// Start redirecting the output.
			System.setErr(new CLUI(System.err));
			System.setOut(new CLUI(System.out));
		}
		
		// Starts copying stdout and 
				//stderr to the file f.
	 
		// Restores the original settings.
		
		public static void stop()
		{
			System.setErr(oldStderr);
			System.setOut(oldStdout);
	 
			try
			{
	                                 
				logfile.flush();
				logfile.close();
				fis.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	 
//		 PrintStream override.
		public void write(int b)
		{
			try
			{
				logfile.write(b);
	 
			}
			catch (Exception e)
			{
				e.printStackTrace();
				setError();
			}
	 
			super.write(b);
		}
	 
		// PrintStream override.
		public void write(byte [] buf, int off, int len)
		{
			try
			{
				logfile.write(buf, off, len);
	 
			}
			catch (Exception e)
			{
				e.printStackTrace();
				setError();
			}
	 
			super.write(buf, off, len);
		}
		
		public static String[] lectureFile(String nameOfFile) throws IOException {
			// the string where the lines of the line are going to be read
			String fileContent;
			
			// storing the content of "words.txt" into string "fileContent"
			fileContent= CLUI.readTextFile(nameOfFile);
			
			// defining the words delimiters for splitting fileContent into words
			String delims1 = "\n";
			
			// splitting fileContent into words
			String[] lignes = fileContent.split(delims1);
			//writer.write("\n\n" +"Le nombre de lignes est " + lignes.length);
			//Peut être faire des boucles avec des lignes[i] pour pouvoir afficher des messages du type "Problème à la ligne i"
			
			return lignes;
			
		    

		}
		
		public static Map<String, Simulation> testEntry(String[] lignes, Map<String, Simulation> simulations ) throws StationOfflineException, ParkingSlotFullException, IOException, StationEmptyException, StationFullException, AskPlanningRideImpossibleException {
			String delims2 = (String) "[ ,,]";
			
			for(int ligne = 0; ligne < lignes.length;ligne++)
		    {
				try {
					String[] mots = (String[]) ((java.lang.String) lignes[ligne]).split((java.lang.String) delims2);
					//writer.write("\n\n" +"Le nombre de mots est " + mots.length);
					String commande = mots[0];
					if(commande.equals("setup"))
					{
						if(mots.length == 2)
						{
							String velibnetworkName = mots[1];
							simulations.put(velibnetworkName, new Simulation(10, 10, 0, 4, 75, velibnetworkName));
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
			    				simulations.put(name, new Simulation(nstations, nslots, 0, sidearea, nbikes, name));
			    				System.out.println("\n setup done ! \n");
			    				}
			    				catch(NumberFormatException e)
			    				{
			    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
			    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"\nPas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("addUser"))
		    		{
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
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else 
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("offline"))
		    		{
		    			if(mots.length == 3)
		    				{
		    				try {
		    				String velibnetworkName = mots[1];
		    				int stationID = Integer.parseInt(mots[2]);
		    				simulations.get(velibnetworkName).getStations().get(stationID).becomeOffline();
		    				System.out.println("\n The station " +  stationID + " of the simulation " + velibnetworkName + " has become offline!");
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    				}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("online"))
		    		{
		    			if(mots.length == 3)
		    				{
		    				try {
		    				String velibnetworkName = mots[1];
		    				int stationID = Integer.parseInt(mots[2]);
		    				simulations.get(velibnetworkName).getStations().get(stationID).becomeOnline();
		    				System.out.println("\n The station " +  stationID + " of the simulation " + velibnetworkName + " has become online!");
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    				}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("rentBike"))
		    		{
		    			if(mots.length == 5)
		    			{
		    				try {
		    				// We had an information of the simulation we placed us
		    				// rentBike <userID, stationID, velibnetworkName, time>
			    			int userID = Integer.parseInt(mots[1]);
			    			int stationID = Integer.parseInt(mots[2]);
			    			String velibnetworkName = mots[3];
			    			Double timeBicycleTaken = Double.parseDouble(mots[4]);
			    			System.out.println("\nThe user " + userID + " try to take a bicycle in the station " + stationID);
			    			simulations.get(velibnetworkName).takeABicycle(userID, stationID, "Mechanical", timeBicycleTaken);
			    			
	
			    			}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("rentBikeInTheStationAdvised"))
		    		{
		    			if(mots.length == 4)
		    			{
		    				try {
		    				// We had an information of the simulation we placed us
		    				// rentBike <userID, stationID, velibnetworkName, time>
			    			int userID = Integer.parseInt(mots[1]);
			    			String velibnetworkName = mots[2];
			    			Double timeBicycleTaken = Double.parseDouble(mots[3]);
			    			System.out.println("\nThe user " + userID + " try to take a bicycle in the station advised");
			    			simulations.get(velibnetworkName).takeABicycleInTheStationSource(userID, "Mechanical", timeBicycleTaken);
			    			
	
			    			}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("returnBike"))
		    		{
		    			if(mots.length == 5)
		    			{
		    				try {
		    				
			    			int userID = Integer.parseInt(mots[1]);
			    			int stationID = Integer.parseInt(mots[2]);
			    			String velibnetworkName = mots[3];
			    			int time = Integer.parseInt(mots[4]);
			    			simulations.get(velibnetworkName).returnABicycle(userID, stationID, time);
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("newRide"))
		    		{
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
			    			simulations.get(velibnetworkName).newRide(userID, latitude, longitude, bicycleType, pref);
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("returnBikeInTheStationAdvised"))
		    		{
		    			if(mots.length == 4)
		    			{
		    				try {
			    			int userID = Integer.parseInt(mots[1]);
			    			String velibnetworkName = mots[2];
			    			int time = Integer.parseInt(mots[3]);
			    			simulations.get(velibnetworkName).returnABicycleInTheStationDestination(userID, time);
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("displayStation"))
		    		{
		    			if(mots.length == 3)
		    			{
		    				try {
		    				String velibnetworkName = mots[1];
		    				int stationID = Integer.parseInt(mots[2]);
		    				String str = simulations.get(velibnetworkName).getStations().get(stationID).returnStationStatistics();
		    				System.out.println(str);
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("displayUser"))
		    		{
		    			if(mots.length == 3)
		    			{
		    				try {
		    				String velibnetworkName = mots[1];
		    				int userID = Integer.parseInt(mots[2]);
		    				String str = simulations.get(velibnetworkName).getUsers().get(userID).returnUserStatistics();
		    				System.out.println(str);
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else if(commande.equals("sortStation"))
		    		{
		    			if(mots.length == 3)
		    			{
		    				try {
		    					String velibnetworkName = mots[1];
		    					String sortpolicy = mots[2];
		    					if (sortpolicy.contentEquals( "mostusedStations")) {
		    						ArrayList<Station> stations = simulations.get(velibnetworkName).getMostUsedStations();
		    						System.out.println(stations);
		    					} else if (sortpolicy.contentEquals( "leastoccupiedStations")) {
		    						ArrayList<Station> stations = simulations.get(velibnetworkName).getLeastOccupiedStations(0, Double.POSITIVE_INFINITY);
		    						System.out.println(stations);
		    					} else {
		    						System.out.println("We do not know this sorting policy :" + sortpolicy);
		    					}
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    					
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments pour la commande sortStation");
		    			}
		    		}
		    		else if(commande.equals("display"))
		    		{
		    			if(mots.length == 2)
		    			{
		    				try {
		    					String velibnetworkName = mots[1];
		    					Simulation simu = simulations.get(velibnetworkName);
		    					System.out.println(simu);
		    				}
		    				catch (NullPointerException e) {
		    					System.out.println("\n One of the arguments of the command \"" + lignes[ligne] + "\" you entered is not correct, has not been defined in the simulation, or you have not even entered a name of an existing simulation");
		    				}
		    				catch(NumberFormatException e)
		    				{
		    					System.out.println("\n\n" +"Vous avez rentré un format non adapté à la ligne " + ligne + " dans les arguments de la commande \"" + lignes[ligne] + "\"");
		    				}
		    			}
		    			else
		    			{
		    				System.out.println("\n\n" +"Pas le bon nombre d'arguments avec la commande \"" + lignes[ligne] + "\" à la ligne " + ligne);
		    			}
		    		}
		    		else
		    		{
		    			System.out.println("\n\n" +"La commande \"" + commande + "\" de \"" + lignes[ligne] + "\" à la ligne " + ligne + " n'est pas reconnue");
		    		}
		    		
		    		//Il faudrait vérifier :
		    			//-Qu'il n'y a pas de null qui sont rentrés qui pourrait faire bugger la suite (sauf si la suite y est résistante)
		    			//-Que les types de données rentrées correspondent à l'argument (par exemple on ne peut pas rentrer "vélo" pour un userID qui est un nombre
		    			//-Que les ints suffisent pour des userID etc
		    			//-Que les casts de String à nos classes crées sont possibles
		    			//
				}
				catch (NullPointerException e) {
					System.out.println("You entered a null command !");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("You entered a invalid command, probably a null argument !");
				}
				
		    }
			return simulations;
		}
	
	


}
