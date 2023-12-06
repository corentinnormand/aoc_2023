import org.junit.jupiter.api.Test
import kotlin.math.min

class Day5Test {
    @Test
    fun test() {
        val input = Day5().readFile("day5.txt").split("\n\n")

        val seeds = input[0].split(":")[1].split(" ")
            .filter { it != "" }
            .map { it.trim().toLong() }
        val maps = input.subList(1, input.size)
            .map { it.split("\n") }
            .map { it.subList(1, it.size) }

            .map {
                it.map { i ->
                    i.split(" ")
                        .map { it.trim().toLong() }
                }
                    .map { Triple(it[0], it[1], it[2]) }
            }
            .toList()

        fun mapSeed(seed: Long, map: List<Triple<Long, Long, Long>>): Long {
            var currentSeed = seed
            for (v in map) {
                if ((v.second..<v.second + v.third).contains(seed)) {
                    currentSeed += (v.first - v.second)
                }
            }
            return currentSeed
        }

        fun mapSeed2(seed: Long, map: List<Triple<Long, Long, Long>>): Long {
            var currentSeed = seed
            for (v in map) {
                if ((v.first..<v.first + v.third).contains(seed)) {
                    currentSeed -= (v.first - v.second)
                }
            }
            return currentSeed
        }


        val maxRes = maps.last().maxOf { it.first + it.third }
        val minRes = maps.last().minOf { it.first }
        println(maxRes)
        println(minRes)
        val seeds2 = seeds
            .chunked(2)
            .map { (it[0]..<it[0] + it[1]) }

        val results = mutableSetOf<Long>()
        var min = Long.MAX_VALUE
        for (range in seeds2) {
            for (seed in range) {
                var currentSeed = seed
                for (i in maps) {
                    currentSeed = mapSeed(currentSeed, i)
                }
                if (min > currentSeed)
                    min = currentSeed
            }
        }

        for (i in 100_000_000_000 downTo 0) {
            var value = i
            for (map in maps.asReversed()) {
                val mapSeed2 = mapSeed2(value, map)
                if (mapSeed2 < 0) {
                    break
                }
                value = mapSeed2
            }
            if (seeds2.any { it.contains(value) }) {
                println(i)
                return
            }
        }


    }
}
