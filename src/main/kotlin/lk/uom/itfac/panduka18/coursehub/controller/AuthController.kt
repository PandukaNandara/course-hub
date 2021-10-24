package lk.uom.itfac.panduka18.coursehub.controller

import lk.uom.itfac.panduka18.coursehub.payload.request.LoginRequest
import lk.uom.itfac.panduka18.coursehub.payload.response.AuthResponse
import lk.uom.itfac.panduka18.coursehub.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 12:59 PM
 */
@RestController
@RequestMapping("/api/auth")
class AuthController(
        val authService: AuthService,
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<AuthResponse> {
        val login = authService.login(loginRequest)
        return ResponseEntity.ok(login)
    }
}
