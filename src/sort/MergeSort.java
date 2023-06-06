package sort;

import java.util.Arrays;

public class MergeSort {
    // Sorts the array using Merge Sort
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return; // Base case: subarray contains 0 or 1 element
        }

        int mid = lo + (hi - lo) / 2; // Calculate middle index
        sort(a, aux, lo, mid); // Sort left half recursively
        sort(a, aux, mid + 1, hi); // Sort right half recursively
        merge(a, aux, lo, mid, hi); // Merge the sorted halves
    }

    // Sorts the array using Merge Sort
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length]; // Auxiliary array
        sort(a, aux, 0, a.length - 1); // Call private sort method
    }

    // Merges two sorted subarrays into one
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; // Copy elements to auxiliary array
        }

        int i = lo, j = mid + 1; // Pointers for left and right subarrays

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++]; // Left subarray is exhausted
            } else if (j > hi) {
                a[k] = aux[i++]; // Right subarray is exhausted
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++]; // Element in right subarray is smaller
            } else {
                a[k] = aux[i++]; // Element in left subarray is smaller
            }
        }
    }

    // Compares two objects
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // Returns true if v < w
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        Integer[] input = {2, 1, 3, 4, 5, 9, 8, 7, 6};
        sort(input); // Sort the input array
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + " "); // Print the sorted array
        }
    }
}
