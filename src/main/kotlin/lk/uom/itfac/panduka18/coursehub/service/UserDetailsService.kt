package lk.uom.itfac.panduka18.coursehub.service

import lk.uom.itfac.panduka18.coursehub.entity.User
import lk.uom.itfac.panduka18.coursehub.payload.request.LoginRequest
import lk.uom.itfac.panduka18.coursehub.payload.response.AuthResponse
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 2:44 PM
 */
@Service
interface UserDetailsService : UserDetailsService {
    fun loadUserById(userId: UUID): UserDetails;
    fun loadUserByDetails(user: User?): UserPrincipal
}
