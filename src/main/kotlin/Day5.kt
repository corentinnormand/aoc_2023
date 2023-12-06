import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Day5 : Aoc() {
    val test = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent().split("\n\n")

    override fun one() {
        val input = test


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
            var curentSeed = seed
            for (v in map) {
                if ((v.second..<v.second + v.third).contains(seed)) curentSeed += (v.first - v.second)
            }
            return curentSeed
        }

        val map1 = seeds.map { seed ->
            var currentSeed = seed
            for (i in maps) {
                currentSeed = mapSeed(currentSeed, i)
            }
            currentSeed
        }
        println(map1.min())

    }

    override fun two() {
        val input = readFile("day5.txt").split("\n\n")
//        val input = test

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

        val seeds2 = seeds
            .chunked(2)
            .map { (it[0]..<it[0] + it[1]) }

        val results = mutableSetOf<Long>()
        var min = Long.MAX_VALUE

        runBlocking {
            coroutineScope {
                for (i in 19976893..4_294_967_296) {
                    val run = run {
                        var value = i
                        for (map in maps.asReversed()) {
                            val mapSeed2 = mapSeed2(value, map)
                            if (mapSeed2 < 0) {
                                break
                            }
                            value = mapSeed2
                        }
                        if (seeds2.any { it.contains(value) }) {
                            println("soluce: $i")
                            return@run i
                        } else {
                            return@run 0
                        }
                    }
                    if (run > 0) {
                        return@coroutineScope
                    }
                }
            }
        }
    }
}

