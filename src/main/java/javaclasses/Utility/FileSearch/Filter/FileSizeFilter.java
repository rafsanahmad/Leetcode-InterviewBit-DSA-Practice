/*
 *
 *  * FileSizeFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 02/19/26, 11:59 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch.Filter;

import java.io.File;

public class FileSizeFilter implements FileFilter {

    private final long min;
    private final long max;

    public FileSizeFilter(long min, long max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean matches(File file) {
        long size = file.length();
        return size >= min && size <= max;
    }
}

