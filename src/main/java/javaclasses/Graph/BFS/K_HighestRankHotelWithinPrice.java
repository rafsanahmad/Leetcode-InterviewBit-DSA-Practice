/*
 * *
 *  * K Highest Rank Hotel Within Price.java
 *  * Created by Rafsan Ahmad on 11/6/22, 7:39 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class K_HighestRankHotelWithinPrice {
    //https://leetcode.com/problems/k-highest-ranked-items-within-a-price-range/
    /*You are given a 0-indexed 2D integer array grid of size m x n that represents a map of the items in a shop.
    The integers in the grid represent the following:

0 represents a wall that you cannot pass through.
1 represents an empty cell that you can freely move to and from.
All other positive integers represent the price of an item in that cell. You may also freely move to and from these
item cells.
It takes 1 step to travel between adjacent grid cells.

You are also given integer arrays pricing and start where pricing = [low, high] and start = [row, col] indicates that you
start at the position (row, col) and are interested only in items with a price in the range of [low, high] (inclusive).
You are further given an integer k.

You are interested in the positions of the k highest-ranked items whose prices are within the given price range.
The rank is determined by the first of these criteria that is different:

Distance, defined as the length of the shortest path from the start (shorter distance has a higher rank).
Price (lower price has a higher rank, but it must be in the price range).
The row number (smaller row number has a higher rank).
The column number (smaller column number has a higher rank).
Return the k highest-ranked items within the price range sorted by their rank (highest to lowest).
 If there are fewer than k reachable items within the price range, return all of them.


Example 1:
res/k_highest_rank_hotel_1.png

Input: grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
Output: [[0,1],[1,1],[2,1]]
Explanation: You start at (0,0).
With a price range of [2,5], we can take items from (0,1), (1,1), (2,1) and (2,2).
The ranks of these items are:
- (0,1) with distance 1
- (1,1) with distance 2
- (2,1) with distance 3
- (2,2) with distance 4
Thus, the 3 highest ranked items in the price range are (0,1), (1,1), and (2,1).

Example 2:
res/k_highest_rank_hotel_2.png

Input: grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
Output: [[2,1],[1,2]]
Explanation: You start at (2,3).
With a price range of [2,3], we can take items from (0,1), (1,1), (1,2) and (2,1).
The ranks of these items are:
- (2,1) with distance 2, price 2
- (1,2) with distance 2, price 3
- (1,1) with distance 3
- (0,1) with distance 4
Thus, the 2 highest ranked items in the price range are (2,1) and (1,2).

Example 3:
res/k_highest_rank_hotel_3.png

Input: grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
Output: [[2,1],[2,0]]
Explanation: You start at (0,0).
With a price range of [2,3], we can take items from (2,0) and (2,1).
The ranks of these items are:
- (2,1) with distance 5
- (2,0) with distance 6
Thus, the 2 highest ranked items in the price range are (2,1) and (2,0).
Note that k = 3 but there are only 2 reachable items within the price range.*/

    class Hotel {
        int distance;
        int price;
        int row;
        int column;

        Hotel(int d, int p, int row, int column) {
            this.distance = d;
            this.price = p;
            this.row = row;
            this.column = column;
        }
    }

    class Node {
        int row;
        int col;
        int dist;

        Node(int r, int c, int d) {
            this.row = r;
            this.col = c;
            this.dist = d;
        }
    }

    //Use BFS to find shortest distance
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        int lowPrice = pricing[0];
        int highPrice = pricing[1];

        int rowLen = grid.length;
        int colLen = grid[0].length;
        int startRow = start[0];
        int startCol = start[1];
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowLen][colLen];
        queue.add(new Node(startRow, startCol, 0));

        //Start point is a Hotel
        if (grid[start[0]][start[1]] > 1) {
            int price = grid[startRow][startCol];
            if (price >= lowPrice && price <= highPrice) {
                hotels.add(new Hotel(0, price, startRow, startCol));
                visited[startRow][startCol] = true;
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < dirs.length; i++) {
                int nextRow = node.row + dirs[i][0];
                int nextColumn = node.col + dirs[i][1];

                if (isValid(nextRow, nextColumn, rowLen, colLen) && !visited[nextRow][nextColumn] && grid[nextRow][nextColumn] != 0) {
                    int price = grid[nextRow][nextColumn];
                    if (price != 1) {
                        //New hotel found
                        Hotel hotel = new Hotel(node.dist + 1, price, nextRow, nextColumn);
                        if (price >= lowPrice && price <= highPrice)
                            hotels.add(hotel);
                    }

                    visited[nextRow][nextColumn] = true;
                    queue.add(new Node(nextRow, nextColumn, node.dist + 1));
                }
            }
        }

        boolean returnAll = false;
        if (hotels.size() <= k) returnAll = true;

        hotels.sort((o1, o2) -> {
            if (o1.distance != o2.distance) {
                return o1.distance - o2.distance;
            } else if (o1.price != o2.price) {
                return o1.price - o2.price;
            } else if (o1.row != o2.row) {
                return o1.row - o2.row;
            } else if (o1.column != o2.column) {
                return o1.column - o2.column;
            }
            return 0;
        });

        if (returnAll) {
            for (int i = 0; i < hotels.size(); i++) {
                Hotel hotel = hotels.get(i);
                List<Integer> list = new ArrayList<>();
                list.add(hotel.row);
                list.add(hotel.column);
                result.add(list);
            }
            return result;
        }

        //Get top K
        int index = 0;
        while (k > 0) {
            Hotel hotel = hotels.get(index);
            List<Integer> list = new ArrayList<>();
            list.add(hotel.row);
            list.add(hotel.column);
            result.add(list);
            k--;
            index++;
        }

        return result;
    }

    boolean isValid(int i, int j, int rowLen, int colLen) {
        if (i >= 0 && i < rowLen && j >= 0 && j < colLen) return true;
        return false;
    }

    public static void main(String[] args) {
        K_HighestRankHotelWithinPrice rankHotelWithinPrice = new K_HighestRankHotelWithinPrice();
        int[][] grid = {{0, 2, 0}};
        int[] price = {2, 2};
        int[] start = {0, 1};
        System.out.println(rankHotelWithinPrice.highestRankedKItems(grid, price, start, 1));

        int[][] grid2 = {{1, 0, 1}, {3, 5, 2}, {1, 0, 1}};
        int[] price2 = {2, 5};
        int[] start2 = {1, 1};
        System.out.println(rankHotelWithinPrice.highestRankedKItems(grid2, price2, start2, 9));

        int[][] grid3 = {{1, 2, 0, 1}, {1, 3, 0, 1}, {0, 2, 5, 1}};
        int[] price3 = {2, 5};
        int[] start3 = {0, 0};
        System.out.println(rankHotelWithinPrice.highestRankedKItems(grid3, price3, start3, 3));
    }

}
