import java.util.Random;

//RandomVaries inherits from Appliance; it is composed of appliances that are switched on at random times during the day and which consume variable amount of resources per hour, when switched on
public class RandomVaries extends Appliance{

	int currentHour;

	//Probability of appliance being switched on; an integer is passed, and the appliance can turn on in any hour between 0 and the integer
	int probability;

	//Minimum units consumed by the appliance
	float minUnits;

	//Maximum units consumed by the appliance
	float maxUnits;

	//Constructor 'RandomVaries' initialises properties of RandomVaries appliances
	public RandomVaries(String applianceName, float minUnits, float maxUnits, int probability){
		super(applianceName);	
		this.minUnits = minUnits; //units consumed per hour
		this.maxUnits = maxUnits;
		this.probability = probability;
		Boolean check;
	}

	//Random number generator; generates a number between minimum and maximum units using Math.random()
	public static float random_float(float Min, float Max){
     	return (float) (Math.random()*(Max-Min))+Min;
	}
	
	//Calculates how many units this appliance has used in this time period
	public void timePasses(){
		try{
			int n;
			//This makes the appliance turn on, according to its probability, in a random manner
			Random rand = new Random();
			boolean val = rand.nextInt(probability)==0;

				if(val == true){
					if (currentHour >= 24){
						currentHour = 1;
					}
					else{
						currentHour ++;
					}
					float unitsConsumed = random_float(minUnits, maxUnits);
					this.tellMeterToConsumeUnits(unitsConsumed);
				}
		}
		catch(Exception e){
			System.out.println("Error! No meter has been set.");
		}
	}

}