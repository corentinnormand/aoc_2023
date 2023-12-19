import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    Day7().two()
}

class Day7 : Aoc("day7.txt") {

    val test = """
    32T3K 765
    T55J5 684
    KK677 28
    KTJJT 220
    QQQJA 483
""".trimIndent()

    class Hand(val str: String) : Comparable<Hand> {
        override fun toString(): String = str

        fun type(): Int {
            val groupBy = str.groupBy { it }
                .mapValues { it.value.size }
            // nothing
            if (groupBy.size == 5)
                return 100
            // pair
            else if (groupBy.size == 4)
                return 200
            // two pairs 
            else if (groupBy.size == 3 && groupBy.values.max() == 2)
                return 300
            // three of a kind
            else if (groupBy.size == 3 && groupBy.values.max() == 3)
                return 400
            // full house
            else if (groupBy.size == 2 && groupBy.values.max() == 3)
                return 500
            // four  of a kind
            else if (groupBy.size == 2 && groupBy.values.max() == 4)
                return 600
            else if (groupBy.size == 1)
                return 700
            else
                return 0
        }


        override fun compareTo(other: Hand): Int {
            if (this.type() == other.type()) {
                for ((i, _) in this.str.withIndex()) {
                    if (this.str[i] != other.str[i]) {
                        return (this.type() + Day7.values[this.str[i]]!!) - (other.type() + Day7.values[other.str[i]]!!)
                    }
                }
            }
            return this.type() - other.type()
        }

    }

    class Hand2(val str: String) : Comparable<Hand2> {
        override fun toString(): String = str


        fun type(): Int {

            val groupBy = str.groupBy { it }
                .mapValues { it.value.size }

            val unique = groupBy.size
            val duplicates = groupBy.values.max()

            val jokers = groupBy.getOrDefault('J', 0)

            when (jokers) {
                0 -> {
                    return if (unique == 5)// nothing
                        100
                    else if (unique == 4)// pair
                        200
                    else if (unique == 3 && duplicates == 2)// two pairs
                        300
                    else if (unique == 3 && duplicates == 3)// three of a kind
                        400
                    else if (unique == 2 && duplicates == 3)// full house
                        500
                    else if (unique == 2 && duplicates == 4)// four  of a kind
                        600
                    else  // five of a kind
                        700
                }

                1 -> {
                    return if (unique == 2) 700
                    else if (unique == 3 && duplicates == 3) 600
                    else if (unique == 3 && duplicates == 2) 500
                    else if (unique == 4) 400
                    else 200
                }

                2 -> {
                    return when (unique) {
                        2 -> 700
                        3 -> 600
                        else -> 400
                    }
                }

                3 -> {
                    return if (unique == 2) return 700
                    else 600
                }

                else -> return 700
            }

        }


        override fun compareTo(other: Hand2): Int {
            if (this.type() == other.type()) {
                for ((i, _) in this.str.withIndex()) {
                    if (this.str[i] != other.str[i]) {
                        return (this.type() + Day7.values2[this.str[i]]!!) - (other.type() + Day7.values2[other.str[i]]!!)
                    }
                }
            }
            return this.type() - other.type()
        }

    }

    override fun one() {
        val input = readFile("day7.txt").lines()
            .map { it.split(" ") }
            .map { Pair(Hand(it[0]), it[1]) }

        val sortedWith = input.sortedBy { it.first }
        println(sortedWith.foldIndexed(0L) { index, acc, pair -> acc + (index + 1) * pair.second.toLong() })

    }

    override fun two() {

        val input = readFile("day7.txt").lines()
            .map { it.split(" ") }
            .map { Pair(Hand2(it[0]), it[1]) }

        val sortedWith = input.sortedBy { it.first }
        println(sortedWith.foldIndexed(0L) { index, acc, pair -> acc + (index + 1) * pair.second.toLong() })

    }

    companion object {
        val values = mapOf(
            'A' to 13,
            'K' to 12,
            'Q' to 11,
            'J' to 10,
            'T' to 9,
            '9' to 8,
            '8' to 7,
            '7' to 6,
            '6' to 5,
            '5' to 4,
            '4' to 3,
            '3' to 2,
            '2' to 1
        )
        val values2 = mapOf(
            'A' to 13,
            'K' to 12,
            'Q' to 11,
            'T' to 10,
            '9' to 9,
            '8' to 8,
            '7' to 7,
            '6' to 6,
            '5' to 5,
            '4' to 4,
            '3' to 3,
            '2' to 2,
            'J' to 1
        )
    }
}

