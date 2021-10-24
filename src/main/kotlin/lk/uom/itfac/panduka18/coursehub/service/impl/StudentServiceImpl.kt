package lk.uom.itfac.panduka18.coursehub.service.impl

import lk.uom.itfac.panduka18.coursehub.constant.Role
import lk.uom.itfac.panduka18.coursehub.entity.Student
import lk.uom.itfac.panduka18.coursehub.entity.User
import lk.uom.itfac.panduka18.coursehub.payload.request.StudentCreationRequest
import lk.uom.itfac.panduka18.coursehub.payload.request.StudentUpdateRequest
import lk.uom.itfac.panduka18.coursehub.payload.response.AuthResponse
import lk.uom.itfac.panduka18.coursehub.repository.StudentRepository
import lk.uom.itfac.panduka18.coursehub.repository.UserRepository
import lk.uom.itfac.panduka18.coursehub.security.TokenProvider
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.StudentService
import lk.uom.itfac.panduka18.coursehub.service.UserDetailsService
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.HttpClientErrorException
import java.util.*

@Component
class StudentServiceImpl(
        val studentRepository: StudentRepository,
        val passwordEncoder: PasswordEncoder,
        val userDetailsService: UserDetailsService,
        val tokenProvider: TokenProvider,
        val userRepository: UserRepository,
) : StudentService {

    override fun create(studentCreationRequest: StudentCreationRequest) {
        val existsByUsername = userRepository.existsByUsername(studentCreationRequest.username)
        if (existsByUsername) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username is already exists")
        }
        val encodedPassword = passwordEncoder.encode(studentCreationRequest.password)
        val student = Student(studentCreationRequest.username, encodedPassword)
        student.name = studentCreationRequest.name
        student.dob = studentCreationRequest.dob
        student.role = Role.STUDENT
        studentRepository.save(student)

    }

    @Transactional
    fun createTokenForNewUser(user: User): AuthResponse {
        val userPrincipal: UserPrincipal = userDetailsService.loadUserByDetails(user)
        val token: String = tokenProvider.createToken(userPrincipal)
        return AuthResponse(token, userPrincipal.getFirstRole())
    }

    override fun update(id: UUID, studentUpdateRequest: StudentUpdateRequest, currentUser: UserPrincipal) {
        if (id != currentUser.id || currentUser.getFirstRole() != Role.ADMIN) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cannot change the user details")
        } else {
            val user = studentRepository.getById(id)
            studentUpdateRequest.name ?: run { user.name = studentUpdateRequest.name!! }
            if(studentUpdateRequest.password != null){
                val oldPassword = studentUpdateRequest.oldPassword!!;
                if(!passwordEncoder.matches(oldPassword, user.password))
                    throw HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE, "Cannot change the password")
                user.password = passwordEncoder.encode(studentUpdateRequest.password);
            }

            studentUpdateRequest.dob ?: run { user.dob = studentUpdateRequest.dob!! }
        }
    }

    override fun delete(id: UUID) {

    }
}
