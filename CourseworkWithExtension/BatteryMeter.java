import java.lang.*;
import java.util.Random;
import java.text.DecimalFormat;

//BatteryMeter inherits properties from Meter class; it is connected to the Battery through the instance battery
public class BatteryMeter extends Meter{

	//instance of a Battery declared
	Battery battery;

	//used batteryUnits
	//float consumedUnits;

	double randomTariff;

	DecimalFormat numberFormat = new DecimalFormat("#.000");

	//BatteryMeter constructor initialises properties of the battery; 'super' keyword is used to inherit the three properties from Meter class
	public BatteryMeter(String utilityName, double unitCost, float meterReading, Battery battery){
		super(utilityName, unitCost, meterReading);
		// The batteryCharge, meterReading and batteryUnitsUsed are all set to 0, and capacityLimit is set to an arbitary number '50'
		this.battery = new Battery(0, 50, 0, 0);
	} 

	//Records and reports the amount of power drawn from the battery and from the main, by calling consumesUnits() in the Battery class
	public double report(){
		unitCost = getUnitCost();
		System.out.println("**************************************************");
		System.out.println("Unitcost: " + numberFormat.format(unitCost));
		random_tariff();
		System.out.println("Tariff: " + numberFormat.format(randomTariff));
		System.out.println("**************************************************");

		if(meterReading >= 0){
			meterReadingCost = 0;
			float remainingUnits = battery.consumesUnits(meterReading);

			//In this case, it is more profitable to use electricity from Mains, selling those many units from the battery
			if(unitCost < randomTariff){
				float batteryUnitsSold;
				batteryUnitsSold = meterReading - remainingUnits;
				sellUnits(batteryUnitsSold);
			}
			
			//In this case, it is more profitable to use electricity from the battery, instead of the mains
			//Units are taken from the battery and the remaining units are taken from the Mains
			else{
				meterReading = remainingUnits; //(total reading = units left in the battery)
			}

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
			float excessProduction = battery.storesUnits(((-1)* meterReading));
			meterReading = 0;
			System.out.println("Amount drawn from Mains: " + numberFormat.format(meterReading));
			meterReadingCost = 0;
			System.out.println("**************************************************");
			System.out.println("Your appliances have produced enough energy that the cost of this hour for the batteryMeter is zero!");
			System.out.println("**************************************************");
			//whenever there is excess production, the units are sold
			if (excessProduction >= 0){
				sellExcessUnits(excessProduction);
			}
			
			return meterReadingCost;
		} 
	}

	//Generates random tariff between three possibilities
	public double random_tariff(){
		double[] tariffArray = {0.010, 0.013, 0.020};
		int idx = new Random().nextInt(tariffArray.length);
		String random = "" + (tariffArray[idx]);
		randomTariff = Double.parseDouble(random);
     		return randomTariff;
	}

	//Sells units when tariff is greater than unitcost
	public void sellUnits(float units){
		if(units >= 0){
			double moneyReceived = randomTariff * units;
			System.out.println("Money from selling units when tariff is greater than unitcost:" + numberFormat.format(moneyReceived));
			double moneySpent = unitCost * units;
			System.out.println("Overall profit: " + numberFormat.format((moneyReceived - moneySpent)));
		}
	}

	//Sells excess production that would be lost unused otherwise
	public void sellExcessUnits(float excessUnits){
			if(excessUnits >=0){
			double moneyReceived = randomTariff * excessUnits;
			System.out.println("Money from selling units over the capacity of the battery:" + numberFormat.format(moneyReceived));	
		}	
	}
}

