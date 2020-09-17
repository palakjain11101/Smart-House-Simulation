import java.text.DecimalFormat;

// The Battery Class has the ability to consume and store units 
public class Battery{

	//Maximum units the battery can hold
	float capacityLimit;

	//Charge stored in the battery at a particular time
	float batteryCharge;

	//Reading on the meter; equivalant to the units used from the Mains
	float meterReading;

	//Units taken from the battery
	float batteryUnitsUsed;

	//Production that would be wasted if not sold
	float excessProduction;

	DecimalFormat numberFormat = new DecimalFormat("0.000");

	//Battery constructor initialises properties of the battery
	public Battery(float batteryCharge, float capacityLimit, float meterReading, float batteryUnitsUsed){
		this.batteryCharge = batteryCharge;
		this.capacityLimit = capacityLimit;
		this.meterReading = meterReading;
		this.batteryUnitsUsed = batteryUnitsUsed;
	}

	//Called by the report() method in BatteryMeter, when consumption exceeds production
	public float consumesUnits(float meterReading){

		//if there is no batteryCharge, no units can be used from the battery
		if(batteryCharge ==0){
			batteryUnitsUsed = 0;
			System.out.println("Units used from the battery: " + numberFormat.format(batteryUnitsUsed));
			System.out.println("Units remaining in the battery: " + numberFormat.format(batteryCharge));
		}

		//if there is batteryCharge, the appliance first consumes units from the battery
		else if(batteryCharge > 0){

			//if there is more charge in the battery than the reading then all of the units are taken from the battery 
			if(batteryCharge >= meterReading){
					batteryUnitsUsed = meterReading;
					batteryCharge = batteryCharge - batteryUnitsUsed;
					System.out.println("Units used from the battery: " + numberFormat.format(batteryUnitsUsed));
					System.out.println("Units remaining in the battery: " + numberFormat.format(batteryCharge));
					meterReading = 0;
			}

			//otherwise, the appliance must take the extra remaining from the Mains
			else{
				float remainingUnits = meterReading - batteryCharge;
				float batteryUnitsUsed = meterReading - remainingUnits;
				System.out.println("Units used from the battery: " + numberFormat.format(batteryUnitsUsed));
				batteryCharge = 0;
				System.out.println("Units remaining in the battery: 0 ");
				meterReading = remainingUnits;
			}
		}

	return meterReading;
	}

	//Called by the report() method in BatteryMeter, when production exceeds consumption
	public float storesUnits(float numberOfUnits){
		
		batteryCharge = batteryCharge + numberOfUnits;

		//Any production that cannot be stored is lost 
		if(batteryCharge >= capacityLimit){
			excessProduction = batteryCharge - capacityLimit;
			batteryCharge = capacityLimit;
			System.out.println("Units remaining in the battery: " + numberFormat.format(batteryCharge));
		}

		//Production at the specific hour is added to the batteryCharge until it exceeds capacityLimit
		else if(batteryCharge < capacityLimit){
			System.out.println("Units remaining in the battery: " + numberFormat.format(batteryCharge));
			excessProduction = 0;
		}

		meterReading = 0;
		return meterReading;
	}

	//Returns capacity of the battery
	public float getCapacityLimit(){
		return capacityLimit;
	}

	//Returns the charge stored in the battery
	public float getStoredUnits(){
		return batteryCharge;
	}

	
}