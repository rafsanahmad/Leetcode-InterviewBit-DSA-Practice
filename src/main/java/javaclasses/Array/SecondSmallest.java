package javaclasses.Array;

/*Given an input array of unsorted integers. Write a code to find second smallest number in an array.

For example :

arr = {-1, 7, 1, 34, 18}

The second smallest number in this array is 1.*/

/*Let’s discuss how we can solve this problem in a single traversal using two variables (smallest and secondSmallest).

Here are the following steps –

i) Declare two variables smallest and secondSmallest. Initialize them with Integer.MAX_VALUE.

ii) Traverse an array and do the following checks –

a) If current element is smaller than the smallest. Then, update the value of smallest variable.

b) If current element is greater than smallest and less than secondSmallest than update the value of secondSmallest.

The time complexity of this approach is O(n).

*/

//Find second lowest number in an array
public class SecondSmallest {

    public static int getSecondSmallest(int arr[]) {

        //Integer.MAX_VALUE is 2147483647
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        //Traverse an array
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] < smallest) {
                secondSmallest = smallest;
                smallest = arr[i];
            }

            if (arr[i] > smallest && arr[i] < secondSmallest) {
                secondSmallest = arr[i];
            }
        }

        return secondSmallest;
    }

    public static void main(String[] args) {

        int arr[] = {-1, 70, 10, 34, 18, 78};

        //Method Call
        int result = getSecondSmallest(arr);

        System.out.println(" Second smallest number " + result);
    }

}

