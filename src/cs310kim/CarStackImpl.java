/* CarStackImpl
 *
 * This implementation class creates and manages an array of cars in a stack
 */
package cs310kim;

/**
 *
 * @author Grant Kim
 * @version 1.0, java assignment 4
 */
public class CarStackImpl {
    private int[] carStack;      //array to hold the cars
    private int top;             //indicates top of stack
    private final int MAX_SIZE = 4;     //maximum size of the car stack array
    
    /**CarStackImpl
     * 
     * Instantiates the basicCarStack array
     */
    public CarStackImpl() {
        carStack = new int[MAX_SIZE];
        top = -1;
    }
    
    /**getCarStack
     * 
     * @return carStack
     */
    public int[] getCarStack() {
        return carStack;
    }
    
    /**getTop
     * 
     * @return top
     */
    public int getTop() {
        return top;
    }
    
    /**push
     *
     * Pushes car onto stack
     * 
     * @param carNumber
     */
    public void push(int carNumber) {
        if (top < carStack.length) {      //checks if stack is full
            top++;
            carStack[top] = carNumber;
        }
        else {
            System.out.println("The car stack is full");
        }
    }
           
    /**pop
     * 
     * Pops cars off of stack, returns car's Number
     * 
     * @return carNumber
     */
    public int pop() {
        int carNumber = -1;
        
        if(top > -1) {
            carNumber = carStack[top];
            top--;
        }
        else {
            System.out.println("The car stack is empty");
        }
        return carNumber;
    }
    
    /**isCarStackEmpty
     * 
     * checks to see if the car stack is empty, returns boolean
     * 
     * @return carStackEmpty
     */
    public boolean isCarStackEmpty() {
        boolean carStackEmpty = false;
        if(top < 0) {
            carStackEmpty = true;
        }
        return carStackEmpty;
    }
    
    /**isCarStackFull
     * 
     * checks to see if the car stack is full, returns boolean
     * 
     * @return carStackFull;
     */
    public boolean isCarStackFull() {
        boolean carStackFull = false;
        if(top >= 4) {
            carStackFull = true;
        }
        return carStackFull;
    }
    
    /**printCarStack
     * 
     * Method to print contents of the car stack
     */
    public void printCarStack() {
        if(!isCarStackEmpty()) {
            System.out.println("Car stack contains " + (top + 1) + " cars");
            for (int index = top; index >= 0; index--) {
                System.out.println ("element[" + index + "] = " + carStack[index]);
            }
            System.out.println();
        }
        else {System.out.println("Car stack is empty");
        }
    }    
}
