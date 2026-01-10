/*
 * *
 *  * Movie Parse.kt
 *  * Created by Rafsan Ahmad on 1/8/26, 1:42AM
 *  * Copyright (c) 2026 . All rights reserved.
 *  *
 *
 */

package kotlinclasses.Problems

import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONObject

class MovieParse {
    /*Use the HTTP GET method to fetch information about recent TV shows.

Query the endpoint:
https://jsonmock.hackerrank.com/api/tvseries

to retrieve all records.
The results are paginated and can be accessed by appending ?page=num to the query string, where num
is the page number.

API Response Structure

The response is a JSON object containing:
page: current page number (Number)
per_page: maximum results per page (Number)
total: total number of results (Number)
total_pages: total number of pages (Number)
data: array of TV series records

TV Series Object Structure
Each TV series object in data contains:
{
  "name": "Game of Thrones",
  "runtime_of_series": "(2011-2019)",
  "certificate": "A",
  "runtime_of_episodes": "57 min",
  "genre": "Action, Adventure, Drama",
  "imdb_rating": 9.3,
  "overview": "Nine noble families fight for control over the lands of Westeros...",
  "no_of_votes": 1773458,
  "id": 1
}

Schema Details
name (String): TV series name
runtime_of_series (String): years the show was in production
certificate (String): rating
runtime_of_episodes (String): average episode length
genre (String)
imdb_rating (Number)
overview (String)
no_of_votes (Number)
id (Number)

runtime_of_series Formats
There are four possible formats:
(2020-2022) → start and end year
(2020-) → still in production
(2020) → produced for one year
(I) or (II) may precede any format → ignore (I) / (II)

Task
Given:
startYear: earliest year of production
endYear: latest year of production or -1

Return a list of names of all TV series that:
Started production in startYear or later

Ended production in endYear or earlier
If endYear == -1, the show must still be in production

Output Requirements
Return a List<String> of TV series names
Sort the list alphabetically

Function Signature
public static List<String> showsInProduction(int startYear, int endYear)*/

    fun showsInProduction(startYear: Int, endYear: Int): List<String> {
        val result = mutableListOf<String>()
        var page = 1
        var totalPages: Int

        do {
            val url = URL("https://jsonmock.hackerrank.com/api/tvseries?page=$page")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            val response = conn.inputStream.bufferedReader().readText()
            val json = JSONObject(response)

            totalPages = json.getInt("total_pages")
            val data = json.getJSONArray("data")

            for (i in 0 until data.length()) {
                val show = data.getJSONObject(i)
                val name = show.getString("name")
                val runtime = show.getString("runtime_of_series")

                val years = parseYears(runtime) ?: continue
                val start = years.first
                val end = years.second

                val validStart = start >= startYear
                val validEnd = if (endYear == -1) end == -1 else end != -1 && end <= endYear

                if (validStart && validEnd) {
                    result.add(name)
                }
            }
            page++
        } while (page <= totalPages)

        return result.sorted()
    }

    private fun parseYears(runtime: String): Pair<Int, Int>? {

        // Remove (I), (II), (III) etc ONLY
        val cleaned = runtime
            .replace(Regex("\\(I+\\)"), "")
            .trim()

        // Extract years using regex
        val regex = Regex("(\\d{4})(?:\\s*[–-]\\s*(\\d{4})?)?")
        val match = regex.find(cleaned) ?: return null

        val start = match.groupValues[1].toInt()
        val end = if (match.groupValues[2].isEmpty()) -1 else match.groupValues[2].toInt()

        return Pair(start, end)
    }
}

fun main() {
    val obj = MovieParse()
    val shows = obj.showsInProduction(2010, 2020)
    shows.forEach { println(it) }
}