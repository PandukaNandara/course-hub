package lk.uom.itfac.panduka18.coursehub.repository;

import lk.uom.itfac.panduka18.coursehub.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsername(username: String): Optional<User>;
    fun existsByUsername(username: String): Boolean;
}
