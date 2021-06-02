package javaclasses.NumberTheory;

public class IsPalindrome {
    /*
    * Given an integer x, return true if x is palindrome integer.

    An integer is a palindrome when it reads the same backward as forward.
    * For example, 121 is palindrome while 123 is not.

    Example 1:
    Input: x = 121
    Output: true*/

    //Leetcode 9
    public boolean isPalindromeNumber(int x) {
        //Using two pointer
        String str = String.valueOf(x);
        int start = 0;
        int end = str.length() - 1;

        while (start != end && start < end && end > start) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        //For last loop case
        if (str.charAt(start) != str.charAt(end)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome palindrome = new IsPalindrome();
        System.out.println(palindrome.isPalindromeNumber(121));
    }
}
