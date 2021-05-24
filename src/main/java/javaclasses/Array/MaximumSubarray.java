package javaclasses.Array;

/*Given an array of positive integers and a positive number K. Write a code to find the maximum sum of a subarray of size k.

For example:

Input: {2, 1, 5, 1, 3, 2}, K = 3

Output: 9 {5, 1, 3}

Let’s first see what all subarrays we can form whose size is 3.

i) First subarray is {2, 1, 5} and it’s sum is 8.

ii) Second subarray is {1, 5, 1} and it’s um is 7.

iii) Third subarray is {5, 1,3} and it’s sum is 9.

iv) Fourth subarray is {1, 3, 2} and it’s sum is 6.

Out of all these subarrays of size three, the maximum sum subarray is {5, 1, 3} and it’s sum is 9.*/

/*The efficient way to solve this problem is by using a sliding window approach. Here, the size of the sliding window is equal to k.

To start with sliding window, we have to declare two variables start and end. Initially, both the pointers point at 0th index.
Then, we move end pointer until it is less than or equal to k-1 and keep start pointer as it is.

So, first we compute the sum of first window whose size is equal to k and after that, we increment both start and end pointer by one position.
 Now the sum of next window can be computed by simply adding the new element and removing the previous element from the current window sum.
 At each step we also keep track of the maximum sum obtained so far.

The time complexity of this approach is O(n) and it’s space complexity is O(1).*/


//find the maximum of all subarrays of size k using sliding window

public class MaximumSubarray {

    public static int getSum(int arr[], int k) {
        int start = 0;
        int sum = 0;
        int maxSum = 0;

        for (int end = 0; end < arr.length; end++) {
            sum = sum + arr[end];

            if (end >= k - 1) {
                maxSum = Math.max(sum, maxSum);
                sum = sum - arr[start];
                start++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        int result = getSum(arr, k);
        System.out.println(result);
    }
}

    