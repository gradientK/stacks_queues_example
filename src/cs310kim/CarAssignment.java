/*CarAssignment
 *
 * This class defines the data and methods for a CarAssignment object and a driver it's 
 * assigned to
 */
package cs310kim;

import java.util.Objects;

/**
 *
 * @author Grant Kim
 * @version 1.0, Java Assn 4
 */
public class CarAssignment {
    private int carNumber;                   //car number
    private String realtorNumber;            //Realtor License Number
    private String carTrim;                  //"LUXURY" or "BASIC"

    /**Car - constructor
     */
    public CarAssignment() {
        this.carNumber = 0;
        this.realtorNumber = null;
        this.carTrim = null;
    }
    
    /**Car - constructor
     * 
     * @param carNumber
     * @param realtorNumber 
     * @param carTrim
     */
    public CarAssignment(int carNumber, String realtorNumber, String carTrim) {
        this.carNumber = carNumber;
        this.realtorNumber = realtorNumber;
        this.carTrim = carTrim;
    }
    
    /**setCarNumber
     * 
     * @param carNumber 
     */
    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    /**setRealtorNumber
     * 
     * @param realtorNumber 
     */
    public void setRealtorNumber(String realtorNumber) {
        this.realtorNumber = realtorNumber;
    }
    
    /**setCarTrim
     * 
     * @param carTrim
     */
    public void setCarTrim(String carTrim) {
        this.carTrim = carTrim;
    }

    /**getCarNumber
     * 
     * @return carNumber
     */
    public int getCarNumber() {
        return carNumber;
    }

    /**getRealtorNumber
     * 
     * @return realtorNumber
     */
    public String getRealtorNumber() {
        return realtorNumber;
    }
    
    /**getCarTrim
     * 
     * @return carTrim
     */
    public String getCarTrim() {
        return carTrim;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.carNumber;
        hash = 67 * hash + Objects.hashCode(this.realtorNumber);
        hash = 67 * hash + Objects.hashCode(this.carTrim);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarAssignment other = (CarAssignment) obj;
        if (this.carNumber != other.carNumber) {
            return false;
        }
        if (!Objects.equals(this.realtorNumber, other.realtorNumber)) {
            return false;
        }
        if (!Objects.equals(this.carTrim, other.carTrim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CarAssignment{" + "carNumber=" + carNumber + ", realtorNumber=" + realtorNumber + ", carTrim=" + carTrim + '}';
    }
}