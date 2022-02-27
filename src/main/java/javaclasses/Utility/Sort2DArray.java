/*
 * *
 *  * Sort2DArray.java
 *  * Created by Rafsan Ahmad on 2/27/22, 12:51 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.Arrays;

public class Sort2DArray {

    public void sortArray(int[][] array) {
        Arrays.sort(array, (o1, o2) -> {
            if (o2[1] == o1[1]) {
                return Integer.compare(o1[0], o2[0]); //Ascending
            }
            return Integer.compare(o2[1], o1[1]);   //Descending
        });
    }

    public void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
        Sort2DArray array = new Sort2DArray();
        array.sortArray(stations);
        array.printArray(stations);
    }
}
