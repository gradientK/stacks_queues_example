/* PrintImpl
 *
 * Creates report for Realtor implemenation object and Property implemenation 
 * object and outputs to assn2report.txt
 */
package cs310kim;

import java.io.*;
import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Grant Kim
 * @version 3.0, Java Assn 4
 */
public class PrintImpl {
    private LinkedList<Property> propertyLog = new LinkedList<>();
    private RealtorLogImpl realtorLog = new RealtorLogImpl();
    private int propertyCount;
    private String outputFilename;

    private CarStackImpl basicCarStack = new CarStackImpl();
    private CarStackImpl luxuryCarStack = new CarStackImpl();
    private RealtorQueueImpl standardRealtorQueue = new RealtorQueueImpl();
    private RealtorQueueImpl topSellerRealtorQueue = new RealtorQueueImpl();    
    private LinkedList<CarAssignment> vehicleUsageLog = new LinkedList<>();
    private String carReportFilename;
    
    /**createReport
     * 
     * Creates report for Realtor implementation object and Property 
     * implementation object and outputs to assn2report.  
     * 
     * @param realtorLog
     * @param propertyLog
     * @param outputFilename
     */
    public void createReport(RealtorLogImpl realtorLog, 
            PropertyLogImpl propertyLog, String outputFilename){
        this.realtorLog = realtorLog;
        this.propertyLog = propertyLog.getPropertyLog();
        propertyCount = propertyLog.getNumProperties();
        this.outputFilename = outputFilename;
        
        File outputFile = new File(outputFilename);           
        PrintWriter writeOut = null;
        try { 
            writeOut = new PrintWriter(outputFile);
        } 
        catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }

        RealtorNode currentNode = this.realtorLog.getHeadNode();
        while(currentNode != null) {         
            if(currentNode.getNextNode() != null) {            
                writeOut.printf(currentNode.getRealtor().getLicenseNumber() + "  " 
                        + currentNode.getRealtor().getLastName() + ", "
                        + currentNode.getRealtor().getFirstName() + "%n%n");
            }
            else {writeOut.printf(currentNode.getRealtor().getLicenseNumber() + "  " 
                + currentNode.getRealtor().getLastName() + ", "
                + currentNode.getRealtor().getFirstName() + "%n%n");       
            }
            Iterator<Property> iter = this.propertyLog.iterator();           
            while(iter.hasNext()) {
                Property property1 = iter.next();
                if(currentNode.getRealtor().getLicenseNumber()
                        .equals(property1.getLicenseNumber()) ) {
                    writeOut.printf("%15d %19s %4d/%.1f  $ %11.2f", 
                            property1.getMlsNumber(),
                            property1.getStreetAddress(),
                            property1.getNumOfBedrooms(),
                            property1.getNumOfBathrooms(),
                            property1.getAskingPrice());
                        if(property1.getSold() == true) {
                                writeOut.print("   SOLD");
                    }
                    writeOut.printf("%n %24s, %s %d %n%n",
                            property1.getCity(),
                            property1.getState(),
                            property1.getZipCode());
                }  
            }
            if(currentNode.getNextNode() != null) {            
                writeOut.printf("    Number of Property Listing for Realtor: %d%n",
                        propertyLog.getNumProperties(
                                currentNode.getRealtor().getLicenseNumber()) );
                writeOut.printf("    Total sales value of Property Listing for "
                        + "Realtor: $ %12.2f%n%n", propertyLog.totalPropertyValue(
                                currentNode.getRealtor().getLicenseNumber()) );
            }                            
            else {
                writeOut.printf("    Number of Property Listing for Realtor: %d%n",
                        propertyLog.getNumProperties(
                                currentNode.getRealtor().getLicenseNumber()) );
                writeOut.printf("    Total sales value of Property Listing for "
                        + "Realtor: $ %12.2f%n%n", propertyLog.totalPropertyValue(
                                currentNode.getRealtor().getLicenseNumber()) );
            }
            currentNode = currentNode.getNextNode(); 
        }
        writeOut.printf("Total Number of PropertyListing for ALL Realtors = %d%n",
                propertyCount);
        writeOut.printf("Total sales value of Property Listings for ALL Realtors"
                + " = $ %13.2f%n%n", propertyLog.totalPropertyValue());
        writeOut.close(); 
        System.out.println("Report is complete -- located in file: " 
                + this.outputFilename);
        System.out.println();      
    }
    
    /**carStatusReport
     * 
     * Creates final text file for current car usage status
     * 
     * @param basicCarStack
     * @param luxuryCarStack
     * @param standardRealtorQueue
     * @param topSellerRealtorQueue
     * @param vehicleUsageLog
     * @param carReportFilename
     */
    public void carStatusReport( 
            CarStackImpl basicCarStack, CarStackImpl luxuryCarStack, 
            RealtorQueueImpl standardRealtorQueue, 
            RealtorQueueImpl topSellerRealtorQueue, 
            VehicleUsageImpl vehicleUsageLog, String carReportFilename) {
        
        this.basicCarStack = basicCarStack;
        this.luxuryCarStack = luxuryCarStack;
        this.standardRealtorQueue = standardRealtorQueue;
        this.topSellerRealtorQueue = topSellerRealtorQueue;
        this.vehicleUsageLog = vehicleUsageLog.getVehicleLog();
        this.carReportFilename = carReportFilename;
        
        //try to open car output file
        File outputFile = new File(this.carReportFilename);       
        PrintWriter writeOut = null;
        try { 
            writeOut = new PrintWriter(outputFile);
        } 
        catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(1);
        }
        
        //output current cars in use
        Iterator<CarAssignment> iter = this.vehicleUsageLog.iterator();  
        writeOut.println("CAR USAGE REPORT");
        while(iter.hasNext()) {
            CarAssignment newCar = iter.next();
            Realtor newRealtor = this.realtorLog.getRealtorNode(
                    newCar.getRealtorNumber()).getRealtor();

            writeOut.println(newRealtor.getFirstName() + " " 
                    + newRealtor.getLastName() + " is using car number " 
                    + newCar.getCarNumber());
        }
        writeOut.println();
        
        //output basic cars in stack
        int basicTop = this.basicCarStack.getTop();
        int[] basicCarArray = this.basicCarStack.getCarStack();
        
        writeOut.println("AVAILABLE CARS");
        writeOut.println("   BASIC CARS");       
        if(this.basicCarStack.isCarStackEmpty() == false) {
            for(int index = basicTop; index >= 0; index--) {
                writeOut.println ("      Car number " + basicCarArray[index] 
                        + " is available");
            }
        }
        else {writeOut.println("      No basic cars are available");
        }        
        System.out.println();
            
        //output luxury cars in stack
        int luxuryTop = this.luxuryCarStack.getTop();
        int[] luxuryCarArray = this.luxuryCarStack.getCarStack();
        
        writeOut.println("   LUXURY CARS");       
        if(this.luxuryCarStack.isCarStackEmpty() == false) {
            for(int index = luxuryTop; index >= 0; index--) {
                writeOut.println ("      Car number " + luxuryCarArray[index] 
                        + " is available");
            }
        }
        else {writeOut.println("      No luxury cars are available");
        }  
        writeOut.println();        
        
        //output top seller queue
        RealtorNode frontNodeTop = topSellerRealtorQueue.getFrontNode();
        
        writeOut.println("TOP SELLER QUEUE");
        if(topSellerRealtorQueue.isQueueEmpty() == true) {
            writeOut.println("There are no top selling realtors waiting");
        }
        else{
            RealtorNode currentNode = frontNodeTop;
            while(currentNode != null) {
                writeOut.println(currentNode.getRealtor().getFirstName() + " "
                        + currentNode.getRealtor().getLastName()
                        + " is waiting");
                currentNode = currentNode.getNextNode();
            }            
        }
        writeOut.println();
        
        //output standar seller queue
        RealtorNode frontNodeStd = standardRealtorQueue.getFrontNode();
        
        writeOut.println("STANDARD REALTOR QUEUE");
        if(standardRealtorQueue.isQueueEmpty() == true) {
            writeOut.println("There are no standard realtors waiting");
        }
        else{
            RealtorNode currentNode = frontNodeStd;
            while(currentNode != null) {
                writeOut.println(currentNode.getRealtor().getFirstName() + " "
                        + currentNode.getRealtor().getLastName()
                        + " is waiting");
                currentNode = currentNode.getNextNode();
            }            
        }
        writeOut.close();
    }
}
