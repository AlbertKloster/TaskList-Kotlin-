package tasklist

enum class Priorities(val string: String) {
    CRITICAL("C"),
    HIGH("H"),
    NORMAL("N"),
    LOW("L");

    companion object {
        fun getPriority(input: String): Priorities? {
            for (priority in Priorities.values()) {
                if (priority.string == input.uppercase()) return priority
            }
            return null
        }
    }
}