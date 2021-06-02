package javaclasses.Array;

//Leetcode = 179
/*
Given a list of non-negative integers nums, arrange them such that they form the largest number.

Note: The result may be very large, so you need to return a string instead of an integer.


Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"
Example 3:

Input: nums = [1]
Output: "1"
Example 4:

Input: nums = [10]
Output: "10"
* */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        String result = "";

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                String str1 = "" + nums[i] + nums[j];
                String str2 = "" + nums[j] + nums[i];
                //Swap two number if ji > ij
                if (str2.compareTo(str1) > 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            result = result + nums[i];
        }
        String replaceZero = result.replaceAll("0", "");
        if (replaceZero.length() == 0) {
            return "0";
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {3, 30, 34, 5, 9};
        LargestNumber ln = new LargestNumber();
        String res = ln.largestNumber(arr);
        System.out.println(res);
    }
}
