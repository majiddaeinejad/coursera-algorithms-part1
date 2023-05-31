import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfString implements StackOfString {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    @Override
    public boolean isEmpty() {
        return first.item == null;
    }

    @Override
    public void push(String value) {
        Node oldFirst = first;
        first = new Node();
        first.item = value;
        first.next = oldFirst;
    }

    @Override
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

    public static void main(String[] args) {
        LinkedStackOfString linkedStackOfString = new LinkedStackOfString();

        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (input.equals("-")) {
                StdOut.print(linkedStackOfString.pop() + " ");
            } else {
                linkedStackOfString.push(input);
            }

        }
    }

}
