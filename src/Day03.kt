import java.lang.IllegalArgumentException

fun main() {

    fun sumPriorities(input: List<Char>) : Int {
        return input.sumOf { char: Char ->
            val value = if (char.isUpperCase()) {
                char.code - 'A'.code + 27
            } else {
                char.code - 'a'.code + 1
            }
            value
        }
    }

    fun part1(input: List<String>): Int {
        val sharedItems = mutableListOf<Char>()
        input.forEach {
            val parts = listOf(it.substring(0, it.length / 2), it.substring(it.length / 2))
            val duplicates = parts[0].filter { char -> parts[1].contains(char) }.toSet()
            sharedItems.addAll(duplicates)
        }
        val sum = sumPriorities(sharedItems)
        return sum
    }

    fun parseLine2(line: String) : Pair<Hand, Hand> {
        val hands = line.split(" ").take(2)
        if (hands.size == 2) {
            val other = Hand.getHand(hands[0])
            val mine = Hand.getHand(other, Result.parseResult(hands[1]))
            return Pair(other, mine)
        } else {
            throw IllegalArgumentException("Unable to parse line $line")
        }
    }

    fun findBadge(input: List<String>) : Char {
        return input[0].first { input[1].contains(it) && input[2].contains(it) }
    }

    fun part2(input: List<String>): Int {
        val groups = input.chunked(3)
        val badges = groups.map { findBadge(it) }
        return sumPriorities(badges)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)
    println()
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
