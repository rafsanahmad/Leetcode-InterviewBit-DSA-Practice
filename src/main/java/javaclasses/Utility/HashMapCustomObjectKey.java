/*
 * *
 *  * HashMapCustomObjectKey.java
 *  * Created by Rafsan Ahmad on 3/9/24, 11:20 AM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.Utility;

import java.util.HashMap;

class Account {
    private int accountNumber;
    private String holderName;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    //Depends only on account number
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accountNumber;
        return result;
    }

    //Compare only account numbers
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (accountNumber != other.accountNumber)
            return false;
        return true;
    }

}

public class HashMapCustomObjectKey {

    public static void main(String[] args) {
        //Create a HashMap with mutable key
        HashMap<Account, String> map = new HashMap<Account, String>();

//Create key 1
        Account a1 = new Account(1);
        a1.setHolderName("A_ONE");
//Create key 2
        Account a2 = new Account(2);
        a2.setHolderName("A_TWO");

//Put mutable key and value in map
        map.put(a1, a1.getHolderName());
        map.put(a2, a2.getHolderName());

//Change the keys state so hash map should be calculated again
        a1.setHolderName("Defaulter");
        a2.setHolderName("Bankrupt");

//Success !! We are able to get back the values
        System.out.println(map.get(a1)); //Prints A_ONE
        System.out.println(map.get(a2)); //Prints A_TWO

//Try with newly created key with same account number
        Account a3 = new Account(1);
        a3.setHolderName("A_THREE");

//Success !! We are still able to get back the value for account number 1
        System.out.println(map.get(a3)); //Prints A_ONE

        map.put(a3, a3.getHolderName());
        System.out.println(map.get(a3)); //Prints A_THREE
    }
}
