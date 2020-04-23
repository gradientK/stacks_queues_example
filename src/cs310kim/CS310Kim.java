/* Real Estate Information Validity
 *
 * Reads data from a file related to Realtor and Property information
 * and validates the information.
 */
package cs310kim;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author Grant Kim
 * @version 4.0, Java Assn 4
 */
public class CS310Kim {
    static RealtorLogImpl realtorLogImpl = new RealtorLogImpl();
    static PropertyLogImpl propertyLogImpl = new PropertyLogImpl();
    
    static CarStackImpl basicCarStack = new CarStackImpl();   //basic car stack
    static CarStackImpl luxuryCarStack = new CarStackImpl();  //luxury car stack
    static RealtorQueueImpl standardRealtorQueue = new RealtorQueueImpl();
    static RealtorQueueImpl topSellerRealtorQueue = new RealtorQueueImpl();
    
    static VehicleUsageImpl vehicleUsageImpl = new VehicleUsageImpl();        
    static PrintImpl printImpl = new PrintImpl();  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final String CLEAN_OUTPUT_FILENAME = "output/assn4cleanReport.txt";
        final String CAR_OUTPUT_FILENAME = "output/carUsageReport.txt";
        final int BASIC_CAR_FLEET = 4;      //number of basic cars
        final int LUXURY_CAR_FLEET = 3;     //number of luxury cars
        
        //process Realtor and Property info
        processFile();
        realtorLogImpl.traverseDisplay();
        propertyLogImpl.traverseDisplay();
        //cleanup        
        System.out.println("Cleaning up realtor and property logs...");
        realtorLogImpl.cleanUp(propertyLogImpl);
        propertyLogImpl.cleanUp();
        //create clean report
        createReport(CLEAN_OUTPUT_FILENAME);
        
        //fill array for basic cars to start full
        for(int fillBasic = 1; fillBasic <= BASIC_CAR_FLEET; fillBasic++) {
            if(basicCarStack.isCarStackFull() == false) {
                basicCarStack.push(fillBasic);                  
            }
        }
        //fill array for luxury cars to start full
            //luxury car number starts where basic cars end
        for(int fillLux = BASIC_CAR_FLEET + 1;
                fillLux <= BASIC_CAR_FLEET + LUXURY_CAR_FLEET; fillLux++) {
            if(luxuryCarStack.isCarStackFull() == false) {    
                luxuryCarStack.push(fillLux);  
            }
        }
        //processCarInfo
        processCarInfo();
        printImpl.carStatusReport(basicCarStack, luxuryCarStack, 
                standardRealtorQueue, topSellerRealtorQueue, vehicleUsageImpl,
                CAR_OUTPUT_FILENAME);
    }
        
    /**processFile
     * 
     * Try to open file and throws exception if file not found. Reads REALTOR or
     * PROPERTY command, and calls methods for add or delete
     */
    private static void processFile() {
        final String INPUT_FILENAME = "input/assn4input1.txt";

        File inputFile = new File(INPUT_FILENAME);          
        Scanner fileInput = null;            
        try {
            fileInput = new Scanner(inputFile);    //attempts to open input file
            System.out.println("Reading data from file " + INPUT_FILENAME);
        }
        catch (FileNotFoundException xin) {
            System.err.println(xin);
            System.exit(1);
        }
        
        String oneLine;
        String[] lineValues;
        int numOfLinesRead = 0;
        while(fileInput.hasNextLine()) {
            numOfLinesRead++;
            oneLine = fileInput.nextLine();
            lineValues = oneLine.split(",");
            if("REALTOR".equals(lineValues[0].toUpperCase()) ) {
                if("ADD".equals(lineValues[1].toUpperCase()) ) {
                    processRealtorAddition(lineValues);
                }
                else if("DEL".equals(lineValues[1].toUpperCase()) ) {
                    processRealtorDeletion(lineValues);
                }
                else {System.out.println("    ERROR: ADD or DEL not detected"); 
                }
            }
            else if ("PROPERTY".equals(lineValues[0].toUpperCase()) ) {
                if("ADD".equals(lineValues[1].toUpperCase()) ) {
                    processPropertyAddition(lineValues);
                }
                else if("DEL".equals(lineValues[1].toUpperCase()) ) {
                    processPropertyDeletion(lineValues);
                }
                else {System.out.println("    ERROR: ADD or DEL not detected"); 
                }
            }
            else {System.out.println("    ERROR: REALTOR or PROPERTY data not "
                    + "detected"); 
            }
        }
        System.out.println("Done reading file. " + numOfLinesRead 
                + " lines read\n");
        fileInput.close();        
    }
    
    /**processRealtorAddition
     * 
     * Creates Realtor object, validates license number and phone number, 
     * checks if license number is unique, adds Realtor object to RealtorLogImpl
     * ArrayList
     * 
     * @param lineValues 
     */
    public static void processRealtorAddition(String[] lineValues) {     
        Realtor realtorOne = new Realtor(lineValues);

        if(realtorOne.goodLicenseNumber() == false) {
            System.out.println("    ERROR: Realtor with license "
                    + lineValues[2] + " has an invalid license number.");
        }
        if(realtorOne.goodPhoneNumber() == false) {
            System.out.println("    ERROR: Realtor with license "
                        + lineValues[2] + " has invalid phone number "
                        + lineValues[5]);   
        }
        if(realtorLogImpl.isLicenseUnique(lineValues[2]) == true) {
            realtorLogImpl.add(realtorOne);                        //add Realtor
            if(realtorOne.goodLicenseNumber() == false 
                    || realtorOne.goodPhoneNumber() == false) {
                System.out.println("  ADDED: Realtor with license " 
                        + lineValues[2] + ", regardless of data errors.");    
            }
            else {
                System.out.println("  ADDED: Realtor with license " 
                        + lineValues[2]);    
            }
        }
        else {System.out.println("    ERROR: License Number " + lineValues[2] 
                + " already exists, Realtor will not be added to the log.");
        }
    }
        
    /**processPropertyAddition
     * 
     * Creates Property object, validates MLS number, state and zip code,
     * calls method to check if Realtor number is unique and if Property number
     * is unique, adds Property object to PropertyLogImpl ArrayList
     * 
     * @param lineValues 
     */
    public static void processPropertyAddition(String[] lineValues) {
        Property propertyOne = new Property(lineValues);
        
        if(propertyOne.goodMlsNumber() == false) {
            System.out.println("    ERROR: Property with MLS number "
                    + lineValues[2] + " has an invalid MLS number.");
        }
        if(propertyOne.goodState() == false) {
            System.out.println("    ERROR: Property with MLS number " 
                    + lineValues[2] + " has invalid state abbreviation " 
                    + lineValues[6]);
        }
        if(propertyOne.goodZipCode() == false) {
            System.out.println("    ERROR: Property with MLS number " 
                    + lineValues[2] + " has invalid zip code " + lineValues[7]);
        }
        
        if(propertyLogImpl.isMlsUnique(Integer.parseInt(lineValues[2]))
                && realtorLogImpl.isLicenseUnique(lineValues[3]) == false) {
            propertyLogImpl.add(propertyOne);                     //add property
            if(propertyOne.goodMlsNumber() == false
                    || propertyOne.goodState() == false
                    || propertyOne.goodZipCode() == false) {
                System.out.println("  ADDED: Property with MLS number "
                        + lineValues[2] + " listed by realtor " 
                        + lineValues[3] + ", regardless of data errors.");
            }
            else{
                System.out.println("  ADDED: Property with MLS number "
                        + lineValues[2] + " listed by realtor " 
                        + lineValues[3]);
            }
        }
        else{
            if(propertyLogImpl.isMlsUnique(Integer.parseInt(lineValues[2])) 
                    == false) {
                System.out.println("  ADD ERROR: Property with MLS number " 
                        + lineValues[2] + " already exists in the realtor log. ");
            }
            else{System.out.println("  ADD ERROR: Property with MLS number "
                        + lineValues[2] + " has Realtor with license "
                        + lineValues[3]);
                System.out.println("             but there is no such Realtor "
                        + "license in the realtor log.");
            }               
            System.out.println("             So property will NOT be added to "
                    + "property log");
        }
    }    
        
    /**processRealtorDeletion
     * 
     * Checks if Realtor license number exists in list, then calls Realtor log
     * remove method, displays confirmation message, then calls Property log 
     * remove method deleting all Property object associated with Realtor 
     * license number
     * 
     * @param lineValues 
     */
    public static void processRealtorDeletion(String[] lineValues) {
        if(realtorLogImpl.isLicenseUnique(lineValues[2]) == false) {
            realtorLogImpl.remove(lineValues[2]);               //remove Realtor
            System.out.println("  DELETED: Realtor with license number " 
                    + lineValues[2] + " has been removed from the realtor log");
            System.out.println("           All realtor's properties will also "
                    + "be removed from the property log");
            propertyLogImpl.remove(lineValues[2]);
        }
        else{System.out.println("  DEL ERROR: Realtor with license " 
                + lineValues[2] + " was not found in the realtor log.");    
        }
    }
    
    /**processPropertyDeletion
     * 
     * Checks if Property MLS number exists in list, then calls Property log 
     * remove method, then displays confirmation message.
     * 
     * @param lineValues 
     */
    public static void processPropertyDeletion(String[] lineValues) {
        if(propertyLogImpl.isMlsUnique(Integer.parseInt(lineValues[2])) == false){
            //remove Property
            propertyLogImpl.remove(Integer.parseInt(lineValues[2]));
            System.out.println("  DELETED: Property with MLS number " 
                    + lineValues[2] + " has been removed from the property log");
        }
        else {System.out.println("  DEL ERROR: Property with MLS number "
                + lineValues[2] + " was not found in the property log.");
        }
    }
        
    /**createReport
     * 
     * Calls method from PrintImpl class to print report
     * 
     * @param nameOfFile
     */
    public static void createReport(String nameOfFile) {
        //if(nameOfFile.equals("output/assn4cleanReport.txt")) {
            System.out.println("\nCreating clean report...");            
        //}
        printImpl.createReport(realtorLogImpl, propertyLogImpl, nameOfFile);    
    }
    
    /**processCarInfo
     * 
     * Tries to open car info file, reads and processes Request or Return 
     */
    public static void processCarInfo() {
        final String CAR_INFO_FILENAME = "input/carInfo1d.txt";

        File carFile = new File(CAR_INFO_FILENAME);          
        Scanner carInput = null;            
        try {
            carInput = new Scanner(carFile);    //attempts to open input file
        }
        catch (FileNotFoundException xin) {     //catches file not found exception
            System.err.println(xin);
            System.exit(1);
        }
        
        String oneLine;
        String[] carLineValues;
        while(carInput.hasNextLine()) {
            oneLine = carInput.nextLine();    //store one line from file as String
            carLineValues = oneLine.split("\\s+");  //white space as delimiter 
            if("REQUEST".equals(carLineValues[0].toUpperCase()) ) {
                processCarRequest(carLineValues);   //calls method for car request
                }
            else if("RETURN".equals(carLineValues[0].toUpperCase()) ) {
                processCarReturn(carLineValues);    //calls method for car return
                }
            else {System.out.println("    ERROR: REQUEST or RETURN not detected"); 
                }
        }
        carInput.close();
    }

    /**processCarRequest
     * 
     * Handle Realtor request for a car
     * 
     * @param carLineValues
     */
    public static void processCarRequest(String[] carLineValues) {
        String licenseNumber = carLineValues[1];     //license number of realtor
        int carNumber;                              //car at top of stack
        String carTrim;                            //"LUXURY" or "BASIC"
        CarAssignment newCarAssignment;           //car used to assign realtor
        Realtor newRealtor;                      //realtor used for queue
            
        //checks if license number exits in Realtor Log
        if(realtorLogImpl.isLicenseUnique(licenseNumber) == true) {
            System.out.println("Unknown realtor " + licenseNumber 
                    + " not allowed access to cars. Request ignored.");
        }
        //checks if license number exists in queues
        else if(topSellerRealtorQueue.isRealtorWaiting(licenseNumber) == true
                    || standardRealtorQueue
                            .isRealtorWaiting(licenseNumber) == true) {
            System.out.println("Realtor " + licenseNumber + " is already "
                    + "waiting in queue. Request ignored.");
        }
        //checks if license number is already using a vehicle
        else if(vehicleUsageImpl.isRealtorDriving(licenseNumber) == true) {
            System.out.println("Request made by " + licenseNumber
                    + " matches Realtor already using vehicle, request ignored.");   
        }
        else {
            newRealtor = realtorLogImpl.getRealtorNode(licenseNumber).getRealtor();
            
            //if realtor is top seller
            if(propertyLogImpl.totalPropertyValue(licenseNumber) > 1000000) {
                //top seller assigned luxury car
                if(luxuryCarStack.isCarStackEmpty() == false) {
                    carNumber = luxuryCarStack.pop();
                    carTrim = "LUXURY";
                    newCarAssignment 
                            = new CarAssignment(carNumber, licenseNumber, carTrim);
                    vehicleUsageImpl.add(newCarAssignment);
                    System.out.println("Top seller " 
                            + newRealtor.getFirstName() + " " 
                            + newRealtor.getLastName()
                            + " has been assigned luxury car number " 
                            + carNumber);
                }
                //top seller assigned basic car
                else if(basicCarStack.isCarStackEmpty() == false) {
                    carNumber = basicCarStack.pop();
                    carTrim = "BASIC";
                    newCarAssignment 
                            = new CarAssignment(carNumber, licenseNumber, carTrim);
                    vehicleUsageImpl.add(newCarAssignment);
                    System.out.println("Top seller "
                            + newRealtor.getFirstName() + " " 
                            + newRealtor.getLastName()
                            + " has been assigned basic car number " 
                            + carNumber);
                }
                //top seller put in top seller queue
                else {
                    topSellerRealtorQueue.addToQueue(newRealtor);
                    System.out.println(newRealtor.getFirstName() + " "
                            + newRealtor.getLastName() 
                            + " waiting in top seller realtor queue");
                }
            }
            //else realtor is not top seller
            else {
                //standard realtor assigned standard car
                if(basicCarStack.isCarStackEmpty() == false) {
                    carNumber = basicCarStack.pop();
                    carTrim = "BASIC";
                    newCarAssignment 
                            = new CarAssignment(carNumber, licenseNumber, carTrim);
                    vehicleUsageImpl.add(newCarAssignment);
                    System.out.println("Standard realtor " 
                            + newRealtor.getFirstName() + " " 
                            + newRealtor.getLastName()
                            + " has been assigned basic car number " 
                            + carNumber);
                }
                //standard seller put in standard queue
                else {
                    standardRealtorQueue.addToQueue(newRealtor);
                    System.out.println(newRealtor.getFirstName() + " "
                            + newRealtor.getLastName() 
                            + " waiting in standard realtor queue");
                }                
            }
        }
    }
    
    /**processCarReturn
     * 
     * Handle Realtors returning car to the lot
     * 
     * @param carLineValues
     */
    public static void processCarReturn(String[] carLineValues) {
        String returningRealtor = carLineValues[1];//license number of realtor     
        CarAssignment returnCar;                  //car assignment object returned
        Realtor returnRealtor;                   //realtor returning car
        Realtor newRealtor;                     //realtor waiting in queue  
        int carNumber;                         //car at top of stack for queue
        String carTrim;                       //car trim popped from stack
        CarAssignment newCarAssignment;      //car assigned to realtor
        
        //checks if driver is out with car, ie listed on vehicle usage list
        if(vehicleUsageImpl.isRealtorDriving(returningRealtor) == false) {
            System.out.println("Returning realtor unknown, " + returningRealtor 
                    + " return ignored.");   
        }
        else {//realtor known to vehicleUsageImpl
            //remove car from usage list
            returnCar = vehicleUsageImpl.remove(returningRealtor);
            returnRealtor = realtorLogImpl
                    .getRealtorNode(returnCar.getRealtorNumber()).getRealtor();
        
            //push car back onto stack, if luxury, else basic
            if(returnCar.getCarTrim().equals("LUXURY")) {
                luxuryCarStack.push(returnCar.getCarNumber());
            }
            else {//if(returnCar.getCarTrim().equals("BASIC))
                basicCarStack.push(returnCar.getCarNumber());
            }
            System.out.println(returnRealtor.getFirstName() + " " 
                    + returnRealtor.getLastName() + " has returned car number "
                    + returnCar.getCarNumber());        
            
            //checks for top seller waiting in queue
            if(topSellerRealtorQueue.isQueueEmpty() == false) {
                newRealtor = topSellerRealtorQueue.removeFromQueue();
            
                //top seller assigned luxury car
                if(luxuryCarStack.isCarStackEmpty() == false) {
                    carNumber = luxuryCarStack.pop();
                    carTrim = "LUXURY";
                    newCarAssignment = new CarAssignment(carNumber, 
                            newRealtor.getLicenseNumber(), carTrim);
                    vehicleUsageImpl.add(newCarAssignment);
                    System.out.println("Top seller " 
                            + newRealtor.getFirstName() + " " 
                            + newRealtor.getLastName()
                            + " has been assigned luxury car number " 
                            + carNumber);
                }
                //top seller assigned basic car
                else {//if(basicCarStack.isCarStackEmpty() == false) {
                    carNumber = basicCarStack.pop();
                    carTrim = "BASIC";
                    newCarAssignment = new CarAssignment(carNumber, 
                            newRealtor.getLicenseNumber(), carTrim);
                    vehicleUsageImpl.add(newCarAssignment);
                    System.out.println("Top seller "
                            + newRealtor.getFirstName() + " " 
                            + newRealtor.getLastName()
                            + " has been assigned basic car number " 
                            + carNumber);
                }               
            }
            //checks for standard realtor in queue
            else if(standardRealtorQueue.isQueueEmpty() == false) {
                newRealtor = standardRealtorQueue.removeFromQueue();
        
                //standard realtor assigned standard car
                if(basicCarStack.isCarStackEmpty() == false) {
                    carNumber = basicCarStack.pop();
                    carTrim = "BASIC";
                    newCarAssignment = new CarAssignment(carNumber, 
                            newRealtor.getLicenseNumber(), carTrim);
                    vehicleUsageImpl.add(newCarAssignment);
                    System.out.println("Standard realtor " 
                            + newRealtor.getFirstName() + " " 
                            + newRealtor.getLastName()
                            + " has been assigned basic car number " 
                            + carNumber);
                }
            }
        }
    }
}