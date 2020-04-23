/* RealtorNode
 * 
 * This class defines the data and methods for a Realtor Node object
 */
package cs310kim;

/**
 *
 * @author Grant Kim
 * @version 2.0, Java Assn 4
 */
public class RealtorNode {
    private Realtor realtor;
    private RealtorNode nextNode;
    
    /**RealtorNode - constructor
     */
    public RealtorNode() {
        this.realtor = null;
        this.nextNode = null;
    }
    
    /**RealtorNode - constructor
     * 
     * @param realtor 
     */
    public RealtorNode(Realtor realtor) {
        this.realtor = realtor;
        this.nextNode = null;
    }
    
    /**setRealtor
     * 
     * @param realtor 
     */
    public void setRealtor(Realtor realtor) {
        this.realtor = realtor;
    }
    
    /**getRealtor
     * 
     * @return realtor
     */
    public Realtor getRealtor() {
        return realtor;
    }
    
    /**setNextNode
     * 
     * @param nextNode 
     */
    public void setNextNode(RealtorNode nextNode) {
        this.nextNode = nextNode;
    }    
    
    /**getNextNode
     * 
     * @return nextNode
     */
    public RealtorNode getNextNode() {
        return nextNode;
    }
}
