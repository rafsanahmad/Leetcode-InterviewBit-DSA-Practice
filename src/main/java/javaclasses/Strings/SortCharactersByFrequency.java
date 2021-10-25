/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Strings;

/*Given a string, write a method to sort it in decreasing order based on the frequency of characters.

For example:

Example 1

Input : “teee”

Output: “eeet”

Example 2:

  Input: “dddbbb”

Output: “dddbbb”   or “bbbddd” (Both d and b appear 3 times so any of the above output are valid)

  Example 3:

  Input:  “Eett”

Output: “ttEe” or “tteE”  (Both e and E are two different characters so the output should not be “Eett”)

*/

/*In this problem, We have to return a string which is sorted by frequency of characters.
To sort them by count of characters first we have to know each character and it’s count.

It means we have to first traverse a string and put each character and it’s count in a Map.
Here i am using HashMap to store character and it’s count.

So, Once we know each character and it’s count. Simply sort the HashMap by values.

The time complexity of this approach is O(n) and space complexity is also O(n).*/


//Return string sorted by frequency of characters

import java.util.HashMap;
import java.util.Map;

public class SortCharactersByFrequency {
    public String frequencySort(String s) {

        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> charCountMap = new HashMap<>();

        int len = s.length();

          /*
           Traverse a string,
           store each character and it's count in a map
          */
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }

           /*
             Sort this map by frequency.
            */

        charCountMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEach(record -> {
                    Character key = record.getKey();
                    int value = record.getValue();
                    //Append the character by the number of times it occurrs.
                    for (int i = 0; i < value; i++) {
                        sb.append(key);
                    }
                });

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}

