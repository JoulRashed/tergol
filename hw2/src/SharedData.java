import java.util.ArrayList;

/**
 * SharedData class represents a shared resource containing an array of integers, 
 * a boolean flag, a fixed integer value, and a boolean array for tracking states.
 * 
 * @author Joul Rashed
 */
public class SharedData 
{
    /**
     * The array of integers shared between components.
     */
    private ArrayList<Integer> array;

    /**
     * The array indicating win states for components.
     */
    private boolean[] winArray;

    /**
     * A flag to represent a shared state.
     */
    private boolean flag;

    /**
     * A constant integer value.
     */
    private final int b;

    /**
     * Constructs a SharedData object with the given array of integers and a constant value.
     * 
     * @param array The array of integers to initialize the shared data.
     * @param b The constant integer value.
     */
    public SharedData(ArrayList<Integer> array, int b) {
        this.array = array;
        this.b = b;
    }

    /**
     * Retrieves the win state array.
     * 
     * @return A boolean array representing win states.
     */
    public boolean[] getWinArray() 
    {
        return winArray;
    }

    /**
     * Updates the win state array.
     * 
     * @param winArray A boolean array to set as the win state array.
     */
    public void setWinArray(boolean[] winArray) 
    {
        this.winArray = winArray;
    }

    /**
     * Retrieves the shared array of integers.
     * 
     * @return An ArrayList of integers.
     */
    public ArrayList<Integer> getArray() 
    {
        return array;
    }

    /**
     * Retrieves the constant integer value.
     * 
     * @return The constant integer value.
     */
    public int getB() 
    {
        return b;
    }

    /**
     * Retrieves the current state of the flag.
     * 
     * @return The boolean flag.
     */
    public boolean getFlag() 
    {
        return flag;
    }

    /**
     * Updates the state of the flag.
     * 
     * @param flag The new value of the flag.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}