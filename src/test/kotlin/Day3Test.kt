import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun test() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent().lines()
        val numbers = mutableSetOf<Int>()
        for (i in 0..input[0].length - 1) {
            for (j in 0..input.size - 1) {
                val c = input[j][i]
                if (!c.isDigit() && c != '.') {
                    for (k in -1..1) {
                        for (l in -1..1) {
                            val adjacentToSymbol = input[j + l][i + k]
                            if (adjacentToSymbol.isDigit()) {
                                var number = adjacentToSymbol.toString()
                                for (m in i + k - 1 downTo 0) {
                                    val left = input[j + l][m]
                                    if (left.isDigit()) {
                                        number = "${left}${number}"
                                    } else {
                                        break
                                    }
                                }
                                for (m in i + k + 1..input.first().length - 1) {
                                    val right = input[j + l][m]
                                    if (right.isDigit()) {
                                        number = "${number}${right}"
                                    } else {
                                        break
                                    }
                                }
                                numbers.add(number.toInt())
                            }
                        }
                    }
                }
            }
        }

        println(numbers.sum())

    }
}
