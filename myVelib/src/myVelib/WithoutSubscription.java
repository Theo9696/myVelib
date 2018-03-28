package myVelib;

public class WithoutSubscription implements SubscriptionPossibility {

	@Override
	public void getCredit(float time, User user, TypeStation typeStation) {
		System.out.println("You can't obtain credits since you are not a suscriber");
		
	}
	/*
	 * Cost of the ride wrt the @time spend, @bicycle uses with no subscription
	 */
	@Override
	public int cost(double time, Bicycle bicycle, User user, TypeStation typeStation) {
		if (bicycle instanceof ElectricalBicycle) {
			return (int) (2*Math.ceil(time));
		} else {
			return (int) (Math.ceil(time));
		
		}
	}
	
	public String toString() {
		return "No subscription";
	}
	

}
