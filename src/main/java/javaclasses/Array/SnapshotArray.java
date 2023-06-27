/*
 * *
 *  * SnapshotArray.java
 *  * Created by Rafsan Ahmad on 6/27/23, 5:08 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.Array;

import java.util.TreeMap;

public class SnapshotArray {
    //https://leetcode.com/problems/snapshot-array/
    /*Implement a SnapshotArray that supports the following interface:

SnapshotArray(int length) initializes an array-like data structure with the given length. Initially,
each element equals 0.
void set(index, val) sets the element at the given index to be equal to val.
int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called
snap() minus 1.
int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with
the given snap_id


Example 1:

Input: ["SnapshotArray","set","snap","set","get"]
[[3],[0,5],[],[0,6],[0,0]]
Output: [null,null,0,null,5]
Explanation:
SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
snapshotArr.set(0,5);  // Set array[0] = 5
snapshotArr.snap();  // Take a snapshot, return snap_id = 0
snapshotArr.set(0,6);
snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5


Constraints:

1 <= length <= 5 * 104
0 <= index < length
0 <= val <= 109
0 <= snap_id < (the total number of times we call snap())
At most 5 * 104 calls will be made to set, snap, and get.*/

    TreeMap<Integer, Integer>[] map;
    int currentSnapId;

    public SnapshotArray(int length) {
        map = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            map[i] = new TreeMap<Integer, Integer>();
            map[i].put(0, 0);
        }
        currentSnapId = 0;
    }

    public void set(int index, int val) {
        map[index].put(currentSnapId, val);
    }

    public int snap() {
        return currentSnapId++;
    }

    public int get(int index, int snap_id) {
        return map[index].floorEntry(snap_id).getValue();
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0, 5);  // Set array[0] = 5
        System.out.println(snapshotArr.snap());  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0, 6);
        System.out.println(snapshotArr.get(0, 0));  // Get the value of array[0]
        // with snap_id = 0, return 5
    }
}
