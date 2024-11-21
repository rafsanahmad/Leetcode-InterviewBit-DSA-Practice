/*
 * *
 *  * FileSystem.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:15 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    private String name;
    private boolean isDirectory;
    private List<FileSystem> subDirectories;
    private List<File> files;

    public FileSystem(String name, boolean isDirectory) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.subDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void addSubDirectory(FileSystem subDirectory) {
        this.subDirectories.add(subDirectory);
    }

    public List<FileSystem> getSubDirectories() {
        return subDirectories;
    }

    public List<File> getFiles() {
        return files;
    }

    public String getName() {
        return name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }
}
