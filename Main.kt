package tasklist

val taskList = mutableListOf<MutableList<String>>()

fun main() {
    while (true) {
        println("Input an action (add, print, end):")
        try {
            when (Actions.getAction(readln().trim())) {
                Actions.ADD -> add()
                Actions.PRINT -> printList()
                Actions.END -> break
            }
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

    println("Tasklist exiting!")

}

private fun add() {
    println("Input a new task (enter a blank line to end):")
    val task = mutableListOf<String>()

    while (true) {
        val subTask = readln().trim()
        if (subTask.isEmpty()) break
        task.add(subTask)
    }

    if (task.isEmpty())
        println("The task is blank")
    else
        taskList.add(task)

}

private fun printList() {
    if (taskList.isEmpty())
        println("No tasks have been input")

    for (i in taskList.indices) {
        val taskNumber = i + 1
        print("${if (taskNumber < 10) "$taskNumber " else taskNumber} ")
        for (j in taskList[i].indices) {
            if (j > 0) print(" ".repeat(3))
            println(taskList[i][j])
        }
        println()
    }
}


