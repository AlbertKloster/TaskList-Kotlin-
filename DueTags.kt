package tasklist

enum class DueTags(val string: String, val color: String) {
    IN_TIME("I", "\u001B[102m \u001B[0m"),
    TODAY("T", "\u001B[103m \u001B[0m"),
    OVERDUE("O", "\u001B[101m \u001B[0m"),
}