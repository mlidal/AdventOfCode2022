
fun main() {


    fun part1(input: String): Int {
        var chars = mutableListOf<Char>()
        input.forEachIndexed { index, char ->
            if (!chars.contains(char)) {
                chars.add(char)
                if (chars.size == 4) {
                    return index + 1
                }
            } else {
                val index = chars.indexOf(char)
                chars = chars.subList(index + 1, chars.size)
                chars.add(char)
            }
        }
        return input.length
    }


    fun part2(input: String): Int {
        var chars = mutableListOf<Char>()
        input.forEachIndexed { index, char ->
            if (!chars.contains(char)) {
                chars.add(char)
                if (chars.size == 14) {
                    return index + 1
                }
            } else {
                val index = chars.indexOf(char)
                chars = chars.subList(index + 1, chars.size)
                chars.add(char)
            }
        }
        return input.length
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput.first()) == 7)
    check(part2(testInput.first()) == 19)

    val input = readInput("Day06")
    println(part1(input.first()))
    println(part2(input.first()))
}
