/*
 * *
 *  * Design HashMap.java
 *  * Created by Rafsan Ahmad on 10/29/23, 7:29 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.HashTable;

public class DesignHashMap {
    /*Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the
map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no
mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.


Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]


Constraints:

0 <= key, value <= 10^6
At most 10^4 calls will be made to put, get, and remove.*/

    class ListNode {
        int key, val;
        ListNode next;

        public ListNode(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }


    // Greater than 10^4 and prime
    static final int size = 19997;
    // Large prime
    static final int mult = 12582919;
    ListNode[] data;

    public DesignHashMap() {
        this.data = new ListNode[size];
    }

    public void put(int key, int value) {
        int h = hash(key);
        ListNode node = data[h];
        if (node != null) {
            //Replace old value
            if (node.key == key) {
                node.val = value;
            } else {
                //Collusion occurs
                //Insert new element at end of list
                while (node.next != null) {
                    node = node.next;
                }

                ListNode newNode = new ListNode(key, value);
                node.next = newNode;
            }
        } else {
            //New node
            ListNode newNode = new ListNode(key, value);
            data[h] = newNode;
        }
    }

    public int get(int key) {
        int h = hash(key);
        ListNode node = data[h];
        while (node != null) {
            if (node.key == key) return node.val;
            node = node.next;
        }

        return -1;
    }

    public void remove(int key) {
        int h = hash(key);
        ListNode node = data[h];
        if (node == null) return;
        if (node.key == key) data[h] = node.next;
        else {
            while (node != null) {
                if (node.next != null && node.next.key == key) {
                    node.next = node.next.next;
                    return;
                }
                node = node.next;
            }
        }
    }

    private int hash(int key) {
        return (int) ((long) key * mult % size);
    }

    public static void main(String[] args) {
        DesignHashMap designHashMap = new DesignHashMap();
        designHashMap.put(1, 1); // The map is now [[1,1]]
        designHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
        System.out.println(designHashMap.get(1));    // return 1, The map is now [[1,1], [2,2]]
        System.out.println(designHashMap.get(3));    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
        designHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
        System.out.println(designHashMap.get(2));    // return 1, The map is now [[1,1], [2,1]]
        designHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
        System.out.println(designHashMap.get(2));    // return -1 (i.e., not found), The map is now [[1,1]]
    }
}
