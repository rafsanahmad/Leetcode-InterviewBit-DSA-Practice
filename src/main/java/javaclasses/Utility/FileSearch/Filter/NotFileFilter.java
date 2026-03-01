/*
 *
 *  * NotFileFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 02/19/26, 11:59 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch.Filter;

import java.io.File;

public class NotFileFilter implements FileFilter {

    private final FileFilter filter;

    public NotFileFilter(FileFilter filter) {
        this.filter = filter;
    }

    @Override
    public boolean matches(File file) {
        return !filter.matches(file);
    }
}

