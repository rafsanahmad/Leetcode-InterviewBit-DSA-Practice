/*
 *
 *  * FileFilterBuilder.java
 *  *
 *  * Created by Rafsan Ahmad on 01/10/26, 1:21 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FileFilterBuilder {

    private final List<FileFilter> andFilters = new ArrayList<>();

    private FileFilterBuilder() {
    }

    public static FileFilterBuilder create() {
        return new FileFilterBuilder();
    }

    // ----- BASIC FILTERS -----

    public FileFilterBuilder nameContains(String keyword) {
        andFilters.add(new NameContainsFilter(keyword));
        return this;
    }

    public FileFilterBuilder sizeBetween(long min, long max) {
        andFilters.add(new FileSizeFilter(min, max));
        return this;
    }

    public FileFilterBuilder modifiedBetween(long start, long end) {
        andFilters.add(new DateRangeFilter(start, end));
        return this;
    }

    // ----- LOGICAL OPERATORS -----

    public FileFilterBuilder or(Consumer<FileFilterBuilder> orGroup) {
        FileFilterBuilder nested = new FileFilterBuilder();
        orGroup.accept(nested);

        andFilters.add(new OrFileFilter(nested.andFilters));
        return this;
    }

    public FileFilterBuilder not(Consumer<FileFilterBuilder> notGroup) {
        FileFilterBuilder nested = new FileFilterBuilder();
        notGroup.accept(nested);

        FileFilter inner = new CompositeFileFilter(nested.andFilters);
        andFilters.add(new NotFileFilter(inner));
        return this;
    }

    public FileFilter build() {
        return new CompositeFileFilter(List.copyOf(andFilters)); // immutable
    }
}
