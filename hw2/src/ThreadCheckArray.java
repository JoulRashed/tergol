
import java.util.ArrayList;

/**
 * The ThreadCheckArray class is a runnable implementation used to check if a subset of integers 
 * in a shared array sums up to a given target value `b`. The solution is computed recursively, 
 * and the results are stored in the shared data object in a thread-safe manner.
 * 
 * @author Joul Rashed
 */
public class ThreadCheckArray implements Runnable 
{
    /**
     * A flag to indicate if a valid subset has been found.
     */
    private boolean flag;

    /**
     * An array indicating which elements of the shared array contribute to the solution.
     */
    private boolean[] winArray;

    /**
     * The shared data object containing the array and target value.
     */
    SharedData sd;

    /**
     * The array of integers to process.
     */
    ArrayList<Integer> array;

    /**
     * The target sum to achieve using elements from the array.
     */
    int b;

    /**
     * Constructs a new ThreadCheckArray object.
     * 
     * @param sd The shared data object containing the array and target value.
     */
    public ThreadCheckArray(SharedData sd) 
    {
        this.sd = sd;    
        synchronized (sd) 
        {
            array = sd.getArray();
            b = sd.getB();
        }        
        winArray = new boolean[array.size()];
    }

    /**
     * A recursive method to check if a subset of the array sums up to the target value.
     * 
     * @param n The current size of the subset being processed.
     * @param b The remaining target value to achieve.
     */
    void rec(int n, int b)
    {
        synchronized (sd) 
        {
            if (sd.getFlag())
                return;
        }    
        if (n == 1)
        {
            if (b == 0 || b == array.get(n - 1))
            {
                flag = true;
                synchronized (sd) 
                {
                    sd.setFlag(true);
                }            
            }
            if (b == array.get(n - 1))
                winArray[n - 1] = true;
            return;
        }
        
        rec(n - 1, b - array.get(n - 1));
        if (flag)
            winArray[n - 1] = true;
        synchronized (sd) 
        {
            if (sd.getFlag())
                return;
        }    
        rec(n - 1, b);
    }

    /**
     * The run method contains the logic for executing the recursive check and updating 
     * the shared data object.
     */
    public void run() {
        if (array.size() != 1)
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.size() - 1, b - array.get(array.size() - 1));
            else 
                rec(array.size() - 1, b);
        if (array.size() == 1)
            if (b == array.get(0) && !flag)
            {
                winArray[0] = true;
                flag = true;
                synchronized (sd) 
                {
                    sd.setFlag(true);
                }
            }
        if (flag)
        {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.size() - 1] = true;
            synchronized (sd) 
            {
                sd.setWinArray(winArray);
            }    
        }
    }
}
