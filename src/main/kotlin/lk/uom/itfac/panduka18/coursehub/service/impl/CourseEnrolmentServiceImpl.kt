package lk.uom.itfac.panduka18.coursehub.service.impl

import lk.uom.itfac.panduka18.coursehub.entity.CourseEnrollment
import lk.uom.itfac.panduka18.coursehub.payload.response.CourseEnrolmentDetail
import lk.uom.itfac.panduka18.coursehub.repository.CourseEnrollmentRepository
import lk.uom.itfac.panduka18.coursehub.repository.CourseRepository
import lk.uom.itfac.panduka18.coursehub.repository.StudentRepository
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.CourseEnrolmentService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import java.util.*
import javax.transaction.Transactional

@Component
class CourseEnrolmentServiceImpl(
        private val courseEnrollmentRepository: CourseEnrollmentRepository,
        private val courseRepository: CourseRepository,
        private val studentRepository: StudentRepository,
) : CourseEnrolmentService {
    override fun enrol(courseId: UUID, currentUser: UserPrincipal) {
        val existsByCourseIdAndStudentId =
                courseEnrollmentRepository.existsByCourseIdAndStudentId(courseId, currentUser.id);
        if (existsByCourseIdAndStudentId) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "The student is already enrolled.")
        }
        val course = courseRepository.getById(courseId);
        val student = studentRepository.getById(currentUser.id);
        val courseEnrollment = CourseEnrollment(
                student, course, Date()
        );
        courseEnrollmentRepository.save(courseEnrollment);
    }

    @Transactional
    override fun unenroll(courseId: UUID, currentUser: UserPrincipal) {
        val existsByCourseIdAndStudentId =
                courseEnrollmentRepository.existsByCourseIdAndStudentId(courseId, currentUser.id);
        if (!existsByCourseIdAndStudentId) {
            throw HttpClientErrorException(HttpStatus.BAD_REQUEST, "You have not enrolled yet.")
        }
        courseEnrollmentRepository.deleteByCourseIdAndStudentId(courseId, currentUser.id);
    }

    override fun getAllStudentEnrolmentByCourseId(courseId: UUID): List<CourseEnrolmentDetail> {
        val allByCourseId = courseEnrollmentRepository.getAllByCourseId(courseId);
        return allByCourseId.map(::CourseEnrolmentDetail);
    }

}
