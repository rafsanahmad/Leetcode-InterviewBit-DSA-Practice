/*
 * *
 *  * KotlinCollectionFunctions.kt
 *  * Created by Rafsan Ahmad on 12/14/21, 2:09 AM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package kotlinclasses

data class User(
    val id: Int,
    val name: String,
    val isCricketLover: Boolean,
    val isFootballLover: Boolean
)

data class User2(
    val id: Int,
    val name: String
)

data class User3(
    val id: Int,
    val name: String,
    val isFootballLover: Boolean
)

class Items(val items: List<String>)

data class Contact(val name: String, val phoneNumber: String)

class KotlinCollectionFunctions {

    //Distinct(), toSet()
    fun removeDuplicate() {
        // Maintain the original order of items
        val devs = arrayOf("Amit", "Ali", "Amit", "Sumit", "Sumit", "Himanshu")
        println(devs.distinct()) // [Amit, Ali, Sumit, Himanshu]

        // Maintain the original order of items
        println(devs.toSet()) // [Amit, Ali, Sumit, Himanshu]

        // Maintain the original order of items
        println(devs.toMutableSet()) // [Amit, Ali, Sumit, Himanshu]

        // DO NOT Maintain the original order of items
        println(devs.toHashSet()) // [Amit, Ali, Sumit, Himanshu]
    }

    //Convert an array or list to a string
    fun joinToString() {
        val someKotlinCollectionFunctions = listOf(
            "distinct", "map",
            "isEmpty", "contains",
            "filter", "first",
            "last", "reduce",
            "single", "joinToString"
        )

        val message = someKotlinCollectionFunctions.joinToString(
            separator = ", ",
            prefix = "Kotlin has many collection functions like: ",
            postfix = "and they are awesome.",
            limit = 3,
            truncated = "etc "
        )

        println(message) // Kotlin has many collection functions like: distinct, map, isEmpty, etc and they are awesome.
    }

    //Transform a collection into a single result
    fun reduceExample() {
        // NOTE: If the list is empty, then it will throw a RuntimeException
        val numList = listOf(1, 2, 3, 4, 5)
        val result = numList.reduce { result, item ->
            result + item
        }
        println(result) // 15
    }

    //Find if all elements are satisfying a particular condition
    fun allExample() {
        val user1 = User(id = 1, name = "Amit", isCricketLover = true, isFootballLover = true)
        val user2 = User(id = 2, name = "Ali", isCricketLover = true, isFootballLover = true)
        val user3 = User(id = 3, name = "Sumit", isCricketLover = true, isFootballLover = false)
        val user4 = User(id = 4, name = "Himanshu", isCricketLover = true, isFootballLover = false)

        val users = arrayOf(user1, user2, user3, user4)

        val allLoveCricket = users.all { it.isCricketLover }
        println(allLoveCricket) // true

        val allLoveFootball = users.all { it.isFootballLover }
        println(allLoveFootball) // false
    }

    //Find a particular element based on a certain condition
    fun singleExample() {
        val users = arrayOf(
            User2(1, "Amit"),
            User2(2, "Ali"),
            User2(3, "Sumit"),
            User2(4, "Himanshu")
        )

        val userWithId3 = users.single { it.id == 3 }
        println(userWithId3) // User(id=3, name=Sumit)

        val userWithId1 = users.find { it.id == 1 }
        println(userWithId1) // User(id=1, name=Amit)
    }

    //Break your list into multiple sublists of smaller size
    fun chunkedExample() {
        val numList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val chunkedLists = numList.chunked(3)
        print(chunkedLists) // [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10]]
        println()
    }

    //Making copies of the array
    fun copyArray() {
        val arrayOne = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val arrayTwo = arrayOf(11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        arrayOne.copyInto(
            destination = arrayTwo,
            destinationOffset = 2,
            startIndex = 0,
            endIndex = 4
        )
        arrayTwo.forEach { print("$it ") } // 11 12 1 2 3 4 17 18 19 20
        println()
    }

    //Changing type of collection to other
    fun changeTypeExample() {
        var uIntArray = UIntArray(5) { 1U }
        var intArray = uIntArray.toIntArray()
        intArray[0] = 0
        println(uIntArray.toList()) // [1, 1, 1, 1, 1]
        println(intArray.toList()) // [0, 1, 1, 1, 1]
    }

    //Associating the data using some key
    fun associateByExample() {
        val contactList = listOf(
            Contact("Amit", "+9199XXXX1111"),
            Contact("Ali", "+9199XXXX2222"),
            Contact("Himanshu", "+9199XXXX3333"),
            Contact("Sumit", "+9199XXXX4444")
        )

        val phoneNumberToContactMap = contactList.associateBy { it.phoneNumber }
        println(phoneNumberToContactMap)
        // Map with key: phoneNumber and value: Contact
        // {
        //     +9199XXXX1111=Contact(name=Amit, phoneNumber=+9199XXXX1111),
        //     +9199XXXX2222=Contact(name=Ali, phoneNumber=+9199XXXX2222),
        //     +9199XXXX3333=Contact(name=Himanshu, phoneNumber=+9199XXXX3333),
        //     +9199XXXX4444=Contact(name=Sumit, phoneNumber=+9199XXXX4444)
        // }

        val phoneNumberToContactMap2 = contactList.associateBy({ it.phoneNumber }, { it.name })
        println(phoneNumberToContactMap2)
        // Map with key: phoneNumber and value: name
        // {
        //     +9199XXXX1111=Amit,
        //     +9199XXXX2222=Ali,
        //     +9199XXXX3333=Himanshu,
        //     +9199XXXX4444=Sumit}
        // }
    }

    //Union of collections
    fun unionExample() {
        val listOne = listOf(1, 2, 3, 3, 4, 5, 6)
        val listTwo = listOf(2, 2, 4, 5, 6, 7, 8)
        println(listOne.union(listTwo)) // [1, 2, 3, 4, 5, 6, 7, 8]
    }

    //Intersection of collections
    fun intersectionExample() {
        val listOne = listOf(1, 2, 3, 3, 4, 5, 6)
        val listTwo = listOf(2, 2, 4, 5, 6, 7, 8)
        println(listOne.intersect(listTwo)) // [2, 4, 5, 6]
    }

    //Keep the specified elements only
    fun retain_removeExample() {
        val listOne = mutableListOf(1, 2, 3, 3, 4, 5, 6)
        val listTwo = listOf(1, 2, 3, 3, 4, 5, 6)
        val listThree = listOf(1, 2, 3, 3, 4, 5, 7)
        println(listOne.retainAll(listTwo)) // false
        println(listOne.retainAll(listThree)) // true
        println(listOne) // [1, 2, 3, 3, 4, 5]
        //you can use removeAll to remove all the elements of one collection that are present in another collection.
    }

    //Filter a collection based on some condition
    fun filterExample() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        val filteredList = list.filter { it % 2 == 0 }
        println(filteredList) // [2, 4, 6, 8]

        //Similarly, you can filter the collection based on the index of elements by using filterIndexed.

        //If you want to store the filtered elements in some collection, then you can use the filterIndexedTo:

        val list2 = listOf(1, 2, 3, 4, 5, 6, 7, 8)
        val filteredList2 = mutableListOf<Int>()
        list2.filterIndexedTo(filteredList2) { index, i -> list[index] % 2 == 0 }
        println(filteredList2) // [2, 4, 6, 8]

        //You can also find the elements that are instances of a specified type in a collection by using
        // filterIsInstance.

        val mixedList = listOf(1, 2, 3, "one", "two", 4, "three", "four", 5, 6, "five", 7)
        val strList = mixedList.filterIsInstance<String>()
        println(strList) // [one, two, three, four, five]
    }

    //Zip collections
    fun zipExample() {
        val listOne = listOf(1, 2, 3, 4, 5)
        val listTwo = listOf("a", "b", "c", "d", "e", "f")
        println(listOne zip listTwo) // [(1, a), (2, b), (3, c), (4, d), (5, e)]

        //zipWithNext return a list of pairs. The elements of the pair will be the adjacent elements of the collection.
        val list = listOf(1, 2, 3, 4, 5)
        println(list.zipWithNext()) // [(1, 2), (2, 3), (3, 4), (4, 5)]

        //Unzip
        val list2 = listOf("Amit" to 8, "Ali" to 10, "Sumit" to 4, "Himanshu" to 2)
        val (players, footballSkills) = list2.unzip()
        println(players) // [Amit, Ali, Sumit, Himanshu]
        println(footballSkills) // [8, 10, 4, 2]
    }

    //Split array into two parts based on some condition
    fun partitionExample() {
        val users = listOf(
            User3(1, "Amit", true),
            User3(2, "Ali", true),
            User3(3, "Sumit", false),
            User3(4, "Himanshu", false)
        )

        val (footballLovers, nonFootballLovers) = users.partition { it.isFootballLover }
        println(footballLovers) // [User(id=1, name=Amit, isFootballLover=true), User(id=2, name=Ali, isFootballLover=true)]
        println(nonFootballLovers) // [User(id=3, name=Sumit, isFootballLover=false), User(id=4, name=Himanshu, isFootballLover=false)]
    }

    //Reverse a list
    fun reverseExample() {
        val list = listOf(1, 2, 3, 4, 5)
        println(list.reversed()) // [5, 4, 3, 2, 1]
        println(list.asReversed()) // [5, 4, 3, 2, 1]
    }

    //Group elements of a collection based on some condition
    fun groupByExample() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println(list.groupBy { it % 4 })
        // {
        //     1=[1, 5, 9],
        //     2=[2, 6, 10],
        //     3=[3, 7],
        //     0=[4, 8]
        // }
    }

    //Sort element of a collection
    fun sortExample() {
        val unsortedAges = listOf(1, 3, 2, 7, 5, 15, 9, 8, 20, 18, 25)

        //Automatic sorting order is ascending
        val youngestToOldestAges = unsortedAges.sorted()
        println("Youngest to oldest ages: $youngestToOldestAges")

        val oldestToYoungestAges = unsortedAges.sortedDescending()
        println("Oldest to youngest ages: $oldestToYoungestAges")
        /*Similarly, there are other functions that can be used to sort the collection based on certain conditions.
         Some of these functions are sortedArray, sortedArrayWith, sortedBy, sortedByDescending,
         sortedArraydescending, sortedWith, etc.*/
    }

    //Binary search
    fun binarySearchExample() {
        //This example uses the sort extension, so put this after it.
        val largeIntArray = arrayOf(
            4,
            9,
            2,
            19,
            1,
            3,
            2,
            6
        )
        //largeIntArray.sort()
        println("Array = ${largeIntArray.asList()}")
        //I can't believe how simple this is either.
        println("Index of element 19: ${largeIntArray.binarySearch(19)}")
    }

    //Map example
    fun mapExample() {
        val userAgeStrings =
            listOf("1", "15", "10", "32", "14", "45", "9", "16", "18", "19", "27", "24")
        //Simple map function that just converts strings to an int
        val userAgeInts = userAgeStrings.map { it.toInt() }
        //A perfect example of the filter feature here
        val userAgesThatCanDrive = userAgeInts.filter { it >= 16 }

        println("All user ages: $userAgeInts")
        println("User ages that can drive: $userAgesThatCanDrive")
    }

    //Map vs Flatmap
    fun mapFlatMapExample() {
        val dataObjects = listOf(
            Items(listOf("a", "b", "c")),
            Items(listOf("1", "2", "3"))
        )

        //With flatMap, you can "flatten" multiple Data::items into one collection as shown with the items variable.
        val items: List<String> = dataObjects
            .flatMap { it.items } //[a, b, c, 1, 2, 3]
        println(items)

        //Using map, on the other hand, simply results in a list of lists.
        val items2: List<List<String>> = dataObjects
            .map { it.items } //[[a, b, c], [1, 2, 3]]
        println(items2)

        /*FLatten produces the same result as flatMap. So flatMap is a combination of the two functions, map{}
        and then flatten()*/
        val nestedCollections: List<String> = dataObjects
            .map { it.items }
            .flatten() //[a, b, c, 1, 2, 3]
        println(nestedCollections)
    }
}

//Total 21 examples
fun main(args: Array<String>) {
    val obj = KotlinCollectionFunctions()
    obj.removeDuplicate()
    obj.joinToString()
    obj.reduceExample()
    obj.allExample()
    obj.singleExample()
    obj.chunkedExample()
    obj.copyArray()
    obj.changeTypeExample()
    obj.associateByExample()
    obj.zipExample()
    obj.filterExample()
    obj.unionExample()
    obj.intersectionExample()
    obj.mapExample()
    obj.binarySearchExample()
    obj.groupByExample()
    obj.sortExample()
    obj.reverseExample()
    obj.retain_removeExample()
    obj.partitionExample()
    obj.mapFlatMapExample()
}