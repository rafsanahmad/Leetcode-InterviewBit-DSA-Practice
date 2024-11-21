/*
 * *
 *  * NameFilter.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:14 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi.Filter;

import javaclasses.OOPDesign.UnixFileApi.File;

public class NameFilter extends Filter {
    private String name;

    public NameFilter(String name) {
        this.name = name;
    }

    @Override
    public boolean match(File file) {
        return file.getName().equals(name);
    }
}
