package queue;

import java.util.NoSuchElementException;

public class LinkedQueueOfString implements QueueOfStrings {

    private Node first;
    private Node last;

    private int size;

    private class Node {
        String item;
        Node next;

        public Node(String item){
            this.item = item;
        }
    }

    /**
     * Adds an item to the end of the queue.
     *
     * @param item the item to be added to the queue
     */
    @Override
    public void enqueue(String item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            // If the queue is empty, set both 'first' and 'last' references to the new node
            first = newNode;
            last = first;
        } else {
            // If the queue is not empty, add the new node after the 'last' node and update the 'last' reference
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     *
     * @return the item at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public String dequeue() {
        if (getSize() == 0)
            throw new NoSuchElementException("Queue is empty");
        String item = first.item;
        first = first.next;
        if(first == null)
            // If 'first' becomes null after removing an item, update 'last' to null as well, indicating an empty queue
            last = null;
        size--;
        return item;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in the queue.
     *
     * @return the number of items in the queue
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Main method to demonstrate the usage of the LinkedQueueOfString class.
     * It takes a space-separated string as input and performs enqueue and dequeue operations on the stack.
     * If an input element is "-", it performs a dequeue operation and prints the dequeued item.
     * Otherwise, it performs a enqueue operation with the input element.
     */
    public static void main(String[] args) {
        LinkedQueueOfString linkedQueueOfString = new LinkedQueueOfString();
        String[] input = "to be or not to - be - - that - - - is".split(" ");
        int x = 0;
        while (x < input.length) {
            if (input[x].equals("-")) {
                System.out.print(linkedQueueOfString.dequeue() + " ");
            } else {
                linkedQueueOfString.enqueue(input[x]);
            }
            x++;

        }
    }

}
