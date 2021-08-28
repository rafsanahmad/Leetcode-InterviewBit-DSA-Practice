package javaclasses.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiCustomComparator {
    static class Transaction {
        int count;
        String name;

        Transaction(int count, String name) {
            this.count = count;
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public String getName() {
            return name;
        }
    }

    public static List<String> groupTransactions(List<String> transactions) {
        Map<String, Integer> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        for (String s : transactions) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        //Create Transaction object
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String name = entry.getKey();
            Integer count = entry.getValue();
            Transaction transaction = new Transaction(count, name);
            transactionList.add(transaction);
        }

        //Sort Transaction list
        Collections.sort(transactionList, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                if (t1.getCount() != t2.getCount()) {
                    return t2.getCount() - t1.getCount();  //Descending
                }
                return t1.getName().compareTo(t2.getName());
            }
        });

        //Generate space separated result list
        for (Transaction t : transactionList) {
            String str = t.getName() + " " + t.getCount();
            result.add(str);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("banana");
        list.add("apple");
        list.add("pear");
        list.add("apple");
        list.add("banana");
        System.out.println(groupTransactions(list));
    }
}
