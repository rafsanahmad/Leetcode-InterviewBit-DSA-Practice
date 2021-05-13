package kotlinclasses

class RightLengthEncoding {

    fun encode(str: String) : String {
        var strArray  = str.toCharArray()
        var currentChar = strArray[0]
        var count = 0
        var builder = StringBuilder()
        for (i in 0 until strArray.size) {
            if (currentChar == strArray[i]) {
                count++
            } else {
                builder.append(currentChar)
                builder.append(count)
                currentChar = strArray[i]
                count = 1
            }
        }
        //for last character
        builder.append(currentChar)
        builder.append(count)
        return builder.toString()
    }
}
fun main(args: Array<String>) {
    var s = "jjjjjjjjjdddddddddiiiuusskkpppuuutttrewqnbhyj"
    var encoded = RightLengthEncoding().encode(s)
    println(encoded)
}