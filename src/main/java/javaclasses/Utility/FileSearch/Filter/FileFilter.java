/*
 *
 *  * FileFilter.java
 *  *
 *  * Created by Rafsan Ahmad on 02/19/26, 11:59 AM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch.Filter;

import java.io.File;

public interface FileFilter {
    boolean matches(File file);
}
