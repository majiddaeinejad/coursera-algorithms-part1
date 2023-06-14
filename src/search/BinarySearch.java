package search;

public class BinarySearch {
    public int search(int[] arr, int key){
        if(arr.length==0)
            return -1;
        int lo = 0;
        int hi = arr.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < arr[mid])
                hi = mid - 1;
            else if(key>arr[mid])
                lo = mid + 1;
            else
                return mid;

        }
        return -1;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        BinarySearch bs = new BinarySearch();
        int result = bs.search(arr, 10);
        System.out.print(result);

    }
}
