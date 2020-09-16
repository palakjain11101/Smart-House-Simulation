
Brief listing of the coursework and how it all works 

All parts (from 1-7) of the coursework have been attempted, and exceptions have been carried out in the construction of the simulation, where appropriate.
Extension 3 has also been attempted and for clarity, two separate directories were created; "Coursework" folder contains all the files for parts 1-7, 
excluding the extension, and the "Courseworkwithextension" includes all the files, and the extension.

All the parts (from 1-7) have been separated in the main method by comments, and each part can be tested separately by commenting the rest of the main method.
The main method has been inserted in the House class, therefore compiling House with command javac House.java will compile all the files and 
simply running House with command 'java House' should run the simulation.


Running the code, including commandline parameters

The ApplianceParser file is responsible for parsing the file, and storing its information.

The simulation file can be run either with no parameters given on the command line, in which case it will use the configfile in the same directory by default,
and will automatically run the simulation for  24*7 hours.
If one parameter is given, this will be recognised as the configuration file and the simulation will still automatically run for 24*7 hours.
If two parameters are given, the first should pass the configuration file and the latter can be any number of hours that the user wants the
simulation to run for.


Extension Description

Extension 3 has been attempted, however, it has been altered a little. The feed-in-tariff system has been built in the BatteryMeter class. 
This version of the BatteryMeter has been put in a separate directory ("CourseWorkwithextension"), along with an updated Battery class, to avoid doubt 
about the alterations.

The Battery Class now returns the excessUnits in the storesUnits() method, rather than the meterReading, so that they can be sold through the BatteryMeter.

In addition to its previous functionalities, the BatteryMeter now generates a random tariff amongst one of these values: 0.010, 0.013 and 0.020, 
using the random_tariff() method per hour. It uses the sellExcessUnits() method to sell any excess units that had been lost previously, at the price 
of the tariff. 

In addition, the batterymeter chooses whether to use the charge in the battery to power the house or to sell it. If the unitCost
(the cost of using electricity from Mains) is greater than the tariff, it would be more profitable to use units straight from the battery
(if there is batteryCharge) to power the house. If the tariff is greater, it would be more profitable to use units straight from Mains to power the 
house and to sell that many battery units back to the utility company. 

In the first case, the program does what it was doing earlier; withdrawing maximum battery units from the battery and taking the rest from the Mains. 
In the case of the latter, the sellUnits() method  is called. Then, the overall profit is calculated by subtracting the cost of using Mains 
electricity from the money collected when that many stored batteryUnits are sold at the tariff price.

No extra code was needed in the Main method to run the extension; the extension can be tested by running House.java like earlier.