import java.io.File

abstract class Aoc {
    fun readFile(fileName: String): List<String> =
        object {}::class.java.getResource(fileName)!!.readText().trim().lines()

    fun main() {
        one()
        two()
    }


    abstract fun one();

    abstract fun two();
}
