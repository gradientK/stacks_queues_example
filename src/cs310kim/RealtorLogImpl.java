/* RealtorLogImpl
 *
 * This implementation class creates and manages an ordered singly linked list 
 * of Realtor Nodes
 */
package cs310kim;

/**
 *
 * @author Grant Kim
 * @version 3.0, Java Assn 4
 */
public class RealtorLogImpl {
    private RealtorNode headNode;
    private RealtorNode previousNode;    
    private RealtorNode currentNode;
    private int listSize = 0;
    
    /**getHeadNode
     * 
     * @return headNode
     */
    public RealtorNode getHeadNode() {
        return headNode;
    }
    
    /**getListSize
     * 
     * @return 
     */
    public int getListSize() {
        return listSize;
    }
    
    /**add
     * 
     * Adds Realtor object to the ordered list
     * 
     * @param realtorObject 
     * @return  addSuccessful
     */
    public boolean add(Realtor realtorObject) {
        boolean addSuccessful = true;
        RealtorNode newNode = null;    
        
        try {newNode = new RealtorNode(realtorObject);
        }
        catch (OutOfMemoryError E) {
            System.err.println("  ERROR - out of memory -- cannot add Realtor "
                    + realtorObject.getLicenseNumber());
        }
        if(headNode ==  null) {
            headNode = newNode;          //list is empty, insert first node
        }
        else if(newNode.getRealtor().getLicenseNumber()
                .compareTo(headNode.getRealtor().getLicenseNumber()) < 0) {
            newNode.setNextNode(headNode);     //node becomes head node
            headNode = newNode;     
        }
        else {
            previousNode = headNode;
            currentNode = headNode.getNextNode();
            
            while(currentNode != null 
                    && newNode.getRealtor().getLicenseNumber()
                            .compareTo(currentNode.getRealtor()
                                    .getLicenseNumber()) >= 0) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }
            newNode.setNextNode(currentNode);
            previousNode.setNextNode(newNode);
        }
        listSize++;  
        return addSuccessful;
    }
    
    /**remove
     * 
     * Removes Realtor object from the ordered list
     * 
     * @param licenseNumber
     * @return removeSuccessful
     */
    public boolean remove(String licenseNumber) {
        boolean removeSuccessful = false;
        previousNode = null;
        currentNode = headNode;

        if(licenseNumber.compareTo(        //if license number matches head Node
                currentNode.getRealtor().getLicenseNumber()) == 0) {
            headNode = headNode.getNextNode();
            listSize--;
            removeSuccessful = true;
        }
        while(currentNode != null 
                && licenseNumber.compareTo(  //while license number does not match
                        currentNode.getRealtor().getLicenseNumber()) != 0) {
            previousNode = currentNode;
            currentNode = currentNode.getNextNode();          
            if(currentNode != null) {
                if(licenseNumber.compareTo(
                        currentNode.getRealtor().getLicenseNumber()) == 0) {
                    previousNode.setNextNode(currentNode.getNextNode());
                    removeSuccessful = true;
                    listSize--;
                }
            }
        }
        return removeSuccessful;
    }
    
    /**isLicenseUnique
     * 
     * Tests if Realtor license number already exists (or is unique) 
     * in Realtor list
     * 
     * @param licenseNumber
     * @return uniqueLicense
     */
    public boolean isLicenseUnique(String licenseNumber) { 
        boolean uniqueLicense = false;
        RealtorNode targetNode = headNode;
        
        while(targetNode != null 
                && licenseNumber.compareTo(
                        targetNode.getRealtor().getLicenseNumber()) != 0) {
            targetNode = targetNode.getNextNode();
        }
        if (targetNode == null) {         //if traverse reached end of list
            uniqueLicense = true;         //license number is unique
        }
        return uniqueLicense;
    }

    /**traverseDisplay
     * 
     * Traverses Realtor Log, prints out each Realtor
     */
    public void traverseDisplay() {
        RealtorNode currentNode = headNode;   
        
        System.out.println("Realtor Log:");
        while(currentNode != null) {
            System.out.println(currentNode.getRealtor());
            currentNode = currentNode.getNextNode();
        }
    }
    
    /**cleanUp
     * 
     * Traverses Realtor Log, removes any Realtors with invalid license numbers
     * 
     * @param propertyLog
     * @return invalidRemoved
     */
    public boolean cleanUp(PropertyLogImpl propertyLog) {
        boolean invalidRemoved = false;
        int loopAgain = 0;
       
        while(loopAgain >= 0) {
            previousNode = null;
            currentNode = headNode;
            
            if(currentNode.getRealtor().goodLicenseNumber() == false) {
                propertyLog.removeForRealtor(
                        currentNode.getRealtor().getLicenseNumber());
                headNode = headNode.getNextNode();
                invalidRemoved = true;
                loopAgain++;                
                listSize--;
            }
            while(currentNode != null 
                    && currentNode.getRealtor().goodLicenseNumber() == true) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();      
                
                if(currentNode != null) {
                    if(currentNode.getRealtor().goodLicenseNumber() == false) {
                    propertyLog.removeForRealtor(
                            currentNode.getRealtor().getLicenseNumber());                        
                        previousNode.setNextNode(currentNode.getNextNode());
                        invalidRemoved = true;
                        loopAgain++;                        
                        listSize--;
                    }
                }
            }
            loopAgain--;
        }              
        return invalidRemoved;
    }
    
    /**getRealtorNode
     * 
     * Finds realtor in list and returns realtor node 
     * 
     * @param licenseNumber
     * @return targetNode
     */
    public RealtorNode getRealtorNode(String licenseNumber) {
        RealtorNode targetNode = headNode;
        
        while(targetNode != null 
                && licenseNumber.compareTo(
                        targetNode.getRealtor().getLicenseNumber()) != 0) {
            targetNode = targetNode.getNextNode();
        }
        return targetNode;           //returns null if license Number not foound
    }
}

