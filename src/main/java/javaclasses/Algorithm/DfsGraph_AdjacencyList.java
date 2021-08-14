package javaclasses.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class DfsGraph_AdjacencyList {

    /*res/dfs.png*/
    static class Node {
        int data;
        boolean visited;
        List<Node> neighbours;

        Node(int data) {
            this.data = data;
            this.neighbours = new ArrayList<>();
        }

        public void addNeighbours(Node node) {
            this.neighbours.add(node);
        }

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }


}
