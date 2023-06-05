package stack;

import java.util.NoSuchElementException;

/**
 * The stack.LinkedStackOfString class represents a stack data structure implemented using a linked list.
 * It follows the Last-In-First-Out (LIFO) principle, where the last element added is the first one to be removed.
 * The class provides basic operations such as push, pop, isEmpty, and size to manipulate the stack.
 */
public class LinkedStackOfString implements StackOfString {

    // Inner class representing a node in the linked list
    private class Node {
        String item;
        Node next;
    }

    private Node first = null; // Reference to the first node
    private int size = 0; // Number of elements in the stack

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return first.item == null;
    }

    /**
     * Adds an item to the top of the stack.
     *
     * @param item the item to be pushed onto the stack.
     */
    @Override
    public void push(String item) {
        // Create a new node
        Node newNode = new Node();
        newNode.item = item;

        // Link the new node to the current first node
        newNode.next = first;

        // Update the first node to the new node
        first = newNode;

        // Increment the size of the stack
        size++;
    }

    @Override
    public String pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        // Retrieve the item from the first node
        String item = first.item;

        // Update the first node to the next node, effectively removing the first node
        first = first.next;

        // Decrement the size of the stack
        size--;

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
        return size;
    }

    /**
     * Main method to demonstrate the usage of the stack.LinkedStackOfString class.
     * It takes a space-separated string as input and performs push and pop operations on the stack.
     * If an input element is "-", it performs a pop operation and prints the popped item.
     * Otherwise, it performs a push operation with the input element.
     */
    public static void main(String[] args) {
        LinkedStackOfString linkedStackOfString = new LinkedStackOfString();
        String[] input = "to be or not to - be - - that - - - is".split(" ");
        int x = 0;
        while (x < input.length) {
            if (input[x].equals("-")) {
                System.out.print(linkedStackOfString.pop() + " ");
            } else {
                linkedStackOfString.push(input[x]);
            }
            x++;

        }
    }

}
