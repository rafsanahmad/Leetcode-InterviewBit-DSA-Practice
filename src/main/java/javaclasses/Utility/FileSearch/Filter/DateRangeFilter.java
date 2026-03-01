/*
 *
 *  * DateRangeFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 02/19/26, 11:59 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch.Filter;

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

