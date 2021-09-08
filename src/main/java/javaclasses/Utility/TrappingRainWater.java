package javaclasses.Utility;

public class TrappingRainWater {
    //Leetcode 42
    //res/rainwatertrap.png
    /*
    * Given n non-negative integers representing an elevation map where the width of each bar is 1,
    * compute how much water it can trap after raining.

    Example 1:
    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
    * In this case, 6 units of rain water (blue section) are being trapped.
    Example 2:

    Input: height = [4,2,0,3,2,5]
    Output: 9
 */

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int len = height.length;
        int result = 0;
        int left = 0;
        int right = len - 1;
        int leftMax = 0;
        int rightMax = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    result = result + (leftMax - height[left]);
                }
                left++;
            } else if (height[right] < height[left]) {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    result = result + (rightMax - height[right]);
                }
                right--;
            }
        }

        return result;
    }
}
