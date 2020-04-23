/* Realtor
 *
 * This class defines the data and methods for a Realtor object
 */
package cs310kim;

import java.util.Objects;

/**
 *
 * @author Grant Kim
 * @version 4.0, Java Assn 4
 */
public class Realtor {
    private String licenseNumber;         //7 digits long
    private String firstName;
    private String lastName;
    private String phoneNumber;           //###-###-####
    private double commissionRate;

    /**Realtor - constructor
     */
    public Realtor() {
        this.licenseNumber = null;
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber = null;
        this.commissionRate = 0.0;
    }
    
    /**Realtor - constructor
     * 
     * @param licenseNumber    
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param commissionRate
     */
    public Realtor(String licenseNumber, String firstName, String lastName, 
            String phoneNumber, double commissionRate) {
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.commissionRate = commissionRate;   
    }
    
    /**Realtor - constructor
     * 
     * @param lineValues
     */
    public Realtor(String[] lineValues) {
        this.licenseNumber = lineValues[2];
        this.firstName = lineValues[3];
        this.lastName = lineValues[4];
        this.phoneNumber = lineValues[5];
        this.commissionRate = Double.parseDouble(lineValues[6]);
    }
    
    /**setLicenseNumber
     * 
     * @param licenseNumber
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    /**setFirstName
     * 
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**setLastName
     * 
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**setPhoneNumber
     * 
     * @param phoneNumber 
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**setCommissionRate
     * 
     * @param commissionRate 
     */
    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
    
    /**getLicenseNumber
     * 
     * @return licenseNumber
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }
    
    /**getFirstName
     * 
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**getLastName
     * 
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**getPhoneNumber
     * 
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**getCommissionRate
     * 
     * @return commissionRate
     */
    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Realtor other = (Realtor) obj;
        if (!Objects.equals(this.licenseNumber, other.licenseNumber)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (Double.doubleToLongBits(this.commissionRate) != Double.doubleToLongBits(
                other.commissionRate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Realtor{" + "licenseNumber=" + licenseNumber + ", firstName=" + 
                firstName + ", lastName=" + lastName + ", phoneNumber=" + 
                phoneNumber + ", commissionRate=" + commissionRate + '}';
    }
    
    /**goodLicenseNumber
     * 
     * Checks license to ensure 2 letters followed by 7 digits
     * 
     * @return Boolean
     */
    public Boolean goodLicenseNumber() {
        boolean returnValidLicense;
        final int LICENSE_LENGTH = 9;
        
        if ((this.licenseNumber.length() == LICENSE_LENGTH)
                && Character.isLetter(this.licenseNumber.charAt(0))
                && Character.isLetter(this.licenseNumber.charAt(1))
                && Character.isDigit(this.licenseNumber.charAt(2))
                && Character.isDigit(this.licenseNumber.charAt(3))
                && Character.isDigit(this.licenseNumber.charAt(4))
                && Character.isDigit(this.licenseNumber.charAt(5))
                && Character.isDigit(this.licenseNumber.charAt(6))
                && Character.isDigit(this.licenseNumber.charAt(7))
                && Character.isDigit(this.licenseNumber.charAt(8)) ) {
            returnValidLicense = true;
        }
        else {
            returnValidLicense = false;
        }
        return returnValidLicense;
    }

    /**goodPhoneNumber
     * 
     * Checks phone number to ensure 12 chars and contains appropriate dashes
     * 
     * @return Boolean
     */
    public Boolean goodPhoneNumber() {
        boolean returnValidPhone;
        final int PHONE_NUMBER_LENGTH = 12;
        
        if ((this.phoneNumber.length() == PHONE_NUMBER_LENGTH)
                && Character.isDigit(this.phoneNumber.charAt(0)) 
                && Character.isDigit(this.phoneNumber.charAt(1))
                && Character.isDigit(this.phoneNumber.charAt(2))  
                && (this.phoneNumber.charAt(3) == '-') 
                && Character.isDigit(this.phoneNumber.charAt(4))
                && Character.isDigit(this.phoneNumber.charAt(5)) 
                && Character.isDigit(this.phoneNumber.charAt(6))                          
                && (this.phoneNumber.charAt(7) == '-') 
                && Character.isDigit(this.phoneNumber.charAt(8))                        
                && Character.isDigit(this.phoneNumber.charAt(9))
                && Character.isDigit(this.phoneNumber.charAt(10)) 
                && Character.isDigit(this.phoneNumber.charAt(11)) ) {
            returnValidPhone = true;
        }  
        else {
            returnValidPhone = false;
        }
        return returnValidPhone;
    }
}