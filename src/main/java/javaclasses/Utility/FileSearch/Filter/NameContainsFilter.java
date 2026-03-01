/*
 *
 *  * NameContainsFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 02/19/26, 11:59 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch.Filter;

import java.io.File;

public class NameContainsFilter implements FileFilter {

    private final String keyword;

    public NameContainsFilter(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean matches(File file) {
        return file.getName().toLowerCase().contains(keyword);
    }
}

