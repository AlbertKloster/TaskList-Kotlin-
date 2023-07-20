package tasklist

enum class Actions(val string: String) {
    ADD("add"), PRINT("print"), END("end");

    companion object {
        fun getAction(input: String): Actions {
            for (action in Actions.values()) {
                if (action.string == input.lowercase()) return action
            }
            throw RuntimeException("The input action is invalid")
        }
    }
}