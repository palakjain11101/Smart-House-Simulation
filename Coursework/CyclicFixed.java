//CyclicFixed inherits from Appliance; it is composed of appliances that are switched on for a set cycle and use fixed number of resources each hour
public class CyclicFixed extends Appliance{

	//Fixed units consumed by appliance per hour
	float unitsConsumed;

	//Fixed number of hours for which the appliance runs
	float activeHours;

	//Initialises current hour
	int currentHour = 1;

	//Constructor 'CyclicFixed' initialises properties of CyclicFixed appliances
	public CyclicFixed(String applianceName, float numberOfUnitsConsumed, float numberOfActiveHours){
		super(applianceName);	
		this.unitsConsumed = numberOfUnitsConsumed; 
		this.activeHours = numberOfActiveHours;
		boolean check = false;

		//the loop is only exited when check = true; the while loop only runs when the numberOfActive hours is not between 1 and 24.
		while(check == false){
			if (numberOfActiveHours >= 1 && numberOfActiveHours <= 24){
				check = true;
			}
		}
	}

	//timepasses() from Appliance is overridden; calculates how many units this appliance has used in the activeHours by calling tellMeterToConsumeUnits() each time
	public void timePasses(){
		try{
			if (currentHour >= 24){
				currentHour = 1;
			}
			else{
				currentHour ++;
			}
			if (currentHour <= activeHours)
			{
				this.tellMeterToConsumeUnits(unitsConsumed);
			}
		}
		catch(Exception e){
			System.out.println("Error! No meter has been set.");
		}
	}

}
