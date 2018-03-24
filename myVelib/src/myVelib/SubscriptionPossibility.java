package myVelib;

public interface SubscriptionPossibility {
	
	public abstract void getCredit(float time, User user,TypeStation typeStation);
	
	public abstract int cost(double time, Bicycle bicycle, User user, TypeStation typeStation);

}
