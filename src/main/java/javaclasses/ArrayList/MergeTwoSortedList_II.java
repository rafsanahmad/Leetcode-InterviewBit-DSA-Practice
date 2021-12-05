/*
 * * Merge Two Sorted List_II.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.ArrayList;

import java.util.ArrayList;

public class MergeTwoSortedList_II {
    //https://www.interviewbit.com/problems/merge-two-sorted-lists-ii/
    /*Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note: You have to modify the array A to contain the merge of A and B. Do not output anything in your code.

TIP: C users, please malloc the result into a new array and return the result.

If the number of elements initialized in A and B are m and n respectively,
the resulting size of array A after your code is executed should be m + n

Example :

Input :
         A : [1 5 8]
         B : [6 9]

Modified A : [1 5 6 8 9]*/

    //Approach - Using Two Pointer
    //Time complexity = O(m+n)
    //Space complexity = O(1)
    public void merge(ArrayList<Integer> A, ArrayList<Integer> B) {
        int i = 0, j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) > B.get(j)) {
                A.add(i, B.get(j));
                j++;
            }
            i++;
        }
        while (j < B.size()) {
            A.add(B.get(j));
            j++;
        }
    }

    public static void main(String[] args) {
        MergeTwoSortedList_II list = new MergeTwoSortedList_II();
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        A.add(-4);
        A.add(3);
        A.add(90);
        B.add(0);
        B.add(-2);
        B.add(-2);
        B.add(9);
        //A = [-4,3,90]
        //B = [0,-2,-2,9]
        //Merged = [-4, 0, -2, -2, 3, 9, 90]
        list.merge(A, B);
        System.out.println("Merged List: " + A);
    }
}
