package sort.heap;

/**
 * Princeton University Heap Sort without using recursive method
 * 1. Build the MaxHeap:
 *    - Start with an array of elements.
 *    - Iterate from the last non-leaf node to the first element of the array.
 *    - For each node, call the `sink` operation to establish the MaxHeap property.
 *    - The `sink` operation compares the node with its children and moves it down the tree until the MaxHeap property is satisfied.
 * 2. Perform the sort:
 *    - Initialize a variable `k` with the size of the array.
 *    - While `k` is greater than 1:
 *      - Swap the first element (root) with the `k`-th element.
 *      - Decrement `k` by 1 to exclude the sorted element.
 *      - Call the `sink` operation on the first element (new root) to restore the MaxHeap property.
 *      - Repeat this process until all elements are sorted.
 * 3. The sorted array is obtained:
 *    - Once the loop in step 2 is completed, the array will contain the elements sorted in ascending order.
 */
public class HeapSort {

    public static void heapSort(Comparable[] arr) {
        int n = arr.length;

        // Build MaxHeap
        for (int k = n/2-1; k >= 0; k--) {
            sink(arr, k, n);
        }

        // Perform the sort
        int k = n - 1;
        while (k > 0) {
            exch(arr, 0, k);
            sink(arr, 0, --k);
        }
    }

    private static void sink(Comparable[] arr, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(arr, j, j+1)) j++;
            if (!less(arr, k, j)) break;
            exch(arr, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void main(String[] args) {
        Integer[] arr = {27, 17, 3, 16, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0};
        heapSort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

}
