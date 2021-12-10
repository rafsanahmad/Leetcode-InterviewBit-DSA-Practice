/*
 * *
 *  * Check If Straight Line.java
 *  * Created by Rafsan Ahmad on 12/11/21, 4:21 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Matrix;

public class CheckIfStraightLine {
    //Leetcode 1232
    //res/line1.jpeg
    //res/line2.jpeg
    /*You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
    Check if these points make a straight line in the XY plane.

Example 1:
Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true


Example 2:
Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false
*/

    public boolean checkStraightLine(int[][] coordinates) {
        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];
        int dx = x1 - x0;
        int dy = y1 - y0;

        for (int i = 2; i < coordinates.length; ++i) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            if ((x - x0) * dy != (y - y0) * dx)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        CheckIfStraightLine line = new CheckIfStraightLine();
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        System.out.println(line.checkStraightLine(coordinates));
    }
}
