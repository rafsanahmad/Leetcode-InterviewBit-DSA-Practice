/*
 * * Slowest Key.java
 *  * Created by Rafsan Ahmad on 12/1/21, 6:06 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Hashing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SlowestKey {
    //Leetcode 1629
    /*A newly designed keypad was tested, where a tester pressed a sequence of n keys, one at a time.

You are given a string keysPressed of length n, where keysPressed[i] was the ith key pressed in the testing
sequence, and a sorted list releaseTimes, where releaseTimes[i] was the time the ith key was released.
Both arrays are 0-indexed. The 0th key was pressed at the time 0, and every subsequent key was pressed at the
exact time the previous key was released.

The tester wants to know the key of the keypress that had the longest duration. The ith keypress had a duration
of releaseTimes[i] - releaseTimes[i - 1], and the 0th keypress had a duration of releaseTimes[0].

Note that the same key could have been pressed multiple times during the test, and these multiple presses of the
same key may not have had the same duration.

Return the key of the keypress that had the longest duration. If there are multiple such keypresses, return the
lexicographically largest key of the keypresses.


Example 1:

Input: releaseTimes = [9,29,49,50], keysPressed = "cbcd"
Output: "c"
Explanation: The keypresses were as follows:
Keypress for 'c' had a duration of 9 (pressed at time 0 and released at time 9).
Keypress for 'b' had a duration of 29 - 9 = 20 (pressed at time 9 right after the release of the previous character and released at time 29).
Keypress for 'c' had a duration of 49 - 29 = 20 (pressed at time 29 right after the release of the previous character and released at time 49).
Keypress for 'd' had a duration of 50 - 49 = 1 (pressed at time 49 right after the release of the previous character and released at time 50).
The longest of these was the keypress for 'b' and the second keypress for 'c', both with duration 20.
'c' is lexicographically larger than 'b', so the answer is 'c'.
Example 2:

Input: releaseTimes = [12,23,36,46,62], keysPressed = "spuda"
Output: "a"
Explanation: The keypresses were as follows:
Keypress for 's' had a duration of 12.
Keypress for 'p' had a duration of 23 - 12 = 11.
Keypress for 'u' had a duration of 36 - 23 = 13.
Keypress for 'd' had a duration of 46 - 36 = 10.
Keypress for 'a' had a duration of 62 - 46 = 16.
The longest of these was the keypress for 'a' with duration 16.


Constraints:

releaseTimes.length == n
keysPressed.length == n
2 <= n <= 1000
1 <= releaseTimes[i] <= 109
releaseTimes[i] < releaseTimes[i+1]
keysPressed contains only lowercase English letters.*/

    //Approach 1 - Using HashMap
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        HashMap<Character, Integer> keyDuration = new HashMap<>();

        int len = keysPressed.length();
        int duration = 0;
        for (int i = 0; i < releaseTimes.length; i++) {
            if (i == 0) duration = releaseTimes[0];
            else duration = releaseTimes[i] - releaseTimes[i - 1];
            if (i < len) {
                char current = keysPressed.charAt(i);
                if (keyDuration.containsKey(current)) {
                    int currentVal = keyDuration.get(current);
                    if (duration > currentVal) {
                        keyDuration.put(current, duration);
                    }
                } else {
                    keyDuration.put(current, duration);
                }
            }
        }

        Comparator<Map.Entry<Character, Integer>> byValueDesc =
                Map.Entry.comparingByValue(Comparator.reverseOrder());
        Comparator<Map.Entry<Character, Integer>> byKeyDesc =
                Map.Entry.comparingByKey(Comparator.reverseOrder());

        Map<Character, Integer> result =
                keyDuration.entrySet()
                        .stream()
                        .sorted(byValueDesc.thenComparing(byKeyDesc))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->
                                e1, LinkedHashMap::new));

        //Get first entry
        Map.Entry<Character, Integer> entry = result.entrySet().iterator().next();
        return entry.getKey();
    }

    //Approach 2 - Using List of custom objects
    public class KeyboardKeys {
        char ch;
        int releaseTime;

        public char getCh() {
            return ch;
        }

        public int getReleaseTime() {
            return releaseTime;
        }

        KeyboardKeys(char ch, int time) {
            this.ch = ch;
            this.releaseTime = time;
        }
    }

    public char slowestKeyApproach2(int[] releaseTimes, String keysPressed) {
        if (keysPressed == null || keysPressed.length() <= 0) {
            return '\0';
        }
        List<KeyboardKeys> keys = new ArrayList<>();
        for (int i = 0; i < releaseTimes.length; i++) {
            char ch = keysPressed.charAt(i);
            int t = releaseTimes[i];
            if (i != 0) {
                t = releaseTimes[i] - releaseTimes[i - 1];
            }
            KeyboardKeys keyboardKeys = new KeyboardKeys(ch, t);
            keys.add(keyboardKeys);
        }

        //Sort keys list
        Collections.sort(keys, new Comparator<KeyboardKeys>() {
            @Override
            public int compare(KeyboardKeys t1, KeyboardKeys t2) {
                if (t1.getReleaseTime() != t2.getReleaseTime()) {
                    return t2.getReleaseTime() - t1.getReleaseTime();  //Descending
                }
                String ch1 = Character.toString(t1.getCh());
                String ch2 = Character.toString(t2.getCh());
                return ch2.compareTo(ch1);
            }
        });
        return keys.get(0).ch;
    }

    public static void main(String[] args) {
        SlowestKey key = new SlowestKey();
        int[] arr = {9, 29, 49, 50};
        System.out.println(key.slowestKey(arr, "cbcd"));
        System.out.println(key.slowestKeyApproach2(arr, "cbcd"));
    }
}
