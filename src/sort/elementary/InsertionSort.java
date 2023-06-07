package sort.elementary;

/**
 * Insertion Sort Algorithm
 * 1- Start with an unsorted array.
 * 2- Iterate through the unsorted subarray from left to right.
 * 3- Compare the current element with left one
 * 4- If the left element is larger than current then exchange it and decrement the current index otherwise go to step 6
 * 5- Repeat steps 3 to 4 for each element until current index is 0
 * 6- Repeat steps 2 to 5 for each element in the unsorted subarray.
 * 7- The algorithm terminates, and the array is sorted.
 */
public class InsertionSort {

    public static void sort(Comparable a[]) {
        int N = a.length;
        if (N < 2)
            return;

        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j - 1]))
                    exch(a, j, j - 1);
                else
                    break;

        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String[] input = "S O R T E X A M P L E".split(" ");
        sort(input);
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }

    }
}
