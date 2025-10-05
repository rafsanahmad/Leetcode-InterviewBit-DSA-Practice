/*
 * *
 *  * Design Movie Rental System.kt
 *  * Created by Rafsan Ahmad on 9/23/25, 2:11PM
 *  * Copyright (c) 2025 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.TreeMap_TreeSet

import java.util.TreeSet

class DesignMovieRentalSystem {
    //https://leetcode.com/problems/design-movie-rental-system/description/
    /*You have a movie renting company consisting of n shops. You want to implement a renting system
    that supports searching for, booking, and returning movies. The system should also support
    generating a report of the currently rented movies.

Each movie is given as a 2D integer array entries where entries[i] = [shopi, moviei, pricei] indicates
that there is a copy of movie moviei at shop shopi with a rental price of pricei. Each shop carries at
most one copy of a movie moviei.

The system should support the following functions:

Search: Finds the cheapest 5 shops that have an unrented copy of a given movie. The shops should be
sorted by price in ascending order, and in case of a tie, the one with the smaller shopi should appear
 first. If there are less than 5 matching shops, then all of them should be returned. If no shop has
 an unrented copy, then an empty list should be returned.
Rent: Rents an unrented copy of a given movie from a given shop.
Drop: Drops off a previously rented copy of a given movie at a given shop.
Report: Returns the cheapest 5 rented movies (possibly of the same movie ID) as a 2D list res where
res[j] = [shopj, moviej] describes that the jth cheapest rented movie moviej was rented from the
shop shopj. The movies in res should be sorted by price in ascending order, and in case of a tie,
the one with the smaller shopj should appear first, and if there is still tie, the one with the
smaller moviej should appear first. If there are fewer than 5 rented movies, then all of them should
be returned. If no movies are currently being rented, then an empty list should be returned.

Implement the MovieRentingSystem class:
MovieRentingSystem(int n, int[][] entries) Initializes the MovieRentingSystem object with n shops and
the movies in entries.
List<Integer> search(int movie) Returns a list of shops that have an unrented copy of the given movie
as described above.
void rent(int shop, int movie) Rents the given movie from the given shop.
void drop(int shop, int movie) Drops off a previously rented movie at the given shop.
List<List<Integer>> report() Returns a list of cheapest rented movies as described above.
Note: The test cases will be generated such that rent will only be called if the shop has an unrented
copy of the movie, and drop will only be called if the shop had previously rented out the movie.


Example 1:
Input
["MovieRentingSystem", "search", "rent", "rent", "report", "drop", "search"]
[[3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]], [1], [0, 1], [1, 2],
[], [1, 2], [2]]
Output
[null, [1, 0, 2], null, null, [[0, 1], [1, 2]], null, [0, 1]]

Explanation
MovieRentingSystem movieRentingSystem = new MovieRentingSystem
(3, [[0, 1, 5], [0, 2, 6], [0, 3, 7], [1, 1, 4], [1, 2, 7], [2, 1, 5]]);
movieRentingSystem.search(1);
// return [1, 0, 2], Movies of ID 1 are unrented at shops 1, 0, and 2. Shop 1 is cheapest; shop 0
and 2 are the same price, so order by shop number.
movieRentingSystem.rent(0, 1);
// Rent movie 1 from shop 0. Unrented movies at shop 0 are now [2,3].
movieRentingSystem.rent(1, 2);
// Rent movie 2 from shop 1. Unrented movies at shop 1 are now [1].
movieRentingSystem.report();
// return [[0, 1], [1, 2]]. Movie 1 from shop 0 is cheapest, followed by movie 2 from shop 1.
movieRentingSystem.drop(1, 2);
// Drop off movie 2 at shop 1. Unrented movies at shop 1 are now [1,2].
movieRentingSystem.search(2);
// return [0, 1]. Movies of ID 2 are unrented at shops 0 and 1. Shop 0 is cheapest, followed by shop 1.


Constraints:

1 <= n <= 3 * 10^5
1 <= entries.length <= 10^5
0 <= shopi < n
1 <= moviei, pricei <= 10^4
Each shop carries at most one copy of a movie moviei.
At most 10^5 calls in total will be made to search, rent, drop and report.*/

    /*Intuition of the Solution
We need to design a system that supports:
search(movie) → Find up to 5 cheapest shops renting a given movie.
rent(shop, movie) → Mark a movie as rented from a shop.
drop(shop, movie) → Return a rented movie back to availability.
report() → List up to 5 currently rented movies, ordered by (price, shop, movie).

The challenge is to keep all these operations efficient.
Key Idea:
Maintain two sorted sets (TreeSet):
One per movie for available copies (price, shop ordering).
One global for rented copies (price, shop, movie ordering).
Maintain a priceMap for quick lookup of a movie’s price in a given shop.
When renting/dropping, we move entries between sets in O(log n).
Searching and reporting only read the smallest few elements (O(1) per element).
This ensures that all operations are log-time efficient, suitable for large inputs.*/

    /*Complexity Analysis
Let:
m = number of shops holding a given movie.
n = total number of rented movies in the system.

1. search(movie)
Looks at up to 5 smallest elements in that movie’s TreeSet.
Time: O(log m + 5) (tree navigation + iteration).
Space: O(1) (just temporary list).

2. rent(shop, movie)
Lookup price in O(1).
Remove from availableMovies[movie] → O(log m).
Add into rentedMovies → O(log n).
Time: O(log m + log n)

Space: O(1)
3. drop(shop, movie)
Lookup price in O(1).
Remove from rentedMovies → O(log n).
Add into availableMovies[movie] → O(log m).
Time: O(log m + log n)
Space: O(1)

4. report()
Retrieves up to 5 smallest rented entries.
Time: O(5) (iteration only; TreeSet already sorted).
Space: O(1)
*/
    data class MovieEntry(val price: Int, val shop: Int)

    data class RentedMovie(val price: Int, val shop: Int, val movie: Int)

    data class ShopMovieKey(val shop: Int, val movie: Int)

    class MovieRentingSystem(n: Int, entries: Array<IntArray>) {

        private val priceMap = mutableMapOf<ShopMovieKey, Int>()

        private val availableMovies = mutableMapOf<Int, TreeSet<MovieEntry>>()

        private val rentedMovies = TreeSet<RentedMovie>(
            compareBy<RentedMovie> { it.price }
                .thenBy { it.shop }
                .thenBy { it.movie }
        )

        init {
            for ((shop, movie, price) in entries) {
                val key = ShopMovieKey(shop, movie)
                priceMap[key] = price

                val comparator = compareBy<MovieEntry> { it.price }
                    .thenBy { it.shop }

                //For Descending
                //val comparator = compareByDescending<MovieEntry> { it.price }
                //  .thenByDescending { it.shop }   // shop also descending

                availableMovies
                    .computeIfAbsent(movie) { TreeSet(comparator) }
                    .add(MovieEntry(price, shop))
            }
        }

        fun search(movie: Int): List<Int> {
            val movies = availableMovies[movie] ?: return emptyList()
            return movies.take(5).map { it.shop }
        }

        fun rent(shop: Int, movie: Int) {
            val key = ShopMovieKey(shop, movie)
            val price = requireNotNull(priceMap[key])
            availableMovies[movie]?.remove(MovieEntry(price, shop))
            rentedMovies.add(RentedMovie(price, shop, movie))
        }

        fun drop(shop: Int, movie: Int) {
            val key = ShopMovieKey(shop, movie)
            val price = requireNotNull(priceMap[key])
            rentedMovies.remove(RentedMovie(price, shop, movie))
            availableMovies[movie]?.add(MovieEntry(price, shop))
        }

        fun report(): List<List<Int>> {
            return rentedMovies.take(5).map { listOf(it.shop, it.movie) }
        }
    }
}

fun main() {
    val system = DesignMovieRentalSystem.MovieRentingSystem(
        3,
        arrayOf(
            intArrayOf(0, 1, 5),
            intArrayOf(0, 2, 6),
            intArrayOf(0, 3, 7),
            intArrayOf(1, 1, 4),
            intArrayOf(1, 2, 7),
            intArrayOf(2, 1, 5)
        )
    )

    println(system.search(1))   // -> [1, 0, 2] (shops offering movie=1 sorted by price then shop)
    system.rent(0, 1)           // shop=0 rents movie=1
    system.rent(1, 2)           // shop=1 rents movie=2
    println(system.report())    // -> [[0, 1], [1, 2]]
    system.drop(1, 2)           // movie=2 returned to shop=1
    println(system.search(2))   // -> [0, 1]
}
