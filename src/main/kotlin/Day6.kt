import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    Day6().two()
}

class Day6 : Aoc() {

    val test = """
    Time:      7  15   30
    Distance:  9  40  200
""".trimIndent()

    fun computeDist(msPressed: Long, timeLeft: Long): Long = msPressed * timeLeft

    override fun one() {
        val input = readFile("day6.txt").split("\n")
            .map { it.split(":")[1] }
            .map { it.split(" ").filter { str -> str.isNotBlank() }.map { str -> str.toLong() } }
        var races = mutableListOf<Pair<Long, Long>>()
        for ((i, _) in input[0].withIndex())
            races.add(Pair(input[0][i], input[1][i]))


        val result = races.map {
            (0..it.first)
                .map { timePressed -> computeDist(timePressed, it.first - timePressed) }
                .count { result -> result > it.second }
        }.reduce { acc, int -> acc * int }

        println(result)

    }

    override fun two() {
        val input = readFile("day6.txt").split("\n")
            .map { it.split(":")[1] }
            .map { it.split(" ").filter { str -> str.isNotBlank() }.reduce { acc, s -> "$acc$s" }.toLong() }


        val result = (0..input[0])
            .map { timePressed -> computeDist(timePressed, input[0] - timePressed) }
            .count { result -> result > input[1] }


        println(result)
    }
}

