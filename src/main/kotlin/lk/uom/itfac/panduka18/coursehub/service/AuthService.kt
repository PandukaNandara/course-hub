package lk.uom.itfac.panduka18.coursehub.service

import lk.uom.itfac.panduka18.coursehub.payload.request.LoginRequest
import lk.uom.itfac.panduka18.coursehub.payload.response.AuthResponse
import org.springframework.stereotype.Service

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 11:30 PM
 */
@Service
interface AuthService {

    fun login(loginRequest: LoginRequest): AuthResponse;
}
