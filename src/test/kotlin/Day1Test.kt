import org.junit.jupiter.api.Test

class Day1Test {
    val test = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().lines()

    @Test
    fun test() {
        val map = test.map { s -> Pair(s.first { it.isDigit() }, s.last { it.isDigit() }) }
        println(map)
        val message = map
            .map { p -> "${p.first}${p.second}".toInt() }
            .reduceRight { i: Int, a: Int -> a + i }
        println(message)

    }

}
