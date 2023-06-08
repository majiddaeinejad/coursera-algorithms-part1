package sort;

import java.util.Arrays;

/**
 * 1. Select a pivot element from the array (typically the last element in the array).
 * 2. Partition the array by rearranging its elements such that all elements smaller than the pivot are placed to the left of the pivot, and all elements greater than the pivot are placed to the right of the pivot.
 * 3. Recursively apply steps 1 and 2 to the sub-arrays on the left and right of the pivot.
 * 4. Repeat this process until the sub-arrays have only one element or are empty.
 * 5. At each recursive step, the pivot element is in its final sorted position.
 * 6. Once all recursive calls are complete, the entire array will be sorted in ascending order.
 */
public class QuickSort {

    public static void sort(Comparable a[]) {
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable a[], int lo, int hi) {
        if (lo < hi) {
            int partition = partition(a, lo, hi);
            sort(a, lo, partition - 1);
            sort(a, partition + 1, hi);
        }
    }

    public static int partition(Comparable a[], int lo, int hi) {
        Comparable pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (less(a[j], pivot)) {
                i++;
                exch(a, i, j);
            }
        }
        exch(a, i + 1, hi);
        return i + 1;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
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
