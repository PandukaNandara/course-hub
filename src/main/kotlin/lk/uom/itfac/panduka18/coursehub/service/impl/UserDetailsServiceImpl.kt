package lk.uom.itfac.panduka18.coursehub.service.impl

import lk.uom.itfac.panduka18.coursehub.constant.Role
import lk.uom.itfac.panduka18.coursehub.entity.Student
import lk.uom.itfac.panduka18.coursehub.entity.User
import lk.uom.itfac.panduka18.coursehub.repository.UserRepository
import lk.uom.itfac.panduka18.coursehub.security.TokenProvider
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.UserDetailsService
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
@RequiredArgsConstructor
class UserDetailsServiceImpl(
        val userRepository: UserRepository,
        val tokenProvider: TokenProvider,
) : UserDetailsService {

    override fun loadUserById(userId: UUID): UserDetails {
        val user: User = userRepository.findById(userId).orElseThrow { UsernameNotFoundException("Cannot find ById for userId $userId") }
        return createUserPrincipal(user)
    }


    override fun loadUserByUsername(username: String): UserDetails {
        val userByEmail: User = userRepository.findByUsername(username).orElseThrow {
            UsernameNotFoundException("Cannot find user for email $username")
        }
        return createUserPrincipal(userByEmail)
    }


    override fun loadUserByDetails(user: User?): UserPrincipal {
        return if (user == null) {
            throw UsernameNotFoundException("Cannot find user.")
        } else {
            createUserPrincipal(user)
        }
    }

    fun createUserPrincipal(user: User): UserPrincipal {
        val authorities: List<Role> = listOf(
                user.role
        )
        val userPrincipal = UserPrincipal(
                user.id!!,
                user.username,
                user.password,
                authorities,
                mapOf(),
                if (user is Student) user.name else user.username
        );

        return userPrincipal
    }
}
