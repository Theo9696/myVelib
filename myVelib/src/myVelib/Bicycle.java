package myVelib;

public abstract class Bicycle {
	
	//Attributes 
	private static int numericalID;
	private int ID;
	
	public abstract String getType();
	
	
	/*
	 * Creator : it creates an unique ID for each bicycle
	 */
	public Bicycle() {
		this.ID = numericalID;
		numericalID++;
	}

	/* ************************************* Getters / Setters ************************************************ */
	public int getID() {
		return ID;
	}

	
	/*
	 * Show main characteristics of bicycle
	 */
	public String toString() {
		return "[bicycle n° " + this.getID() + " | type : " +this.getType() + "]";
	}
}

