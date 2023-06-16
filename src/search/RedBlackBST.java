package search;

/**
 * 1. Define a RedBlackBST class with generic type parameters for the key and value.
 * 2. Define constants for the colors RED and BLACK.
 * 3. Define a nested Node class within the RedBlackBST class to represent individual nodes in the tree. Each node contains the key, value, left and right child nodes, color, and size.
 * 4. Implement methods to check if a node is red, get the size of a node or the entire tree, and check if the tree is empty.
 * 5. Implement a method to get the value associated with a specific key in the tree. It performs a binary search by comparing the key with the keys in the nodes and traversing left or right accordingly.
 * 6. Implement a method to check if a key exists in the tree by calling the get method and checking if the returned value is not null.
 * 7. Implement a private method to perform a right rotation on a given node. This rotation balances the tree by making a left-leaning link lean to the right.
 * 8. Implement a private method to perform a left rotation on a given node. This rotation balances the tree by making a right-leaning link lean to the left.
 * 9. Implement a put method to insert a key-value pair into the tree. It uses a recursive approach to find the appropriate position for the new node and performs necessary rotations and color flips to maintain the Red-Black BST properties.
 * 10. Implement a private method to perform a color flip on a given node and its two children. This operation changes the colors of the node and its children.
 * 11. Implement a private method called balance to restore the Red-Black BST properties after inserting or deleting a node. It performs rotations and color flips to fix any violations.
 * 12. Implement private methods called moveRedLeft and moveRedRight to handle the case where a node is red and both its right child and right child's left child are black. These methods perform necessary rotations and color flips to make the right child or one of its children red.
 * 13. Implement a private method called min to find the node with the minimum key in a given subtree.
 * 14. Implement a private method called deleteMin to delete the node with the minimum key from a given subtree. It recursively moves red nodes to the left and restores balance using the balance method.
 * 15. Implement a delete method to remove a node with the specified key from the tree. It handles different cases based on the color and position of the node, performs rotations and color flips when necessary, and restores balance using the balance method.
 * 16. Implement a height method to calculate the height of the tree. It recursively calculates the height of each subtree and returns the maximum height between the left and right subtrees.
 * These steps outline the main operations and transformations performed in a Red-Black BST without explicitly mentioning the code implementation.
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {

    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.color == RED;
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private int size() {
        return size(root);
    }

    /**
     * Implement a method to get the value associated with a specific key in the tree. It performs a binary search by
     * comparing the key with the keys in the nodes and traversing left or right accordingly.
     */
    private Value get(Node node, Key key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node.val;
        }
        return null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }


    /**
     * - The rotateRight method performs a right rotation on a given node, node.
     * - It assumes that node is not null and that node.left is a red node.
     * - The purpose of the rotation is to balance the tree by making a left-leaning link lean to the right.
     * - The steps involved in the rotation are as follows:
     * 1. Assign the left child of node to a variable, temp.
     * 2. Set the left child of node to be the right child of temp.
     * 3. Set the right child of temp to be node.
     * 4. Copy the color of node to temp.
     * 5. Set the color of node to RED.
     * 6. Update the size of node and temp based on the sizes of their children.
     * 7. Return temp, which becomes the new root of the subtree.
     */
    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;
        temp.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return temp;
    }

    /**
     * - The rotateLeft method performs a left rotation on a given node, node.
     * - It assumes that node is not null and that node.right is a red node.
     * - The purpose of the rotation is to balance the tree by making a right-leaning link lean to the left.
     * - The steps involved in the rotation are as follows:
     * 1. Assign the right child of node to a variable, temp.
     * 2. Set the right child of node to be the left child of temp.
     * 3. Set the left child of temp to be node.
     * 4. Copy the color of node to temp.
     * 5. Set the color of node to RED.
     * 6. Update the size of node and temp based on the sizes of their children.
     * 7. Return temp, which becomes the new root of the subtree.
     */
    private Node rotateLeft(Node node) {
        assert (node != null) && isRed(node.right);
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = RED;
        temp.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return temp;
    }

    /**
     * - The flipColors method is responsible for flipping the colors of a given node, node, and its two children.
     * - The purpose of the color flip is to maintain the Red-Black BST properties by eliminating a two-node with two red children.
     * - The steps involved in the color flip are as follows:
     * 1. Invert the color of node (i.e., change it from RED to BLACK or from BLACK to RED).
     * 2. Invert the color of node's left child.
     * 3. Invert the color of node's right child.
     */
    private void flipColors(Node node) {
        // node must have opposite color of its two children
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }


    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    /**
     * - The put method is responsible for inserting a new key-value pair into the Red-Black BST. It maintains the Red-Black BST properties during the insertion process.
     * - Initially, it performs a standard binary search to find the position where the new node should be inserted.
     * - After inserting the new node, the method checks for three cases to ensure the Red-Black BST properties are maintained:
     * 1. If the right child is red and the left child is black, a left rotation is performed on the current node (rotateLeft method) to fix the violation.
     * 2. If both the left child and its left child are red, a right rotation is performed on the current node (rotateRight method) to fix the violation.
     * 3. If both the left child and right child are red, a color flip is performed on the current node and its children (flipColors method) to fix the violation.
     * - Finally, the size of the current node is updated, and the balanced tree is returned.
     */
    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val;

        // fix-up any right-leaning links
        balance(node);

        return node;
    }


    // restore red-black tree invariant
    private Node balance(Node node) {

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    // Assuming that node is red and both node.right and node.right.left
    // are black, make node.right or one of its children red.
    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }


    /**
     * - The deleteMin method is responsible for deleting the node with the minimum key from a given subtree.
     * - Initially, it checks whether the left child of the current node is null. If it is null, it means the current node is the node with the minimum key in the subtree.
     * - If the left child is not red and its left child is not red, the method performs a moveRedLeft operation (moveRedLeft method) to make the left child or one of its children red.
     * - Then, it recursively calls the deleteMin method on the left child until it finds the node with the minimum key.
     * - After deleting the node with the minimum key, the method restores balance in the subtree using the balance method.
     * - Finally, the balanced subtree is returned.
     */
    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;

        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);

        node.left = deleteMin(node.left);
        return balance(node);
    }

    /**
     * - The delete method is responsible for deleting a node with the specified key from the Red-Black BST.
     * - Initially, it checks whether the specified key is less than or greater than the current node's key to determine which subtree to explore.
     * - If the key is less than the current node's key, the method performs a moveRedLeft operation (moveRedLeft method) to make the left child or one of its children red, ensuring that the current node has at least one red child.
     * - If the key is equal to the current node's key and the right child is null, it means the current node is the node to be deleted. The method simply returns null to remove the node.
     * - If the key is equal to the current node's key, the method finds the minimum key in the right subtree using the min method and replaces the current node's key and value with those of the minimum key node.
     * - The deleteMin method is then called on the right child to delete the minimum key node.
     * - If the key is greater than the current node's key, the method performs a moveRedRight operation (moveRedRight method) to make the right child or one of its children red, ensuring that the current node has at least one red child.
     * - After deleting the node, the method restores balance in the subtree using the balance method.
     * - Finally, the balanced subtree is returned.
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }


    // delete the key-value pair with the given key rooted at node
    private Node delete(Node node, Key key) {
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left))
                node = rotateRight(node);
            if (key.compareTo(node.key) == 0 && (node.right == null))
                return null;
            if (!isRed(node.right) && !isRed(node.right.left))
                node = moveRedRight(node);
            if (key.compareTo(node.key) == 0) {
                Node x = min(node.right);
                node.key = x.key;
                node.val = x.val;
                node.right = deleteMin(node.right);
            } else node.right = delete(node.right, key);
        }
        return balance(node);
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.println(node.key + ": " + node.val);
            traverseInOrder(node.right);
        }
    }

    public static void main(String[] args) {
        // Create a RedBlackBST object
        RedBlackBST<Integer, String> tree = new RedBlackBST<>();

        // Insert key-value pairs into the tree
        tree.put(5, "Apple");
        tree.put(2, "Banana");
        tree.put(8, "Orange");
        tree.put(1, "Grapes");
        tree.put(4, "Mango");
        tree.put(7, "Pineapple");
        tree.put(9, "Watermelon");

        // Get the value associated with a specific key
        String value = tree.get(4);
        System.out.println("Value for key 4: " + value);

        // Check if the tree contains a specific key
        boolean containsKey = tree.contains(7);
        System.out.println("Contains key 7: " + containsKey);

        // Delete a key-value pair from the tree
        tree.delete(2);

        // Get the size of the tree
        int size = tree.size();
        System.out.println("Size of the tree: " + size);

        // Get the height of the tree
        int height = tree.height();
        System.out.println("Height of the tree: " + height);

        tree.traverseInOrder(tree.root);
    }

}
