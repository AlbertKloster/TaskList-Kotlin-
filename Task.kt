package tasklist

import java.time.LocalDateTime


data class Task(val priority: Priorities, val deadline: LocalDateTime, val subTasks: MutableList<String>)
