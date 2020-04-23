/* VehicleUsageImpl
 *
 * This implementation class creates and manages a LinkedList of cars currently
 * in use by a realtor
 */
package cs310kim;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Grant Kim
 * @version 1.0, java assignment 4
 */
public class VehicleUsageImpl {
    private LinkedList<CarAssignment> vehicleLog = new LinkedList<>();
    private int vehicleCount;

    /**getVehicleLog
     * 
     * @return vehicleLog
     */
    public LinkedList<CarAssignment> getVehicleLog() {
        return vehicleLog;
    }
    
    /**getVehicleCount
     * 
     * @return vehicleCount
     */
    public int getVehicleCount() {
        return vehicleCount;
    }
    
    /**add
     * 
     * Appends CarAssignment object to end of list and returns true if successful
     * 
     * @param carObject
     * @return addSuccessful
     */
    public boolean add(CarAssignment carObject) {
        boolean addSuccessful;
        
        vehicleLog.addLast(carObject);
        vehicleCount++;
        
        addSuccessful = true;
        return addSuccessful;
    }
    
    /**remove
     *
     * Removes CarAssignment object from list matching Realtor license number 
     * and returns car number
     * 
     * @param licenseNumber
     * @return returnCar
     */
    public CarAssignment remove(String licenseNumber) {
        CarAssignment returnCar = null;
        Iterator<CarAssignment> iter = vehicleLog.iterator();
        
        while(iter.hasNext()) {
            CarAssignment newCarAssignment = iter.next();
            if(licenseNumber.equals(newCarAssignment.getRealtorNumber()) ) {
                returnCar = newCarAssignment;
                iter.remove();
                vehicleCount--;
            }
        }
        return returnCar;
    }
    
    /**isRealtorDriving
     *
     * Traverses through list to check if realtor/driver is out
     * 
     * @param licenseNumber
     * @return realtorDriving
     */
    public boolean isRealtorDriving(String licenseNumber) {
        boolean realtorDriving = false;
        Iterator<CarAssignment> iter = vehicleLog.iterator();

        while(iter.hasNext()) {
            CarAssignment newCarAssignment = iter.next();
            if(licenseNumber.equals(newCarAssignment.getRealtorNumber()) ){
                realtorDriving = true;
            }
        }
        return realtorDriving;
    }
    
    /**traverseDisplay
     *
     * Traverses Vehicle Usage Log, prints out each CarAssignment in use
     */
    public void traverseDisplay() {
        Iterator<CarAssignment> iter = vehicleLog.iterator();
        
        System.out.println("Vehicle Usage Log:");
        while(iter.hasNext()) {
            CarAssignment newCar = iter.next();
            System.out.println(newCar);
        }
        System.out.println();
    }

    Iterator<CarAssignment> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
