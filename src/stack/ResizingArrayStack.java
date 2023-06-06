package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code ResizingArrayStack} class represents a last-in-first-out (LIFO) stack
 * of generic items.
 * It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 * for peeking at the top item, testing if the stack is empty, and iterating through
 * the items in LIFO order.
 * <p>
 * This implementation uses a resizing array, which double the underlying array
 * when it is full and halves the underlying array when it is one-quarter full.
 * The <em>push</em> and <em>pop</em> operations take constant amortized time.
 * The <em>size</em>, <em>peek</em>, and <em>is-empty</em> operations takes
 * constant time in the worst case.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;

    private Object[] a;         // array of items
    private int n;            // number of elements on stack


    /**
     * Initializes an empty stack.
     */
    public ResizingArrayStack() {
        a = new Object[INIT_CAPACITY];
        n = 0;
    }

    /**
     * Is this stack empty?
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the stack.
     *
     * @return the number of items in the stack
     */
    public int size() {
        return n;
    }


    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Object[] copy = new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }


    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void push(Item item) {
        if (n == a.length) resize(2 * a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    @SuppressWarnings("unchecked")
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = (Item) a[n - 1];
        a[n - 1] = null;                              // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
        return item;
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    @SuppressWarnings("unchecked")
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
        return (Item) a[n - 1];
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // a array iterator, in reverse order
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        @SuppressWarnings("unchecked")
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (Item) a[i--];
        }
    }

    /**
     * Main method to demonstrate the usage of the stack.LinkedStackOfString class.
     * It takes a space-separated string as input and performs push and pop operations on the stack.
     * If an input element is "-", it performs a pop operation and prints the popped item.
     * Otherwise, it performs a push operation with the input element.
     */
    public static void main(String[] args) {

        ResizingArrayStack<String> resizingArrayStack = new ResizingArrayStack<>();
        String[] input = "to be or not to - be - - that - - - is".split(" ");
        int x = 0;
        while (x < input.length) {
            if (input[x].equals("-")) {
                System.out.print(resizingArrayStack.pop() + " ");
            } else {
                resizingArrayStack.push(input[x]);
            }
            x++;

        }
    }

}
