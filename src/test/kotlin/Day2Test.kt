import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun test() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent().lines()


        val result = input.map { it.split(":") }
            .map { s ->
                val second = s[1].split(';')
                    .map { str -> str.split(",") }
                    .map { str -> str.map { it.trim() } }
                    .map { str -> str.map { it.split(" "); } }
                    .map { str -> str.map { s -> Pair(s[1], s[0].toInt()) } }
                    .map { it.toMap() }
                    .flatMap { it.entries }
                    .groupBy { it.key }
                    .mapValues { it.value.sumOf { v -> v.value } }
                val pair = Pair(s[0].split(" ")[1].toInt(), second)
                println(pair)
                pair
            }
            .filter { it.second.all { e -> (e.key == "blue" && e.value < 14) || (e.key == "green" && e.value < 13) || (e.key == "red" && e.value < 12) } }
            .sumOf { it.first }
        println(result)
    }

}
