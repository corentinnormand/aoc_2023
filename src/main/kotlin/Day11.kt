import kotlin.math.abs

fun main() {
    Day11().main()
}

class Day11 : Aoc("day11.txt") {
    val test = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent()


    fun getColumn(matrix: List<String>, col: Int): String = matrix.map { v -> v[col] }.joinToString("")


    data class Coords(val x: Int, val y: Int)

    fun distance(
        from: Coords,
        to: Coords,
        emptyCols: Set<Int>,
        emptyRows: Set<Int>,
        expansion: Long = 1L
    ): Long =
        abs(from.x - to.x) + abs(from.y - to.y) +
                (emptyCols.filter {
                    (if (from.x < to.x) from.x..to.x else to.x..from.x).contains(it)
                }.size * expansion) +
                (emptyRows.filter {
                    (if (from.y < to.y) from.y..to.y else to.y..from.y).contains(it)
                }.size * expansion)


    override fun one() {


        val input = input().lines()
        val emptyRows =
            input.mapIndexed { i, k -> Pair(i, k) }.filter { !it.second.contains("#") }.map { it.first }.toSet()

        val emptyCols =
            input[0].indices.map { (it to getColumn(input, it)) }.filter { !it.second.contains("#") }.map { it.first }
                .toSet()


        val galaxies =
            input.mapIndexed { y, v -> v.mapIndexed { x, c -> (Coords(x, y) to c) } }.flatten()
                .filter { it.second == '#' }


        val sumOfDistances = galaxies.mapIndexed { i, first ->
            galaxies.filterIndexed { j, _ -> j > i }.map { (first.first to it.first) }
        }
            .flatten()
            .sumOf { distance(it.first, it.second, emptyCols, emptyRows) }

        println(sumOfDistances)
    }

    override fun two() {


        val input = test.lines()
        val emptyRows =
            input.mapIndexed { i, k -> Pair(i, k) }.filter { !it.second.contains("#") }.map { it.first }.toSet()

        val emptyCols =
            input[0].indices.map { (it to getColumn(input, it)) }.filter { !it.second.contains("#") }.map { it.first }
                .toSet()


        val galaxies =
            input.mapIndexed { y, v -> v.mapIndexed { x, c -> (Coords(x, y) to c) } }.flatten()
                .filter { it.second == '#' }


        val sumOfDistances = galaxies.mapIndexed { i, first ->
            galaxies.filterIndexed { j, _ -> j > i }.map { (first.first to it.first) }
        }
            .flatten()
            .sumOf { distance(it.first, it.second, emptyCols, emptyRows, 10) }

        println(sumOfDistances)
    }

}
