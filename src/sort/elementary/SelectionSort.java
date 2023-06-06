package sort.elementary;

public class SelectionSort {

    public static void sort(Comparable a[]) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;                  //assume i index is minimum
            for (int j = i; j < N; j++) { //traversal the array to find new minimum
                if (less(a[j], a[min]))   //compare current minimum with this j index
                    min = j;              //Put the j into min if a[j] is less than current min
            }
            exch(a, i, min);              //exchange the a[i] with the new minimum found

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
        Integer[] input = {1, 2, 3, 4, 5, 9, 8, 7, 6};
        sort(input);
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }

    }
}
