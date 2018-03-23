package myVelib;

public class Vlibre implements SubscriptionPossibility {

	@Override
	public void getCredit(float time, User user) {
		user.setTimecreditbalance(user.getTimecreditbalance()+time);
		System.out.println("You obtain " + time + " hour of credit");
		
		
		
	}

	@Override
	public int cost(float time, Bicycle bicycle, User user) {
		// TODO Auto-generated method stub
		float surplus = (time - (int)(Math.floor(time)));
		float diff = (surplus - user.getTimecreditbalance());
		int timeToPay = 0;
		
		if (diff <= 0 ) {
			getCredit(-surplus, user);
			timeToPay = (int)(Math.floor(time));
		
		} else if (surplus == 0 ) {
			timeToPay = (int)(Math.floor(time));
		}else {
			timeToPay = (int)(Math.ceil(time));
			getCredit(1-surplus, user);
		}
			
		if (timeToPay == 0)
			return 0;
	
		else if (bicycle instanceof ElectricalBicycle) {
			return (int) (2*Math.max((timeToPay-1),0) + 1);
		} else {
			return (int) (Math.max((timeToPay-1),0));
				
		}
	}
	public String toString() {
		return "card Vlibre";
	}
}
