package stack;

import java.util.NoSuchElementException;

public class ResizingArrayStackOfStrings implements StackOfString {

    private String[] s;
    private int N;

    /**
     * Creates a resizable array stack with an initial capacity of 1.
     */
    public ResizingArrayStackOfStrings(){
        s = new String[1];
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Resizes the underlying array to the specified capacity.
     *
     * @param capacity the new capacity of the array.
     */
    private void resize(int capacity)
    {
        // Create a new array with the specified capacity
        String[] copy = new String[capacity];

        // Copy the elements from the original array to the new array
        for (int i = 0; i < N; i++)
            copy[i] = s[i];

        // Update the reference to the new array
        s = copy;
    }

    /**
     * Adds an item to the top of the stack. Resizes the s array if length is equal to current array capacity
     *
     * @param item the item to be pushed onto the stack.
     */
    @Override
    public void push(String item) {
        if (N == s.length)
            // If the array is full, resize it to double its current capacity
            resize(2 * s.length);
        s[N++] = item; // Add the item to the top of the stack and increment the N
    }

    @Override
    public String pop() {
        if(isEmpty())
            throw new NoSuchElementException();

        // Decrement the index and then use the index to retrieve the item from the array
        String item = s[--N];

        // Set the array element to null to allow garbage collection
        s[N] = null;

        // Check if the stack size is one-quarter of the array size,
        // if so, resize the array to half its current capacity
        if(N > 0 && N == s.length / 4)
            resize(s.length / 2);

        return item ;
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
     * Main method to demonstrate the usage of the ResizingArrayStackOfStrings class.
     * It takes a space-separated string as input and performs push and pop operations on the stack.
     * If an input element is "-", it performs a pop operation and prints the popped item.
     * Otherwise, it performs a push operation with the input element.
     */
    public static void main(String[] args) {
        ResizingArrayStackOfStrings resizingArrayStackOfStrings = new ResizingArrayStackOfStrings();
        String[] input = "to be or not to - be - - that - - - is".split(" ");
        int x = 0;
        while (x < input.length) {
            if (input[x].equals("-")) {
                System.out.print(resizingArrayStackOfStrings.pop() + " ");
            } else {
                resizingArrayStackOfStrings.push(input[x]);
            }
            x++;

        }

    }
}
