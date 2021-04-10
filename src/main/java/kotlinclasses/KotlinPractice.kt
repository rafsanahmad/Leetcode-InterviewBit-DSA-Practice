package kotlinclasses

class KotlinPractice {
    val square = { number:Int -> number*number}

    val calculateGrade = fun(grade: Int): String {
        if (grade <0 || grade>100) {
            return "error"
        } else if(grade < 40) {
            return "Fail"
        } else if (grade < 70) {
            return "Pass"
        }
        return "Distinction"
    }
}
fun main(args: Array<String>) {
    println("Heloo world")
    val kt = KotlinPractice()
    val n = kt.square(7)
    println(n)
    println(kt.calculateGrade(50))
}