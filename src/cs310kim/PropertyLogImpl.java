/*PropertyLogImpl
 * 
 * This implementation class creates and manages a linked list of 
 * Property objects
 */
package cs310kim;

import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * @author Grant Kim
 * @version 3.0, Java Assn 4
 */
public class PropertyLogImpl {
    private LinkedList<Property> propertyLog = new LinkedList<>();
    private int propertyCount;
        
    /**getPropertyLog
     * 
     * Returns the propertyLog attribute
     * 
     * @return propertyLog
     */
    public LinkedList<Property> getPropertyLog() {
        return propertyLog;
    }
    
    /**getNumProperties
     * 
     * Returns the propertyCount attribute
     * 
     * @return propertyCount
     */ 
    public int getNumProperties() {
        return propertyCount;
    }   
    
    /**add
     * 
     * Appends Property object to end of list and returns true if successful
     * 
     * @param propertyObject
     * @return addSuccessful
     */
    public boolean add(Property propertyObject) {
        boolean addSuccessful;
        
        propertyLog.addLast(propertyObject);
        propertyCount++;
        addSuccessful = true;        
        return addSuccessful;
    }
    
    /**remove
     * 
     * Removes all Property objects from list matching Realtor license number
     * and returns true if successful
     * 
     * @param licenseNumber
     * @return removeSuccessful
     */ 
    public boolean remove(String licenseNumber) {
        boolean removeSuccessful = false;
        Iterator<Property> iter = propertyLog.iterator();

        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(licenseNumber.equals(newProperty.getLicenseNumber()) ) {
                iter.remove();
                propertyCount--;
                removeSuccessful = true;
            }
        }         
        return removeSuccessful;        
    }
    
    /**remove
     * 
     * Removes all Property objects from list matching property MLS number
     * 
     * @param mlsNumber
     * @return removeSuccessful
     */
    public boolean remove(int mlsNumber) {
        boolean removeSuccessful = false;
        Iterator<Property> iter = propertyLog.iterator();
        
        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(mlsNumber == newProperty.getMlsNumber()) {
                iter.remove();
                propertyCount--;
                removeSuccessful = true;
            }
        }
        return removeSuccessful;
    }
    
    /**isMlsUnique
     * 
     * Tests if Property with MLS number exists in propertyLog
     * 
     * @param mlsNumber
     * @return uniqueMls
     */ 
    public boolean isMlsUnique(int mlsNumber) {
        boolean uniqueMls = true;        
        Iterator<Property> iter = propertyLog.iterator();

        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(mlsNumber == newProperty.getMlsNumber()) {
                uniqueMls = false;
            }
        }
        return uniqueMls;
    }
    
    /**getNumProperties
     * 
     * Returns number of Property objects in propertyLog with specific license
     * number
     * 
     * @param licenseNumber
     * @return numOfProperties
     */
    public int getNumProperties(String licenseNumber) {
        int numOfProperties = 0;
        Iterator<Property> iter = propertyLog.iterator();

        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(licenseNumber.equals(newProperty.getLicenseNumber())) {
                numOfProperties++;
            }  
        }        
        return numOfProperties;
    }
    
    /**totalPropertyValue
     * 
     * Returns sum of all asking prices of all properties
     * 
     * @return totalValueAll
     */ 
    public double totalPropertyValue() {
        double totalValueAll = 0.0;
        Iterator<Property> iter = propertyLog.iterator();

        while(iter.hasNext()) {
            Property newProperty = iter.next();
            totalValueAll = totalValueAll + newProperty.getAskingPrice();
        }
        return totalValueAll;
    }
    
    /**totalPropertyValue
     * 
     * Returns sum of asking prices for properties with specific realtor license
     * number
     * 
     * @param licenseNumber
     * @return totalValueRealtor
     */
    public double totalPropertyValue(String licenseNumber) {
        double totalValueRealtor = 0;
        Iterator<Property> iter = propertyLog.iterator();
        
        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(licenseNumber.equals(newProperty.getLicenseNumber())) {
                totalValueRealtor = totalValueRealtor 
                        + newProperty.getAskingPrice();
            }  
        }        
        return totalValueRealtor;
    }
    
    /**traverseDisplay
     *      
     * Traverses Property Log, prints out each Property
     */
    public void traverseDisplay() {  
        Iterator<Property> iter = propertyLog.iterator();
        
        System.out.println("Property Log:");  
        while(iter.hasNext()) {
            Property newProperty = iter.next();            
            System.out.println(newProperty);
        }
        System.out.println();
    }
    
    /**cleanUp
     * 
     * Traverses Property Log, removes any Property with invalid MLS numbers
     * 
     * @return invalid Removed
     */
    public boolean cleanUp() {
        boolean invalidRemoved = false;
        Iterator<Property> iter = propertyLog.iterator();   
      
        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(newProperty.goodMlsNumber() == false) {
                iter.remove();
                System.out.println("Invalid MLS number for property " 
                        + newProperty.getMlsNumber() + " -- Deleting property "
                        + "from log");
                propertyCount--;
                invalidRemoved = true;
            }  
        }        
        return invalidRemoved;    
    }   
    
    /**removeForRealtor
     * 
     * Removes all Property objects from list matching Realtor license number
     * and returns true if successful
     * 
     * @param licenseNumber
     * @return removeSuccessful
     */ 
    public boolean removeForRealtor(String licenseNumber) {
        boolean removeSuccessful = false;

        System.out.println("Invalid realtor license number for realtor "
                        + licenseNumber);
        System.out.println("    Removing realtor "
                        + licenseNumber 
                        + " and all properties associated with this realtor");
        
        Iterator<Property> iter = propertyLog.iterator();
        while(iter.hasNext()) {
            Property newProperty = iter.next();
            if(licenseNumber.equals(newProperty.getLicenseNumber()) ) {
                System.out.println("    Removing property " 
                        + newProperty.getMlsNumber() + " listed by Realtor " 
                        + licenseNumber);
                iter.remove();
                propertyCount--;
                removeSuccessful = true;
            }
        }         
        return removeSuccessful;        
    }
}