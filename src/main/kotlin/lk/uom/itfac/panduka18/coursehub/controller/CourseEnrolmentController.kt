package lk.uom.itfac.panduka18.coursehub.controller

import lk.uom.itfac.panduka18.coursehub.payload.response.CourseEnrolmentDetail
import lk.uom.itfac.panduka18.coursehub.security.CurrentUser
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.security.permisson.AdminPermission
import lk.uom.itfac.panduka18.coursehub.security.permisson.StudentPermission
import lk.uom.itfac.panduka18.coursehub.service.CourseEnrolmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 5:48 PM
 */
@RestController
@RequestMapping("/api/courses")
class CourseEnrolmentController(
        private val courseEnrolmentService: CourseEnrolmentService,
) {
    @PostMapping("/enrols/{courseId}")
    @StudentPermission
    fun enrolToCourse(@PathVariable courseId: UUID, @CurrentUser currentUser: UserPrincipal) {
        courseEnrolmentService.enrol(courseId, currentUser)
    }

    @DeleteMapping("/enrols/{courseId}")
    @StudentPermission
    fun unenrollToCourse(@PathVariable courseId: UUID, @CurrentUser currentUser: UserPrincipal) {
        courseEnrolmentService.unenroll(courseId, currentUser)
    }

    @GetMapping("/enrols/{courseId}")
    @AdminPermission
    fun getAllEnrolmentOfThisCourse(@PathVariable courseId: UUID): ResponseEntity<List<CourseEnrolmentDetail>> {
        val studentEnrolmentByCourseId = courseEnrolmentService
                .getAllStudentEnrolmentByCourseId(courseId)
        return ResponseEntity.ok(studentEnrolmentByCourseId);
    }
}
