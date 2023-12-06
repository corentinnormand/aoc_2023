import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun test() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent().lines()

        fun strToSet(string: String) =
            string.split(" ")
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .toSet()


        val tmp = input
            .map { it.split(":") }
            .map { Pair(it[0].split(" ")[1], it[1]) }
            .map { Pair(it.first.toInt(), it.second.split("|")) }
            .map { Pair(it.first, strToSet(it.second[0]).intersect(strToSet(it.second[1])).size) }
            .map { Pair(it.first, (it.first + 1..it.first + it.second).toSet()) }
            .toMap()
        var index = 0

        println(tmp)
        val inputs = tmp.values.toMutableList()
        while (inputs.isNotEmpty()) {
            val line = inputs[0]
            inputs.removeAt(0)
            inputs.addAll(line.map { tmp[it]!! }.filter { it.isNotEmpty() })
            index++
        }

        println(index)
    }
}
