/*
 * *
 *  * Associate By Group By.kt
 *  * Created by Rafsan Ahmad on 9/29/25, 3:10PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Collections;

class AssociateByGroupBy {
    /*associateBy is another super useful Kotlin collection function (kind of like the
    cousin of groupBy).

What associateBy Does:
It creates a Map<K, V> from a collection by:
Choosing a key from each element (via a selector function).
Keeping the element itself as the value (unless you also specify a value selector).

Unlike groupBy (which groups into lists), associateBy keeps only one value per key.
If two elements map to the same key, the last one wins.*/
}

fun main() {
    data class Employee(val id: Int, val name: String)

    //Example 1: Index elements by property
    val employees = listOf(
        Employee(1, "Alice"),
        Employee(2, "Bob"),
        Employee(3, "Charlie")
    )

    // Create map: id -> employee
    val empMap = employees.associateBy { it.id }

    println(empMap)
    // {1=Employee(id=1, name=Alice), 2=Employee(id=2, name=Bob),
    // 3=Employee(id=3, name=Charlie)}

    //Example 2: Custom key and value
    val words = listOf("apple", "banana", "cherry")

    // Key = first letter, Value = word length
    val result = words.associateBy(
        keySelector = { it.first() },
        valueTransform = { it.length }
    )

    println(result)
    // {a=5, b=6, c=6}

    //Example 3: Difference from groupBy
    //groupBy → collects all values per key (List).
    //associateBy → keeps last value per key.
    val words2 = listOf("apple", "apricot", "banana")

    val group = words2.groupBy { it.first() }
    println(group)
    // {a=[apple, apricot], b=[banana]}

    val assoc = words2.associateBy { it.first() }
    println(assoc)
    // {a=apricot, b=banana}   (apple overwritten by apricot)
}