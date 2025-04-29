/*
 * *
 *  * Min Area Rectangle.java
 *  * Created by Rafsan Ahmad on 3/10/25, 4:13PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package javaclasses.HashTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinAreaRectangle {
    //res/rec1.jpg
    //res/rec2.jpg
    //res/rectangle_coordinates.png
    //https://leetcode.com/problems/minimum-area-rectangle/description/
    /*You are given an array of points in the X-Y plane points where points[i] = [xi, yi].

Return the minimum area of a rectangle formed from these points, with sides parallel to the
 X and Y axes. If there is not any such rectangle, return 0.



Example 1:
Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:
Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Constraints:
1 <= points.length <= 500
points[i].length == 2
0 <= xi, yi <= 4 * 104
All the given points are unique.*/

    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<>());
            }
            map.get(p[0]).add(p[1]);
        }
        int min = Integer.MAX_VALUE;

        for (int[] p1 : points) {
            for (int[] p2 : points) {
                int x1 = p1[0], y1 = p1[1]; // Coordinates of Point A

                int x2 = p2[0], y2 = p2[1]; // Coordinates of Point B

                if (x1 == x2 || y1 == y2) {
                    continue;
                }
                if (map.get(x1).contains(y2) && map.get(x2).contains(y1)) { // find other two points
                    min = Math.min(min, Math.abs(x1 - x2) * Math.abs(y1 - y2));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public static void main(String[] args) {
        MinAreaRectangle rectangle = new MinAreaRectangle();
        int[][] points = {{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}};
        System.out.println(rectangle.minAreaRect(points));
    }
}
