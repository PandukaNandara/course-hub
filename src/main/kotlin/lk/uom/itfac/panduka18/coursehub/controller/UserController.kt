package lk.uom.itfac.panduka18.coursehub.controller

import lk.uom.itfac.panduka18.coursehub.payload.response.CurrentUserDetail
import lk.uom.itfac.panduka18.coursehub.security.CurrentUser
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 12:59 PM
 */
@RestController
@RequestMapping("/api/users")
class UserController(
        val authService: AuthService,
) {
    @GetMapping("/me")
    fun getCurrentUserDetails(@CurrentUser userPrincipal: UserPrincipal): ResponseEntity<CurrentUserDetail> {
        return ResponseEntity.ok(CurrentUserDetail(userPrincipal.name, userPrincipal.getFirstRole()))
    }
}
