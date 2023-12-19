class Day1 : Aoc("day1.txt") {

    val set = mapOf<String, Int>(
        "1" to 1,
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )

    override fun one() {


        val message = readFile("day1.txt").lines()
            .map { s -> Pair(s.first { it.isDigit() }, s.last { it.isDigit() }) }
            .map { p -> "${p.first}${p.second}".toInt() }
            .reduceRight { i: Int, a: Int -> a + i }
        println(message)
    }

    override fun two() {
        val message = readFile("day1.txt").lines()
            .map { s -> Pair(s.findAnyOf(set.keys)!!.second, s.findLastAnyOf(set.keys)!!.second) }
            .map { p -> "${set[p.first]}${set[p.second]}".toInt() }
            .reduceRight { i: Int, a: Int -> a + i }
        println(message)
    }
}
