/*
 * *
 *  * Shuffle Array.java
 *  * Created by Rafsan Ahmad on 1/11/22, 12:51 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShuffleArray {
    //Leetcode 384
    /*Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the
     array should be equally likely as a result of the shuffling.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.


Example 1:

Input
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
Output
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

Explanation
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
                       // Any permutation of [1,2,3] must be equally likely to be returned.
                       // Example: return [3, 1, 2]
solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
*/

    private int[] array;
    private int[] original;

    private Random rand = new Random();

    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }

    public ShuffleArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        ShuffleArray array = new ShuffleArray(nums);
        // Shuffle the array [1,2,3] and return its result.
        System.out.println(Arrays.toString(array.shuffle()));
        // Any permutation of [1,2,3] must be equally likely to be returned.
        // Example: return [3, 1, 2]
        // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
        System.out.println(Arrays.toString(array.reset()));
        // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
        System.out.println(Arrays.toString(array.shuffle()));
    }
}
