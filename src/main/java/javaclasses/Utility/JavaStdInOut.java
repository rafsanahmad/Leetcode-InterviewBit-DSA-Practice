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
        scanner.close();

        System.out.println("String is: " + myString);
        System.out.println("Int is: " + myInt);
    }
}
