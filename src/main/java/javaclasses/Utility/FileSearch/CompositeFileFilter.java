/*
 * *
 *  * CompositeFileFilter.java
 *  * Created by Rafsan Ahmad on 1/10/26, 1:01PM
 *  * Copyright (c) 2026 . All rights reserved.
 *  *
 *
 */

package javaclasses.Utility.FileSearch;

import java.io.File;
import java.util.List;

// AND FILTER
public class CompositeFileFilter implements FileFilter {

    private final List<FileFilter> filters;

    public CompositeFileFilter(List<FileFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(File file) {
        for (FileFilter filter : filters) {
            if (!filter.matches(file)) {
                return false;
            }
        }
        return true;
    }
}

