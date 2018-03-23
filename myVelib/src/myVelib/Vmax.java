package myVelib;

public class Vmax implements SubscriptionPossibility {

	@Override
	public void getCredit(float time, User user) {
		user.setTimecreditbalance(user.getTimecreditbalance()+time);
		System.out.println("You obtain " + time + "hour of credit");
		
	}

	@Override
	public int cost(float time, Bicycle bicycle , User user) {
		
		float surplus = time - (int)(Math.floor(time));
		float diff = (surplus - user.getTimecreditbalance());
		int timeToPay = 0;
		
		if (diff <= 0 ) {
			getCredit(-surplus, user);
			timeToPay = (int)(Math.floor(time));
		
		} else if (surplus ==0 ) {
			timeToPay = (int)(Math.floor(time));
		} else {
			timeToPay = (int)(Math.ceil(time));
			getCredit(1-surplus, user);
		}
			
		return (int) (Math.max((timeToPay-1),0));
				
	}
	
	public String toString() {
		return "card Vmax";
	}

}
