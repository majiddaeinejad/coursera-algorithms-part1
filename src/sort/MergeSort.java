package sort;


/**
 1. If the array has fewer than two elements, it is already considered sorted. Return the array as the result.
 2. Divide the unsorted array into two roughly equal halves.
 3. Recursively apply "Merge" Sort to each half, until each subarray contains only one element (which is considered sorted).
 4. Merge the two sorted subarrays generated from the previous step into a single sorted subarray.
 - Create an auxiliary array of the same size as the subarray being merged.
 - Copy the elements from the subarray being merged into the auxiliary array.
 - Initialize two pointers, one for each subarray, pointing to the first element of each subarray.
 - Initialize additional pointer for the original array, pointing to the starting index of the subarray being merged.
 - Compare the elements at the current positions of the two subarray pointers and choose the smaller one.
 - Append the smaller element to the original array using the pointer for the original array.
 - Move the pointer of the subarray from which the element was chosen one position ahead.
 - Move the pointer for the original array one position ahead.
 - Repeat the previous three steps until one of the subarrays is completely processed.
 - Copy the remaining elements from the unfinished subarray to the original array using the pointer for the original array.
 5. The subarray is now sorted.
 6. The algorithm terminates, and the entire array is now sorted.

 */
public class MergeSort {
    // Sorts the array using Merge Sort
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) {
            return; // Stop condition(Base case): subarray contains 0 or 1 element
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
                a[k] = aux[j++]; // Left subarray is finished before
            } else if (j > hi) {
                a[k] = aux[i++]; // Right subarray is finsihed before
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
