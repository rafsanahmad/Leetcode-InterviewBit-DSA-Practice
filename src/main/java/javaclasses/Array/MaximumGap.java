package javaclasses.Array;

public class MaximumGap {
    //Leetcode 164
    /*Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
    If the array contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

Example 1:
Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

Example 2:
Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.*/

    /*We all know that, merge, heap or quick sorting algorithm can achieve no better than O(n Log n) time complexity.
    But for linear time requirement, bucket sort, counting sort and radix sort are often good options.

For this specific problem, bucket sort is not a good option because it usually works well on uniform distributions.
Otherwise, in each bucket, the insertion sort may cause heavy burden on time complexity. Counting sort, has time
complexity O(n + k), where k is the range of the elements. But when k > n^2, it is worse than the common sorting
algorithms. So we use the radix sort for solving this problem.

The idea of radix sort is as follows:

Sort elements according to each digit (from lower to higher).
For each digit, use counting sort.

For example, A = [36 504 79 98 8 55 2 113].

The Radix sorting goes like following steps:

Sort 1st digit:
A = [2, 113, 504, 55, 36, 98, 8, 79]

Sort 2nd digit:
A = [2, 504, 8, 113, 36, 55, 79, 98]

Sort 3rd digit:
A = [2, 8, 36, 55, 79, 98, 113, 504]

Done.

To sort array according to each digit, counting sort is used. Note that, we only need to have a array of size 10 to
store the frequencies of elements. This is because we only consider and sort a single digit in each iteration of
Radix sort.

The general form of counting sort is:
(1) Count the frequencies of each elements.  (count[a[i]] ++, this also considers the duplicates)
(2) Get the index of each number in the output array. (count[i]+= count[i-1])
(3) Put numbers in order. (output[count[a[i]] = a[i], count[a[i]]-- to handle the duplicates)

For radix sort, there is minor modifications, details see the code below.
*/
    int getMax(int[] nums) {
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            m = Math.max(m, nums[i]);
        }
        return m;
    }

    void countSort(int[] nums, int nd) {
        int n = nums.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(nums[i] / nd) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(nums[i] / nd) % 10] - 1] = nums[i];
            count[(nums[i] / nd) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = output[i];
        }
    }

    void radixsort(int[] nums) {
        int max_n = getMax(nums);
        for (int nd = 1; max_n / nd > 0; nd *= 10) {
            countSort(nums, nd);
        }
    }

    int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        radixsort(nums);
        int res = Math.abs(nums[1] - nums[0]);
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > res) {
                res = Math.abs(nums[i] - nums[i - 1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ar = {3, 6, 9, 1};
        System.out.println(new MaximumGap().maximumGap(ar));
    }
}
