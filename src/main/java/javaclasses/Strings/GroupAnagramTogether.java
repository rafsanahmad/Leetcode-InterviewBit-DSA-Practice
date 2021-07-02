package javaclasses.Strings;
/*Given an array of strings or a list of words, write a code to group anagrams together.

what are Anagrams?

Two strings are said to be anagrams of each other if it contains the same characters,
only the order of character in both the strings is different.

Check whether two strings are anagrams of each other or not

For example, Word “car” and “rac” are anagrams of each other. As both, the strings contain the same number of characters
only the order of character is different in both the strings.

Now, let’s understand this problem statement with example.

For example:

Input: {“eat”, “tea”, “tan”, “ate”, “nat”, “bat”}

Output:

[
   ["ate","eat","tea"],
   ["nat","tan"],
   ["bat"]
]
*/

/*If two strings are anagrams of each other, then their sorted sequence is the same. By keeping this point in mind we can solve this problem.

Here are the following steps to group anagrams.

i) Traverse a list of string.

ii) Pick each string and sort it. For sorting, first, convert a string into a character array and then sort this array.

iii) Create a map and put sorted string as a key to this map to group all the anagrams together.


The time complexity of this approach is O(NMlogM), where N is the length of the array and M is the longest word in an array.*/

//Print all anagrams together

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagramTogether {

    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();

        //Initialize hashmap
        HashMap<String, List<String>> map = new HashMap<>();

        //Traverse a list of string
        for (String str : strs) {

            //Convert to character array
            char[] chArr = str.toCharArray();
            //Sort character array
            Arrays.sort(chArr);
            //Create a string
            String key = new String(chArr);

            //Create a key from a sorted string
            //if this key is found add new string element
            if (map.containsKey(key)) {
                map.get(key).add(str);

            } else {
                List<String> strList = new ArrayList<>();
                strList.add(str);
                map.put(key, strList);
            }
        }

        result.addAll(map.values());
        return result;
    }

    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagrams(strs);
        result.forEach(t -> System.out.println(t + " "));
    }
}