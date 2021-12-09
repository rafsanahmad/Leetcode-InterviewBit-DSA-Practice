/*
 * * Mobile Keyboard.java
 *  * Created by Rafsan Ahmad on 10/25/21, 9:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package javaclasses.Utility;

public class MobileKeyboard {

    /* C -> Caps Lock toggle
       B -> Delete last Character
    */
    //System.out.println(MobileKeyboard.mobileKeyboard("CrCellBax")) - Prints "Relax"
    public String mobileKeyboard(String S) {
        StringBuilder stringBuilder = new StringBuilder("");
        boolean capsLockStatus = false;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == 'C' || ch == 'B') {
                switch (ch) {
                    case 'C':
                        capsLockStatus = !capsLockStatus;
                        break;
                    case 'B':
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        break;

                }
                continue;
            }
            if (capsLockStatus) {
                stringBuilder.append(Character.toUpperCase(ch));
            } else {
                stringBuilder.append(Character.toLowerCase(ch));
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        MobileKeyboard keyboard = new MobileKeyboard();
        System.out.println(keyboard.mobileKeyboard("CrCellBax"));
    }


}
