/*
 * *
 *  * Supervisor Scope.kt
 *  * Created by Rafsan Ahmad on 5/26/25, 1:28PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Concepts.Coroutine

import kotlinx.coroutines.*

class SupervisorScope {
    /*What is supervisorScope?
In Kotlin Coroutines, supervisorScope is a coroutine builder that starts a new scope where
child coroutine failures do not cancel the parent or sibling coroutines. It's typically used
when you want to launch multiple child coroutines and handle their failures independently.

🔸 Benefits of supervisorScope
✅ Failure isolation – One child coroutine can fail without affecting others.
✅ Structured concurrency – Still adheres to coroutine scope hierarchy.
✅ Safe exception handling – Prevents a single exception from cancelling everything.
✅ Simplified error recovery – Handle errors locally without affecting unrelated logic.

🔸 Comparison: coroutineScope vs supervisorScope
Feature	coroutineScope	                                supervisorScope
Failure in one child Cancels all siblings + parent	    Affects only that child
Use-case All-or-nothing operations	                    Independent concurrent tasks
*/
}

/*coroutineScope – App-Specific Use Case
🔸 Use Case:
Imagine you're fetching user profile and user settings together, and both must succeed.
If one fails, cancel all.*/
class MyViewModel {
    suspend fun loadUserData() {
        try {
            coroutineScope {
                val profile = async { fetchUserProfile() }
                val settings = async { fetchUserSettings() }

                // Wait until both complete
                val userProfile = profile.await()
                val userSettings = settings.await()

                // Update UI state
                println("Profile: $userProfile, Settings: $userSettings")
            }
        } catch (e: Exception) {
            // If either fails, handle here
            println("Failed to load user data: ${e.message}")
        }
    }

    suspend fun fetchUserProfile(): String {
        delay(500)
        return "User Profile"
    }

    suspend fun fetchUserSettings(): String {
        delay(300)
        throw Exception("Settings failed!") // Will cancel profile too
    }
}

/*supervisorScope – App-Specific Use Case
🔸 Use Case:
You're uploading multiple images. If one fails, you still want to upload the rest and show
partial success.*/

class UploadViewModel {
    suspend fun uploadImages() {
        supervisorScope {
            val results = mutableListOf<String>()

            val jobs = listOf(
                async { uploadImage("image1.jpg") },
                async { uploadImage("image2.jpg") },
                async { uploadImage("image3.jpg") } // Let's say this fails
            )

            jobs.forEach { job ->
                try {
                    results.add(job.await())
                } catch (e: Exception) {
                    println("Upload failed: ${e.message}")
                }
            }

            println("Upload completed. Success: $results")
        }
    }

    suspend fun uploadImage(fileName: String): String {
        delay(300)
        if (fileName == "image3.jpg") throw Exception("Upload error: $fileName")
        return "$fileName uploaded"
    }
}


fun main() = runBlocking {
    //App specific example
    val viewModel = MyViewModel()
    /*Output:
    * Failed to load user data: Settings failed!
    * Because coroutineScope cancels all children if one fails, fetchUserProfile()
    * is also cancelled.*/
    viewModel.loadUserData()

    val viewModel2 = UploadViewModel()
    /*Output:
    * Upload failed: Upload error: image3.jpg
    * Upload completed. Success: [image1.jpg uploaded, image2.jpg uploaded]
    * Thanks to supervisorScope, the failure of image3.jpg does not cancel image1.jpg and
    * image2.jpg.* */
    viewModel2.uploadImages()

    println("Main starts")

    supervisorScope {
        val job1 = launch {
            try {
                println("Job1 starts")
                delay(100)
                throw Exception("Job1 failed!")
            } catch (e: Exception) {
                println("Job1 error: ${e.message}")
            }
        }

        val job2 = launch {
            println("Job2 starts")
            delay(300)
            println("Job2 completed")
        }

        // Wait for both jobs to finish
        joinAll(job1, job2)
    }


    /*Job4 still completes even after failure of Job3 using coroutineScope*/
    /*In the code, Job3 handles its own exception, so no exception escapes to the parent
    (coroutineScope). That means the parent coroutine is never cancelled, and Job4 continues
    to run normally.

🧠 Important principle: coroutineScope will cancel all children only if an unhandled
    exception escapes from any child.
    */
    coroutineScope {
        val job3 = launch {
            try {
                println("Job3 starts")
                delay(100)
                throw Exception("Job3 failed!")
            } catch (e: Exception) {
                println("Job3 error: ${e.message}")
            }
        }

        val job4 = launch {
            println("Job4 starts")
            delay(300)
            println("Job4 completed")
        }

        // Wait for both jobs to finish
        joinAll(job3, job4)
    }

    //Try removing the try-catch
    coroutineScope {
        val job5 = launch {
            println("Job5 starts")
            delay(100)
            throw Exception("Job5 failed!") // No try-catch
        }

        val job6 = launch {
            println("Job6 starts")
            delay(300)
            println("Job6 completed") // <-- This won't print
        }

        joinAll(job5, job6)
    }

    println("Main ends")

    /*Output:
    * Main starts
Job1 starts
Job2 starts
Job1 error: Job1 failed!
Job2 completed
Job3 starts
Job4 starts
Job3 error: Job3 failed!
Job4 completed
Job5 starts
Job6 starts
Exception in thread "main" java.lang.Exception: Job5 failed!
* */
}