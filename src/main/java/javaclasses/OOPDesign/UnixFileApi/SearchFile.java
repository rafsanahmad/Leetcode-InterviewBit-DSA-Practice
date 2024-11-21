/*
 * *
 *  * SearchFile.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:15 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javaclasses.OOPDesign.UnixFileApi.Filter.ExtensionFilter;
import javaclasses.OOPDesign.UnixFileApi.Filter.Filter;
import javaclasses.OOPDesign.UnixFileApi.Filter.NameFilter;
import javaclasses.OOPDesign.UnixFileApi.Filter.SizeFilter;

public class SearchFile {
    private FileSystem directory;
    private List<Filter> filters;
    private String condition;

    public SearchFile(FileSystem directory, Map<String, Object> filters, String condition) {
        this.directory = directory;
        this.filters = new ArrayList<>();
        this.condition = condition;

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String filterType = entry.getKey();
            Object filterValue = entry.getValue();
            switch (filterType) {
                case "NameFilter":
                    this.filters.add(new NameFilter((String) filterValue));
                    break;
                case "SizeFilter":
                    Object[] sizeProperties = (Object[]) filterValue;
                    this.filters.add(new SizeFilter((Integer) sizeProperties[0], (String) sizeProperties[1]));
                    break;
                case "ExtensionFilter":
                    this.filters.add(new ExtensionFilter((String) filterValue));
                    break;
            }
        }
    }

    public boolean checkConditions(File file) {
        if (condition == null) {
            return filters.get(0).match(file);
        } else if (condition.equals("AND")) {
            for (Filter filter : filters) {
                if (!filter.match(file)) {
                    return false;
                }
            }
            return true;
        } else if (condition.equals("OR")) {
            for (Filter filter : filters) {
                if (filter.match(file)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public List<String> findFiles() {
        List<String> result = new ArrayList<>();
        Queue<FileSystem> queue = new LinkedList<>();
        queue.add(directory);

        while (!queue.isEmpty()) {
            FileSystem current = queue.poll();
            for (File file : current.getFiles()) {
                if (checkConditions(file)) {
                    result.add(file.getName());
                }
            }
            for (FileSystem subDirectory : current.getSubDirectories()) {
                queue.add(subDirectory);
            }
        }
        return result;
    }
}
