package tasklist

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

val taskList = mutableListOf<Task>()

fun main() {
    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        try {
            when (Actions.getAction(readln().trim())) {
                Actions.ADD -> add()
                Actions.PRINT -> printList()
                Actions.EDIT -> edit()
                Actions.DELETE -> delete()
                Actions.END -> break
            }
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

    println("Tasklist exiting!")

}

private fun edit() {
    printList()
    if (taskList.isEmpty()) return
    val taskToEdit = getTaskNumber()
    val fieldToEdit = getFieldToEdit()
    val task = taskList[taskToEdit - 1]
    when (fieldToEdit) {
        TaskFields.PRIORITY -> setPriority(task)
        TaskFields.DATE -> setDate(task)
        TaskFields.TIME -> setTime(task)
        TaskFields.TASK -> setTask(task)
    }
    println("The task is changed")
}

private fun getFieldToEdit(): TaskFields {
    while (true) {
        println("Input a field to edit (priority, date, time, task):")
        try {
            return TaskFields.getTaskField(readln())
        } catch (e: RuntimeException) {
            println("Invalid field")
        }
    }
}

private fun setPriority(task: Task) {
    task.priority = getPriority()
}

private fun setDate(task: Task) {
    val hour = task.deadline.hour
    val minute = task.deadline.minute
    val date = getDate()
    val time = "$hour:$minute".parseToTime()
    task.deadline = LocalDateTime.parse(date.toString() + "T" + time + ":00")
}

private fun setTime(task: Task) {
    val year = task.deadline.year
    val month = task.deadline.monthValue
    val dayOfMonth = task.deadline.dayOfMonth
    task.deadline = getDateTime(LocalDate.parse("$year-$month-$dayOfMonth".parseToDate()))
}

private fun setTask(task: Task) {
    task.subTasks = getSubTaskList()
}

private fun delete() {
    printList()
    if (taskList.isEmpty()) return
    taskList.removeAt(getTaskNumber() - 1)
    println("The task is deleted")
}

private fun getTaskNumber(): Int {
    while (true) {
        println("Input the task number (1-${taskList.size}):")
        val taskNumber = readln()
        if (!taskNumber.matches(Regex("\\d+"))) {
            println("Invalid task number")
            continue
        }
        if (taskNumber.toInt() !in 1..taskList.size) {
            println("Invalid task number")
            continue
        }
        return taskNumber.toInt()
    }
}

private fun add() {
    val priority = getPriority()
    val date = getDate()
    val deadline = getDateTime(date)

    val subTaskList = getSubTaskList()
    if (subTaskList.isEmpty())
        println("The task is blank")
    else
        taskList.add(Task(priority, deadline, subTaskList))

}

private fun getPriority(): Priorities {
    while (true) {
        println("Input the task priority (C, H, N, L):")
        return Priorities.getPriority(readln()) ?: continue
    }
}

private fun getDate(): LocalDate {
    while (true) {
        println("Input the date (yyyy-mm-dd):")
        try {
            return LocalDate.parse(readln().parseToDate())
        } catch (e: RuntimeException) {
            println("The input date is invalid")
        }
    }
}

private fun getDateTime(date: LocalDate): LocalDateTime {
    while (true) {
        println("Input the time (hh:mm):")
        try {
            return LocalDateTime.parse(date.toString() + "T" + readln().parseToTime() + ":00")
        } catch (e: RuntimeException) {
            println("The input time is invalid")
        }
    }
}

private fun getSubTaskList(): MutableList<String> {
    println("Input a new task (enter a blank line to end):")
    val subTaskList = mutableListOf<String>()

    while (true) {
        val subTask = readln().trim()
        if (subTask.isEmpty()) break
        subTaskList.add(subTask)
    }
    return subTaskList
}

private fun printList() {
    if (taskList.isEmpty())
        println("No tasks have been input")

    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    for (i in taskList.indices) {
        val taskNumber = i + 1
        print("${if (taskNumber < 10) "$taskNumber " else taskNumber} ")
        print(dateTimeFormatter.format(taskList[i].deadline))
        print(" ${taskList[i].priority.string}")
        println(" ${getDueTag(taskList[i].deadline)}")
        for (subTask in taskList[i].subTasks) {
            print(" ".repeat(3))
            println(subTask)
        }
        println()
    }
}

private fun getDueTag(deadline: LocalDateTime): String {
    val numberOfDays = deadline.until(LocalDateTime.now(), ChronoUnit.DAYS)
    return if (numberOfDays == 0L) "T" else if (numberOfDays < 0) "I" else "O"
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
