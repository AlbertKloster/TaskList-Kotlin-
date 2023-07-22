package tasklist

enum class Priorities(val string: String, val color: String) {
    CRITICAL("C", "\u001B[101m \u001B[0m"),
    HIGH("H", "\u001B[103m \u001B[0m"),
    NORMAL("N", "\u001B[102m \u001B[0m"),
    LOW("L", "\u001B[104m \u001B[0m");

    companion object {
        fun getPriority(input: String): Priorities? {
            for (priority in Priorities.values()) {
                if (priority.string == input.uppercase()) return priority
            }
            return null
        }
    }
}