/*
 * *
 *  * File.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:12 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi;

public class File {
    private String name;
    private String extension;
    private int size;

    public File(String name, String extension, int size) {
        this.name = name;
        this.extension = extension;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public int getSize() {
        return size;
    }
}
