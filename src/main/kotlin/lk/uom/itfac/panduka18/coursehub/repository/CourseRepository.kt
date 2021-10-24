package lk.uom.itfac.panduka18.coursehub.repository;

import lk.uom.itfac.panduka18.coursehub.entity.Course
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CourseRepository : JpaRepository<Course, UUID> {
}
