/*
 *
 *  * FileSearchMain.java
 *  *
 *  * Created by Rafsan Ahmad on 01/10/26, 1:22 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.Utility.FileSearch;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FileSearchMain {

    public static void main(String[] args) {

        long start = LocalDate.of(2025, 1, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

        long end = System.currentTimeMillis();

        /*
         * Filter logic:
         * (
         *   name contains "Prompt"
         *   AND size between 1KB and 10MB
         *   AND modified between start and end date
         *   AND (name contains "prod" OR name contains "stage")
         *   AND NOT (name contains "backup")
         * )
         */
        FileFilter filter = FileFilterBuilder.create()
                .nameContains("prompt")                       // AND
                .sizeBetween(1024, 10 * 1024 * 1024)           // AND
                .modifiedBetween(start, end)                  // AND
                .or(or -> or                                  // AND (OR group)
                        .nameContains("prod")
                        .nameContains("Prompt")
                )
                .not(not -> not                               // AND (NOT group)
                        .nameContains("backup")
                )
                .build();

        List<File> results = new ArrayList<>();

        // Simple recursive (single-threaded) search
        FileSearcher.search(new File("C:\\Users\\User\\Downloads"), filter, results);

        results.forEach(file ->
                System.out.println(file.getAbsolutePath())
        );
    }
}

