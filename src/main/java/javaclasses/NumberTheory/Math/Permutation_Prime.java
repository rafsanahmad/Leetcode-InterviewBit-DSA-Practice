package javaclasses.NumberTheory.Math;

import java.util.ArrayList;
import java.util.List;
/*Given a number N, task is to Check whether it is a permutable prime number or not.

A Permutable prime number is that number which after switching the position of digits through any permutation is also prime.
Some of the permutable prime numbers are 2, 3, 5, 7, 11, etc.
        Input : 31
        Output : Yes
        Explanation :
        Both 13 and 31 are prime.

        Input : 19
        Output : No
        Explanation :
        19 is prime but 91 is not*/

public class Permutation_Prime {
    static List<String> permString = new ArrayList<>();

    public static int PrimeChecker(int num) {

        // code goes here
        if (num <= 1) return 0;

        //Find all permutation of num - using recursion
        //check if any of them is prime
        String numString = String.valueOf(num);
        permutationFinder("", numString);

        for (int i = 0; i < permString.size(); i++) {
            int value = Integer.parseInt(permString.get(i));
            if (!isPrime(value)) {
                return 0;
            }
        }
        return 1;
    }

    //find permutation using recursive approach
    public static void permutationFinder(String prefix, String str) {
        int len = str.length();
        if (len == 0) {
            //found permutation
            permString.add(prefix);
        } else {
            for (int i = 0; i < len; i++) {
                permutationFinder(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, len));
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1)
            return false;

        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // keep this function call here
        //Scanner s = new Scanner(System.in);
        //System.out.print(PrimeChecker(s.nextLine()));
        int result = PrimeChecker(13);
        System.out.print("Found Prime: " + result);
    }
}

