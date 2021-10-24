package lk.uom.itfac.panduka18.coursehub.repository;

import lk.uom.itfac.panduka18.coursehub.entity.CourseEnrollment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CourseEnrollmentRepository : JpaRepository<CourseEnrollment, UUID> {
    fun existsByCourseIdAndStudentId(course_id: UUID, student_id: UUID): Boolean;
    fun deleteByCourseIdAndStudentId(course_id: UUID, student_id: UUID);
    fun getAllByCourseId(course_id: UUID): List<CourseEnrollment>;
}
