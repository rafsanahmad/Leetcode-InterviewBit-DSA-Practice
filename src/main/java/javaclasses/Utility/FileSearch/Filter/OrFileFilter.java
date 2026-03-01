/*
 *
 *  * OrFileFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 02/19/26, 11:59 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch.Filter;

import java.io.File;
import java.util.List;

public class OrFileFilter implements FileFilter {

    private final List<FileFilter> filters;

    public OrFileFilter(List<FileFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(File file) {
        for (FileFilter filter : filters) {
            if (filter.matches(file)) {
                return true;
            }
        }
        return false;
    }
}

