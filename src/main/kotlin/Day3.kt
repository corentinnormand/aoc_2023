class Day3 : Aoc() {
    override fun one() {
        val input = readFile("day3.txt").lines()
        val numbers = mutableListOf<Int>()
        for (i in 0..<input[0].length) {
            for (j in input.indices) {
                val c = input[j][i]
                if (!c.isDigit() && c != '.') {
                    val adjacentNumbers = mutableSetOf<Int>()
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
                                for (m in i + k + 1..<input.first().length) {
                                    val right = input[j + l][m]
                                    if (right.isDigit()) {
                                        number = "${number}${right}"
                                    } else {
                                        break
                                    }
                                }
                                adjacentNumbers.add(number.toInt())
                            }
                        }
                    }
                    numbers.addAll(adjacentNumbers.toList())
                }
            }
        }

        println(numbers.sum())
    }

    override fun two() {
        val input = readFile("day3.txt").lines()
        val numbers = mutableListOf<Int>()
        for (i in 0..<input[0].length) {
            for (j in input.indices) {
                val c = input[j][i]
                if (!c.isDigit() && c == '*') {
                    val adjacentNumbers = mutableSetOf<Int>()
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
                                for (m in i + k + 1..<input.first().length) {
                                    val right = input[j + l][m]
                                    if (right.isDigit()) {
                                        number = "${number}${right}"
                                    } else {
                                        break
                                    }
                                }
                                adjacentNumbers.add(number.toInt())
                            }
                        }
                    }
                    if (adjacentNumbers.size == 2) {
                        numbers.add(adjacentNumbers.reduce { acc, i -> acc * i })
                    }
                }
            }
        }

        println(numbers.sum())
    }


}
