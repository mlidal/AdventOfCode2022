import java.lang.IllegalArgumentException

enum class Result {
    WIN, LOOSE, DRAW;

    companion object {
        fun parseResult(name: String) : Result {
            return when(name) {
                "X" -> LOOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw IllegalArgumentException("Unrecognized name $name")
            }
        }
    }
}

enum class Hand {
    ROCK, PAPER, SCISSORS;

    companion object {
        fun getHand(name: String) : Hand {
            return if (name == "A" || name == "X") ROCK else if (name == "B" || name == "Y") PAPER else if (name == "C" || name == "Z") SCISSORS else throw IllegalArgumentException("Unrecognized name $name")
        }

        fun getHand(other: Hand, result: Result) : Hand {
            return when(other) {
                ROCK -> when (result) {
                    Result.WIN -> PAPER
                    Result.LOOSE -> SCISSORS
                    Result.DRAW -> ROCK
                }
                PAPER -> when (result) {
                    Result.WIN -> SCISSORS
                    Result.LOOSE -> ROCK
                    Result.DRAW -> PAPER
                }
                SCISSORS -> when (result) {
                    Result.WIN -> ROCK
                    Result.LOOSE -> PAPER
                    Result.DRAW -> SCISSORS
                }
            }
        }
    }

    private fun value() : Int {
        return when(this) {
            ROCK -> 1
            PAPER -> 2
            SCISSORS -> 3
        }
    }

    private fun result(other: Hand) : Result {
        when(this) {
            ROCK -> return when(other) {
                ROCK -> Result.DRAW
                PAPER -> Result.LOOSE
                SCISSORS -> Result.WIN
            }
            PAPER -> return when(other) {
                ROCK -> Result.WIN
                PAPER -> Result.DRAW
                SCISSORS -> Result.LOOSE
            }
            SCISSORS -> return when(other) {
                ROCK -> Result.LOOSE
                PAPER -> Result.WIN
                SCISSORS -> Result.DRAW
            }
        }
    }

    fun score(other: Hand) : Int {
        val result = when(result(other)) {
            Result.WIN -> 6
            Result.LOOSE -> 0
            Result.DRAW -> 3
        }
        return value() + result
    }
}

fun main() {
    fun parseLine(line: String) : Pair<Hand, Hand> {
        val hands = line.split(" ").map { Hand.getHand(it) }.toList()
        if (hands.size == 2) {
            return Pair(hands[0], hands[1])
        } else {
            throw IllegalArgumentException("Unable to parse line $line")
        }
    }

    fun part1(input: List<String>): Int {
        val hands = input.map { parseLine(it) }.toList()
        val scores =  hands.map { it.second.score(it.first) }
        return scores.sum()
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


    fun part2(input: List<String>): Int {
        val hands = input.map { parseLine2(it) }.toList()
        val scores =  hands.map { it.second.score(it.first) }
        return scores.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
