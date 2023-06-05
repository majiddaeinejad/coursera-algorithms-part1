package queue;

public interface QueueOfStrings {

    /**
     * insert a new string onto queue
     * @param item
     */
    void enqueue(String item);

    /**
     * remove and return the string least recently added
     * @return first item
     */
    String dequeue();

    /**
     * Checks whether the stack is empty.
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     *
     * @return number of strings on the queue
     */
    int getSize();
}
