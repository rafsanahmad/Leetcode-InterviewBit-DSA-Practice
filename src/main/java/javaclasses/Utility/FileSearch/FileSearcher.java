/*
 *
 *  * FileSearcher.java
 *  *
 *  * Created by Rafsan Ahmad on 01/10/26, 1:22 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch;

import java.io.File;
import java.util.List;

public class FileSearcher {

    public static void search(File dir, FileFilter filter, List<File> result) {
        if (dir == null || !dir.exists()) return;

        if (dir.isFile()) {
            if (filter.matches(dir)) {
                result.add(dir);
            }
            return;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                search(file, filter, result);
            }
        }
    }
}

