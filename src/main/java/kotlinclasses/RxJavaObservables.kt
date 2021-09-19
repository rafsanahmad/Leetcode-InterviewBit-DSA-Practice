package kotlinclasses

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxJavaObservables {
    fun threadNames() {
        val myRandomObservable = Observable.just("")
            .subscribeOn(Schedulers.computation())
            .doOnNext {
                println("[1] Which thread? -> " + Thread.currentThread().name)
            }
            .doOnNext {
                println("[2] Which thread? -> " + Thread.currentThread().name)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.io())
            .doOnNext {
                println("[3] Which thread? -> " + Thread.currentThread().name)
            }
            .observeOn(Schedulers.single())
            .debounce(400L, TimeUnit.MILLISECONDS)
            .subscribe {
                println("[4] Which thread? -> " + Thread.currentThread().name)
            }
    }

    fun startRStream() {
        //Create an Observable
        val myObservable = getObservable()

        //Create an Observer
        val myObserver = getObserver()

        //Subscribe myObserver to myObservable
        myObservable.subscribe(myObserver)
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            //Every time onNext is called, print the value to Android Studio’s Logcat
            override fun onNext(s: String) {
                println("onNext: $s")
            }

            //Called if an exception is thrown
            override fun onError(e: Throwable) {
                println("onError: " + e.message)
            }

            //When onComplete is called, print the following to Logcat
            override fun onComplete() {
                println("onComplete")
            }
        }
    }

    //Give myObservable some data to emit
    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }
}
class Cat (name: String) {
    var name = name;
    fun greet() { println("Hello ${name}") }
}

fun main(args: Array<String>) {
    val rxJavaObservables = RxJavaObservables()
    //rxJavaObservables.threadNames()
    rxJavaObservables.startRStream()
    val thunderCat = Cat("ThunderCat")
    thunderCat.greet()
}