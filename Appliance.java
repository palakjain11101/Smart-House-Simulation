//The Appliance class defines the basic properties and methods that all the different appliance classes will use.
public abstract class Appliance{

	//Defines the name of the particular appliance
	String name;

	//The meter instance is used to record the appliance's consumption and production
	Meter meter;

	//Represents the current hour in the 24 hour period
	int currentHour;

	public Appliance(String applianceName){
		this.name = applianceName;	
	}

	//Setter method defined to set meter 
	public void setMeter(Meter newMeterValue){
		this.meter = newMeterValue;
	}

	//Getter method to get the name of the appliance
	public String getName(){
		return name;
	}

	/* Denotes one increment of time passing (1 increment of time = 1 hour); 
	it is defined in each of the classes that inherit from appliance */
	public abstract void timePasses();

	//Calls consumeUnits() on the Meter associated with this appliance
	protected void tellMeterToConsumeUnits(float totalUnits){
		meter.consumeUnits(totalUnits);
	}
}