package lk.uom.itfac.panduka18.coursehub.service;

import lk.uom.itfac.panduka18.coursehub.model.CourseDto
import lk.uom.itfac.panduka18.coursehub.payload.request.NewCourseRequest;
import lk.uom.itfac.panduka18.coursehub.payload.request.UpdateCourseRequest
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import org.springframework.stereotype.Service;
import java.util.*

/**
 * Created by IntelliJ IDEA.
 *
 * @author pandu
 * Date: 2021-10-22
 * Time: 10:25 AM
 */
@Service
interface CourseService {
    fun create(vararg newCourse: NewCourseRequest);
    fun getAll(userPrincipal: UserPrincipal?): List<CourseDto>
    fun edit(id: UUID, request: UpdateCourseRequest)
    fun delete(courseId: UUID)
    fun getOne(courseId: UUID, userPrincipal: UserPrincipal?): CourseDto?
}
