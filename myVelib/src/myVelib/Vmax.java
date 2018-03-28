package myVelib;

public class Vmax implements SubscriptionPossibility {

	@Override
	public void getCredit(float time, User user, TypeStation typeStation) {
		// Credit of the ride and eventually bonus credits for having returning the bicycle in a plus station
		user.setTimecreditbalance(user.getTimecreditbalance()+time + typeStation.getExtraCredit());
		System.out.println("You obtain " + time + "hour of credit");
		
	}

	@Override
	/*
	 * Cost of the ride wrt the @time spend, @bicycle uses with a Vmax subscription
	 */
	public int cost(double time, Bicycle bicycle , User user, TypeStation typeStation) {
		
		float surplus = (float) (time - (int)(Math.floor(time)));
		float diff = (surplus - user.getTimecreditbalance());
		int timeToPay = 0;
		
		if (diff <= 0 ) {
			getCredit(-surplus, user, typeStation);
			timeToPay = (int)(Math.floor(time));
		
		} else if (surplus ==0 ) {
			timeToPay = (int)(Math.floor(time));
		} else {
			timeToPay = (int)(Math.ceil(time));
			getCredit(1-surplus, user, typeStation);
		}
			
		return (int) (Math.max((timeToPay-1),0));
				
	}
	
	public String toString() {
		return "card Vmax";
	}

}
