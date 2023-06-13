package sort.heap;

/**
 * 1. Build a MaxHeap from the input array:
 *    - Start by considering the input array as a binary tree, where each element represents a node.
 *    - To build the MaxHeap, iterate from the last non-leaf node to the first element of the array.
 *    - For each node, compare its value with its children and swap them if necessary to ensure that the parent node is greater than both its children.
 *    - Continue this process until the entire array forms a MaxHeap.
 * 2. Extract the maximum element and restore the MaxHeap property:
 *    - The maximum element in the MaxHeap is located at the root, which is the first element of the array.
 *    - Swap this element with the last element of the heap (last element of the array).
 *    - Reduce the size of the heap by one to exclude the last element, which is now in its final sorted position.
 *    - Restore the MaxHeap property by "sinking" the new root element down the tree.
 *    - Compare the new root with its children, and swap it with the larger child if necessary, repeating this process until the heap property is satisfied.
 * 3. Repeat step 2 until all elements are extracted:
 *    - Continue the extraction process by repeatedly extracting the maximum element and restoring the MaxHeap property.
 *    - Each extraction will place the next largest element in its correct sorted position.
 *    - Repeat this process until all elements are extracted from the heap.
 * 4. The extracted elements form a sorted array:
 *    - Once all elements are extracted, the array will contain the input elements in sorted order.
 *
 */
public class RecursiveHeapSort {


    private static void heapSort(int[] arr) {
        int n = arr.length;
        buildMaxHeap(arr, n);

        // Extract elements from the heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to the end
            swap(arr, 0, i);

            // Heapify the reduced heap
            heapify(arr, i, 0);
        }

    }

    private static void buildMaxHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = {27, 17, 3, 16, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0};
        heapSort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
