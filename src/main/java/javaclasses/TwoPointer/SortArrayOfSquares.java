package javaclasses.TwoPointer;

import java.util.Arrays;

public class SortArrayOfSquares {
    //https://www.interviewbit.com/problems/sort-array-with-squares/
    //Leetcode 977
    /*Problem Description
Given a sorted array A containing N integers both positive and negative.

You need to create another array containing the squares of all the elements in A and return it in non-decreasing
order.

Try to this in O(N) time.

Input Format
First and only argument is an integer array A.

Output Format
Return a integer array as described in the problem above.

Example Input
Input 1:
 A = [-6, -3, -1, 2, 4, 5]

Input 2:
 A = [-5, -4, -2, 0, 1]

Example Output
Output 1:
 [1, 4, 9, 16, 25, 36]

Output 2:
 [0, 1, 4, 16, 25]*/
//Two - pointer
    public int[] sortArraySquare(int[] A) {
        int[] answer = new int[A.length];
        int i = 0;
        int j = A.length - 1;
        int k = A.length - 1;
        while (i <= j) {
            if (Math.abs(A[i]) > Math.abs(A[j])) {
                answer[k--] = A[i] * A[i];
                i++;
            } else {
                answer[k--] = A[j] * A[j];
                j--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {-6, -3, -1, 2, 4, 5};
        System.out.println(Arrays.toString(new SortArrayOfSquares().sortArraySquare(arr)));
    }

}
