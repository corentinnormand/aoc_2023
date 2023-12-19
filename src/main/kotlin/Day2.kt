class Day2 : Aoc("day2.txt") {


    override fun one() {
        val input = readFile("day2.txt").lines()
        val result = input.asSequence().map { it.split(":") }
            .map { s ->
                val second = s[1].split(';')
                    .map { str -> str.split(",") }
                    .map { str -> str.map { it.trim() } }
                    .map { str -> str.map { it.split(" "); } }
                    .map { str -> str.map { s -> Pair(s[1], s[0].toInt()) } }
                    .map { it.toMap() }
                    .flatMap { it.entries }
                    .groupBy { it.key }
                    .mapValues { it.value.maxOf { v -> v.value } }
                Pair(s[0].split(" ")[1].toInt(), second)
            }
            .filter { it.second.all { e -> (e.key == "blue" && e.value <= 14) || (e.key == "green" && e.value <= 13) || (e.key == "red" && e.value <= 12) } }
            .sumOf { it.first }
        println(result)
    }

    override fun two() {
        val input = readFile("day2.txt").lines()
        val result = input.asSequence().map { it.split(":") }
            .map { s ->
                val second = s[1].split(';')
                    .map { str -> str.split(",") }
                    .map { str -> str.map { it.trim() } }
                    .map { str -> str.map { it.split(" "); } }
                    .map { str -> str.map { s -> Pair(s[1], s[0].toInt()) } }
                    .map { it.toMap() }
                    .flatMap { it.entries }
                    .groupBy { it.key }
                    .mapValues { it.value.maxOf { v -> v.value } }
                Pair(s[0].split(" ")[1].toInt(), second)
            }
            .map { it.second }
            .map { it.values.reduce { acc, value -> acc * value } }
            .sum()
        println(result)
    }
}
