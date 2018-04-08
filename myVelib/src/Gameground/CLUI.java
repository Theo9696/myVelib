package gameground;

import java.io.*;
import java.util.*;

import command.*;
//import myVelib.Station;
import exception.AskPlanningRideImpossibleException;
import exception.ParkingSlotFullException;
import exception.StationEmptyException;
import exception.StationFullException;
import exception.StationOfflineException;

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

			else if (lignes[0].length() >= 7 && lignes[0].substring(0,7).contentEquals("runtest")) {
				
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
					
					Command command = new ConstructorCommand().create(commande);
					
					if (command == null) {
						System.out.println("\n\n" +"Command \"" + commande + "\" of \"" + lignes[ligne] + "\" at the lign " + ligne + " is not recognized");
					} else {
					
						command.test(mots, simulations, lignes, ligne);
				
					}
		    		
				}
				catch (NullPointerException e) {
					System.out.println("You entered a null or not recognized command !");
				}
				catch (IndexOutOfBoundsException e) {
					System.out.println("You entered a invalid command, probably a null argument !");
				}
				
		    }
			return simulations;
		}
	
	


}
