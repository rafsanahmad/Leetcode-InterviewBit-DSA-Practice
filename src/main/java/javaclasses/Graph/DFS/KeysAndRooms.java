/*
 * *
 *  * Keys And Rooms.java
 *  * Created by Rafsan Ahmad on 12/25/22, 3:50 PM
 *  * Copyright (c) 2023 . All rights reserved.
 *
 */

package javaclasses.Graph.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeysAndRooms {
    //https://leetcode.com/problems/keys-and-rooms/description/
    /*There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all
    the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it
unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can
visit all the rooms, or false otherwise.



Example 1:

Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation:
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.

Example 2:

Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
*/

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int totalRooms = rooms.size();

        boolean[] visited = new boolean[totalRooms];
        List<Integer> firstRoom = rooms.get(0);
        visited[0] = true;

        //For each adjacent rooms in first room
        for (int i = 0; i < firstRoom.size(); i++) {
            int key = firstRoom.get(i);

            //Add to disconnectedRooms if room is not visited and no loop occurs
            if (!visited[key]) {
                dfsRoomUtil(rooms, key, visited);
            }
        }

        for (boolean b : visited) {
            if (!b) return false;
        }

        return true;
    }

    public void dfsRoomUtil(List<List<Integer>> adj, int index, boolean[] visited) {
        if (!visited[index]) {
            visited[index] = true;

            List<Integer> rooms = adj.get(index);
            for (int i = 0; i < rooms.size(); i++) {
                int key = rooms.get(i);

                if (!visited[key]) {
                    dfsRoomUtil(adj, key, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        KeysAndRooms keysAndRooms = new KeysAndRooms();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(Arrays.asList(1));
        rooms.add(Arrays.asList(2));
        rooms.add(Arrays.asList(3));
        rooms.add(Arrays.asList());

        System.out.println(keysAndRooms.canVisitAllRooms(rooms));


        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));

        System.out.println(keysAndRooms.canVisitAllRooms(rooms2));
    }
}
