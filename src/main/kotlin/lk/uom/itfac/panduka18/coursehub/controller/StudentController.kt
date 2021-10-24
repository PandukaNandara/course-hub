package lk.uom.itfac.panduka18.coursehub.controller

import lk.uom.itfac.panduka18.coursehub.payload.request.StudentCreationRequest
import lk.uom.itfac.panduka18.coursehub.payload.request.StudentUpdateRequest
import lk.uom.itfac.panduka18.coursehub.security.CurrentUser
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.StudentService
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 12:59 PM
 */
@RestController
@RequestMapping("/api/students")
class StudentController(
        var studentService: StudentService,
) {
    @PostMapping("/new")
    fun createStudent(@RequestBody studentCreationRequest: StudentCreationRequest) {
        return studentService.create(studentCreationRequest);
    }

    @PatchMapping("/{id}")
    fun updateStudent(
            @PathVariable id: UUID,
            @RequestBody studentUpdateRequest: StudentUpdateRequest,
            @CurrentUser currentUser: UserPrincipal,
    ) {
        return studentService.update(id, studentUpdateRequest, currentUser);
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: UUID) {
        return studentService.delete(id);
    }
}
