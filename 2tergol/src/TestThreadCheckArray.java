import java.util.Scanner;
import java.util.ArrayList;

/**
 * The  class is responsible for creating and managing threads
 * that check for a specific number within an array. It initializes an array based on user input,
 * starts multiple threads, and displays the results.
 * @author moatsemshiekhahmad
 */
public class TestThreadCheckArray {
    /**
     * The main method that runs the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Thread thread1, thread2;
            
            // Get array size from user
            System.out.println("Enter array size");
            int num = input.nextInt();
            ArrayList<Integer> array = new ArrayList<>();
            
            // Get array elements from user
            System.out.println("Enter numbers for array");
            for (int index = 0; index < num; index++) 
                array.add(input.nextInt());
            
            // Get the number to search for in the array
            System.out.println("Enter number");
            num = input.nextInt();
            
            // Create a shared data object
            SharedData sd = new SharedData(array, num);
            
            // Create and start threads
            thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
            thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
            thread1.start();
            thread2.start();
            
            try {
                // Wait for both threads to finish
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (!sd.getFlag()) {
                System.out.println("Sorry");
                return;
            }
            
            System.out.println("Solution for b: " + sd.getB() + ", n = " + sd.getArray().size());
            
            // Display index positions
            System.out.print("I:    ");
            for (int index = 0; index < sd.getArray().size(); index++)
                System.out.print(index + "    ");
            System.out.println();
            
            // Display array values
            System.out.print("A:    ");
            for (int index : sd.getArray()) {
                System.out.print(index);
                int counter = 5;
                while (true) {
                    index = index / 10;
                    counter--;
                    if (index == 0)
                        break;
                }
                for (int i = 0; i < counter; i++)
                    System.out.print(" ");
            }
            System.out.println();
            
            // Display win array (boolean results from thread check)
            System.out.print("C:    ");
            for (boolean index : sd.getWinArray()) {
                if (index)
                    System.out.print("1    ");
                else
                    System.out.print("0    ");    
            }
        }
    }
}
