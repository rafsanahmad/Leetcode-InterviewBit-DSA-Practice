/*
 * *
 *  * Log Dump.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:57PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Problems

// Analyze log. Return unique username logs and remove id attribute
/*Problem Description — Unique User Log Analyzer
You are given a raw log string containing multiple user records.
Each user record is separated by a semicolon (;), and each field inside a record is separated by
a comma (,).
Every record contains key-value pairs such as name, username, email, and id.

Your task is to:
Parse the log string into individual user entries.
Remove the id field from each record.
Keep only unique users based on the username field (if the same username appears multiple times,
keep only the first occurrence).

Return a cleaned-up string containing unique user entries, each ending with a semicolon (;), and
separated by spaces.

Input Example
val log =
    """name=Dan B, username=db, email=db@gmail.com, id=123;
       name=Hannah, username=hsmith, id=333, email=hsm@test.com;
       name=Dan Brick, username=db, email=db@gmail.com, id=663;"""

Expected Output
name=Dan B, username=db, email=db@gmail.com; name=Hannah, username=hsmith, email=hsm@test.com;

Explanation
There are three user entries.
Two of them have the same username=db.
You remove all fields with the key id.
You keep only the first occurrence of each unique username.
You rejoin the cleaned-up entries into a single string.

Key Concepts
Regex splitting (\\s*,\\s* and \\s*;\\s*) to handle flexible spaces.
Filtering and mapping to process key-value pairs.
Distinct filtering using distinctBy { it.first } to keep unique usernames.
Sequence processing for efficiency on large log data.
*/

fun logDump(): String {
    val log =
        """name=Dan B, username=db, email=db@gmail.com, id=123; name=Hannah, username=hsmith, 
            id=333, email=hsm@test.com; name=Dan Brick, username=db, email=db@gmail.com, id=663;"""

    val comma = Regex("\\s*,\\s*")
    val semicolon = Regex("\\s*;\\s*")

    //asSequence() is optional
    return log.split(semicolon).asSequence().mapNotNull { userString ->
        var username: String? = null
        val filteredUserFieldString = userString.split(comma)
            .filter { fieldString -> // filter field strings not to include id
                val keyVal = fieldString.split("=")
                // check if array contains exactly 2 items
                if (keyVal.size == 2) {
                    // look for username
                    if (keyVal[0] == "username") {
                        username = keyVal[1]
                    }
                    // omit id field
                    keyVal[0] != "id" // return@filter
                } else {
                    false // return@filter
                }
            }.joinToString(separator = ", ") // join field back to string
        // omit fieldString without id and add ; to the end of fieldString
        if (username == null) null else Pair(
            username, "$filteredUserFieldString;"
        ) // return@mapNotNull
    }.distinctBy { it.first } // distinct by username
        .joinToString(separator = " ") { it.second }
}

//Alternative approach - Readability
fun logDumpApproach2(): String {
    val log = """
        name=Dan B, username=db, email=db@gmail.com, id=123;
        name=Hannah, username=hsmith, id=333, email=hsm@test.com;
        name=Dan Brick, username=db, email=db@gmail.com, id=663;
    """.trimIndent()

    val users = log.split(";")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    val seenUsernames = mutableSetOf<String>()
    val result = StringBuilder()

    for (user in users) {
        val fields = user.split(",").map { it.trim() }
        val fieldMap = mutableMapOf<String, String>()

        for (field in fields) {
            val parts = field.split("=")
            if (parts.size == 2) {
                val key = parts[0]
                val value = parts[1]
                fieldMap[key] = value
            }
        }

        val username = fieldMap["username"] ?: continue
        if (username in seenUsernames) continue
        seenUsernames.add(username)

        // Remove id field
        fieldMap.remove("id")

        // Reconstruct user info string
        val userString = fieldMap.entries.joinToString(", ") { "${it.key}=${it.value}" }
        result.append(userString).append("; ")
    }

    return result.toString()
}

fun main(args: Array<String>) {
    println(logDump())
    println(logDumpApproach2())
}