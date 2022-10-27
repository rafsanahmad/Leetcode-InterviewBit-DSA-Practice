/*
 * * All String Permutation.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Recursion;

public class AllStringPermutation {
    //res/permutation_recursion.gif
    /*Below are the permutations of string ABC.
      ABC ACB BAC BCA CBA CAB*/


    /**
     * permutation function
     *
     * @param str string to calculate permutation for
     * @param start   starting index
     * @param end   end index
     */
    /*Time Complexity: O(n*n!) Note that there are n! permutations and it requires O(n) time to print a permutation.
    Auxiliary Space: O(r – l)
    */
    private void permute(String str, int start, int end) {
        if (start == end)
            System.out.print(str + " ");
        else {
            for (int i = start; i <= end; i++) {
                str = swap(str, start, i);
                permute(str, start + 1, end);
                str = swap(str, start, i);
            }
        }
    }

    /**
     * Swap Characters at position
     *
     * @param a string value
     * @param i position 1
     * @param j position 2
     * @return swapped string
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        String str = "ABC";
        int n = str.length();
        AllStringPermutation permutation = new AllStringPermutation();
        permutation.permute(str, 0, n - 1);
    }
}
