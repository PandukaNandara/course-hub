package lk.uom.itfac.panduka18.coursehub.service.impl

import lk.uom.itfac.panduka18.coursehub.constant.Role
import lk.uom.itfac.panduka18.coursehub.entity.Course
import lk.uom.itfac.panduka18.coursehub.model.CourseDto
import lk.uom.itfac.panduka18.coursehub.payload.request.NewCourseRequest
import lk.uom.itfac.panduka18.coursehub.payload.request.UpdateCourseRequest
import lk.uom.itfac.panduka18.coursehub.repository.CourseEnrollmentRepository
import lk.uom.itfac.panduka18.coursehub.repository.CourseRepository
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lk.uom.itfac.panduka18.coursehub.service.CourseService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class CourseServiceImpl(val courseRepository: CourseRepository, var courseEnrollmentRepository: CourseEnrollmentRepository) : CourseService {

    override fun create(vararg newCourse: NewCourseRequest) {
        val map = newCourse.map { (code, courseMode, name, maxNumberOfStudent, link) ->
            Course(code, courseMode, name, maxNumberOfStudent, link)
        };
        courseRepository.saveAll(map);
    }

    override fun getAll(userPrincipal: UserPrincipal?): List<CourseDto> {
        val all = courseRepository.findAll();
        return all.map {
            val courseDto = CourseDto(it);
            if (userPrincipal != null && userPrincipal.getFirstRole() == Role.STUDENT)
                courseDto.isEnrolled = courseEnrollmentRepository.existsByCourseIdAndStudentId(it.id!!, userPrincipal.id);
            courseDto
        };
    }

    @Transactional
    override fun delete(courseId: UUID) {
        val course = courseRepository.getById(courseId);
        courseEnrollmentRepository.deleteAll(course.enrollment);
        courseRepository.deleteById(courseId);
    }

    override fun getOne(courseId: UUID, userPrincipal: UserPrincipal?): CourseDto? {
        val one = courseRepository.findById(courseId);
        return one.map { CourseDto(it) }.orElse(null);
    }

    override fun edit(id: UUID, request: UpdateCourseRequest) {
        val optional = courseRepository.findById(id);
        if (optional.isPresent) {
            val course = optional.get();
            if (request.code != null) {
                course.code = request.code!!;
            }
            if (request.name != null) {
                course.name = request.name!!;
            }
            if (request.link != null) {
                course.link = request.link;
            }
            if (request.maxNumberOfStudent != null) {
                course.maxNumberOfStudent = request.maxNumberOfStudent!!;
            }
            courseRepository.save(course);
        }
    }
}
