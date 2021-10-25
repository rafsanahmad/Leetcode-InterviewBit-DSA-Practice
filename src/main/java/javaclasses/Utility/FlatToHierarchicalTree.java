/*
 * *
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FlatToHierarchicalTree<T> {

    private T head;

    private ArrayList<FlatToHierarchicalTree<T>> leafs = new ArrayList<>();

    private FlatToHierarchicalTree<T> parent = null;

    private HashMap<T, FlatToHierarchicalTree<T>> locate = new HashMap<>();

    public FlatToHierarchicalTree(T head) {
        this.head = head;
        locate.put(head, this);
    }

    public void addLeaf(T root, T leaf) {
        if (locate.containsKey(root)) {
            locate.get(root).addLeaf(leaf);
        } else {
            addLeaf(root).addLeaf(leaf);
        }
    }

    public FlatToHierarchicalTree<T> addLeaf(T leaf) {
        FlatToHierarchicalTree<T> t = new FlatToHierarchicalTree<T>(leaf);
        leafs.add(t);
        t.parent = this;
        t.locate = this.locate;
        locate.put(leaf, t);
        return t;
    }

    public FlatToHierarchicalTree<T> setAsParent(T parentRoot) {
        FlatToHierarchicalTree<T> t = new FlatToHierarchicalTree<T>(parentRoot);
        t.leafs.add(this);
        this.parent = t;
        t.locate = this.locate;
        t.locate.put(head, this);
        t.locate.put(parentRoot, t);
        return t;
    }

    public T getHead() {
        return head;
    }

    public FlatToHierarchicalTree<T> getTree(T element) {
        return locate.get(element);
    }

    public FlatToHierarchicalTree<T> getParent() {
        return parent;
    }

    public Collection<T> getSuccessors(T root) {
        Collection<T> successors = new ArrayList<T>();
        FlatToHierarchicalTree<T> tree = getTree(root);
        if (null != tree) {
            for (FlatToHierarchicalTree<T> leaf : tree.leafs) {
                successors.add(leaf.head);
            }
        }
        return successors;
    }

    public Collection<FlatToHierarchicalTree<T>> getSubTrees() {
        return leafs;
    }

    public static <T> Collection<T> getSuccessors(T of, Collection<FlatToHierarchicalTree<T>> in) {
        for (FlatToHierarchicalTree<T> tree : in) {
            if (tree.locate.containsKey(of)) {
                return tree.getSuccessors(of);
            }
        }
        return new ArrayList<T>();
    }

    @Override
    public String toString() {
        return printTree(0);
    }

    private static final int indent = 2;

    private String printTree(int increment) {
        String s = "";
        String inc = "";
        for (int i = 0; i < increment; ++i) {
            inc = inc + " ";
        }
        s = inc + head;
        for (FlatToHierarchicalTree<T> child : leafs) {
            s += "\n" + child.printTree(increment + indent);
        }
        return s;
    }

    public static void main(String[] args) {
        FlatToHierarchicalTree<Integer> tree = new FlatToHierarchicalTree<>(1);
        tree.addLeaf(1, 2);
        tree.addLeaf(2, 3);
        tree.addLeaf(3, 4);
        tree.addLeaf(3, 5);
        tree.addLeaf(2, 6);
        System.out.println(tree.printTree(2));
    }
}
