package week.pkg11.trees;

public class Node<T extends Comparable<T>> {
    public T[] keys;
    public Node<T>[] children;
    public boolean isLeaf;
    public int numKeys;

    public Node() {
        keys = (T[]) new Comparable[3];
        children = new Node[4];
        isLeaf = true;
        numKeys = 0;
    }

    public boolean isFull() {
        return numKeys == 3;
    }
}
