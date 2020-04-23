/* Property
 *
 * This class defines the data and methods for a Property Object
 */
package cs310kim;

import java.util.Objects;

/**
 *
 * @author Grant Kim
 * @version 4.0, Java Assn 4
 */
public class Property {
    private int mlsNumber;                  //7 digits long
    private String licenseNumber;    //2 letters, 7 digits
    private String streetAddress;
    private String city;
    private String state;                   //CO or WY
    private int zipCode;                    //5 digits starting with 80,81,82,83
    private int numOfBedrooms;
    private double numOfBathrooms;
    private boolean sold;
    private double askingPrice;

    /**Property - constructor
     */
    public Property() {
        this.mlsNumber = 0;
        this.licenseNumber = null;
        this.streetAddress = null;
        this.city = null;
        this.state = null;
        this.zipCode = 0;
        this.numOfBedrooms = 0;
        this.numOfBathrooms = 0.0;
        this.sold = false;
        this.askingPrice = 0.0;
    }

    /**Property - constructor
     * 
     * @param mlsNumber
     * @param licenseNumber
     * @param streetAddress
     * @param city
     * @param state
     * @param zipCode
     * @param numOfBedrooms
     * @param numOfBathrooms
     * @param sold
     * @param askingPrice 
     */
    public Property(int mlsNumber, String licenseNumber, 
            String streetAddress, String city, String state, int zipCode, 
            int numOfBedrooms, double numOfBathrooms, boolean sold, 
            double askingPrice) {
        this.mlsNumber = mlsNumber;
        this.licenseNumber = licenseNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.numOfBedrooms = numOfBedrooms;
        this.numOfBathrooms = numOfBathrooms;
        this.sold = sold;
        this.askingPrice = askingPrice;
    }

     /**setPropertyAttributes
     *
     * @param lineValues
     */
    public Property(String[] lineValues) {
        boolean soldTF = false;
        if ("Y".equalsIgnoreCase(lineValues[10])) {
            soldTF = true;
        }
        else if ("N".equalsIgnoreCase(lineValues[10])) {
            soldTF = false;
        }
        this.mlsNumber = Integer.parseInt(lineValues[2]);
        this.licenseNumber = lineValues[3];
        this.streetAddress = lineValues[4];
        this.city = lineValues[5];
        this.state = lineValues[6];
        this.zipCode = Integer.parseInt(lineValues[7]);
        this.numOfBedrooms = Integer.parseInt(lineValues[8]);
        this.numOfBathrooms = Double.parseDouble(lineValues[9]);
        this.sold = soldTF;
        this.askingPrice = Double.parseDouble(lineValues[11]);
    }  

    /**setMlsNumber
     * 
     * @param mlsNumber 
     */
    public void setMlsNumber(int mlsNumber) {
        this.mlsNumber = mlsNumber;
    }

    /**set LicenseNumber
     * 
     * @param licenseNumber 
     */
    public void setlicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**setStreetAddress 
     * 
     * @param streetAddress 
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**setCity
     * 
     * @param city 
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**setState
     * 
     * @param state 
     */
    public void setState(String state) {
        this.state = state;
    }

    /**setZipCode
     * 
     * @param zipCode 
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**setNumOfBedrooms
     * 
     * @param numOfBedrooms 
     */
    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    /**setNumOfBathrooms
     * 
     * @param numOfBathrooms 
     */
    public void setNumOfBathrooms(double numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    /**setSold
     * 
     * @param sold 
     */
    public void setSold(boolean sold) {
        this.sold = sold;
    }

    /**setAskingPrice
     * 
     * @param askingPrice 
     */
    public void setAskingPrice(double askingPrice) {
        this.askingPrice = askingPrice;
    }

    /**getMlsNumber
     * 
     * @return mlsNumber
     */
    public int getMlsNumber() {
        return mlsNumber;
    }

    /**getLicenseNumber
     * 
     * @return licenseNumber
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**getStreetAddress
     * 
     * @return streeAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**getCity
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**getState
     * 
     * @return state
     */
    public String getState() {
        return state;
    }

    /**getZipCode
     * 
     * @return zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**getNumOfBedrooms
     * 
     * @return numOfBedrooms
     */
    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    /**getNumOfBathrooms
     * 
     * @return numOfBathrooms
     */
    public double getNumOfBathrooms() {
        return numOfBathrooms;
    }

    /**getSold
     * 
     * @return sold
     */
    public boolean getSold() {
        return sold;
    }

    /**getAskingPrice
     * 
     * @return askingPrice
     */
    public double getAskingPrice() {
        return askingPrice;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Property other = (Property) obj;
        if (this.mlsNumber != other.mlsNumber) {
            return false;
        }
        if (!Objects.equals(this.licenseNumber, 
                other.licenseNumber)) {
            return false;
        }
        if (!Objects.equals(this.streetAddress, other.streetAddress)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (this.zipCode != other.zipCode) {
            return false;
        }
        if (this.numOfBedrooms != other.numOfBedrooms) {
            return false;
        }
        if (Double.doubleToLongBits(this.numOfBathrooms) != 
                Double.doubleToLongBits(other.numOfBathrooms)) {
            return false;
        }
        if (this.sold != other.sold) {
            return false;
        }
        if (Double.doubleToLongBits(this.askingPrice) != 
                Double.doubleToLongBits(other.askingPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Property{" + "mlsNumber=" + mlsNumber + 
                ", licenseNumber=" + licenseNumber + 
                ", streetAddress=" + streetAddress + ", city=" + city 
                + ", state=" + state + ", zipCode=" + zipCode 
                + ", numOfBedrooms=" + numOfBedrooms + ", numOfBathrooms=" 
                + numOfBathrooms + ", sold=" + sold + ", askingPrice=" 
                + askingPrice + '}';
    }
    
    /**goodMlsNumber
     * 
     * Checks the MLS number to ensure it is 7 digits long
     * 
     * @return Boolean
     */
    public Boolean goodMlsNumber() {
        if (this.mlsNumber >= 1000000 && this.mlsNumber <= 9999999) { 
            return true;
        }
        else {return false;
        }
      
    }
    
    /**goodState
     * 
     * Checks state is either "CO" or "WY"
     * 
     * @return Boolean
     */
    public Boolean goodState() {
        if ("CO".equals(this.state) || "WY".equals(this.state)) {
            return true;
        }
        else {return false;            
        } 
    }
        
    /**goodZipCode
     * 
     * Checks zip code is 5 digits long starting with 80, 81, 82, or 83
     * 
     * @return Boolean
     */
    public Boolean goodZipCode() {
        if (this.zipCode >= 80000 && this.zipCode <= 83999) {
            return true;
        }
        else {return false;   
        }  
    } 
}
