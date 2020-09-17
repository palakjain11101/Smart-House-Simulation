import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

//The ApplianceParser class reads the cofig file line by line and extracts its data
public class ApplianceParser{
	String fileName = "configfile.txt";
	String line;
	String name;
	String subclass;
	String meter;
	float minUnitsConsumed;
	float maxUnitsConsumed;
	float fixedUnitsConsumed;
	int probabilitySwitchedOn;
	float cycleLength; 
	House house;
	BufferedReader reader;
	String value;

	//ApplianceParser constructor initialises the instance of a House, and the fileName
	public ApplianceParser(House house, String fileName){
		this.house = house;
		this.fileName = fileName;
	}
	
	//Returns instance house
	public House getHouse(){
		return this.house;	
	}

	//Resets the name of the appliance every time a blank line appears
	public void reset() {
		this.name = "";
	}

	//Parses the configuration file and extracts the information that the House needs
	public void parse(){
		try{
				//Reads the file one line at a time using BufferedReader
				reader = new BufferedReader(new FileReader(fileName));	
				while ((line = reader.readLine()) != null){

					//Identifies the type of appliance; new block of code follows each time
					if (line.equals("")) {
						Appliance appliance = this.createAppliance();
						if(appliance != null){
							this.addApplianceToHouse(appliance);
							this.reset();	
						}	
					} 

					//Otherwise, parses the line, splits by  ":"
					else {		
						String[] lineParts = line.split(": ");
						String parameter = lineParts[0];
						if(lineParts.length > 1){
							value = lineParts[1];
						}
						else{
							value = " ";
						}
						
						//From now on, the value is set depending on what is in lineParts[0]
						if (parameter.equals("name")) {
							this.name = value;
						}

						else if(parameter.equals("subclass")){
							this.subclass = value;
						}

						else if(parameter.equals("meter")){
							this.meter = value;
						}

						else if(parameter.equals("Min units consumed")){
							if(lineParts.length > 1){
								this.minUnitsConsumed = Float.parseFloat(value);
							}
						}

						else if(parameter.equals("Max units consumed")){
							if(lineParts.length > 1){
								this.maxUnitsConsumed = Float.parseFloat(value);
							}
						}

						else if(parameter.equals("Fixed units consumed")){
							if(lineParts.length > 1){
								this.fixedUnitsConsumed = Float.parseFloat(value);
							}
						}
							
						else if(parameter.equals("Probability switched on")){
							if(lineParts.length > 1){
								String probability[] = line.split(" in ");
								this.probabilitySwitchedOn = Integer.parseInt(probability[1]);
							}
						}
						
						else if(parameter.equals("Cycle length")){
							if(lineParts.length > 1){
								String cycle[] = value.split("/");
								this.cycleLength = Float.parseFloat(cycle[0]);
							}
						}
					
					}
				}
			
			reader.close();	
		}

		catch (IOException e){
			e.printStackTrace();
		}
	}


	//Creates a new instance of the appropriate class, with appropriate parameters
		public Appliance createAppliance(){
			if (this.name.length() > 0){
				Appliance appliance = null;

				if(this.subclass.equals("CyclicFixed")){
						appliance = new CyclicFixed(this.name, this.fixedUnitsConsumed, this.cycleLength);
				}

				if(this.subclass.equals("CyclicVaries")){
					appliance = new CyclicVaries(this.name, this.minUnitsConsumed, this.maxUnitsConsumed, this.cycleLength);
				}

				if(this.subclass.equals("RandomFixed")){
					appliance = new RandomFixed(this.name, this.fixedUnitsConsumed, this.probabilitySwitchedOn);
				}

				if(this.subclass.equals("RandomVaries")){
					appliance = new RandomVaries(this.name, this.minUnitsConsumed, this.maxUnitsConsumed, this.probabilitySwitchedOn);
				}

			return appliance;

			}

			return null;
		}

	//Adds the instances to the House, linked to the appropriate meter 
	public void addApplianceToHouse(Appliance appliance){
		try{
			if(this.meter.equals("electric")){
				house.addElectricAppliance(appliance);	
			}
			else if(this.meter.equals("water")){
				house.addWaterAppliance(appliance);
			}
		}
		catch(Exception e){
			System.out.println("Utility that the appliance consumes does not match the meter it is attached to !");
		}
	}

}
