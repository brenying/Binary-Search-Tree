package week.pkg11.trees;

public class Tree<T extends Comparable<T>> {
    private Node<T> root;

    public Tree() {
        root = null;
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node<>();
            root.keys[0] = value;
            root.numKeys = 1;
        } 
        else {
            SplitResult<T> result = insertRecursive(root, value);
            if (result != null) {
                Node<T> newRoot = new Node<>();
                newRoot.keys[0] = result.middleKey;
                newRoot.children[0] = result.left;
                newRoot.children[1] = result.right;
                newRoot.numKeys = 1;
                newRoot.isLeaf = false;
                root = newRoot;
            }
            
        }
    }

    private SplitResult<T> insertRecursive(Node<T> node, T value) {
        if (node.isLeaf) {
            insertIntoNode(node, value, null, null);
            if (node.isFull()) {
                return splitNode(node);
            }
            return null;
        }
        int index = 0;
        while (index < node.numKeys && value.compareTo(node.keys[index]) > 0) {
            index++;
        }

        SplitResult<T> result = insertRecursive(node.children[index], value);

        if (result != null) {
            insertIntoNode(node, result.middleKey, result.left, result.right);
            if (node.isFull()) {
                return splitNode(node);
            }
        }

        return null;
    }

    private void insertIntoNode(Node<T> node, T key, Node<T> left, Node<T> right) {
        int i = node.numKeys - 1;

        while (i >= 0 && key.compareTo(node.keys[i]) < 0) {
            node.keys[i + 1] = node.keys[i];
            if (!node.isLeaf) {
                node.children[i + 2] = node.children[i + 1];
            }
            i--;
        }

        node.keys[i + 1] = key;
        if (!node.isLeaf) {
            node.children[i + 1] = left;
            node.children[i + 2] = right;
        }

        node.numKeys++;
        node.isLeaf = (node.children[0] == null);
    }

    private SplitResult<T> splitNode(Node<T> node) {
        T middleKey = node.keys[1];

        Node<T> left = new Node<>();
        Node<T> right = new Node<>();

        left.keys[0] = node.keys[0];
        left.numKeys = 1;

        right.keys[0] = node.keys[2];
        right.numKeys = 1;

        left.isLeaf = right.isLeaf = node.isLeaf;

        if (!node.isLeaf) {
            left.children[0] = node.children[0];
            left.children[1] = node.children[1];
            right.children[0] = node.children[2];
            right.children[1] = node.children[3];
            left.isLeaf = right.isLeaf = false;
        }

        return new SplitResult<>(middleKey, left, right);
    }
    public void printTree() {
        printRecursive(root, 0);
    }

    private void printRecursive(Node<T> node, int level) {
        if (node == null) return;

        for (int i = 0; i < level; i++) System.out.print("   ");
        System.out.print("[ ");
        for (int i = 0; i < node.numKeys; i++) {
            System.out.print(node.keys[i] + " ");
        }
        System.out.println("]");

        for (int i = 0; i <= node.numKeys; i++) {
            if (node.children[i] != null) {
                printRecursive(node.children[i], level + 1);
            }
        }
    }
}

class SplitResult<T extends Comparable<T>> {
    public T middleKey;
    public Node<T> left, right;

    public SplitResult(T middleKey, Node<T> left, Node<T> right) {
        this.middleKey = middleKey;
        this.left = left;
        this.right = right;
    }
}
