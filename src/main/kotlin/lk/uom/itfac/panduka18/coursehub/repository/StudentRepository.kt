package lk.uom.itfac.panduka18.coursehub.repository;

import lk.uom.itfac.panduka18.coursehub.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, UUID> {
    fun existsByUsername(username: String): Boolean;
}
