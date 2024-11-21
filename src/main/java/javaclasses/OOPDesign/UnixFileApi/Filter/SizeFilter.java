/*
 * *
 *  * SizeFilter.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:14 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi.Filter;

import javaclasses.OOPDesign.UnixFileApi.File;

public class SizeFilter extends Filter {
    private int size;
    private String operator;

    public SizeFilter(int size, String operator) {
        this.size = size;
        this.operator = operator;
    }

    @Override
    public boolean match(File file) {
        switch (operator) {
            case ">":
                return file.getSize() > size;
            case ">=":
                return file.getSize() >= size;
            case "<":
                return file.getSize() < size;
            case "<=":
                return file.getSize() <= size;
            case "==":
                return file.getSize() == size;
            default:
                return false;
        }
    }
}

