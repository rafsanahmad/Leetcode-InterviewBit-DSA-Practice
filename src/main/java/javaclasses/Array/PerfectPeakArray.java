package javaclasses.Array;

public class PerfectPeakArray {
    //https://www.interviewbit.com/problems/perfect-peak-of-array/

/*Given an integer array A of size N.
You need to check that whether there exist a element which is strictly greater than all the elements on
left of it and strictly smaller than all the elements on right of it.

If it exists return 1 else return 0.

NOTE:
Do not consider the corner elements i.e A[0] and A[N-1] as the answer.

Problem Constraints
3 <= N <= 105
1 <= A[i] <= 109

Input Format
First and only argument is an integer array A containing N integers.

Output Format
Return 1 if there exist a element that is strictly greater than all the elements on left of it and strictly
smaller than all the elements on right of it else return 0.

Example Input
Input 1:
 A = [5, 1, 4, 3, 6, 8, 10, 7, 9]

Input 2:
 A = [5, 1, 4, 4]

Example Output
Output 1:
 1
Output 2:
 0

Example Explanation
Explanation 1:
 A[4] = 6 is the element we are looking for.
 All elements on left of A[4] are smaller than it and all elements on right are greater.

Explanation 2:
 No such element exits.*/

    public int perfectPeakUsingTwoPointer(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int result = 0;
        int left;
        int len = A.length;
        int right;
        int maxLeft = A[0];

        for (int i = 1; i < len; i++) {
            if (A[i] > maxLeft) {
                maxLeft = Math.max(A[i], maxLeft);
                left = i;
                right = len - 1;
                while (right >= left) {
                    if (A[right] > maxLeft) {
                        --right;
                    } else {
                        break;
                    }
                }
                if (right == left && right != len - 1 && left != 0) {
                    return 1;
                }
            }
        }
        return result;
    }

    public int perfectPeak(int[] A) {
        int n = A.length;
        int[] right = new int[n];
        int[] left = new int[n];
        left[0] = A[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], A[i]);
        }

        right[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(A[i], right[i + 1]);

        }

        for (int i = 1; i < n - 1; i++) {
            if (A[i] > left[i - 1] && A[i] < right[i + 1])
                return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        PerfectPeakArray array = new PerfectPeakArray();
        int[] ar3 = {5, 1, 4, 3, 6, 8, 10, 7, 9};
        System.out.println(array.perfectPeak(ar3));
    }

}

