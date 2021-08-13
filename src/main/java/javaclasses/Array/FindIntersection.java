package javaclasses.Array;
/*Given two sorted arrays, Write a java code to find intersection of two arrays.

For example, if the input arrays are:

arr1[] = {2, 3, 6, 7, 9, 11}
arr2[] = {4, 6, 8, 9, 12}

Then your program should print intersection as {6, 9}. Before writing actual code, let’s first discuss different approaches to solve this problem.*/

/*In this approach, we first initialize an empty set.Then, we traverse the first array and put each element of the first array in a set.
Now, For every element x of the second array, we search x in the set.
If x is present, then print it. The time complexity of this approach is O(m+n).*/

import java.util.HashSet;

/**
 * Using hashset to find intersection of two arrays
 */
public class FindIntersection {

    public static void main(String[] args) {

        int[] arr1 = {2, 3, 4, 5, 6};
        int[] arr2 = {4, 6, 7, 8, 9};

        //Declare hashset
        HashSet set1 = new HashSet();

        //Traverse an array, put each element in a set
        for (int val : arr1) {
            set1.add(val);
        }

        /**
         Traverse second array values,
         Search the value in a set (set1),
         If element is found then print it.
         */
        for (int val : arr2) {
            if (set1.contains(val)) {
                System.out.println(val);
            }
        }

    }
}


/*2nd way*/
/*public class Intersection {
 
     public static void main(String[] args) {
 
        Integer arr1[] = {2, 3, 4, 5, 6};
        Integer arr2[] = {4, 6, 7, 8, 9};
        
        HashSet set1 = new HashSet<>(Arrays.asList(arr1));
        HashSet set2 = new HashSet<>(Arrays.asList(arr2));
 
        set1.retainAll(set2);
        System.out.println(set1);
    }
} */
