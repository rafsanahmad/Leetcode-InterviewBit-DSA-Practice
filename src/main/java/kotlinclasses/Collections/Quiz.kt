/*
 * *
 *  * Quiz.kt
 *  * Created by Rafsan Ahmad on 9/25/25, 12:02PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Collections

import kotlinclasses.Collections.Quiz.Employee

/*Give a list of employees with id, name, department, salary Print the employee name with
highest salary in each department*/

class Quiz {
    data class Employee(
        val id: Int,
        val name: String,
        val department: String,
        val salary: Double
    )
}

fun main() {
    /*output:
    IT -> Bob (90000.0)
    HR -> David (65000.0)
    Finance -> Eve (85000.0)
    */
    val employees = listOf(
        Employee(1, "Alice", "IT", 75000.0),
        Employee(2, "Bob", "IT", 90000.0),
        Employee(3, "Charlie", "HR", 60000.0),
        Employee(4, "David", "HR", 65000.0),
        Employee(5, "Eve", "Finance", 85000.0),
        Employee(6, "Frank", "Finance", 80000.0)
    )

    // Group employees by department and find highest salary in each
    val maxByDept = employees
        .groupBy { it.department }
        .mapValues { (_, emps) -> emps.maxByOrNull { it.salary } }

    // Print results
    maxByDept.forEach { (dept, emp) ->
        println("$dept -> ${emp?.name} (${emp?.salary})")
    }

    //{A=[Alice, Alex], B=[Bob], C=[Charlie]}
    val names = listOf("Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Alex")
    val grouped = names.groupBy { it.first() }
    println(grouped)
}