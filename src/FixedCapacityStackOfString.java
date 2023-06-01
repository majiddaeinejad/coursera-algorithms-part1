public class FixedCapacityStackOfString implements StackOfString {

    private String s[];
    private int N = 0;
    public FixedCapacityStackOfString(int size){
        s = new String[size];
    }

    @Override
    public boolean isEmpty() {
        return N==0;
    }

    @Override
    public void push(String item) {
        s[N++] = item;
    }

    @Override
    public String pop() {
        return s[--N];
    }
}
