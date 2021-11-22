/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.ArrayList;

public class GridDistance {

    //Find retail location with distance less than K
    // 0 -> Empty location, 1 -> Already filled
    public int solution(int K, int[][] A) {
        int result = 0;
        //Store house location
        ArrayList<int[]> houses = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }

        //Search through 2d grid for possible retail location
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                //Empty location found
                if (A[i][j] == 0) {
                    boolean found = true;
                    for (int m = 0; m < houses.size(); m++) {
                        int[] location = houses.get(m);
                        int distance = Math.abs(i - location[0]) + Math.abs(j - location[1]);
                        if (distance > K) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        ++result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        GridDistance chain = new GridDistance();
        int[] arr1 = {0, 1, 0};
        int[] arr2 = {0, 0, 0};
        int[] arr3 = {0, 0, 1};
        int[][] grid = new int[][]{arr1, arr2, arr3};
        System.out.println(chain.solution(5, grid));
    }

}
