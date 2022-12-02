fun main() {
    fun part1(input: List<String>): Int {
        val calories = mutableListOf<Int>()
        var calorieSum = 0
        input.forEach {
            if (it.isNotBlank()) {
                calorieSum += it.toInt()
            } else {
                calories.add(calorieSum)
                calorieSum = 0
            }
        }
        calories.add(calorieSum)
        return calories.max()
    }

    fun part2(input: List<String>): Int {
        val calories = mutableListOf<Int>()
        var calorieSum = 0
        input.forEach {
            if (it.isNotBlank()) {
                calorieSum += it.toInt()
            } else {
                calories.add(calorieSum)
                calorieSum = 0
            }
        }
        calories.add(calorieSum)
        calories.sortDescending()
        return calories.take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
