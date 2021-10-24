package lk.uom.itfac.panduka18.coursehub.service.impl

import lk.uom.itfac.panduka18.coursehub.entity.User
import lk.uom.itfac.panduka18.coursehub.payload.request.LoginRequest
import lk.uom.itfac.panduka18.coursehub.payload.response.AuthResponse
import lk.uom.itfac.panduka18.coursehub.repository.UserRepository
import lk.uom.itfac.panduka18.coursehub.security.TokenProvider
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.AuthService
import lk.uom.itfac.panduka18.coursehub.service.UserDetailsService
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import kotlin.jvm.Throws

@Component
class AuthServiceImpl(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository,
        val tokenProvider: TokenProvider,
        val userDetailsService: UserDetailsService
) : AuthService {

    @Throws(HttpClientErrorException::class)
    override fun login(loginRequest: LoginRequest): AuthResponse {
        val userByEmail: User = userRepository.findByUsername(loginRequest.username).orElseThrow {
            UsernameNotFoundException("Cannot find user for email ${loginRequest.username}")
        }
        if (!passwordEncoder.matches(loginRequest.password, userByEmail.password)) {
            throw HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Username or password is invalid");
        }
        val userPrincipal = userDetailsService.loadUserByDetails(userByEmail);
        val token: String = tokenProvider.createToken(userPrincipal);
        return AuthResponse(token, userPrincipal.getFirstRole());
    }
}
