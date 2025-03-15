/*
 * *
 *  * LogDump.kt
 *  * Created by Rafsan Ahmad on 3/15/25, 7:57PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Problems

// Analyze log. Return unique username logs and remove id attribute
fun logDump(): String {
    val log =
        "name=Dan B, username=db, email=db@gmail.com, id=123; name=Hannah, username=hsmith, id=333, email=hsm@test.com; name=Dan Brick, username=db, email=db@gmail.com, id=663;"

    val comma = Regex("\\s*,\\s*")
    val semicolon = Regex("\\s*;\\s*")

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

inline fun <T : Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}

inline fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> safeLet(
    p1: T1?, p2: T2?, p3: T3?, p4: T4?, block: (T1, T2, T3, T4) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}

fun main(args: Array<String>) {
    println(logDump())
}