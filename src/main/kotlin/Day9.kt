fun main(args: Array<String>) {
    Day9().two()
}

class Day9 : Aoc() {

    val test = """
   10 13 16 21 30 45
""".trimIndent()


    override fun one() {
        val input = readFile("day9.txt").lines()

        val first = input
            .map { it.split(" ") }
            .map { it.map { str -> str.toInt() } }

        val tmp = first[0].toMutableList()
        val res = first.map { it.toMutableList() }
            .map {
                val oneRes = mutableListOf(it)
                while (oneRes.last().any { it != 0 }) {
                    val newLine = mutableListOf<Int>()
                    val last = oneRes.last()
                    for (i in 0..<last.size - 1) {
                        newLine.add(last[i + 1] - last[i])
                    }
                    oneRes.add(newLine)
                }
                oneRes.last().add(0)
                for (i in oneRes.size - 2 downTo 0) {
                    oneRes[i].add(oneRes[i].last() + oneRes[i + 1].last())
                }
                oneRes.first().last()
            }
            .sum()

        println(res)
    }

    override fun two() {
        val input =readFile("day9.txt").lines()

        val first = input
            .map { it.split(" ") }
            .map { it.map { str -> str.toInt() } }

        val tmp = first[0].toMutableList()
        val res = first.map { it.toMutableList() }
            .map {
                val oneRes = mutableListOf(it)
                while (oneRes.last().any { it != 0 }) {
                    val newLine = mutableListOf<Int>()
                    val last = oneRes.last()
                    for (i in 0..<last.size - 1) {
                        newLine.add(last[i + 1] - last[i])
                    }
                    oneRes.add(newLine)
                }
                oneRes.last().addFirst(0)
                for (i in oneRes.size - 2 downTo 0) {
                    oneRes[i].addFirst(oneRes[i].first() - oneRes[i + 1].first())
                }
                oneRes.first().first()
            }
            .sum()

        println(res)

    }

}

