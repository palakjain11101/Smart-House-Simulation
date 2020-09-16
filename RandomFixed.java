import java.util.Random;

//RandomFixed inherits from Appliance; it is composed of appliances that are switched on at random times during the day but consume a fixed amount of resources when switched on
public class RandomFixed extends Appliance{
	
	//Fixed units consumed by appliance per hour
	float unitsConsumed;

	int currentHour;

	//Probability of appliance being switched on; an integer is passed, and the appliance can turn on in any hour between 0 and the integer
	int probability;

	//Constructor 'RandomFixed' initialises properties of RandomFixed appliances
	public RandomFixed(String applianceName, float numberOfUnitsConsumed, int probability){
		super(applianceName);	
		this.unitsConsumed = numberOfUnitsConsumed; 
		this.probability = probability;
		Boolean check;

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
				this.tellMeterToConsumeUnits(unitsConsumed);
			}
		}
		catch(Exception e){
			System.out.println("Error! No meter has been set.");
		}
	}
	
}