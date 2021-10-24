package lk.uom.itfac.panduka18.coursehub.entity

import java.util.*
import javax.persistence.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:55 AM
 */
@Entity
class Student(username: String, password: String) : User(username, password) {
    lateinit var name: String;
    lateinit var dob: Date;

    @OneToMany(mappedBy = "student")
    var course: List<CourseEnrollment> = listOf();
}
