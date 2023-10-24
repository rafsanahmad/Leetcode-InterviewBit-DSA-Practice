/*
 * *
 *  * Next Power Of 2.java
 *  * Created by Rafsan Ahmad on 10/25/23, 2:06 AM
 *  * Copyright (c) 2023 . All rights reserved.
 *  *
 *
 */

package javaclasses.BitManipulation;

public class NextPowerOf2 {

    public int nextPowerOf2(int num) {
        if (num == 0) {
            return 1;
        }
        if (num > 0 && (num & (num - 1)) == 0) {
            return num;
        }
        while ((num & (num - 1)) > 0) {
            num = num & (num - 1);
        }
        return num << 1;
    }

    public static void main(String args[]) {
        NextPowerOf2 np = new NextPowerOf2();
        System.out.println(np.nextPowerOf2(4));
    }
}
