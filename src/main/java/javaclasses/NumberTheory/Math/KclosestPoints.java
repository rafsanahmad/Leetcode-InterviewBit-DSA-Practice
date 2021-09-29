package javaclasses.NumberTheory.Math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
* Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k,
return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

*/
public class KclosestPoints {

    public int[][] kClosest(int[][] points, int k) {
        int[][] result = new int[k][2];

        HashMap<Integer, Double> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            double diff1 = Math.pow((x1), 2);
            double diff2 = Math.pow((y1), 2);
            double sqrt = Math.sqrt(diff1 + diff2);
            map.put(i, sqrt);
        }

        map = sortByValue(map);
        List<Integer> list = new ArrayList<>(map.keySet());
        for (int i = 0; i < k; i++) {
            int index = list.get(i);
            result[i][0] = points[index][0];
            result[i][1] = points[index][1];
        }
        return result;
    }

    public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Double>> list
                = new LinkedList<Map.Entry<Integer, Double>>(
                hm.entrySet());

        // Sort the list using lambda expression
        Collections.sort(
                list,
                (i1,
                 i2) -> i1.getValue().compareTo(i2.getValue()));

        // put data from sorted list to hashmap
        HashMap<Integer, Double> temp
                = new LinkedHashMap<Integer, Double>();
        for (Map.Entry<Integer, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
