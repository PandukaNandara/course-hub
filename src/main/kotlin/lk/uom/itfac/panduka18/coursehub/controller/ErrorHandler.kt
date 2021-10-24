package lk.uom.itfac.panduka18.coursehub.controller

import lk.uom.itfac.panduka18.coursehub.payload.response.ApiError
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 11:38 PM
 */
@ControllerAdvice
class ErrorHandler {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(HttpClientErrorException::class)
    fun handle(
            ex: RuntimeException,
            request: HttpServletRequest?, response: HttpServletResponse?,
    ): ResponseEntity<ApiError> {
        log.error(ex.message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiError(
                                HttpStatus.NOT_FOUND,
                                Date(),
                                ex.message ?: ""
                        )
                );
    }
}
