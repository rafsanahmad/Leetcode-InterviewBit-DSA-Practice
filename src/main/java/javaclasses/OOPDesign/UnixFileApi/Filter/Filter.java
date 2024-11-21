/*
 * *
 *  * Filter.java
 *  * Created by Rafsan Ahmad on 11/21/24, 1:14 PM
 *  * Copyright (c) 2024 . All rights reserved.
 *  *
 *
 */

package javaclasses.OOPDesign.UnixFileApi.Filter;

import javaclasses.OOPDesign.UnixFileApi.File;

public abstract class Filter {
    public abstract boolean match(File file);
}
