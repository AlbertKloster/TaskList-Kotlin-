package tasklist

fun main() {
    val taskListHandler = TaskListHandler()
    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        try {
            when (Actions.getAction(readln().trim())) {
                Actions.ADD -> taskListHandler.add()
                Actions.PRINT -> taskListHandler.printList()
                Actions.EDIT -> taskListHandler.edit()
                Actions.DELETE -> taskListHandler.delete()
                Actions.END -> break
            }
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }
    taskListHandler.exit()
}
