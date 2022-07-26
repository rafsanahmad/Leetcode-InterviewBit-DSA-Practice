/*
 * *
 *  * Created by Rafsan Ahmad on 11/26/21, 8:44 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JavaStdInOut {
    /*1. Scanner Class (easy, less typing, but not recommended very slow
    * 2.BufferedReader (fast, but not recommended as it requires a lot of typing):
The Java.io.BufferedReader class reads text from a character-input stream, buffering characters to provide for the
efficient reading of characters, arrays, and lines. With this method, we will have to parse the value every time
for the desired type. Reading multiple words from a single line adds to its complexity because of the use of
Stringtokenizer and hence this is not recommended.*/

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

        //scanner.close();

        //Using BufferedReader and StringTokenizer
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        StringTokenizer st = null;
        try {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int count = 0;
            while (n-- > 0) {
                int x = Integer.parseInt(br.readLine());
                if (x % k == 0)
                    count++;
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Using Only BufferedReader
        try {
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter line by space:");
            String line = br.readLine(); // line is: hello 1 3.0
            String[] data = line.split(" ");
            String word = data[0];
            int number = Integer.parseInt(data[1]);
            double dValue = Double.parseDouble(data[2]);
            System.out.println(number);
            System.out.println(dValue);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
