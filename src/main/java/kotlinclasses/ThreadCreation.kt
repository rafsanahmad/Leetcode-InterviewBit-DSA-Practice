package kotlinclasses

class ThreadCreation {
    // Extending the Thread class to implement threads.
    class SimpleThread: Thread() {
        public override fun run() {
            println("${Thread.currentThread()} has run.")
        }
    }
    // Implementing the Runnable interface to implement threads.
    class SimpleRunnable: Runnable {
        public override fun run() {
            println("${Thread.currentThread()} has run.")
        }
    }
}

fun main(args: Array<String>) {
    val thread = ThreadCreation.SimpleThread()
    thread.start() // Will output: Thread[Thread-0,5,main] has run.
    val runnable = ThreadCreation.SimpleRunnable()
    val thread1 = Thread(runnable)
    thread1.start() // Will output: Thread[Thread-1,5,main] has run
}