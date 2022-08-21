/*
 * *
 *  * Count subarrays with equal number of occurrences of two given elements.java
 *  * Created by Rafsan Ahmad on 8/20/22, 4:46 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Hashing;

import java.util.HashMap;
import java.util.Map;

public class CountSubarrayWithEqualOccurences {
    /*Given an array and two integers say, x and y, find the number of subarrays in which the number of occurrences of
    x is equal to the number of occurrences of y.

Examples:

Input : arr[] = {1, 2, 1},
        x = 1, y = 2
Output : 2
The possible sub-arrays have same equal number
of occurrences of x and y are:
1) {1, 2}, x and y have same occurrence(1).
2) {2, 1}, x and y have same occurrence(1).

Input : arr[] = {1, 2, 1},
        x = 4, y = 6
Output : 6
The possible sub-arrays have same equal number of
occurrences of x and y are:
1) {1}, x and y have same occurrence(0).
2) {2}, x and y have same occurrence(0).
3) {1}, x and y have same occurrence(0).
1) {1, 2}, x and y have same occurrence(0).
2) {2, 1}, x and y have same occurrence(0).
3) {1, 2, 1}, x and y have same occurrence(0).

Input : arr[] = {1, 2, 1},
        x = 1, y = 1
Output : 6
The possible sub-arrays have same equal number
of occurrences of x and y are:
1) {1}, x and y have same occurrence(1).
2) {2}, x and y have same occurrence(0).
3) {1}, x and y have same occurrence(1).
1) {1, 2}, x and y have same occurrence(1).
2) {2, 1}, x and y have same occurrence(1).
3) {1, 2, 1}, x and y have same occurrences (2).*/

    /*Solution approach:
    We create two arrays say, countX[] and countY[], which denotes the number of occurrences of x and y, respectively,
    till that point in the array.
    Then, we evaluate another array, say diff which stores (countX[i]-countY[i]),
    i be the index of the array.
    Now, store the count of each element of array diff in a map, say m.
    Initialize result as m[0] since the occurrence of 0 in diff array gives us subarray count where the required
    condition is followed.

Now, iterate through the map and using handshake formula, update the result, since two same values in diff array
indicate that the subarray contains the same number of occurrences of x and y.

Explanation:
arr[] = {1, 2, 2, 3, 4, 1};
x = 2, y = 3;

Two arrays countX[] and countY[] are be evaluated as-
countX[] = {0, 1, 2, 2, 2, 2};
countY[] = {0, 0, 0, 1, 1, 1};

Hence, diff[] = {0, 1, 2, 1, 1, 1};
(diff[i] = countX[i]-countY[i], i be the index of array)

Now, create a map and store the count of each element of diff in it,
so, finally, we get-
m[0] = 1, m[1] = 4, m[2] = 1;

Initialize result as m[0]
i.e result = m[0] = 1

Further, using handshake formula, updating the
result as follows-
result  = result + (1*(1-1))/2 = 1 + 0 = 1
result  = result + (4*(4-1))/2 = 1 + 6 = 7
result  = result + (1*(1-1))/2 = 7 + 0 = 7

so, the final result will be 7, required subarrays having
same number of occurrences of x and y.
 */

    /*
    Time Complexity – O(N^2)
    Auxiliary Space – O(N)
    */
    static int equalOccurrenceBruteForce(int[] arr, int n, int x, int y) {
        int result = 0;

        // Check for each subarray for the required condition
        for (int i = 0; i <= n - 1; i++) {
            int ctX = 0, ctY = 0;
            for (int j = i; j <= n - 1; j++) {
                if (arr[j] == x)
                    ctX += 1;
                else if (arr[j] == y)
                    ctY += 1;
                if (ctX == ctY)
                    result += 1;
            }
        }

        return result;
    }

    /*
    Time Complexity – O(N)
    Auxiliary Space – O(N)
    */
    static int equalOccurrence(int[] arr, int n, int x, int y) {
        int[] countX = new int[n];
        int[] countY = new int[n];

        Map<Integer, Integer> map = new HashMap<>();

        // To store counts of same diff Count occurrences of x and y
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                if (i != 0)
                    countX[i] = countX[i - 1] + 1;
                else
                    countX[i] = 1;
            } else {
                if (i != 0)
                    countX[i] = countX[i - 1];
                else
                    countX[i] = 0;
            }
            if (arr[i] == y) {
                if (i != 0)
                    countY[i] = countY[i - 1] + 1;
                else
                    countY[i] = 1;
            } else {
                if (i != 0)
                    countY[i] = countY[i - 1];
                else
                    countY[i] = 0;
            }

            // Increment count of current
            if (map.containsKey(countX[i] - countY[i])) {
                map.put(countX[i] - countY[i], map.get(countX[i] - countY[i]) + 1);
            } else {
                map.put(countX[i] - countY[i], 1);
            }
        }

        // Traverse map and compute result.
        int result = map.get(0);
        for (Map.Entry<Integer, Integer> it : map.entrySet())
            result = result + ((it.getValue()) * ((it.getValue()) - 1)) / 2;

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 1};
        int n = arr.length;
        int x = 2, y = 3;
        System.out.println(equalOccurrence(arr, n, x, y));

        int[] arr2 = {1, 2, 1};
        System.out.println(equalOccurrence(arr2, 3, 4, 6));
        System.out.println(equalOccurrenceBruteForce(arr2, 3, 4, 6));
    }
}
