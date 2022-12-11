import kotlin.math.abs
import kotlin.text.StringBuilder

fun main() {
    fun part1(input: List<String>): Int {

        var cycle = 1
        var signalStrength = 1
        val signalStrengths = mutableListOf<Int>()
        input.forEach { line ->
            if (line.startsWith("addx")) {
                cycle += 1
                if (listOf(20, 60, 100, 140, 180, 220).contains(cycle)) {
                    signalStrengths.add(cycle * signalStrength)
                }
                val param = line.substring("addx ".length).toInt()
                cycle += 1
                signalStrength += param
                if (listOf(20, 60, 100, 140, 180, 220).contains(cycle)) {
                    signalStrengths.add(cycle * signalStrength)
                }
            } else if (line == "noop"){
                cycle += 1
                if (listOf(20, 60, 100, 140, 180, 220).contains(cycle)) {
                    signalStrengths.add(cycle * signalStrength)
                }
            } else {
                throw IllegalArgumentException("Unknown operation $line")
            }
        }
        return signalStrengths.sum()
    }

    fun part2(input: List<String>): List<String> {
        val result = mutableListOf<String>()
        var cycle = 1
        var linePosition = 0
        val lineLength = 40
        var signalStrength = 1
        val lineText = StringBuilder()
        lineText.append('#')
        input.forEach { line ->
            if (line.startsWith("addx")) {
                cycle += 1
                if (linePosition < lineLength - 1) {
                    linePosition += 1
                } else {
                    result.add(lineText.toString())
                    lineText.clear()
                    linePosition = 0
                }
                if (abs(linePosition - signalStrength) <= 1) {
                    lineText.append('#')
                } else {
                    lineText.append('.')
                }
                val param = line.substring("addx ".length).toInt()
                cycle += 1
                if (linePosition < lineLength - 1) {
                    linePosition += 1
                } else {
                    result.add(lineText.toString())
                    lineText.clear()
                    linePosition = 0
                }
                signalStrength += param
                if (abs(linePosition - signalStrength) <= 1) {
                    lineText.append('#')
                } else {
                    lineText.append('.')
                }
            } else if (line == "noop"){
                cycle += 1
                if (linePosition < lineLength - 1) {
                    linePosition += 1
                } else {
                    result.add(lineText.toString())
                    lineText.clear()
                    linePosition = 0
                }
                if (abs(linePosition - signalStrength) <= 1) {
                    lineText.append('#')
                } else {
                    lineText.append('.')
                }
            } else {
                throw IllegalArgumentException("Unknown operation $line")
            }
        }
        result.forEach {
            println(it)
        }
        return result

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    val part2Result = """##..##..##..##..##..##..##..##..##..##..
                         ###...###...###...###...###...###...###.
                         ####....####....####....####....####....
                         #####.....#####.....#####.....#####.....
                         ######......######......######......####
                         #######.......#######.......#######.....
                         """.trimIndent()
    //check(part2(testInput).joinToString("") == part2Result)

    println()
    println()
    println()
    val input = readInput("Day10")
    println(part1(input))
    part2(input).forEach { println(it) }
}
