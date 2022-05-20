/*
 * *
 *  * JumpGame II.java
 *  * Created by Rafsan Ahmad on 5/20/22, 11:19 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

public class JumpGame_II {
    //Leetcode 45
    /*Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.



Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the
last index.

Example 2:
Input: nums = [2,3,0,1,4]
Output: 2
*/

    public int jump(int[] nums) {
        int current = 0;
        int next = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > current) {
                current = next;
                result++;
            }
            next = Math.max(next, i + nums[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        JumpGame_II game_ii = new JumpGame_II();
        int[] ar = {2, 3, 0, 1, 4};
        int[] ar2 = {2, 3, 1, 3, 1, 1};
        System.out.println(game_ii.jump(ar));
        System.out.println(game_ii.jump(ar2));
    }
}
