/*
 * *
 *  * Segment Tree.java
 *  * Created by Rafsan Ahmad on 3/26/23, 1:15 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Algorithm;

public class SegmentTree {
    /*Let us consider the following problem to understand Segment Trees.
We have an array arr[0 . . . n-1]. We should be able to

Find the sum of elements from index l to r where 0 <= l <= r <= n-1
Change the value of a specified element of the array to a new value x. We need to do arr[i] = x where 0 <= i <= n-1.

Representation of Segment trees
Leaf Nodes are the elements of the input array.
Each internal node represents some merging of the leaf nodes. The merging may be different for different problems.
For this problem, merging is sum of leaf nodes under a node.
An array representation of tree is used to represent Segment Trees. For each node at index i, the left child is at
index (2*i+1), right child at (2*i+2) and the parent is at  (⌊(i – 1) / 2⌋).

Construction of Segment Tree from the given array:
We start with a segment arr[0 . . . n-1]. and every time we divide the current segment into two (if it has not yet
 become a segment of length 1), and then call the same procedure on both halves, and for each such segment, we store
 the sum in the corresponding node.
All levels of the constructed segment tree will be completely filled except the last level. Also, the tree will be a
Full Binary Tree because we always divide segment in two, at every level. Since the constructed tree is always a full
binary tree with n leaves, there will be n-1 internal nodes. So the total number of nodes will be 2*n – 1.

What is the height of the segment tree for a given array:
Height of the segment tree will be ⌈log₂N⌉. Since the tree is represented using array and relation between parent and
child indexes must be maintained, size of memory allocated for segment tree will be (2 * 2⌈log2n⌉  – 1).

Query for Sum of a given range
Once the tree is constructed, how to get the sum using the constructed segment tree. The following is the algorithm to
get the sum of elements.

int getSum(node, l, r)
{
   if the range of the node is within l and r
        return value in the node
   else if the range of the node is completely outside l and r
        return 0
   else
    return getSum(node's left child, l, r) +
           getSum(node's right child, l, r)
}
In the above implementation, there are three cases we need to take into consideration

If the range of the current node while traversing the tree is not in the given range then did not add the value of
that node in ans
If the range of node is partially overlapped with the given range then move either left or right according to the
overlapping
If the range is completely overlapped by the given range then add it to the ans
Update a value:
Like tree construction and query operations, the update can also be done recursively. We are given an index which
needs to be updated. Let diff be the value to be added. We start from the root of the segment tree and add diff to
all nodes which have given index in their range. If a node doesn’t have a given index in its range, we don’t make any
 changes to that node.

The algorithmic steps to implement a segment tree are:
Initialize the segment tree with a size equal to 4 * n, where n is the number of elements in the array.
In the buildTree function, the base case is when the left and right bounds of the current segment are equal.
In this case, the value of the current node in the segment tree is set to the value of the corresponding element in
the array.
For the rest of the cases, calculate the midpoint of the current segment and recursively call the buildTree function
for the left and right subsegments.
In the query function, the base case is when the current segment is completely contained within the query range.
In this case, the value of the current node in the segment tree is returned.
For the rest of the cases, calculate the midpoint of the current segment and recursively call the query function
for the left and right subsegments. The minimum (or maximum, or sum, etc.) of the values returned from the left and
right subsegments is returned.
The query function can be called with the left and right bounds of the desired range to get the desired result.
*/


    int st[]; // The array that stores segment tree nodes

    /* Constructor to construct segment tree from given array. This
       constructor  allocates memory for segment tree and calls
       constructSTUtil() to  fill the allocated memory */
    SegmentTree(int arr[], int n) {
        // Allocate memory for segment tree
        //Height of segment tree
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));

        //Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        st = new int[max_size]; // Memory allocation

        constructSTUtil(arr, 0, n - 1, 0);
    }

    // A utility function to get the middle index from corner indexes.
    int getMid(int s, int e) {
        return s + (e - s) / 2;
    }

    /*  A recursive function to get the sum of values in given range
        of the array.  The following are parameters for this function.

      st    --> Pointer to segment tree
      si    --> Index of current node in the segment tree. Initially
                0 is passed as root is always at index 0
      ss & se  --> Starting and ending indexes of the segment represented
                    by current node, i.e., st[si]
      qs & qe  --> Starting and ending indexes of query range */
    int getSumUtil(int ss, int se, int qs, int qe, int si) {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return st[si];

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = getMid(ss, se);
        return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    /* A recursive function to update the nodes which have the given
       index in their range. The following are parameters
        st, si, ss and se are same as getSumUtil()
        i    --> index of the element to be updated. This index is in
                 input array.
       diff --> Value to be added to all nodes which have i in range */
    void updateValueUtil(int ss, int se, int i, int diff, int si) {
        // Base Case: If the input index lies outside the range of this segment
        if (i < ss || i > se)
            return;

        // If the input index is in range of this node, then update the value of the node and its children
        st[si] = st[si] + diff;
        if (se != ss) {
            int mid = getMid(ss, se);
            updateValueUtil(ss, mid, i, diff, 2 * si + 1);
            updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
        }
    }

    // The function to update a value in input array and segment tree.
    // It uses updateValueUtil() to update the value in segment tree
    void updateValue(int arr[], int n, int i, int new_val) {
        // Check for erroneous input index
        if (i < 0 || i > n - 1) {
            System.out.println("Invalid Input");
            return;
        }

        // Get the difference between new value and old value
        int diff = new_val - arr[i];

        // Update the value in array
        arr[i] = new_val;

        // Update the values of nodes in segment tree
        updateValueUtil(0, n - 1, i, diff, 0);
    }

    // Return sum of elements in range from index qs (query start) to qe (query end).  It mainly uses getSumUtil()
    int getSum(int n, int qs, int qe) {
        // Check for erroneous input values
        if (qs < 0 || qe > n - 1 || qs > qe) {
            System.out.println("Invalid Input");
            return -1;
        }
        return getSumUtil(0, n - 1, qs, qe, 0);
    }

    // A recursive function that constructs Segment Tree for array[ss..se].
    // si is index of current node in segment tree st
    int constructSTUtil(int arr[], int ss, int se, int si) {
        // If there is one element in array, store it in current node of segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then recur for left and
        // right subtrees and store the sum of values in this node
        int mid = getMid(ss, se);
        st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                constructSTUtil(arr, mid + 1, se, si * 2 + 2);
        return st[si];
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTree tree = new SegmentTree(arr, n);

        // Build segment tree from given array
        // Print sum of values in array from index 1 to 3
        System.out.println("Sum of values in given range = " + tree.getSum(n, 1, 3));

        // Update: set arr[1] = 10 and update corresponding segment tree nodes
        tree.updateValue(arr, n, 1, 10);

        // Find sum after the value is updated
        System.out.println("Updated sum of values in given range = " + tree.getSum(n, 1, 3));
    }
}
