package tasklist

fun main() {
    val taskList = mutableListOf<String>()
    println("Input the tasks (enter a blank line to end):")

    while (true) {
        val task = readln().trim()
        if (task.isEmpty()) break
        taskList.add(task)
    }

    if (taskList.isEmpty())
        println("No tasks have been input")

    for (i in taskList.indices) {
        val taskNumber = i + 1
        println("${if (taskNumber < 10) "$taskNumber " else taskNumber} ${taskList[i]}")
    }
}


