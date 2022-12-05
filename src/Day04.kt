import java.lang.IllegalArgumentException


fun main() {

    fun part1(input: List<String>): Int {
        var containsCount = 0
        input.forEach {
            val parts = it.split(",")
            val (start1, end1) = parts[0].split("-").map { it.toInt() }
            val (start2, end2) = parts[1].split("-").map { it.toInt() }
            if (start1 <= start2 && end1 >= end2 || start2 <= start1 && end2 >= end1) {
                containsCount += 1
            }
        }
        return containsCount
    }



    fun part2(input: List<String>): Int {
        var containsCount = 0
        input.forEach {
            val parts = it.split(",")
            val (start1, end1) = parts[0].split("-").map { it.toInt() }
            val range1 = start1..end1
            val (start2, end2) = parts[1].split("-").map { it.toInt() }
            val range2 = start2..end2
            if (range1.intersect(range2).isNotEmpty()) containsCount += 1
        }
        return containsCount
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
