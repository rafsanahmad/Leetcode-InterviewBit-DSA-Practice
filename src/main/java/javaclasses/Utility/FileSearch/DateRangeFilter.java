/*
 * *
 *  * DateRangeFilter.java
 *  * Created by Rafsan Ahmad on 1/10/26, 1:00PM
 *  * Copyright (c) 2026 . All rights reserved.
 *  *
 *
 */

package javaclasses.Utility.FileSearch;

import java.io.File;

public class DateRangeFilter implements FileFilter {

    private final long start;
    private final long end;

    public DateRangeFilter(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean matches(File file) {
        long modified = file.lastModified();
        return modified >= start && modified <= end;
    }
}

