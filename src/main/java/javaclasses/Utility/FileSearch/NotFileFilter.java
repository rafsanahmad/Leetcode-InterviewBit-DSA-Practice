/*
 *
 *  * NotFileFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 01/10/26, 1:24 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch;

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

