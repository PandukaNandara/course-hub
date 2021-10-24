package lk.uom.itfac.panduka18.coursehub.payload.request

import lk.uom.itfac.panduka18.coursehub.constant.CourseMode

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 12:00 PM
 */
data class UpdateCourseRequest(
    var code: String?,
    val type: CourseMode?,
    var name: String?,
    var maxNumberOfStudent: Int?,
    val link: String?
) {
}
