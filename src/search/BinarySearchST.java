package search;

/**
 * The BinarySearchST class represents a symbol table implemented using binary search
 * 1. Initialize the symbol table with an initial capacity or the provided capacity.
 * 2. Implement resizing of the symbol table when necessary to accommodate more elements.
 * 3. Keep track of the number of key-value pairs in the symbol table.
 * 4. Provide methods to retrieve the size of the symbol table and check if it is empty.
 * 5. Implement a binary search algorithm to find the rank of a given key in the symbol table.
 * 6. Implement a method to get the value associated with a given key.
 *    - Check for null key argument and return null if the symbol table is empty.
 *    - Use binary search to find the rank of the key in the symbol table.
 *    - If the key is found, return the associated value.
 *    - If the key is not found, return null.
 * 7. Implement a method to insert a new key-value pair into the symbol table.
 *    - Check for null key argument and throw an exception if found.
 *    - If the key already exists in the table, update its associated value.
 *    - If the symbol table is full, resize it to accommodate more elements.
 *    - Shift all keys and values after the insertion point to the right.
 *    - Insert the new key and value at the appropriate position and increment the size.
 * 8. Implement a method to delete a key-value pair from the symbol table.
 *    - Check for null key argument and throw an exception if found.
 *    - If the symbol table is empty, return.
 *    - Find the rank of the key in the symbol table using binary search.
 *    - If the key is not found, return.
 *    - Shift all keys and values after the deletion point to the left.
 *    - Decrement the size and set the last position to null to avoid loitering.
 *    - If the symbol table is 1/4 full, resize it to reduce the capacity.
 * Note: These are general steps to understand the logic of the code. The actual implementation may include additional optimizations and error handling.
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Object[] keys;
    private Object[] vals;
    private int n = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = new Object[capacity];
        vals = new Object[capacity];
    }

    private void resize(int capacity) {
        Object[] tempk = new Object[capacity];
        Object[] tempv = new Object[capacity];

        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        keys = tempk;
        vals = tempv;

    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < n && key.compareTo((Key) keys[i]) == 0)
            return (Value) vals[i];
        return null;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            @SuppressWarnings("unchecked")
            int cmp = key.compareTo((Key) keys[mid]);
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    @SuppressWarnings("unchecked")
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // key is already in table
        if (i < n && ((Key) keys[i]).compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        if (n == keys.length) {
            resize(2 * keys.length);
        }

        //shift right all keys and values after i
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;

    }

    @SuppressWarnings("unchecked")
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || ((Key) keys[i]).compareTo(key) != 0) {
            return;
        }

        //shift left all keys and values after i
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        n--;
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4)
            resize(keys.length / 2);

    }


}
