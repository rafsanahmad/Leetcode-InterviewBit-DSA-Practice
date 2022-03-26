/*
 * *
 *  * Count Smaller Number After Self.java
 *  * Created by Rafsan Ahmad on 3/26/22, 9:25 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CountSmallerNumberAfterSelf {
    //Leetcode 315
    /*You are given an integer array nums and you have to return a new counts array.
    The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example 1:
Input: nums = [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

Example 2:
Input: nums = [-1]
Output: [0]

Example 3:
Input: nums = [-1,-1]
Output: [0,0]
*/
    //Time Limit Exceeded
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        List<Integer> sortedList = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            int val = nums[i];
            sortedList.add(val);
            Collections.sort(sortedList);
            int insertPos = find_insert_index(sortedList, val);
            result[i] = insertPos;
        }
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    // Function to find insert position of K
    public int find_insert_index(List<Integer> list, int K) {
        // Lower and upper bounds
        int start = 0;
        int end = list.size() - 1;
        // Traverse the search space
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < K)
                start = mid + 1;
            else
                end = mid - 1;
        }
        // Return insert position
        return end + 1;
    }

    //Optimized Approach
    //res/Count-of-Smaller-Numbers-After-Self.png
    /*(Use Self Balancing BST)
    A Self Balancing Binary Search Tree (AVL, Red Black,.. etc) can be used to get the solution in O(nLogn)
    time complexity. We can augment these trees so that every node N contains size the subtree rooted with N.
    We have used AVL tree in the following implementation.
    We traverse the array from right to left and insert all elements one by one in an AVL tree.
    While inserting a new key in an AVL tree, we first compare the key with root. If key is greater than root,
    then it is greater than all the nodes in left subtree of root. So we add the size of left subtree to the
    count of smaller element for the key being inserted. We recursively follow the same approach for all nodes
    down the root.*/

    class Node {
        Node left;
        Node right;

        int value;
        int count;
        int numLeft;

        public Node(int value) {
            this.value = value;
        }

    }

    public List<Integer> countSmallerOptimized(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Node root = new Node(nums[nums.length - 1]);
        root.count = 1;
        result.add(0);

        for (int i = nums.length - 2; i >= 0; i--) {
            result.add(insertNode(root, nums[i]));
        }
        Collections.reverse(result);
        return result;
    }

    public int insertNode(Node root, int value) {
        Node p = root;
        int result = 0;

        while (p != null) {
            if (value > p.value) {
                result += p.count + p.numLeft;
                if (p.right == null) {
                    Node t = new Node(value);
                    t.count = 1;
                    p.right = t;
                    return result;
                } else {
                    p = p.right;
                }
            } else if (value == p.value) {
                p.count++;
                return result + p.numLeft;
            } else {
                p.numLeft++;

                if (p.left == null) {
                    Node t = new Node(value);
                    t.count = 1;
                    p.left = t;
                    return result;
                } else {
                    p = p.left;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        CountSmallerNumberAfterSelf numberAfterSelf = new CountSmallerNumberAfterSelf();
        int[] arr = {5, 2, 6, 1};
        System.out.println(numberAfterSelf.countSmaller(arr));
        System.out.println(numberAfterSelf.countSmallerOptimized(arr));
    }
}
