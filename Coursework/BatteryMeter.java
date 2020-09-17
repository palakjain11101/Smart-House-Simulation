import java.lang.*;
import java.util.Random;
import java.text.DecimalFormat;

//BatteryMeter inherits properties from Meter class; it is connected to the Battery through the instance battery
public class BatteryMeter extends Meter{

	//Instance of a Battery declared
	Battery battery;

	//Units the battery stores when meterReading is negative in value
	float storedUnits;
	
	DecimalFormat numberFormat = new DecimalFormat("0.000");

	//BatteryMeter constructor initialises properties of the battery; 'super' keyword is used to inherit the three properties from Meter class
	public BatteryMeter(String utilityName, double unitCost, float meterReading, Battery battery){
		super(utilityName, unitCost, meterReading);
		// The batteryCharge, meterReading and batteryUnitsUsed are all set to 0, and capacityLimit is set to an arbitary number '50'
		this.battery = new Battery(0, 50, 0, 0);
	} 

	//Records and reports the amount of power drawn from the battery and from the main, by calling consumesUnits() in the Battery class
	public double report(){

		//if meterReading >= 0, consumption exceeds production and units are taken either from the battery, the mains or both
		if(meterReading >= 0){
			meterReadingCost = 0;
			meterReading = battery.consumesUnits(meterReading);
			System.out.println("Amount drawn from Mains: " + numberFormat.format(meterReading));
			meterReadingCost = meterReading * unitCost;
			System.out.println("**************************************************");
			System.out.println("The batteryMeter has a meter reading of " + numberFormat.format(meterReading) + " and a total cost of " + numberFormat.format(meterReadingCost) + ".");
			System.out.println("**************************************************");
			meterReading = 0;
			return meterReadingCost;
		}

		//if meterReading <0, production exceeds consumption and the excess units get stored in the battery
		else{
			storedUnits = (-1)*meterReading;
			meterReading = battery.storesUnits(storedUnits);
			System.out.println("Amount drawn from Mains: " + numberFormat.format(meterReading));
			meterReadingCost = 0;
			System.out.println("**************************************************");
			System.out.println("Your appliances have produced enough energy that the cost of this hour for the batteryMeter is zero!");
			System.out.println("**************************************************");
			return meterReadingCost;
		} 

	}
}

