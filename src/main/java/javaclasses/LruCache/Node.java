/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

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
