/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.ArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RearrangeList {
    //https://www.interviewbit.com/problems/rearrange-array/
    /*Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]
Lets say N = size of the array. Then, following holds true :

All elements in the array are in the range [0, N-1]
N * N does not overflow for a signed integer*/

    //Approach 1 - using arraylist
    public void arrange(ArrayList<Integer> a) {
        List<Integer> newList = new ArrayList<>(a);
        for (int i = 0; i < a.size(); i++) {
            int current = newList.get(i);
            int value = newList.get(current);
            a.set(i, value);
        }
    }

    //Approach 2 - using HashMap
    public void arrangeUsingMap(ArrayList<Integer> a) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < a.size(); i++) {
            hm.put(i, a.get(a.get(i)));
        }

        for (int i = 0; i < a.size(); i++) {
            a.set(i, hm.get(i));
        }
    }

    //Using 0(1) space
    public void arrangeOptimized(ArrayList<Integer> A) {
        // we'll intelligently store two values in a single array block.
// Got this idea after seeing hint that N*N will not overflow.

// If I want to store 11, 15; I will actually store (11 + N*15)
// Like this, we can recover 11 by finding reminder,
// and 15 by finding quotient.

// old array values will be saved as reminders
//new array values should be saved as quotients
// Max int saved would be (N-1)+ (N-1)*N = N*N - 1

        int n = A.size();

        for (int i = 0; i < n; i++) {
            int current = A.get(i);
            int value = current + n * (A.get(current) % n);
            A.set(i, value);
            //here doing %n bcoz we dont know that element is updated or not
        }

        for (int i = 0; i < n; i++) {
            int current = A.get(i);
            int value = (current - (current % n)) / n;//this'll recover the new values
            A.set(i, value);
        }
    }

    public static void main(String[] args) {
        RearrangeList array = new RearrangeList();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0);
        array.arrangeOptimized(list);
        System.out.println(list.toString());
    }
}
