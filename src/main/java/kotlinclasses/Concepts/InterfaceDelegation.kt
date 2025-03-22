/*
 * *
 *  * InterfaceDelegation.kt
 *  * Created by Rafsan Ahmad on 3/21/25, 1:50PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts

import kotlinclasses.Concepts.InterfaceDelegation.Application
import kotlinclasses.Concepts.InterfaceDelegation.ConsoleLogger

class InterfaceDelegation {
    // Define an interface
    interface Logger {
        fun log(message: String)
    }

    // Implement the interface
    class ConsoleLogger : Logger {
        override fun log(message: String) {
            println("Logging to console: $message")
        }
    }

    // Delegate the implementation of Logger to ConsoleLogger
    // Using By Keyword to delegate interface implementation
    class Application(logger: Logger) : Logger by logger
}

fun main() {
    val consoleLogger = ConsoleLogger()
    val app = Application(consoleLogger)

    // Calls log() method of ConsoleLogger via delegation
    app.log("Application started")
}