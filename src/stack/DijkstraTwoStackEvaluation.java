package stack;

public class DijkstraTwoStackEvaluation {
    private ResizingArrayStack<String> ops = new ResizingArrayStack<>();
    private ResizingArrayStack<String> vals = new ResizingArrayStack<>();

    public static void main(String[] args) {
        ResizingArrayStack<String> ops = new ResizingArrayStack<>();
        ResizingArrayStack<Double> vals = new ResizingArrayStack<>();
        String[] input = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )".split(" ");
        int x = 0;
        while (x < input.length) {
            String s = input[x];
            if (s.equals("(")) ;
            else
                if (s.equals("+")) ops.push(s);
            else
                if (s.equals("*")) ops.push(s);
            else
                if (s.equals(")")) {
                    String op = ops.pop();
                    if (op.equals("+"))
                        vals.push(vals.pop() + vals.pop());
                    else
                        if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            } else {
                    vals.push(Double.parseDouble(s));
                }
            x++;
        }
        System.out.println(vals.pop());
    }


}
