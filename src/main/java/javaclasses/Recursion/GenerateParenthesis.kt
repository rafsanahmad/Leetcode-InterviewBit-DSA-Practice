package javaclasses.Recursion

class GenerateParenthesis {
    //Leetcode 22
    fun generateParenthesis(n: Int): Array<String> {
        val result = ArrayList<String>()
        dfs(result, "", n, n)
        return result.toTypedArray()
    }

    fun dfs(result: ArrayList<String>, str: String, left: Int, right: Int) {
        if (left > right) return
        if (left == 0 && right == 0) {
            result.add(str)
            return
        }
        if (left > 0) {
            dfs(result, "$str(", left - 1, right)
        }
        if (right > 0) {
            dfs(result, "$str)", left, right - 1)
        }
    }
}