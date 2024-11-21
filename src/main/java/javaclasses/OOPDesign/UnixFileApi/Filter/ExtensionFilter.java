/*
 * *
 *  * ExtensionFilter.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:14 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi.Filter;

import javaclasses.OOPDesign.UnixFileApi.File;

public class ExtensionFilter extends Filter {
    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean match(File file) {
        return file.getExtension().equals(extension);
    }
}

