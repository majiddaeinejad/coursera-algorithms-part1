import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class FixedCapacityStackOfString implements StackOfString {

    private String s[];
    private int N = 0;

    /**
     * Creates a fixed capacity stack with the specified size.
     *
     * @param size the maximum number of elements the stack can hold.
     */
    public FixedCapacityStackOfString(int size) {
        s = new String[size];
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Adds an item to the top of the stack.
     *
     * @param item the item to be pushed onto the stack.
     */
    @Override
    public void push(String item) {
        //Set the Nth index of array to the item and the increment it
        s[N++] = item;
    }

    /**
     * Removes and returns the item from the top of the stack.
     *
     * @return the item that was removed from the stack.
     * @throws NoSuchElementException if the stack is empty.
     */
    @Override
    public String pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        // Decrement the index and then use the index to retrieve the item from the array
        String item = s[--N];

        // Set the array element to null to allow garbage collection
        s[N] = null;

        // Return the removed item
        return item;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack.
     */
    @Override
    public int size() {
        return N;
    }

    /**
     * Main method to demonstrate the usage of the LinkedStackOfString class.
     * It takes a space-separated string as input and performs push and pop operations on the stack.
     * If an input element is "-", it performs a pop operation and prints the popped item.
     * Otherwise, it performs a push operation with the input element.
     */
    public static void main(String[] args) {
        FixedCapacityStackOfString fixedCapacityStackOfString = new FixedCapacityStackOfString(10);
        String[] input = "to be or not to - be - - that - - - is".split(" ");
        int x = 0;
        while (x < input.length) {
            if (input[x].equals("-")) {
                System.out.print(fixedCapacityStackOfString.pop() + " ");
            } else {
                fixedCapacityStackOfString.push(input[x]);
            }
            x++;

        }
    }
}
