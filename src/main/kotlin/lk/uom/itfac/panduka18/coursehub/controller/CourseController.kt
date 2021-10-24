package lk.uom.itfac.panduka18.coursehub.controller

import lk.uom.itfac.panduka18.coursehub.model.CourseDto
import lk.uom.itfac.panduka18.coursehub.payload.request.NewCourseRequest
import lk.uom.itfac.panduka18.coursehub.payload.request.UpdateCourseRequest
import lk.uom.itfac.panduka18.coursehub.security.CurrentUser
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.security.permisson.AdminPermission
import lk.uom.itfac.panduka18.coursehub.service.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 12:58 PM
 */
@RestController
@RequestMapping("/api/courses")
class CourseController(
        private val courseService: CourseService,
) {
    @GetMapping
    fun getAllCourses(@CurrentUser userPrincipal: UserPrincipal?): ResponseEntity<List<CourseDto>> {
        val all = courseService.getAll(userPrincipal)
        return ResponseEntity.ok(all)
    }

    @GetMapping("/{courseId}")
    fun getOnCourse(@PathVariable courseId: UUID,
                      @CurrentUser userPrincipal: UserPrincipal?): ResponseEntity<CourseDto> {
        val one = courseService.getOne(courseId, userPrincipal)
        return ResponseEntity.ok(one)
    }

    @PutMapping("/{courseId}")
    fun updateCourses(@PathVariable courseId: UUID,
                        @RequestBody updateCourseRequest: UpdateCourseRequest,
                      @CurrentUser userPrincipal: UserPrincipal?) {
        courseService.edit(courseId,updateCourseRequest);
    }

    @PostMapping
    @AdminPermission
    fun createCourse(@RequestBody newCourseRequest: NewCourseRequest, @CurrentUser userPrincipal: UserPrincipal?) {
        courseService.create(newCourseRequest);
    }


    @DeleteMapping("/{courseId}")
    @AdminPermission
    fun deleteCourse(@PathVariable courseId: UUID, @CurrentUser userPrincipal: UserPrincipal?) {
        courseService.delete(courseId);
    }
}
