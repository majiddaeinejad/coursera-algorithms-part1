public class LinkedStackOfString implements StackOfString {

    private Node first;

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
        first.next = new Node();
        first.item = value;
        first.next = oldFirst;


    }

    @Override
    public String pop() {
        String item = first.item;
        first = first.next;
        return item;
    }

}
