package kotlinclasses


class PersonDetails() {
    var name: String? = "Abcd"
    var contactNumber: String = "1234567890"
    var address: String = "xyz"
    fun displayInfo() = print(
        "\n Name: $name\n " +
                "Contact Number: $contactNumber\n " +
                "Address: $address\n"
    )
}

class ScopeFunction {

    //Let
    fun performLetOperation() {
        val person = PersonDetails().let {
            "The name of the Person is: ${it.name}"
        }

        val name = PersonDetails().name?.let {
            "The name of the Person is: $it"
        }
        println(name)
        println(person)
    }

    fun performLetChainOperation() {
        val numbers = mutableListOf("One", "Two", "Three", "Four", "Five")
        val resultsList = numbers.map { it.length }.filter { it > 3 }
        print(resultsList)
    }


    //Run
    /*The “run” operator is similar to the “let” operator in terms of accepting a return value that is
    different from the object on which the scope function is being applied to. Hence, a “run” operator
    can be used to initialize an object and return the result of it.*/
    fun performRunOperation() {
        var person = PersonDetails().run {
            name = "From Run"
            contactNumber = "0987654321"
            return@run "The details of the Person is: ${displayInfo()}"
        }

        val name = PersonDetails().name?.run {
            "The name of the Person is: $this"
        }
        println(person)
        println(name)
    }

    //With
    /*The “with” operator is completely similar to the run operator that we just discussed.
    It also refers to the context of the object as “this”, similar to how the “run” operator uses it.*/
    fun performWithOperation() {
        val person = with(PersonDetails()) {
            name = "From With"
            return@with "The name of the Person is: ${this.name}"
        }
        println(person)
    }

    //With Vs Run
    fun performWithVsRunOperation() {
        val person: PersonDetails? = PersonDetails()
        with(person) {
            this?.name = "From with"
            this?.contactNumber = "1234"
            this?.address = "wasd"
            this?.displayInfo()
        }

        val person2: PersonDetails? = null
        person2?.run {
            name = "From run"
            contactNumber = "1234"
            address = "wasd"
            displayInfo()
        }
    }

    //Apply
    /*The apply function is similar to the run functionality only in terms of referring to the context
    of the object as “this” and not “it” and also in providing null safety checks*/
    fun performApplyOperation() {
        val person: PersonDetails? = null
        person?.apply {
            name = "From apply"
            contactNumber = "1234"
            address = "wasd"
            displayInfo()
        }
    }

    fun performApplyVsRunOperation() {
        var person = PersonDetails().run {
            name = "From Run"
            contactNumber = "0987654321"
            return@run "The details of the Person is: ${displayInfo()}"
        }
        println(person)

        //Cannot return string/returns object
        var person2 = PersonDetails().apply {
            name = "From Apply"
            contactNumber = "1117654321"
        }
        println("Name of the person is ${person2.name}")
    }

    //Also
    /*The “also” function is similar to the let functionality only in terms of referring to the context
    of the object as “it” and not “this” and also in providing null safety checks*/
    fun performAlsoOperation() {
        val name = PersonDetails().also { currentPerson ->
            print("Current name is: ${currentPerson.name}\n")
            currentPerson.name = "modifiedName"
        }.run {
            "Modified name is: $name\n"
        }
        print(name)
    }
}

fun main() {
    val scopeFunction = ScopeFunction()

    //Let
    scopeFunction.performLetOperation()
    scopeFunction.performLetChainOperation()

    //Run
    scopeFunction.performRunOperation()

    //with
    scopeFunction.performWithOperation()
    scopeFunction.performWithVsRunOperation()

    //Apply
    scopeFunction.performApplyOperation()
    scopeFunction.performApplyVsRunOperation()

    //Also
    scopeFunction.performAlsoOperation()
}