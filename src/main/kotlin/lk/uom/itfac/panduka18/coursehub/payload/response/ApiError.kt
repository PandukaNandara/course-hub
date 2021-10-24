package lk.uom.itfac.panduka18.coursehub.payload.response

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import java.util.*

class ApiError(
        val status: Int,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        val timestamp: Date,
        val message: String,
) {
    constructor(
            status: HttpStatus,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            timestamp: Date,
            message: String,
    ) : this(status.value(), timestamp, message)
}
