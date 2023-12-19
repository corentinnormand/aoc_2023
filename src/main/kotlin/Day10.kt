import java.awt.Polygon

fun main() {
    Day10().two()
}

class Day10 : Aoc("day10.txt") {


//    val test = """
//        .F----7F7F7F7F-7....
//        .|F--7||||||||FJ....
//        .||.FJ||||||||L7....
//        FJL7L7LJLJ||LJ.L-7..
//        L--J.L7...LJS7F-7L7.
//        ....F-J..F7FJ|L7L7L7
//        ....L7.F7||L7|.L7L7|
//        .....|FJLJ|FJ|F7|.LJ
//        ....FJL-7.||.||||...
//        ....L---J.LJ.LJLJ...
//    """.trimIndent()

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
    """.trimIndent()

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
                    return Coords(i, j)
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
        val all = mutableListOf<List<Pair<Coords, Char>>>()
        for (coords in listOf(Coords(-1, 0), Coords(0, -1), Coords(1, 0), Coords(0, 1))) {
            var last = startingCoords
            var current = Coords(startingCoords.x + coords.x, startingCoords.y + coords.y)
            val currentChar = get(input = input, current)
            val allCoords = mutableListOf<Pair<Coords, Char>>()
            while (currentChar != 'S') {
                val nextTile = nextTile(input = input, last = last, current = current) ?: break
                last = current
                current = nextTile
                allCoords.add(Pair(current, currentChar))
            }
            all.add(allCoords)
        }

        val maxOf = (all.maxOf { it.size } / 2) + 1

        println(maxOf)
    }

    override fun two() {
//        val input = readFile("day10.txt").lines()
        val input = test.lines()
        val startingCoords = findStart(input)
        val all = mutableListOf<List<Pair<Coords, Char>>>()

        for (coords in listOf(Coords(-1, 0), Coords(0, -1), Coords(1, 0), Coords(0, 1))) {
            var last = startingCoords
            var current = Coords(startingCoords.x + coords.x, startingCoords.y + coords.y)
            if (current.x > 0 && current.y > 0) {
                var currentChar = get(input = input, current)
                val allCoords = mutableListOf<Pair<Coords, Char>>()
                while (currentChar != 'S') {
                    currentChar = get(input, current)
                    val nextTile = nextTile(input = input, last = last, current = current) ?: break
                    allCoords.add(Pair(current, currentChar))
                    last = current
                    current = nextTile
                }
                all.add(allCoords)
            }
        }
        println(all.map { it.size })
        val res = all.maxByOrNull { it.size }!!
            .toSet()

        val goodChars = res
            .map {
                when (it.second) {
                    '7' -> Pair(it.first, '┓')
                    'J' -> Pair(it.first, '┛')
                    'F' -> Pair(it.first, '┏')
                    'L' -> Pair(it.first, '┗')
                    '-' -> Pair(it.first, '━')
                    '|' -> Pair(it.first, '┃')
                    else -> it
                }
            }
            .toSet()

        val polygon = Polygon()
        for (i in res) {
            polygon.addPoint(i.first.x, i.first.y)
        }

        println((res.size / 2) + 1)
        var count = 0
        for (y in input.indices) {
            for (x in input[y].indices) {
                val find = goodChars.find { it.first == Coords(x, y) }
                if (polygon.contains(x, y) && find == null) {
                    print("I")
                    count++;
                } else {
                    print(find?.second ?: "O")
                }
            }
            println()
        }
        println(count)
        println(count - res.size)
    }
}
