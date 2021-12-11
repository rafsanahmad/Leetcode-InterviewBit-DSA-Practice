/*
 * * Check If Max Min Heap.java
 *  * Created by Rafsan Ahmad on 11/14/21, 12:19 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Tree;

import java.util.ArrayList;
import java.util.List;

public class CheckIfMaxMinHeap {
    /*Given an integer array, check if it represents min-heap or not.

For example, the first array represents a min-heap, but the second array isn’t as it violate the heap property.

Array 1 = [2,3,4,5,10,15]

          2
       /     \
     3        4
   /   \     /
  5    10   15

Array 1 = [2,10,4,5,3,15]

          2
       /     \
     10       4
   /   \     /  \
  5    3    15

Since the input is an array, the heap’s structural property (all levels except the last level are full)
cannot be violated.
We only have to worry about the Min Heap Property (the priority of a node is at least as large as that of
its parent) for a given problem.

Since array indexing begins from 0, for a given index i of an array of size n, if 2×i + 2 > n is true then A[i]
represents a leaf node. Otherwise, when 2×i + 2 <= n is true, A[i] represents internal heap node.

For instance, consider array [2, 3, 4, 5, 10, 15] of size n = 6. Here, [2, 3, 4] are internal nodes as
2×i + 2 <= 6 is true and [5, 10, 15] are leaf nodes as 2×i + 2 > 6 is true.

Now that we know how to differentiate between a leaf node and an internal node based on the given array index
and the total number of nodes, let’s return to the given problem.

*/
    // Iterative function to check if a given array represents
    // min-heap or not
    public static boolean checkMinHeap(int[] A) {
        // check for all internal nodes that their left child and
        // right child (if present) holds min-heap property or not

        // start with index 0 (the root of the heap)
        for (int i = 0; i <= (A.length - 2) / 2; i++) {
            if (A[i] > A[2 * i + 1] || (2 * i + 2 != A.length && A[i] > A[2 * i + 2])) {
                return false;
            }
        }

        return true;
    }

    // max-heap or not
    //Return "max" if max-heap else return comma separated ints which does not satisfy the condition
    public static String checkMaxHeap(int[] A) {
        boolean checkMax = true;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= (A.length - 2) / 2; i++) {
            if (A[i] < A[2 * i + 1] || (2 * i + 2 != A.length && A[i] < A[2 * i + 2])) {
                checkMax = false;
                list.add(A[i]);
            }
        }
        if (checkMax) return "max";
        else {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                res.append(list.get(i));
                if (i != list.size() - 1) {
                    res.append(",");
                }
            }
            return res.toString();
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6};

        if (checkMinHeap(A)) {
            System.out.println("The given array is a min-heap");
        } else {
            System.out.println("The given array is not a min-heap");
        }

        int[] B = {10, 8, 6, 7, 5, 3, 12};
        System.out.println(checkMaxHeap(B));
    }
}
