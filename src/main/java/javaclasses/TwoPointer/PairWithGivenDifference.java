package javaclasses.TwoPointer;

import java.util.Arrays;

public class PairWithGivenDifference {
    //https://www.interviewbit.com/problems/pair-with-given-difference/
    /*Given an one-dimensional unsorted array A containing N integers.

You are also given an integer B, find if there exists a pair of elements in the array whose difference is B.
Return 1 if any such pair exists else return 0.

Input Format
First argument is an integer array A of size N.
Second argument is an integer B.

Output Format
Return 1 if any such pair exists else return 0.

Example Input
Input 1:
 A = [5, 10, 3, 2, 50, 80]
 B = 78

Input 2:
 A = [-10, 20]
 B = 30

Example Output
Output 1:
 1

Output 2:
 1

Example Explanation
Explanation 1:

 Pair (80, 2) gives a difference of 78.
Explanation 2:

 Pair (20, -10) gives a difference of 30 i.e 20 - (-10) => 20 + 10 => 30*/

    public int solve(int[] A, int B) {
        int len = A.length - 1;
        int left = 0;
        int right = 1;
        Arrays.sort(A);

        while (left <= len && right <= len) {
            int diff = A[right] - A[left];
            if (left != right && diff == B) {
                return 1;
            } else if (diff < B) right++;
            else left++;
        }

        return 0;
    }

    public static void main(String[] args) {
        PairWithGivenDifference difference = new PairWithGivenDifference();
        int[] arr = {5, 10, 3, 2, 50, 80};
        System.out.println(difference.solve(arr, 40));
    }

}
