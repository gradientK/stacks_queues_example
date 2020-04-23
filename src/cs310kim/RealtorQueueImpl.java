/* RealtorQueueImpl
 *
 * This implementation class creates and manages a singly linked list of
 * Realtors in a queue
 */
package cs310kim;

/**
 *
 * @author Grant Kim
 * @version 1.0, java assignment 4
 */
public class RealtorQueueImpl {
    private RealtorNode frontNode;
    private RealtorNode rearNode;
    
    /**getFrontNode
     * 
     * @return frontNode
     */
    public RealtorNode getFrontNode() {
        return frontNode;
    }
    
    /**RealtorQueueImpl
     * 
     * instantiates the Realtor queue
     */
    public RealtorQueueImpl() {
        frontNode = null;
        rearNode = null;
    }
    
    /**addToQueue
     * 
     * Adds Realtor to queue
     * 
     * @param realtorObject 
     */
    public void addToQueue(Realtor realtorObject) {
        try {
            RealtorNode newNode = new RealtorNode(realtorObject);

            if(rearNode != null) {
                rearNode.setNextNode(newNode);
            }
            else {
                frontNode = newNode;
            }
            rearNode = newNode;
        }
        catch (OutOfMemoryError e) {
            System.err.println("ERROR: out of memory, unable to add realtor "
                    + " to queue");
        }
    }
    
    /**removeFromQueue
     * 
     * Removes Realtor from queue, returns Realtor removed
     * 
     * @return returnRealtor
     */
    public Realtor removeFromQueue() {
        Realtor returnRealtor = null;
        if(frontNode != null) {
            returnRealtor = frontNode.getRealtor();
            frontNode = frontNode.getNextNode();
                if(frontNode == null) {
                    rearNode = null;
                }
        }
        else {System.out.println("Queue was empty - cannot return Realtor");
        }
        return returnRealtor;
    }

    /**isQueueEmpty
     * 
     * Checks to see if queue is empty, returns boolean
     * 
     * @return realtorQueueEmpty
     */
    public boolean isQueueEmpty() {
        boolean realtorQueueEmpty = false;
        if(frontNode == null) {
            realtorQueueEmpty = true;
        }
        return realtorQueueEmpty;
    }

    /**calculateNumberOfNodes
     * 
     * Calculates number of nodes, returns number
     * 
     * @return count
     */
    public int calculateNumberOfNodes() {
        int count = 0;
        RealtorNode currentNode = frontNode;
        while(currentNode != null) {
            count++;
            currentNode = currentNode.getNextNode();
        }
        return count;
    }
    
    /**isRealtorWaiting
     * 
     * Checks queue to see if Realtor is already waiting in line
     * 
     * @param licenseNumber
     * @return isWaiting
     */
    public boolean isRealtorWaiting(String licenseNumber) {
        boolean isWaiting = false;
        
        if(!isQueueEmpty()) {
            RealtorNode currentNode = frontNode;
            while(currentNode != null) {
                if(licenseNumber
                        .equals(currentNode.getRealtor().getLicenseNumber()) ) {
                    isWaiting = true;
                }
                currentNode = currentNode.getNextNode();
            }
       }
        return isWaiting;
    }
    
    /**printQueue
     * 
     * method to print the contents of the queue for realtors
     */
    public void printQueue() {
        if(!isQueueEmpty()) {
            int count;
            RealtorNode currentNode = frontNode;
            count = calculateNumberOfNodes();
           
            if(count > 1) {
                System.out.println("The queue contains " + count + " realtors");
            }
            else {System.out.println("The queue contains 1 realtor");
            }
            count = 1;
            while(currentNode != null) {
                System.out.println("node[" + count + "] = " 
                        + currentNode.getRealtor());
                count++;
                currentNode = currentNode.getNextNode();
            }
            System.out.println();
        }
        else {System.out.println("Queue is empty");
        }
    }
}
