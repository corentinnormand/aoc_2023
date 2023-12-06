class Day4 : Aoc() {
    private fun strToSet(string: String) =
        string.split(" ")
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .map { it.toInt() }
            .toSet()

    override fun one() {
        val input = readFile("day4.txt").lines()
        val result = input.map { it.split(":")[1] }
            .map { it.split("|") }
            .map { Pair(strToSet(it[0]), strToSet(it[1])) }
            .map { it.first.intersect(it.second).size }
            .filter { it > 0 }
            .map { 1 shl (it - 1) }
            .sum()

        println(result)
    }

    override fun two() {

        val tmp = readFile("day4.txt").lines()
            .filter { it.isNotBlank() }
            .map { it.split(":") }
            .map { Pair(it[0].split(" ").last(), it[1]) }
            .map { Pair(it.first.trim().toInt(), it.second.split("|")) }
            .map { Pair(it.first, strToSet(it.second[0]).intersect(strToSet(it.second[1])).size) }
            .map { Pair(it.first, (it.first + 1..it.first + it.second).toSet()) }
            .toMap()

        var index = 0

        val inputs = tmp.values.toMutableList()
        while (inputs.isNotEmpty()) {
            val line = inputs[0]
            inputs.removeAt(0)
            val elements = line.map { tmp[it]!! }
            inputs.addAll(elements)
            if (index % 10000 == 0) {
                println(inputs.size)
            }
            index++
        }

        println(index)
    }
}
