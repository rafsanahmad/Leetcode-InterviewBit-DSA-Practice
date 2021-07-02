package javaclasses.Strings;

/*Given two strings, write a code to check whether two strings are anagram of each other or not.
In this tutorial, I am going to discuss multiple approaches and their java implementation to check if two strings are anagrams or not.

*/

/*Two strings are said to be anagrams of each other if it contains the same characters,
only the order of characters in both the strings is different. In other words, both strings must contain the same exact
letters in the same exact frequency.

Let’s understand it through an example –

For example –

i)

str1 – car

str2 – rac

In this example, str1 and str2 are anagrams of each other. As both, the strings contain the same letters
only the order of characters in both the strings is different.

*/

/*In this method, we count each character of the first string then subtracting it from the
count of the second string. Finally, we check if the character count is zero. If it is not zero(0) then the two string is not an anagram.

The time complexity of this approach is O(n).*/


import java.util.*;

class ValidAnagram {

    public static boolean isAnagram(String str1, String str2) {
 
      /*If both strings is of different length,
        then it's not an anagram */
        if (str1.length() != str2.length())
            return false;

        //Create an array of size 256
        int[] countarr = new int[256];

        for (int i = 0; i < str1.length(); i++) {
            //Increment character count for str1
            countarr[str1.charAt(i)]++;

            //decrement character count for str2
            countarr[str2.charAt(i)]--;
        }

        for (int j = 0; j < countarr.length; j++) {
            //if it's not zero
            if (countarr[j] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //String input
        String str1 = in.nextLine();
        String str2 = in.nextLine();

        if (isAnagram(str1, str2)) {
            System.out.println("Anagram");
        } else {
            System.out.println("Not an Anagram");
        }
    }
}

