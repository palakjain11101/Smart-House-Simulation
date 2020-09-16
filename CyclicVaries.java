//CyclicVaries inherits from Appliance; it is composed of appliances that are switched on for a set cycle but use a variable amount of resources each hour
public class CyclicVaries extends Appliance{

	//Fixed number of hours for which the appliance runs
	float activeHours;

	//Initialises current hour
	int currentHour = 1;

	//Minimum units consumed by the appliance
	float minUnits;

	//Maximum units consumed by the appliance
	float maxUnits;

	//Constructor 'CyclicVaries' initialises properties of CyclicVaries appliances
	public CyclicVaries(String applianceName, float numberOfActiveHours, float minUnits, float maxUnits){
		super(applianceName);
		this.activeHours = numberOfActiveHours;
		this.minUnits = minUnits;
		this.maxUnits = maxUnits;
		Boolean check;

		//The loop is only exited when check = true
		while(check = false){
			if (numberOfActiveHours >= 1 && numberOfActiveHours <= 24){
				check = true;
			}
		}

	}

	//Random number generator; generates a number between minimum and maximum units using Math.random()
	public static float random_float(float Min, float Max){
     	return (float) (Math.random()*(Max-Min))+Min;
	}

	//calculates how many units this appliance has used in this time period
	public void timePasses(){
		try{
			if (currentHour >= 24){
				currentHour = 1;
			}
			else{
				currentHour ++;
			}
			if (currentHour <= activeHours){
				float unitsConsumed = random_float(minUnits, maxUnits);
				this.tellMeterToConsumeUnits(unitsConsumed);
			}
		}

		catch(Exception e){
			System.out.println("Error! No meter has been set.");
		}
	}
		
}