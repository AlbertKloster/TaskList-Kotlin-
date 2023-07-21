package tasklist

enum class TaskFields(val string: String) {
    PRIORITY("priority"),
    DATE("date"),
    TIME("time"),
    TASK("task"),
    ;

    companion object {
        fun getTaskField(input: String): TaskFields {
            for (field in TaskFields.values()) {
                if (field.string == input.lowercase()) return field
            }
            throw RuntimeException("Wrong task field $input")
        }
    }
}