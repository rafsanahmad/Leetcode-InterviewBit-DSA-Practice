/*
 * *
 *  * Jump Game.java
 *  * Created by Rafsan Ahmad on 12/27/21, 1:00 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Array;

public class JumpGame {
    /*You are given an integer array nums. You are initially positioned at the array's first index, and each
    element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
impossible to reach the last index.
*/
/*Analysis
We can track the maximum index that can be reached. The key to solve this problem is to find:
1) when the current position can not reach next position (return false) , and
2) when the maximum index can reach the end (return true).

The largest index that can be reached is: i + A[i].*/

    public boolean canJump(int[] nums) {
        if (nums.length <= 1)
            return true;

        int max = nums[0]; //max stands for the largest index that can be reached.

        for (int i = 0; i < nums.length; i++) {
            //if not enough to go to next
            if (max <= i && nums[i] == 0)
                return false;

            //update max
            if (i + nums[i] > max) {
                max = i + nums[i];
            }

            //max is enough to reach the end
            if (max >= nums.length - 1)
                return true;
        }

        return false;
    }

    public boolean canJumpApproach2(int[] nums) {
        //it shows at max what index can I reach.
        //initially I can only reach index 0, hence reach = 0
        int reach = 0;

        for (int i = 0; i < nums.length; i++) {
            //at every index we will check if we can least able to
            //reach that particular index.

            //reach >= idx -> great, carry on. Otherwise,
            if (reach < i) return false;

            //now as you can reach this index, it's time to update your reach
            //as at every index, you're getting a new jump length.
            reach = Math.max(reach, i + nums[i]);
        }


        //this means that you reached till the end of the array
        return true;
    }

    public static void main(String[] args) {
        JumpGame game = new JumpGame();
        int[] arr = {2, 3, 1, 1, 4};
        int[] arr2 = {3, 2, 1, 0, 4};
        System.out.println(game.canJump(arr));
        System.out.println(game.canJump(arr2));

        System.out.println(game.canJumpApproach2(arr));
        System.out.println(game.canJumpApproach2(arr2));
    }
}
