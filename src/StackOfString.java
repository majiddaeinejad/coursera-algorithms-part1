/**
 * By implementing this interface we can provide a stack data-structure that can:
 * 1- Push a string value into the stack
 * 2- Pop the least recently added value
 * 3- Check the stack if it's empty by isEmpty
 * 4- Check the size of the stack
 */
public interface StackOfString {

    boolean isEmpty();

    void push(String item);

    String pop();

    int size();
}
