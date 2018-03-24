package myVelib;

public class PlusStation implements TypeStation {
	
	public String getType() {
		return "Plus";
	}
	
	public float getExtraCredit() {
		return (float)1/12;
	}
}
