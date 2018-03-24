package myVelib;

public abstract class Bicycle {
	
	private static int numericalID;
	private int ID;
	
	public abstract String getType();
	
	public Bicycle() {
		this.ID = numericalID;
		numericalID++;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String toString() {
		return "I am the " + this.getID() + " bicycle and i am " +this.getType();
	}
}

