import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class FixedCapacityStackOfString implements StackOfString {

    private String s[];
    private int N = 0;

    public FixedCapacityStackOfString(int size) {
        s = new String[size];
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public void push(String item) {
        s[N++] = item;
    }

    @Override
    public String pop() {
        String item = s[--N];
        s[N] = null;
        return item;
    }

    @Override
    public int size() {
        return N;
    }

    public static void main(String[] args) {
        FixedCapacityStackOfString fixedCapacityStackOfString = new FixedCapacityStackOfString(10);
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            if (input.equals("-")) {
                StdOut.print(fixedCapacityStackOfString.pop() + " ");
            } else {
                fixedCapacityStackOfString.push(input);
            }
        }
    }
}
