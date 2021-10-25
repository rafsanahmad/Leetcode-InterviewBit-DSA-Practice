/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;

public class PickFromBothSides {

    /*Given an integer array A of size N.

You can pick B elements from either left or right end of the array A to get maximum sum.

Find and return this maximum possible sum.

NOTE: Suppose B = 4 and array A contains 10 elements then

You can pick first four elements or can pick last four elements or can pick 1 from front and 3 from back etc .
you need to return the maximum possible sum of elements you can pick.

Input Format
First argument is an integer array A.
Second argument is an integer B.

Output Format
Return an integer denoting the maximum possible sum of elements you picked.

Example Input
Input 1:
 A = [5, -2, 3 , 1, 2]
 B = 3

Input 2:
 A = [1, 2]
 B = 1

Example Output
Output 1:
 8

Output 2:
 2

Example Explanation
Explanation 1:
 Pick element 5 from front and element (1, 2) from back so we get 5 + 1 + 2 = 8

Explanation 2:
 Pick element 2 from end as this is the maximum we can get
*/

    /* Approach: To solve the problem more efficiently we will implement Sliding Window concept.

1. Initialize two integers with 0, curr_points and max_points to represents current points and maximum points
respectively.
2. Now, iterate over K elements one by one from the beginning and form the window of size K, also update the value
of curr_points by curr_points + arr[i] and max_points with the value of curr_points.
3. After that in each step, take one element from the end of the array and remove the rightmost element from the
previously selected window with beginning elements where the window size always remains K.
4. Update the values for curr_points and max_points accordingly.
5. At last, we have K elements from the end of the array, and max_points contains the required result that has to be
returned.*/

    public int solve(ArrayList<Integer> A, int B) {
        // Initialize variables
        int curr_points = 0;
        int max_points = 0;

        int size = A.size();
        // Iterate over first B element of array and update the value for curr_points
        for (int i = 0; i < B; i++)
            curr_points += A.get(i);

        // Update value for max_points
        max_points = curr_points;

        // j points to the end of the array
        int j = size - 1;

        for (int i = B - 1; i >= 0; i--) {
            curr_points = curr_points + A.get(j) - A.get(i);
            max_points = Math.max(curr_points, max_points);
            j--;
        }

        // Return the final result
        return max_points;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(-2);
        list.add(3);
        list.add(1);
        list.add(2);
        System.out.println(new PickFromBothSides().solve(list, 3));
    }
}
