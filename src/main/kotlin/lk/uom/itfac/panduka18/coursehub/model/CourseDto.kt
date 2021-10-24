package lk.uom.itfac.panduka18.coursehub.model

import lk.uom.itfac.panduka18.coursehub.constant.CourseMode
import lk.uom.itfac.panduka18.coursehub.entity.Course
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 11:44 AM
 */
open class CourseDto(
        open var id: UUID,
        open var code: String,
        open var type: CourseMode,
        open var name: String,
        open var maxNumberOfStudent: Int,
        open var link: String,
) {
    var isEnrolled: Boolean? = null

    constructor(course: Course) : this(course.id!!, course.code, course.type, course.name, course.maxNumberOfStudent, course.link)
}
