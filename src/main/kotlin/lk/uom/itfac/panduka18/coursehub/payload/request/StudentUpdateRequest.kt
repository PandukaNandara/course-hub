package lk.uom.itfac.panduka18.coursehub.payload.request

import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:02 PM
 */
data class StudentUpdateRequest(
        var username: String?,
        var password: String?,
        var oldPassword: String?,
        var name: String?,
        var dob: Date?,
) {

}
