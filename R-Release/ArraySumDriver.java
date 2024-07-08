package main;
//Sousanna Chugunova
//Dr.Thai
//CMSC204

import java.util.Scanner;

public class ArraySumDriver {
    private final static int ARRAY_SIZE = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer[] myArray = new Integer[ARRAY_SIZE];
        int index = 0;
        
        // Populate the array with initial values
        myArray[index++] = 13;
        myArray[index++] = 5;
        myArray[index++] = 12;
        myArray[index++] = 6;

        System.out.println("Integer array: " + java.util.Arrays.toString(myArray));
        
        //Summing first 4 elements
        int sum = sumOfArray(myArray, 3);
        System.out.println("Sum of first 4 elements: " + sum); 
        
        // Adding more elements to the array
        myArray[index++] = 7;
        myArray[index++] = 1;
        
        System.out.println("Integer array: " + java.util.Arrays.toString(myArray));
        
        //Summing first 6 elements
        sum = sumOfArray(myArray, 5);
        System.out.println("Sum of first 6 elements: " + sum); 

        // Asking user for Fibonacci position
        System.out.print("Enter the position of Fibonacci number to compute: ");
        int position = scanner.nextInt();

        // Computing Fibonacci number at the specified position
        int[] fibValues = new int[position + 1]; // Array to store Fibonacci values
        int fibResult = fibonacci(position, fibValues);
        System.out.println("At position " + position + ", the Fibonacci number is: " + fibResult);

        scanner.close();
    }
    
    
    /**
     * Recursive method for generating sum of values in array up to a specified index.
     * @param arr array of Integers
     * @param num index of array to sum all values up to (including num)
     * @return sum of array values up to index num
     */
    public static int sumOfArray(Integer[] arr, int num) {
        // Base case: when num is 0, return the first element
        if (num == 0) {
            return arr[0];
        }
        // Recursive case
        return arr[num] + sumOfArray(arr, num - 1);
    }

    
    /**
     * Recursive method for computing Fibonacci numbers using dynamic programming.
     * @param n index of the Fibonacci number to compute
     * @param value array to store previously computed Fibonacci numbers
     * @return the Fibonacci number at index n
     */
    public static int fibonacci(int n, int[] value) {
        // Base cases: Fibonacci(0) = 0, Fibonacci(1) = 1
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // If value is already computed, return it
        if (value[n] != 0) {
            return value[n];
        }

        // Compute the (n-1)th and (n-2)th Fibonacci numbers if not already computed
        if (value[n - 1] == 0) {
            value[n - 1] = fibonacci(n - 1, value);
        }
        if (value[n - 2] == 0) {
            value[n - 2] = fibonacci(n - 2, value);
        }

        // Compute the nth Fibonacci number
        value[n] = value[n - 1] + value[n - 2];
        return value[n];
    }
}
