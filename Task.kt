package tasklist

import java.time.LocalDateTime


data class Task(var priority: Priorities, var deadline: LocalDateTime, var subTasks: MutableList<String>)
