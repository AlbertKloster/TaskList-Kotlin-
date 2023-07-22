package tasklist

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime

class LocalDateTimeAdapter {
    @ToJson
    fun toJson(ldt: LocalDateTime): String {
        return ldt.toString()
    }

    @FromJson
    fun fromJson(ldt: String): LocalDateTime {
        return LocalDateTime.parse(ldt)
    }
}