package lk.uom.itfac.panduka18.coursehub.entity;

import lk.uom.itfac.panduka18.coursehub.constant.CourseMode
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 * Created by IntelliJ IDEA.
 *
 * @author pandu
 * Date: 2021-10-22
 * Time: 10:16 AM
 */
@Entity
class Course(
        @Column(unique = true)
        var code: String,
        val type: CourseMode,
        var name: String,
        var maxNumberOfStudent: Int,
        var link: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    var id: UUID? = null;

    @OneToMany(mappedBy = "course")
    var enrollment: List<CourseEnrollment> = listOf();
}
