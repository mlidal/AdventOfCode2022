sealed class Entry(val name: String) {

    override fun toString(): String {
        return name
    }

}

class File(name: String, val size: Int) : Entry(name) {

}

class Directory(name: String, val parent: Directory? = null, val children: MutableList<Entry> = mutableListOf()) : Entry(name) {

    override fun toString(): String {
        return name
    }

    fun getSize() : Int {
        var size = 0
        children.forEach { child ->
            size += when(child) {
                is File -> child.size
                is Directory -> child.getSize()
            }
        }
        return size
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        val rootDirectory = Directory("/")
        var currentDirectory = rootDirectory
        val directories = mutableListOf(rootDirectory)

        input.forEach { line ->
            if (line.startsWith("$")) {
                val parts = line.split(" ")
                if (parts[1] == "cd") {
                    currentDirectory = if (parts[2] == "/") {
                        rootDirectory
                    } else if (parts[2] == "..") {
                        currentDirectory.parent!!
                    } else {
                        currentDirectory.children.first { it is Directory && it.name == parts[2] } as Directory
                    }
                }
            }
            else {
                val parts = line.split(" ")
                if (parts[0] == "dir") {
                    val directory = Directory(parts[1], currentDirectory)
                    directories.add(directory)
                    currentDirectory.children.add(directory)
                } else {
                    val file = File(parts[1], parts[0].toInt())
                    currentDirectory.children.add(file)
                }
            }
        }
        var sum = 0
        directories.forEach { directory ->
            val size = directory.getSize()
            if (size <= 100000) {
                sum += size
            }
        }
        return sum

    }

    fun part2(input: List<String>): Int {
        val rootDirectory = Directory("/")
        var currentDirectory = rootDirectory
        val directories = mutableListOf(rootDirectory)

        input.forEach { line ->
            if (line.startsWith("$")) {
                val parts = line.split(" ")
                if (parts[1] == "cd") {
                    currentDirectory = if (parts[2] == "/") {
                        rootDirectory
                    } else if (parts[2] == "..") {
                        currentDirectory.parent!!
                    } else {
                        currentDirectory.children.first { it is Directory && it.name == parts[2] } as Directory
                    }
                }
            }
            else {
                val parts = line.split(" ")
                if (parts[0] == "dir") {
                    val directory = Directory(parts[1], currentDirectory)
                    directories.add(directory)
                    currentDirectory.children.add(directory)
                } else {
                    val file = File(parts[1], parts[0].toInt())
                    currentDirectory.children.add(file)
                }
            }
        }

        val diskSize = 70000000
        val missingSpace = 30000000 - (diskSize - rootDirectory.getSize())
        var smallestDirectory = rootDirectory
        var smallestSize = rootDirectory.getSize()

        directories.forEach { directory ->
            val size = directory.getSize()
            if (size >= missingSpace && size < smallestSize) {
                smallestDirectory = directory
                smallestSize = size
            }
        }
        return smallestSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
