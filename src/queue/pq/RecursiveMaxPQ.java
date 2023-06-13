package queue.pq;

/**
 * 1. Create a max heap priority queue data structure.
 * 2. The data structure should have an array to store the elements and a variable to keep track of the number of elements.
 * 3. Implement a method to check if the priority queue is empty.
 * 4. Implement a method to insert an element into the priority queue.
 * 5. After inserting the element, maintain the max heap property by comparing the element with its parent and swapping if necessary.
 * 6. Implement a method to extract the maximum element from the priority queue.
 * 7. Replace the root element with the last element in the heap and decrement the size of the priority queue.
 * 8. Restore the max heap property by comparing the new root element with its children and swapping if necessary.
 * 9. Implement a method to build a max heap from an array of elements.
 * 10. Starting from the last non-leaf node, iterate down and call the heapify operation for each node to ensure the max heap property is satisfied.
 * 11. Implement the heapify operation to maintain the max heap property at a given index.
 * 12. Compare the element at the index with its children and find the largest element.
 * 13. If the largest element is not the current element, swap them and recursively call heapify on the affected child node.
 * 14. Implement a swap operation to exchange two elements in the array.
 * 15. Use these methods and operations to handle insertion, extraction, and maintaining the max heap property of the priority queue.
 * These general steps describe the underlying operations and concepts needed to implement a max heap priority queue according to the book "Introduction to Algorithms."
 */
public class RecursiveMaxPQ {
    private int[] pq;
    private int size;

    public RecursiveMaxPQ(int capacity) {
        pq = new int[capacity];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int key) {
        if (size == pq.length) {
            throw new IllegalStateException("Priority queue is full");
        }

        pq[size] = key;
        size++;

        buildMaxHeap();
    }

    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }

        int max = pq[0];
        pq[0] = pq[size - 1];
        size--;

        heapify(0);

        return max;
    }

    private void buildMaxHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int largestIndex = index;

        if (leftChildIndex < size && pq[leftChildIndex] > pq[largestIndex]) {
            largestIndex = leftChildIndex;
        }

        if (rightChildIndex < size && pq[rightChildIndex] > pq[largestIndex]) {
            largestIndex = rightChildIndex;
        }

        if (largestIndex != index) {
            swap(index, largestIndex);
            heapify(largestIndex);
        }
    }

    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 3, 4, 9, 2, 6, 8, 5};
        RecursiveMaxPQ maxPQ = new RecursiveMaxPQ(arr.length);
        for (int i = 0; i < arr.length; i++) {
            maxPQ.insert(arr[i]);
//            System.out.print(maxPQ.max() + " ");
        }
        System.out.println();
        while(maxPQ.size>0) {
            System.out.print(maxPQ.extractMax() + " ");
        }
    }
}
