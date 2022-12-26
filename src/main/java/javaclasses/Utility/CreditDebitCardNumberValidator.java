/*
 * *
 *  * CreditDebitCardNumberValidator.java
 *  * Created by Rafsan Ahmad on 12/26/22, 11:00 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.util.TreeMap;

public class CreditDebitCardNumberValidator {
    /*Card numbers are 16 digit numbers like 4444 4444 4444 4444. BIN numbers are first few digits of card numbers
    like 4444 4444 44. The inputs given initially are card types for the range of BIN numbers.
    A cache has to be built initially and then given a card number, card type is to be returned,
    I assume in approximately O(1).
Ex:
Input:
BIN range object =
[["4444 4444 11", "4444 4444 44", "Visa credit"],
["4500 0000 55", "4999 9999 00", "Visa debit"],
["4999 9999 99", "5555 0000 00", "Master credit"],
["6666 4444 11", "7777 0000 00", "Amex"]].

CardNumber: 4733 6109 7901 2139
Output: Visa debit

Explanation: BIN for 4733 6109 7901 2139 is 4733 6109 79 which falls between BIN range of visa debit
(4500 0000 55 to 4999 9999 00).
BIN range object is an array of array where each internal array, array[0] is start of range, array[1] is end of range
and array[2] is card type for that range. Note that different card types need not have continous BIN range -
for example: if a cardNumber 6000 0000 0000 0000 is given, it does not belong to any card types and hence null is to be
returned.
It was unclear in the question if BIN range given in BIN range object would always be 10 digits or there can BIN range
of 9 digits or 12 digits or so on... I assumed it would be fixed to 10 digits and continued to code.
(Many card numbers will be given and respective card type or null is to be returned)*/

    class Bin {
        String start;
        String end;
        String type;

        public Bin(String start, String end, String type) {
            this.start = start;
            this.end = end;
            this.type = type;
        }
    }

    TreeMap<String, Bin> binDb;
    int l = Integer.MAX_VALUE;

    public void buildDb(String[][] bins) {
        this.binDb = new TreeMap<String, Bin>((a, b) -> a.compareTo(b));
        for (String[] b : bins) {
            l = Math.min(l, b[0].length());
            Bin bin = new Bin(b[0], b[1], b[2]);
            binDb.put(b[0], bin);
        }
    }

    public String matchCard(String cardNo) {
        cardNo = cardNo.substring(0, 0 + l);
        String floorKey = binDb.floorKey(cardNo);
        String ceilKey = binDb.ceilingKey(cardNo);
        if (floorKey != null) {
            Bin bin = binDb.get(floorKey);
            if (isBinMatch(cardNo, bin)) {
                return bin.type;
            }
        }
        if (ceilKey != null) {
            Bin bin = binDb.get(ceilKey);
            if (isBinMatch(cardNo, bin)) {
                return bin.type;
            }
        }
        return null;
    }

    public boolean isBinMatch(String cardNo, Bin bin) {
        return cardNo.compareTo(bin.start) >= 0 && cardNo.compareTo(bin.end) <= 0;
    }

    public static void main(String[] args) {

        CreditDebitCardNumberValidator validator = new CreditDebitCardNumberValidator();

        String[][] bins = {
                {"4444 4444 11", "4444 4444 44", "Visa credit"},
                {"4500 0000 55", "4999 9999 00", "Visa debit"},
                {"4999 9999 99", "5555 0000 00", "Master credit"},
                {"6666 4444 11", "7777 0000 00", "Amex"}
        };

        validator.buildDb(bins);
        assert (validator.matchCard("4733 6109 7901 2139") == "Visa debit");
        assert (validator.matchCard("6000 0000 0000 0000") == null);
        System.out.println("done");
    }
}
