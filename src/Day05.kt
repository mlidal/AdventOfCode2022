import java.util.*

data class Operation(val number: Int, val start: Int, val end: Int) {

}

fun parseCargo(input: List<String>) : List<Stack<Char>> {
    val stacks = input.last().split(Regex(" \\d ")).map { Stack<Char>() }.toList()
    input.subList(0, input.size - 1).forEach {
        it.chunked(4).forEachIndexed { index, text ->
            if (text.isNotBlank()) {
                val code = text.trim()[1]
                stacks[index].push(code)
            }
        }
    }

    return stacks
}

fun parseOperations(input: List<String>) : List<Operation> {
    val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
    return input.map {
        val groups = regex.findAll(it).toList().first().groups
        Operation(groups[1]!!.value.toInt(), groups[2]!!.value.toInt() - 1, groups[3]!!.value.toInt() - 1)
    }
}


fun main() {


    fun part1(input: List<String>): String {
        val operationStart = input.indexOfFirst { it.isEmpty() }
        val cargo = input.subList(0, operationStart)
        val stacks = parseCargo(cargo)

        val operations = parseOperations(input.subList(operationStart + 1, input.size))
        operations.forEach { operation ->
            for (i in 0 until operation.number) {
                val element = stacks[operation.start].first()
                stacks[operation.start].remove(element)
                stacks[operation.end].insertElementAt(element, 0)
            }
        }
        return stacks.map { it.first() }.joinToString("")
    }





    fun part2(input: List<String>): String {
        val operationStart = input.indexOfFirst { it.isEmpty() }
        val cargo = input.subList(0, operationStart)
        val stacks = parseCargo(cargo)

        val operations = parseOperations(input.subList(operationStart + 1, input.size))
        operations.forEach { operation ->
            val elements = stacks[operation.start].subList(0, operation.number).toList()
            for (i in 0 until operation.number) {
                stacks[operation.start].removeAt(0)
            }
            val reversed = elements.reversed()
            reversed.forEach {
                stacks[operation.end].insertElementAt(it, 0)
            }
        }
        return stacks.map { it.first() }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
