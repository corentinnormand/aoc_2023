fun main() {
    Day10().one()
}

class Day10 : Aoc() {


    val test = """
        FF7FSF7F7F7F7F7F---7
        L|LJ||||||||||||F--J
        FL-7LJLJ||||||LJL-77
        F--JF--7||LJLJ7F7FJ-
        L---JF-JLJ.||-FJLJJ7
        |F|F-JF---7F7-L7L|7|
        |FFJF7L7F-JF7|JL---7
        7-L-JL7||F7|L7F-7F7|
        L.L7LFJ|||||FJL7||LJ
        L7JLJL-JLJLJL--JLJ.L
    """.trimIndent().lines()

    val test2 = """
        ...........
        .S-------7.
        .|F-----7|.
        .||.....||.
        .||.....||.
        .||.....||.
        .|L-7.F-J|.
        .|..|.|..|.
        .L--J.L--J.
        ...........
    """.trimIndent()

    data class Coords(val x: Int, val y: Int)


    fun get(input: List<String>, coords: Coords): Char = input[coords.y][coords.x]

    fun findStart(input: List<String>): Coords {
        for ((j, _) in input.withIndex()) {
            for ((i, _) in input[j].withIndex()) {
                if (input[j][i] == 'S') {
                    return Coords(j, i)
                }
            }
        }
        return Coords(0, 0)
    }


    fun nextTile(input: List<String>, last: Coords, current: Coords): Coords? {

        val vect = Coords(current.x - last.x, current.y - last.y)
        val currentChar = get(input, current)

        when (vect) {
            Coords(1, 0) -> return when (currentChar) {
                'J' -> Coords(current.x, current.y - 1)
                '7' -> Coords(current.x, current.y + 1)
                '-' -> Coords(current.x + 1, current.y)
                else -> return null
            }

            Coords(-1, 0) -> return when (currentChar) {
                'L' -> Coords(current.x, current.y - 1)
                'F' -> Coords(current.x, current.y + 1)
                '-' -> Coords(current.x - 1, current.y)
                else -> return null
            }

            Coords(0, 1) -> return when (currentChar) {
                'L' -> Coords(current.x + 1, current.y)
                'J' -> Coords(current.x - 1, current.y)
                '|' -> Coords(current.x, current.y + 1)
                else -> return null
            }

            Coords(0, -1) -> return when (currentChar) {
                'F' -> Coords(current.x + 1, current.y)
                '|' -> Coords(current.x, current.y - 1)
                '7' -> Coords(current.x - 1, current.y)
                else -> return null
            }

            else -> return null

        }
    }

    override fun one() {
        val input = readFile("day10.txt").lines()
        val startingCoords = findStart(input)
        val all = mutableListOf<List<Coords>>()
        for (coords in listOf(Coords(-1, 0), Coords(0, -1), Coords(1, 0), Coords(0, 1))) {
            var last = startingCoords
            var current = Coords(startingCoords.x + coords.x, startingCoords.y + coords.y)
            val currentChar = get(input = input, current)
            val allCoords = mutableListOf<Coords>()
            while (currentChar != 'S') {
                val nextTile = nextTile(input = input, last = last, current = current) ?: break
                last = current
                current = nextTile
                allCoords.add(current)
            }
            all.add(allCoords)
        }

        val maxOf = (all.maxOf { it.size } / 2) + 1

        println(maxOf)
    }

    override fun two() {
        val input = readFile("day10.txt").lines()
        val startingCoords = findStart(input)
        val all = mutableListOf<List<Coords>>()
        for (coords in listOf(Coords(-1, 0), Coords(0, -1), Coords(1, 0), Coords(0, 1))) {
            var last = startingCoords
            var current = Coords(startingCoords.x + coords.x, startingCoords.y + coords.y)
            if (current.x > 0 && current.y > 0) {
                val currentChar = get(input = input, current)
                val allCoords = mutableListOf<Coords>()
                while (currentChar != 'S') {
                    val nextTile = nextTile(input = input, last = last, current = current) ?: break
                    allCoords.add(current)
                    last = current
                    current = nextTile
                }
                all.add(allCoords)
            }
        }

        val res = all.maxByOrNull { it.size }!!
            .toSet()

        var count = 0
        for (y in input.indices) {
            for (x in input[y].indices) {
                val currentChar = get(input, Coords(x, y))
                if (currentChar == '.') {
                    val findxHigh = res.filter { it.x > x && it.y == y }.size
                    val findxLow = res.filter { it.x < x && it.y == y }.size
                    val findyHigh = res.filter { it.y > y && it.x == x }.size
                    val findyLow = res.filter { it.y < y && it.x == x }.size
                    val cond = (findxHigh % 2 == 0 && findxLow % 2 == 0) ||
                            (findyHigh % 2 == 0 && findyLow % 2 == 0)
                    print(
                        if (cond) 'O' else {
                            count++; 'I'
                        }
                    )
                } else {
                    print(currentChar)
                }
            }
            println()
        }
        println(count)
    }
}
