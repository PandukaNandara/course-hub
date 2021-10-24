package lk.uom.itfac.panduka18.coursehub.payload.response

import lk.uom.itfac.panduka18.coursehub.entity.CourseEnrollment
import lk.uom.itfac.panduka18.coursehub.model.StudentDto
import java.util.*

class CourseEnrolmentDetail(
        val date: Date,
        val student: StudentDto,
) {
    constructor(courseEnrolment: CourseEnrollment) : this(
            courseEnrolment.date,
            StudentDto(courseEnrolment.student)
    );
}
