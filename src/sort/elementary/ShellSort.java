package sort.elementary;

import edu.princeton.cs.algs4.Shell;

/**
 * Shell Sort starts with a larger gap and gradually reduces the gap until it reaches 1,
 * performing Insertion Sort on subarrays with decreasing gaps. The final pass with a gap of 1 ensures that the array is completely sorted.
 * 1. Start with an unsorted array.
 * 2. Define an increment sequence, which determines the gap between elements to be compared and sorted.
 * 3. Initialize the increment value, typically the largest value from the chosen increment sequence.
 * 4. Iterate through the array, considering subarrays of elements that are "increment" positions apart.
 * 5. Within each subarray, perform an Insertion Sort on the elements, comparing each element with the previous elements in the subarray and inserting it into its correct position.
 * 6. Reduce the increment value to a smaller value from the chosen increment sequence.
 * 7. Repeat steps 4 to 6 until the increment value becomes 1.
 * 8. Perform a final Insertion Sort pass on the array with an increment value of 1 to completely sort the array.
 * 9. The algorithm terminates, and the array is sorted.
 */
public class ShellSort {
    public static void sort(Comparable[] a) {
        int n = a.length;
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
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
