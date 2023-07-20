package tasklist

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val taskList = mutableListOf<Task>()

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
    val priority: Priorities

    while (true) {
        println("Input the task priority (C, H, N, L):")
        priority = Priorities.getPriority(readln()) ?: continue
        break
    }

    var date: LocalDate

    while (true) {
        println("Input the date (yyyy-mm-dd):")
        try {
            date = LocalDate.parse(readln().parseToDate())
            break
        } catch (e: RuntimeException) {
            println("The input date is invalid")
        }
    }

    var deadline: LocalDateTime

    while (true) {
        println("Input the time (hh:mm):")
        try {
            deadline = LocalDateTime.parse(date.toString() + "T" + readln().parseToTime() + ":00")
            break
        } catch (e: RuntimeException) {
            println("The input time is invalid")
        }
    }

    println("Input a new task (enter a blank line to end):")
    val subTaskList = mutableListOf<String>()

    while (true) {
        val subTask = readln().trim()
        if (subTask.isEmpty()) break
        subTaskList.add(subTask)
    }

    if (subTaskList.isEmpty())
        println("The task is blank")
    else
        taskList.add(Task(priority, deadline, subTaskList))

}

private fun printList() {
    if (taskList.isEmpty())
        println("No tasks have been input")

    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    for (i in taskList.indices) {
        val taskNumber = i + 1
        print("${if (taskNumber < 10) "$taskNumber " else taskNumber} ")
        print(dateTimeFormatter.format(taskList[i].deadline))
        println(" ${taskList[i].priority.string}")
        for (subTask in taskList[i].subTasks) {
            print(" ".repeat(3))
            println(subTask)
        }
        println()
    }
}

private fun String.parseToDate(): String {
    try {
        val (yyyy, mm, dd) = this.split("-")
        val year = if (yyyy.length == 4) yyyy else "20$yyyy"
        val month = if (mm.length == 2) mm else "0$mm"
        val day = if (dd.length == 2) dd else "0$dd"
        return "$year-$month-$day"
    } catch (e: RuntimeException) {
        throw RuntimeException("Wrong date format")
    }
}

private fun String.parseToTime(): String {
    try {
        val (m, s) = this.split(":")
        val min = if (m.length == 2) m else "0$m"
        val sec = if (s.length == 2) s else "0$s"
        return "$min:$sec"
    } catch (e: RuntimeException) {
        throw RuntimeException("Wrong time format")
    }
}
