package javaclasses.GreedyAlgorithm;

import java.util.Arrays;

public class DistributeCandy {
    //https://www.interviewbit.com/problems/distribute-candy/
    /*There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

1. Each child must have at least one candy.
2. Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Input Format:

The first and the only argument contains N integers in an array A.
Output Format:

Return an integer, representing the minimum candies to be given.
Example:

Input 1:
    A = [1, 2]

Output 1:
    3

Explanation 1:
    The candidate with 1 rating gets 1 candy and candidate with rating cannot get 1 candy as 1 is its neighbor.
    So rating 2 candidate gets 2 candies. In total, 2 + 1 = 3 candies need to be given out.

Input 2:
    A = [1, 5, 2, 1]

Output 2:
    7

Explanation 2:
    Candies given = [1, 3, 2, 1]
*/

    public int candy(int[] A) {
        int sum = 0;
        int size = A.length;
        int[] left2right = new int[size];
        Arrays.fill(left2right, 1);
        int[] right2left = new int[size];
        Arrays.fill(right2left, 1);

        for (int i = 1; i < size; i++) {
            if (A[i] > A[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = size - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < size; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        DistributeCandy candy = new DistributeCandy();
        int[] arr = {1, 5, 2, 1};
        System.out.println(candy.candy(arr));
    }
}
