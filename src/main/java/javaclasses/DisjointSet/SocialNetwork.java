/*
 * *
 *  * Social Network.java
 *  * Created by Rafsan Ahmad on 7/2/22, 10:56 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.DisjointSet;

public class SocialNetwork {
    /*Consider a special social network where people are called connected if one person is connected to other with any
    number of intermediate connections. For example if a person x is connected with y and y is connected with z,
    then x is also considered to be connected with z. We are given a set of friend requests as input. We are also
    given a set of queries where each query has input pair i and j. For each query, we need to tell whether i and j
    are connected or not.

Examples:

Input : Connections :
connect(0, 1), connect(1, 2), connect(0, 3), connect(5, 6), connect (0, 7)
areConnected(2, 7)
areConnected(2, 6)
areConnected(1, 7)
Output :
Yes
No
Yes
Explanation : Note that 0 is connected to 2 and 0 is also connected to 7. Therefore 2 and 7 are considered as connected.

Input : Connections :
connect(0, 2), connect(4, 2), connect(1, 3)
areConnected(0, 4)
areConnected(0, 1)
Output :
Yes
No*/

    // A Java program to implement Special Social Network using Disjoint Set Data Structure.
    int[] rank, parent;
    int n;

    // Constructor
    public SocialNetwork(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    // Creates n sets with single item in each
    void makeSet() {
        for (int i = 0; i < n; i++) {
            // Initially, all elements are in their own set.
            parent[i] = i;
        }
    }

    // Returns representative of x's set
    int find(int x) {
        // Finds the representative of the set that x is an element of
        if (parent[x] != x) {

            // if x is not the parent of itself Then x is not the representative of his set,
            parent[x] = find(parent[x]);
            // so we recursively call Find on its parent and move i's node directly under the representative of this set
        }
        return parent[x];
    }

    // Unites the set that includes x and the set that includes x
    void connect(int x, int y) {
        // Find representatives of two sets
        int xRoot = find(x), yRoot = find(y);

        // Elements are in the same set, no need to unite anything.
        if (xRoot == yRoot)
            return;

        // If x's rank is less than y's rank
        if (rank[xRoot] < rank[yRoot])

            // Then move x under y so that depth  of tree remains less
            parent[xRoot] = yRoot;

            // Else if y's rank is less than x's rank
        else if (rank[yRoot] < rank[xRoot])

            // Then move y under x so that depth of  tree remains less
            parent[yRoot] = xRoot;

        else // if ranks are the same
        {
            // Then move y under x (doesn't matter which one goes where)
            parent[yRoot] = xRoot;

            // And increment the the result tree's rank by 1
            rank[xRoot] = rank[xRoot] + 1;
        }
    }

    boolean areConnected(int i, int j) {
        return find(i) == find(j);
    }

    public static void main(String[] args) {
        // Let there be 5 persons with ids as 0, 1, 2, 3 and 4
        int n = 5;
        SocialNetwork dus = new SocialNetwork(n);

        // 0 is a friend of 2
        dus.connect(0, 2);

        // 4 is a friend of 2
        dus.connect(4, 2);

        // 3 is a friend of 1
        dus.connect(3, 1);

        // Check if 4 is a friend of 0
        if (dus.areConnected(0, 4))
            System.out.println("Yes");
        else
            System.out.println("No");

        // Check if 1 is a friend of 0
        if (dus.areConnected(0, 1))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}