/*
 * *
 *  * Shortest Path From Source To Destination Matrix.java
 *  * Created by Rafsan Ahmad on 7/31/22, 7:01 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ShortestPathFromSourceToDestinationMatrix {
    /*Given an N × N matrix of positive integers, find the shortest path from the first cell of the matrix to its last
    cell that satisfies given constraints.

We are allowed to move exactly k steps from any cell in the matrix where k is the cell’s value, i.e., from a cell (i, j)
 having value k in a matrix M, we can move to (i+k, j), (i-k, j), (i, j+k), or (i, j-k). The diagonal moves are not
 allowed.

For example,

Input:

[ 7  1  3  5  3  6  1  1  7  5 ]
[ 2  3  6  1  1  6  6  6  1  2 ]
[ 6  1  7  2  1  4  7  6  6  2 ]
[ 6  6  7  1  3  3  5  1  3  4 ]
[ 5  5  6  1  5  4  6  1  7  4 ]
[ 3  5  5  2  7  5  3  4  3  6 ]
[ 4  1  4  3  6  4  5  3  2  6 ]
[ 4  4  1  7  4  3  3  1  4  2 ]
[ 4  4  5  1  5  2  3  5  3  5 ]
[ 3  6  3  5  2  2  6  4  2  1 ]

Output:

The shortest path length is 6
The shortest path is (0, 0) (0, 7) (0, 6) (1, 6) (7, 6) (7, 9) (9, 9)


Input:

[ 4  4  6  5  5  1  1  1  7  4 ]
[ 3  6  2  4  6  5  7  2  6  6 ]
[ 1  3  6  1  1  1  7  1  4  5 ]
[ 7  5  6  3  1  3  3  1  1  7 ]
[ 3  4  6  4  7  2  6  5  4  4 ]
[ 3  2  5  1  2  5  1  2  3  4 ]
[ 4  2  2  2  5  2  3  7  7  3 ]
[ 7  2  4  3  5  2  2  3  6  3 ]
[ 5  1  4  2  6  4  6  7  3  7 ]
[ 1  4  1  7  5  3  6  5  3  4 ]

Output:

The shortest path length is 6
The shortest path is (0, 0) (0, 4) (5, 4) (5, 2) (5, 7) (5, 9) (9, 9)*/

/*Following is the complete algorithm:

Create an empty queue and enqueue the source cell having a distance 0 from source (itself) and mark it as visited.
Loop till queue is empty

Dequeue the front node.
If the popped node is the destination node, return its distance.
Otherwise, for each of four adjacent cells of the current cell, enqueue each of the valid cells with +1 distance and
mark them as visited.
If all the queue nodes are processed, and the destination is not reached, then return false.
Note that in BFS, all cells having the shortest path as 1 are visited first, followed by their adjacent cells having
the shortest path as 1 + 1 = 2 and so on. So if we reach any node in BFS, its shortest path is one more than the
shortest path of the parent. So, the first occurrence of the destination cell gives us the result, and we can stop our
search there. The shortest path cannot possibly exist from some other cell for which we haven’t reached the given node
yet. If any such path was possible, we would have already explored it.


Note that we can find all the possible locations we can move to from the given location by using the array that stores
the relative position of movement from any location. For example, if the current location is (x, y), we can move to
(x + row[k], y + col[k]) for 0 <= k < 4 using the following array:

row[] = { -1, 0, 0, 1 }
col[] = { 0, -1, 1, 0 }

So, from position (x, y), we can move to:

(x – 1, y)
(x, y – 1)
(x, y + 1)
(x + 1, y)*/

    // A queue node used in BFS
    static class Node {
        // (x, y) represents coordinates of a cell in the matrix
        int x, y;

        // maintain a parent node for printing the final path
        Node parent;

        // `level` stores the distance of a current node from the source node (i.e., BFS level)
        int level;

        Node(int x, int y, Node parent, int level) {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.level = level;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ')';
        }
    }

    // Below arrays detail all four possible movements from a cell
    private static final int[] row = {-1, 0, 0, 1};
    private static final int[] col = {0, -1, 1, 0};

    // The function returns false if (x, y) is not a valid position
    private static boolean isValid(int x, int y, int N) {
        return (x >= 0 && x < N) && (y >= 0 && y < N);
    }

    // Utility function to find path from source to destination
    private static void findPath(Node node, List<String> path) {
        if (node != null) {
            findPath(node.parent, path);
            path.add(node.toString());
        }
    }

    // Find the shortest route in a matrix from source cell (x, y) to
    // destination cell (N-1, N-1)
    public static List<String> findPath(int[][] matrix, int x, int y) {
        // list to store shortest path
        List<String> path = new ArrayList<>();

        // base case
        if (matrix == null || matrix.length == 0) {
            return path;
        }

        // `N × N` matrix
        int N = matrix.length;

        // create a queue and enqueue the first node
        Queue<Node> q = new ArrayDeque<>();
        Node src = new Node(x, y, null, 0);
        q.add(src);

        // set to check if the matrix cell is visited before or not
        Set<String> visited = new HashSet<>();

        String key = src.x + "," + src.y;
        visited.add(key);

        // loop till queue is empty
        while (!q.isEmpty()) {
            // dequeue front node and process it
            Node curr = q.poll();
            int i = curr.x, j = curr.y;
            int level = curr.level;

            // return if the destination is found
            if (i == N - 1 && j == N - 1) {
                findPath(curr, path);
                System.out.println("The shortest path length is " + level);
                return path;
            }

            // value of the current cell
            int n = matrix[i][j];

            // check all four possible movements from the current cell
            // and recur for each valid movement
            for (int k = 0; k < row.length; k++) {
                // get next position coordinates using the value of the current cell
                x = i + row[k] * n;
                y = j + col[k] * n;

                // check if it is possible to go to the next position
                // from the current position
                if (isValid(x, y, N)) {
                    // construct the next cell node
                    Node next = new Node(x, y, curr, level + 1);

                    key = next.x + "," + next.y;

                    // if it isn't visited yet
                    if (!visited.contains(key)) {
                        // enqueue it and mark it as visited
                        q.add(next);
                        visited.add(key);
                    }
                }
            }
        }

        // we reach here if the path is not possible
        return path;
    }

    public static void main(String[] args) {
        int[][] matrix =
                {
                        {1, 2, 3},
                        {1, 5, 6},
                        {2, 8, 9}
                };

        int[][] matrix2 =
                {
                        {4, 4, 6, 5, 5, 1, 1, 1, 7, 4},
                        {3, 6, 2, 4, 6, 5, 7, 2, 6, 6},
                        {1, 3, 6, 1, 1, 1, 7, 1, 4, 5},
                        {7, 5, 6, 3, 1, 3, 3, 1, 1, 7},
                        {3, 4, 6, 4, 7, 2, 6, 5, 4, 4},
                        {3, 2, 5, 1, 2, 5, 1, 2, 3, 4},
                        {4, 2, 2, 2, 5, 2, 3, 7, 7, 3},
                        {7, 2, 4, 3, 5, 2, 2, 3, 6, 3},
                        {5, 1, 4, 2, 6, 4, 6, 7, 3, 7},
                        {1, 4, 1, 7, 5, 3, 6, 5, 3, 4}
                };

        int[][] matrix3 =
                {
                        {7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 1, 1, 1, 1, 1, 1, 1, 1, 7},
                        {7, 7, 7, 7, 7, 7, 7, 7, 7, 7}
                };

        // Find a route in the matrix from source cell (0, 0) to destination cell (N-1, N-1)
        List<String> path = findPath(matrix, 0, 0);

        if (path != null && path.size() > 0) {
            System.out.print("The shortest path is " + path);
        } else {
            System.out.println("Destination is not found");
        }

        System.out.println();

        List<String> path2 = findPath(matrix, 0, 0);

        if (path2 != null && path2.size() > 0) {
            System.out.print("The shortest path is " + path2);
        } else {
            System.out.println("Destination is not found");
        }
    }
}
