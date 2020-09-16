//The Meter Class represents an object that manages the consumption and production of a particular utility.
import java.text.DecimalFormat;

public class Meter{

	DecimalFormat numberFormat = new DecimalFormat("0.000");

	//Describes the type of utility
	private String utilityName; 

	//The cost of one unit of this type of utility
	protected double unitCost;
	
	//Represents the balance of units used since the last meter reading
	public float meterReading;
	
	//Cost associated with the meter reading
	public double meterReadingCost;


	//Constructor 'Meter' initialises these three values 
	public Meter(String utilityName, double unitCost, float meterReading){	
		this.utilityName = utilityName;
		this.unitCost = unitCost;
		this.meterReading = meterReading;
	}


	//Adjusts meterReading, so that numberOfUnits is added to it every time consumeUnits() is called
	public void consumeUnits(float numberOfUnits){
		meterReading += numberOfUnits;
		System.out.println("Meter reading: " + numberFormat.format(meterReading));
	}

	//Returns cost of the particular utility
	public double getUnitCost(){
		return unitCost;
	}

	//Reports the utilityName, meterReading, and the meterReadingCost; calling this method resets the meterReading to zero  
	public double report() {
		meterReadingCost = 0.0;

		//if meterReading is greater than 0, overall, units are consumed, therefore the cost reported will be the cost per unit multiplied by the meterReading
		if(meterReading >= 0){
			//Casting float meterReading to double
			meterReadingCost = (double)meterReading * unitCost;
			System.out.println("**************************************************");
			System.out.println("The " + utilityName+ " meter has a meter reading of " + numberFormat.format(meterReading)+ " and a total cost of " + numberFormat.format(meterReadingCost) + ".");
			System.out.println("**************************************************");
			meterReading = 0;
			return meterReadingCost;
		}

		//if meterReading is less than 0, overall, units are produced rather than consumed, thus, the cost is automatically 0
		else{
				System.out.println("**************************************************");
				System.out.println("Your appliances have produced enough energy that the cost of this hour for the " + utilityName + " is zero!");
				System.out.println("**************************************************");
				return meterReadingCost;
		} 
	}
		
}