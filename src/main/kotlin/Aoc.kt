import java.io.File

abstract class Aoc(val day: String) {
    fun readFile(fileName: String): String =
        object {}::class.java.getResource(fileName)!!.readText().trim()

    fun input() = readFile(day)

    fun main() {
        one()
        two()
    }


    abstract fun one();

    abstract fun two();
}
