/*
 * * Variable Arguments.java
 *  * Created by Rafsan Ahmad on 11/28/21, 12:39 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

public class VariableArguments {

    public void someMethod(String... info) {
        for (String someInfo : info) {
            // any operation
        }
        // The info can be accessed using index based loops too
        for (int i = 0; i < info.length; i++) {
            String s = info[i];
            //some operation
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        VariableArguments arguments = new VariableArguments();
        arguments.someMethod("Java", "Interview");
        arguments.someMethod("Java", "Interview", "Questions");
        arguments.someMethod(new String[]{"Java", "Interview", "Questions"});
    }

}
