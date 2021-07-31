package javaclasses.ArrayList;

import java.util.List;
import java.util.ArrayList;

public class PascalTriangle {
    //Leetcode 118
    /*Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]*/

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> nums = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) nums.add(1);
                else if (j < i) {
                    int num = result.get(i - 1).get(j - 1) + result.get(i - 1).get(j);
                    nums.add(num);
                } else if (j == i) nums.add(1);
            }
            result.add(nums);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle().generate(5));
    }
}
