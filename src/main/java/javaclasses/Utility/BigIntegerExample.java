/*
 * * Big Integer Example.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

import java.math.BigInteger;

public class BigIntegerExample {
    //Calculate Long Factorials

    public BigInteger getFactorial(int A) {
        BigInteger fact = new BigInteger("1");
        while (A > 0) {
            fact = fact.multiply(BigInteger.valueOf(A));
            A--;
        }
        return fact;
    }

    public int getTrailingZeros(BigInteger num) {
        int result = 0;
        while (num.compareTo(BigInteger.ZERO) > 0) {
            if (num.mod(BigInteger.valueOf(10)).equals(BigInteger.ZERO)) {
                ++result;
            } else {
                break;
            }
            num = num.divide(BigInteger.valueOf(10));
        }
        return result;
    }

    public static void main(String[] args) {
        BigIntegerExample example = new BigIntegerExample();
        BigInteger factorial = example.getFactorial(100);
        System.out.println(factorial);
        System.out.println(example.getTrailingZeros(factorial));
    }
}
