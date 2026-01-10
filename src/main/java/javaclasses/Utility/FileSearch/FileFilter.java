/*
 * *
 *  * FileFilter.java
 *  * Created by Rafsan Ahmad on 1/10/26, 12:51PM
 *  * Copyright (c) 2026 . All rights reserved.
 *  *
 *
 */

package javaclasses.Utility.FileSearch;

import java.io.File;

public interface FileFilter {
    boolean matches(File file);
}
