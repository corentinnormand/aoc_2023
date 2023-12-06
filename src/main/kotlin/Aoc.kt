import java.io.File

abstract class Aoc {
    fun readFile(fileName: String): String =
        object {}::class.java.getResource(fileName)!!.readText().trim()

    fun main() {
        one()
        two()
    }


    abstract fun one();

    abstract fun two();
}
