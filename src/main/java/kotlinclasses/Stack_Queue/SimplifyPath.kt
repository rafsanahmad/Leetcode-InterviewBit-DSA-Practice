/*
 * *
 *  * SimplifyPath.kt
 *  * Created by Rafsan Ahmad on 6/23/25, 6:05 AM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Stack_Queue

import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class SimplifyPath {
    //https://leetcode.com/problems/simplify-path/description/
    /*You are given an absolute path for a Unix-style file system, which always begins with a
    slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory
or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or
parent directories.
Return the simplified canonical path.



Example 1:
Input: path = "/home/"
Output: "/home"

Explanation:
The trailing slash should be removed.

Example 2:
Input: path = "/home//foo/"
Output: "/home/foo"

Explanation:
Multiple consecutive slashes are replaced by a single one.

Example 3:
Input: path = "/home/user/Documents/../Pictures"
Output: "/home/user/Pictures"

Explanation:
A double period ".." refers to the directory up a level (the parent directory).

Example 4:
Input: path = "/../"
Output: "/"

Explanation:
Going one level up from the root directory is not possible.

Example 5:
Input: path = "/.../a/../b/c/../d/./"
Output: "/.../b/d"

Explanation:
"..." is a valid name for a directory in this problem.


Constraints:
1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.*/

    fun simplifyPath(path: String): String {
        val stack: Stack<String> = Stack()
        val queue: Queue<String> = LinkedList()
        var builder = StringBuilder()
        var i = 0
        var len = path.length
        while (i < len) {
            if (path[i] == '/') {
                while (i + 1 < len && path[i + 1] == '/') {
                    i++
                    continue
                }

                val str = builder.toString()
                if (str == ".." && stack.isNotEmpty()) {
                    queue.remove(stack.pop())
                } else if (str.isNotEmpty() && str != "." && str != "..") {
                    stack.push(str)
                    queue.add(str)
                }
                builder = StringBuilder()
            } else {
                builder.append(path[i])
            }

            i++
        }

        if (builder.toString().isNotEmpty()) {
            val str = builder.toString()
            if (str == ".." && stack.isNotEmpty()) {
                queue.remove(stack.pop())
            } else if (str.isNotEmpty() && str != "." && str != "..") {
                queue.add(str)
            }
        }

        builder = StringBuilder()
        while (queue.isNotEmpty()) {
            builder.append("/")
            builder.append(queue.remove())
        }

        return if (builder.toString().isEmpty()) "/" else builder.toString()
    }

    //More Clean - Readable
    fun simplifyPath2(path: String): String {
        // Split the path by '/', this will give us parts of the path
        val parts = path.split("/")
        val stack = mutableListOf<String>()

        for (part in parts) {
            when {
                part.isEmpty() || part == "." -> {
                    // Skip empty parts and '.' which means current directory
                    continue
                }

                part == ".." -> {
                    // Go up one directory, if possible
                    if (stack.isNotEmpty()) {
                        stack.removeAt(stack.size - 1)
                    }
                }

                else -> {
                    // Valid directory name, add to the path
                    stack.add(part)
                }
            }
        }

        // Join the result with '/', and add leading '/' for root
        return "/" + stack.joinToString("/")
    }
}

fun main() {
    val obj = SimplifyPath()
    println(obj.simplifyPath("/home/"))
    println(obj.simplifyPath("/home//foo/"))
    println(obj.simplifyPath("/home/user/Documents/../Pictures"))
    println(obj.simplifyPath("/../"))
    println(obj.simplifyPath("/.../a/../b/c/../d/./"))

    println()
    println(obj.simplifyPath2("/home/"))
    println(obj.simplifyPath2("/home//foo/"))
    println(obj.simplifyPath2("/home/user/Documents/../Pictures"))
    println(obj.simplifyPath2("/../"))
    println(obj.simplifyPath2("/.../a/../b/c/../d/./"))
}