/*
 *
 *  * OrFileFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 01/10/26, 1:23 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch;

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

