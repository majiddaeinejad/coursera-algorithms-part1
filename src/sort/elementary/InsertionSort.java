package sort.elementary;

import edu.princeton.cs.algs4.Insertion;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(Comparable a[]) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--)  //traversal the array from right to left to find object less than right one
                if (less(a[j], a[j - 1]))   //compare if is less then exchange
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
