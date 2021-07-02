package javaclasses.Array;

/*Given an array of unsorted integers (Positive integers), We have to write a code to find a subarray whose sum is equal to a given sum.

We have to return subarray indexes (start and end index).

For Example –

  Example 1 :

Input : arr = {10, 2, 4, 7, 5}, sum = 13

Output: {1, 3}

The number present from 1st to 3rd indexes are 2, 4, 7. When we add (2 + 4 + 7) it is 13.

Example 2 :

Input : arr = {1, 4, 20, 3, 10, 5}, sum = 33

  Output: {2, 4}

Example 3 :

  Input : arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sum = 15

   Output: {0, 4}

*/

/*In this example, I am explaining two pointer approach to solve this problem in a  single traversal.
 Two pointer approach is also known as sliding window.
 
The idea here is to declare two variables start and end.
Initially, Both pointers point at 0th index. Keep start pointer as it is and start moving end pointer
until the value of sum is less than k. If sum is equal to k then return the indexes.

If the value of sum is greater than k then move start pointer and also subtract the value present at start index from the sum variable.
Keep repeating this process until the value of sum is greater than k.

The time complexity of this approach is O(n).*/

//Two pointer approach to find a subarray whose sum is equal to k.
public class SubarrayGivenSum {

    public static int[] subarraySum(int[] arr, int k) {

        //Declare two variable start and end
        int start = 0;
        int end = 1;

        //In sum variable, Assign the value present at 0th index
        int sum = arr[0];
        int len = arr.length;

        while (end <= len) {

            while (sum > k && start < end - 1) {
                sum = sum - arr[start];
                start++;
            }

            if (sum == k) {
                return new int[]{start, end - 1};
            }

            if (end < len)
                sum = sum + arr[end];

            end++;

        }

        return new int[]{-1};
    }

    public static void main(String[] args) {


        int[] arr = {15, 2, 4, 8, 9, 5, 10, 23};
        int sum = 23;

        int[] result = subarraySum(arr, sum);

        for (int val : result) {
            System.out.print(val + " ");

        }
    }
}


