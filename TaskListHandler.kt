package tasklist

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


const val TASK_FIELD_LENGTH = 44
const val TASK_LIST_FILE = "tasklist.json"

class TaskListHandler() {

    val taskList = load()

    private fun load(): MutableList<Task> {
        val file = File(TASK_LIST_FILE)
        if (file.exists()) {
            return getTaskListAdapter().fromJson(file.readText()) ?: mutableListOf()
        }
        return mutableListOf()
    }

    fun exit() {
        println("Tasklist exiting!")
        File(TASK_LIST_FILE).writeText(getTaskListAdapter().toJson(taskList))
    }

    private fun getTaskListAdapter(): JsonAdapter<MutableList<Task>> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).add(LocalDateTimeAdapter()).build()
        val type = Types.newParameterizedType(MutableList::class.java, Task::class.java)
        return moshi.adapter(type)
    }

    fun edit() {
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

    fun delete() {
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

    fun add() {
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

    fun printList() {
        if (taskList.isEmpty()) {
            println("No tasks have been input")
            return
        }

        val builder = StringBuilder(
            "+----+------------+-------+---+---+--------------------------------------------+\n" +
                    "| N  |    Date    | Time  | P | D |                   Task                     |\n" +
                    "+----+------------+-------+---+---+--------------------------------------------+\n"
        )
        val separator = "+----+------------+-------+---+---+--------------------------------------------+\n"
        val emptyColumns = "|    |            |       |   |   |"

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

        for (i in taskList.indices) {
            val taskNumber = i + 1
            builder.append("|")
            builder.append(" ${if (taskNumber < 10) "$taskNumber " else taskNumber} ")
            builder.append("|")
            builder.append(" ${dateFormatter.format(taskList[i].deadline)} ")
            builder.append("|")
            builder.append(" ${timeFormatter.format(taskList[i].deadline)} ")
            builder.append("|")
            builder.append(" ${taskList[i].priority.color} ")
            builder.append("|")
            builder.append(" ${getDueTag(taskList[i].deadline).color} ")
            builder.append("|")

            val allLines = getAllLines(taskList[i].subTasks)
            builder.append(allLines.first())
            builder.append(" ".repeat(TASK_FIELD_LENGTH - allLines.first().length))
            builder.append("|\n")

            for (index in 1 until allLines.size) {
                builder.append(emptyColumns)
                builder.append(allLines[index])
                builder.append(" ".repeat(TASK_FIELD_LENGTH - allLines[index].length))
                builder.append("|\n")
            }
            builder.append(separator)
        }
        print(builder)
        println()
    }

    private fun getAllLines(subTasks: MutableList<String>): MutableList<String> {
        val allLines = mutableListOf<String>()
        for (task in subTasks) {
            for (i in task.indices step TASK_FIELD_LENGTH) {
                val  substring = if (i + TASK_FIELD_LENGTH < task.length) task.substring(i, i + TASK_FIELD_LENGTH) else task.substring(i)
                allLines.add(substring)
            }
        }
        return allLines
    }

    private fun getDueTag(deadline: LocalDateTime): DueTags {
        val numberOfDays = deadline.until(LocalDateTime.now(), ChronoUnit.DAYS)
        return if (numberOfDays == 0L) DueTags.TODAY else if (numberOfDays < 0) DueTags.IN_TIME else DueTags.OVERDUE
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
}