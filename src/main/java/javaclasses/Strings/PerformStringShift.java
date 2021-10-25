/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given a string s containing lowercase English letters, and a matrix shift,
where shift[i] = [direction, amount]:

 ** Direction can be 0 (for left shift) or 1 (for right shift). 
 ** Amount is the amount by which string s is to be shifted.
 ** A left shift by 1 means remove the first character of s and append it to the end.
 ** Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.

    Return the final string after all operations.


 For Example - 
 
 Example 1:
 
  Input: s = "abc", shift = [[0,1], [1,2]]
  Output: "cab"

  Explanation: 
   [0,1] means shift to left by 1. "abc"  ==  "bca"
   [1,2] means shift to right by 2. "bca" == "cab"
  
   
   
 Example 2:
 
  Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
  Output: "efgabcd"
  
  
  Explanation:  
   [1,1] means shift to right by 1. "abcdefg" == "gabcdef"
   [1,1] means shift to right by 1. "gabcdef" == "fgabcde"
   [0,2] means shift to left by 2. "fgabcde" == "abcdefg"
   [1,3] means shift to right by 3. "abcdefg" == "efgabcd"
 
This problem is the day 14 challenge of LeetCode 30 day challenge.*/

/*LeetCode 1427*/

/*Important point -
Equal number of left shift cancels equal number right shift operations
Based on this observation instead of doing right and left shift everytime, whenever we encounter zero (left shift)
and amount to rotate count and when we encounter 1 subtract it from rotate count.

After that if the result is positive it means we have to do the left shift by that amount
simialrly, if the result is negative it means we have to do that right shift by that amount.

*/

public class PerformStringShift {

    public static String stringShiftOpt(String s, int[][] shift) {
        int rotateCount = 0;
        for (int[] value : shift) {
            if (value[0] == 0) {
                rotateCount += value[1];
            } else {
                rotateCount -= value[1];
            }
        }

        //Efective rotation
        rotateCount = rotateCount % s.length();
        if (rotateCount > 0) {
            s = leftShift(s, rotateCount);
        } else if (rotateCount < 0) {
            s = rightShift(s, -(rotateCount));
        }
        return s;
    }

    public static String leftShift(String s, int num) {
        return s.substring(num) + s.substring(0, num);
    }

    public static String rightShift(String s, int num) {
        return s.substring(s.length() - num) + s.substring(0, s.length() - num);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        int[][] shift = {{1, 1}, {1, 1}, {0, 2}, {1, 3}};
        System.out.println(stringShiftOpt(s, shift));
    }
}