
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.lang.NullPointerException;

//The House class has the electric and water meter attached to it and has a number of appliances, each attached to the appropriate meter
public class House{
	Meter electric;
	Meter water;
	int numberOfHours;

	//Object allows all vales to be printed to 3 decimal places for neatness and simplicity
	DecimalFormat numberFormat = new DecimalFormat("#.000");


	public House(Meter electricMeter, Meter waterMeter){
		this.electric = electricMeter;
		this.water = waterMeter;
	}

	ArrayList <Appliance> listOfAppliances = new ArrayList <Appliance>(); 

	//Adds the specified appliance to the House, and sets its meter to be the House electric meter
	public void addElectricAppliance(Appliance appliance){
		try{
			appliance.setMeter(electric);
			listOfAppliances.add(appliance);
			System.out.println("Appliance " + appliance.getName() + " is added to the list of electric appliances");
		}
		catch(NullPointerException e){
			System.out.println("Null Pointer Exception! Appliance could be added and the meter could not be set.");
		}
	}

	//Adds the specified appliance to the House, and sets its meter to be the House water meter
	public void addWaterAppliance(Appliance appliance){
		try{
			appliance.setMeter(water);
			listOfAppliances.add(appliance);
			System.out.println("Appliance " + appliance.getName() + " is added to the list of water appliances");
		}
		catch(NullPointerException e){
			System.out.println("Null Pointer Exception! Appliance could not be added and the meter could not be set.");
		}
	}

	//Removes the specified appliance from the house
	public void removeAppliance(Appliance appliance){
		listOfAppliances.remove(appliance);
	}

	//Returns the number of appliances in the house
	public int numAppliances(){
		return listOfAppliances.size();
	}	

	//Simulates one unit of time passing in the house by calling timePasses() on each of the appliances
	public double activate(){
		for(Appliance appliance : listOfAppliances) {
			appliance.timePasses();
			System.out.println(appliance.name + " is added");	
		}
		double total = electric.report() + water.report();
		System.out.println("Total cost reported by both meters: " + numberFormat.format(total));
		return total;

	} 

	//This overloaded version of the activate() method takes number of hours as a parameter and simulates the number of hours
	public void activate(int numberOfHours){
		//Total cost accumulated over number of hours passed has been initialised to 0
		double totalForNHours = 0;
		for(int i=1; i<=numberOfHours; i++){

			for(Appliance appliance : listOfAppliances) {
				appliance.timePasses();
				System.out.println(appliance.name + " is added");	
			}

		double total = electric.report() + water.report();
		System.out.println("Total cost reported by both meters in Hour " + i + " : " + numberFormat.format(total) + "\n");
		//Total for numberOfHours is calaculated by adding the total each hour to the sum of costs in previous hours
		totalForNHours = totalForNHours + total;
		} 
		
		System.out.println("Total cost reported by both meters in " + numberOfHours + " hours : " + numberFormat.format(totalForNHours) + "\n");


	}

	public static void main(String[] args){
		//Part 1 test
		Meter meter = new Meter("electric", 6 , (float)0);
		meter.consumeUnits((float)(2.0));
		meter.consumeUnits((float)(6.5));
		meter.consumeUnits((float)(4.3));
		meter.consumeUnits((float)(3.0));
		meter.consumeUnits((float)(8.5));
		meter.report();

		CyclicFixed lights = new CyclicFixed("lights",6,5);
		lights.setMeter(meter);
		lights.timePasses();
		meter.report(); 

		//Part 2 test
		Meter waterMeter = new Meter("water", 0.002, 0);
		Meter elecMeter = new Meter("elec", 0.013, 0);
		House house1 = new House(elecMeter, waterMeter);
		CyclicFixed fridge = new CyclicFixed("fridge", 2 , 24);

		house1.addElectricAppliance(lights);
		//fridge has been added as a water appliance only to check the functionality of the waterMeter
		house1.addWaterAppliance(fridge);

		house1.activate();

		//Part 3 test
		CyclicVaries cooker = new CyclicVaries("cooker", 2, 4, 18);
		RandomFixed television = new RandomFixed("television", 2, 6);
		RandomVaries windTurbine = new RandomVaries("windTurbine", -3, -1, 2);

		house1.addElectricAppliance(cooker);
		house1.addElectricAppliance(television);
		house1.addElectricAppliance(windTurbine);
		house1.activate();
		house1.activate();
		house1.activate();
		
		//Part 4 test
		Battery battery = new Battery(0, 20, 0, 0);
		BatteryMeter batteryMeter = new BatteryMeter("electric", 0.013, 0, battery);
		House house2 = new House(batteryMeter, waterMeter);

		house2.addElectricAppliance(lights);
		house2.addWaterAppliance(fridge); 
		house2.addElectricAppliance(cooker);
		house2.addElectricAppliance(television);
		house2.addElectricAppliance(windTurbine);

		//Part 5 test
		try{
			Thread.sleep(500);
			house2.activate(5);
		}
	
		catch (InterruptedException e){
			System.out.println(e);
		}


		//Part 6 and 7 test
     		House house = new House(batteryMeter, waterMeter);	

     		String inputConfigFile;
     		int hours;

     		// if user provides no parameters, the input file remains "configfile.txt", and the simulation runs for 24*7 hours by default
     		if((args.length) == 0){
     			inputConfigFile = "configfile.txt";
    			hours = 24*7;
     		}

     		// if user provides one parameter, the input file is taken as the paramter, and the simulaton runs for default number of hours
     		else if(args.length == 1){
     			inputConfigFile = args[0];
     			hours = 24*7;
     		}

	     	//if the user provides two parameters, the first parameter will be recognised as the input file and the second parameter as the number of hours
     		else{
     			inputConfigFile = args[0];
     			hours = Integer.parseInt(args[1]);
     		}


     		ApplianceParser applianceParser = new ApplianceParser(house, inputConfigFile);
     		applianceParser.parse();
     	
     		//In order to call the activate() method on the instance house, the getHouse() method must be called
     		house = applianceParser.getHouse();

     		//Calls activate on house for the number of hours defined by the user
     		house.activate(hours);

    	} 
}
