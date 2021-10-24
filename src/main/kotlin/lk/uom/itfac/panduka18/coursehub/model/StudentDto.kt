package lk.uom.itfac.panduka18.coursehub.model

import lk.uom.itfac.panduka18.coursehub.entity.Student
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 6:17 PM
 */
class StudentDto(
        var id: UUID,
        var name: String,
        var dob: Date,
) {
    constructor(entity: Student) : this(
            entity.id!!,
            entity.name,
            entity.dob
    )
}
