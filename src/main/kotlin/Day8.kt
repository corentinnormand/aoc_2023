import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    Day8().two()
}

class Day8 : Aoc() {

    val test = """
    LR

    11A = (11B, XXX)
    11B = (XXX, 11Z)
    11Z = (11B, XXX)
    22A = (22B, XXX)
    22B = (22C, 22C)
    22C = (22Z, 22Z)
    22Z = (22B, 22B)
    XXX = (XXX, XXX)
""".trimIndent().lines()


    override fun one() {
        val input = readFile("day8.txt").lines()
        val directions = input[0].toList()
        val instructions = input.subList(2, input.size)
            .map { it.split(" = ") }
            .associate {
                val a = """[A-Z]{3}""".toRegex().findAll(it[1])
                    .map { res -> res.value }
                    .toList()
                Pair(it[0], Pair(a[0], a[1]))
            }
        var value = "AAA"
        var steps = 0
        while (value != "ZZZ") {
            for (d in directions) {
                val pair = instructions.get(value)
                value = if (d == 'L') pair!!.first else pair!!.second
                steps++
            }

        }
        println(steps)

    }

    override fun two() {
        val input = readFile("day8.txt").lines()
        val directions = input[0].toList()
        val instructions = input.subList(2, input.size)
            .map { it.split(" = ") }
            .associate {
                val a = """[1-9A-Z]{3}""".toRegex().findAll(it[1])
                    .map { res -> res.value }
                    .toList()
                Pair(it[0], Pair(a[0], a[1]))
            }
        val values = instructions.filterKeys { it.last() == 'A' }.keys
        
        
        fun getSteps(value: String): Long {
            var steps = 0L
            var tmp = value
            while (tmp.last() != 'Z') {
                for (d in directions) {
                    val pair = instructions.get(tmp)
                    tmp = if (d == 'L') pair!!.first else pair!!.second
                    steps++
                }
            }
            return steps
        }

        fun gcd(a: Long, b: Long): Long {
            var a1: Long = a
            var b1: Long = b
            while (b1 != 0L) {
                val temp = b1;
                b1 = a1 % b1;
                a1 = temp;
            }
            return a1;
        }

        fun lcm(a: Long, b: Long): Long {
            return (a / gcd(a, b)) * b;
        }

        val reduce = values
            .map { getSteps(it) }
            .reduce { acc, l -> lcm(acc, l) }
        println(reduce)


    }

}

