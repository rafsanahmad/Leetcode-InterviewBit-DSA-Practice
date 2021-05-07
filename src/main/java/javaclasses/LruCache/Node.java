package javaclasses.LruCache;

public class Node {
    int key;
    int value;
    public Node prev;
    public Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
