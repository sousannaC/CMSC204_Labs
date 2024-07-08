//sousanna chugunova
//DR.Thai
//CMSC204

package main;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class SolitaireGame {

    public static void main(String[] args) {
        List<Integer> numbers = generateRandomNumbers(12); // Generate 12 random numbers
        System.out.print("Initial List:");
        printList(numbers);

        playSolitaireMatchingGame(numbers);

        System.out.println("\nFinal List:");
        printList(numbers);

        System.out.println("\nGame over. No more pairs can be removed.");
    }

    // Generate a list of 12 random integers between 10 and 99
    private static List<Integer> generateRandomNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int num = rand.nextInt(90) + 10; // generates a number between 10 and 99
            numbers.add(num);
        }
        return numbers;
    }

    // Print a list of integers
    private static void printList(List<Integer> list) {
        for (Integer num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Play the solitaire matching game
    private static void playSolitaireMatchingGame(List<Integer> numbers) {
        boolean removedAnyPair;
        do {
            removedAnyPair = false;
            ListIterator<Integer> iterator = numbers.listIterator();
            List<Integer> newList = new ArrayList<>(); 

            while (iterator.hasNext()) {
                int current = iterator.next();
                if (iterator.hasNext()) {
                    int next = iterator.next();
                    if (canBeRemoved(current, next)) {
                        // Remove the last element returned by next() (next)
                        iterator.remove();
                        // Remove the current element
                        iterator.previous();
                        iterator.remove();
                        removedAnyPair = true;
                    } else {
                        iterator.previous(); 
                        newList.add(current); 
                    }
                } else {
                    newList.add(current); 
                }
            }

            if (removedAnyPair) {
                System.out.print("New List: ");
                printList(newList);
            }
        } while (removedAnyPair);
    }


    private static boolean canBeRemoved(int num1, int num2) {
    	
       
        int num1FirstDigit = num1 / 10;
        int num1SecondDigit = num1 % 10;
        int num2FirstDigit = num2 / 10;
        int num2SecondDigit = num2 % 10;

        // Check matching rules
        return (num1FirstDigit == num2FirstDigit || num1FirstDigit == num2SecondDigit ||
                num1SecondDigit == num2FirstDigit || num1SecondDigit == num2SecondDigit);
    }
}
