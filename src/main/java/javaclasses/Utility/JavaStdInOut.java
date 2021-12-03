/*
 * *
 *  * Created by Rafsan Ahmad on 11/26/21, 8:44 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.Scanner;

public class JavaStdInOut {

    public static void main(String[] args) {
        System.out.println("Enter your name:");
        Scanner scanner = new Scanner(System.in);
        String myString = scanner.next();
        System.out.println("Enter your age:");
        int myInt = scanner.nextInt();

        System.out.println("String is: " + myString);
        System.out.println("Int is: " + myInt);

        //Test Case
        System.out.println("Enter test case:");
        int t = scanner.nextInt();
        System.out.println("Enter numbers");
        for (int i = 0; i < t; i++) {
            int num = scanner.nextInt();
            System.out.println(num);
        }


        //STD IN - Test case with Queries
        /*Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        int index = 0;
        int[] arr = new int[total];

        for (int i = 0; i < total; i++) {
            int num = scanner.nextInt();
            arr[index++] = num;
        }

        int query = scanner.nextInt();

        List<int[]> queries = new ArrayList<>();
        for (int i = 0; i < query; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int[] ar = {start, end};
            queries.add(ar);
        }

        int[] result = yourFunction(arr, queries);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }*/
        scanner.close();
    }
}
