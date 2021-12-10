/*
 * * Max Difference.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

/*Given an array of integers, find Maximum difference between two elements such that larger number appears after
the smaller number.
In this tutorial, I am going to discuss multiple approaches and their java code to find maximum difference between
two elements.

For example :

Example 1:

arr = {2, 5, 15, 6, 4}
Output: 13

The difference between 15 and 2 is 13. Element 15 is greater than 2 and it satisfies our condition that larger number
appears after the smaller number.

Example 2:

arr = {7, 9, 5, 6, 13, 2};
Output : 8

The difference between 13 and 5 is 8 (13-5).*/

/*In this approach, instead of taking difference of the picked element with every other element, we can take the
difference with the minimum element found so far.
So we need to keep track of two things:

1) Maximum difference found so far.
2) Minimum number visited so far.*/

/*
 Find Maximum Difference between Two Elements of an Array in 
 O(n) Time Complexity.
*/
public class MaxDifference {

    public static int findMaxDiff(int[] arr) {

        int maxDiff = arr[1] - arr[0];
        int minEle = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if ((arr[i] - minEle) > maxDiff) {
                maxDiff = arr[i] - minEle;
            }

            if (arr[i] < minEle) {
                minEle = arr[i];
            }
        }

        return maxDiff;
    }

    public static void main(String[] args) {

        int arr[] = {7, 9, 5, 6, 13, 2, 15};
        int result = findMaxDiff(arr);

        System.out.println(result);
    }
}

