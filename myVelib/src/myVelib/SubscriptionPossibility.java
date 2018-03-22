package myVelib;

public interface SubscriptionPossibility {
	
	public abstract void getCredit(float time, User user);
	
	public abstract int cost(float time, Bicycle bicycle, User user);

}
