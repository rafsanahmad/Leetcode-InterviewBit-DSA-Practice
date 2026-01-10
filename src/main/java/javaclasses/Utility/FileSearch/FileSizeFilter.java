/*
 * *
 *  * FileSizeFilter.java
 *  * Created by Rafsan Ahmad on 1/10/26, 1:00PM
 *  * Copyright (c) 2026 . All rights reserved.
 *  *
 *
 */

package javaclasses.Utility.FileSearch;

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

