package lk.uom.itfac.panduka18.coursehub.service

import lk.uom.itfac.panduka18.coursehub.payload.response.CourseEnrolmentDetail
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 5:29 PM
 */
@Service
interface CourseEnrolmentService {
    fun enrol(courseId: UUID, currentUser: UserPrincipal);
    fun unenroll(courseId: UUID, currentUser: UserPrincipal)
    fun getAllStudentEnrolmentByCourseId(courseId: UUID): List<CourseEnrolmentDetail>
}
