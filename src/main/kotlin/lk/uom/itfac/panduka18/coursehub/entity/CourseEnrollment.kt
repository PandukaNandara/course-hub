package lk.uom.itfac.panduka18.coursehub.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 10:22 AM
 */
@Entity
class CourseEnrollment(
        @ManyToOne
        @JoinColumn(name = "student_id")
        var student: Student,
        @ManyToOne
        @JoinColumn(name = "course_id")
        var course: Course,

        var date: Date
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    var id: UUID? = null;

}
