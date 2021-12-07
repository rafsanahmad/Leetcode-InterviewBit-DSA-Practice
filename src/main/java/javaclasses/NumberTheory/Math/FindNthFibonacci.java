/*
 * * Find Nth Fibonacci.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.NumberTheory.Math;

public class FindNthFibonacci {
    //https://www.interviewbit.com/problems/find-nth-fibonacci/
    /*Problem Description

Given an integer A you need to find the Ath fibonacci number modulo 10^9 + 7.

The first fibonacci number F1 = 1

The first fibonacci number F2 = 1

The nth fibonacci number Fn = Fn-1 + Fn-2 (n > 2)


Problem Constraints
1 <= A <= 10^9.

Input Format
First argument is an integer A.

Output Format
Return a single integer denoting Ath fibonacci number modulo 10^9 + 7.

Example Input
Input 1:
 A = 4
Input 2:
 A = 3

Example Output
Output 1:
 3
Output 2:
 2

Example Explanation
Explanation 1:
 F3 = F2 + F1 = 1 + 1 = 2
 F4 = F3 + F2 = 2 + 1 = 3

Explanation 2:
 F3 = F2 + F1 = 1 + 1 = 2
*/

    //Gives error for large input
    public int findNthFiboNacciNovice(int A) {
        long result = 0;
        long[] ar = new long[A + 1];
        ar[0] = 0;
        ar[1] = 1;
        for (int i = 2; i <= A; i++) {
            ar[i] = ar[i - 1] + ar[i - 2];
        }
        result = ar[A];
        return (int) (result % 1000000007);
    }

    public int findNthFiboNacciOptimized(int a) {
        if (a == 1)
            return 1;
        long[][] arr = {{1, 1}, {1, 0}};
        long[][] arr1 = {{1, 1}, {1, 0}};
        arr = pow(arr, arr1, a - 1);
        // System.out.println(Arrays.toString(arr[0]) + ":" + Arrays.toString(arr[1]));
        return (int) (arr[0][0] % 1000000007);
    }

    public long[][] pow(long[][] a, long[][] b, int p) {
        if (p == 1)
            return a;
        long[][] r = pow(a, b, p / 2);
        long[][] x = matMul(r, r);
        if (p % 2 == 0)
            return x;
        return matMul(x, b);
    }

    public long[][] matMul(long[][] r, long[][] a) {
        long j = ((r[0][0] * a[0][0]) % 1000000007 + (r[0][1] * a[1][0]) % 1000000007) % 1000000007;
        long k = ((r[0][0] * a[0][1]) % 1000000007 + (r[0][1] * a[1][1]) % 1000000007) % 1000000007;
        long l = ((r[1][0] * a[0][0]) % 1000000007 + (r[1][1] * a[1][0]) % 1000000007) % 1000000007;
        long m = ((r[1][0] * a[0][1]) % 1000000007 + (r[1][1] * a[1][1]) % 1000000007) % 1000000007;
        r[0][0] = j;
        r[0][1] = k;
        r[1][0] = l;
        r[1][1] = m;
        return r;
    }

    public static void main(String[] args) {
        FindNthFibonacci fibonacci = new FindNthFibonacci();
        System.out.println(fibonacci.findNthFiboNacciNovice(100));
        System.out.println(fibonacci.findNthFiboNacciOptimized(100));
    }
}
