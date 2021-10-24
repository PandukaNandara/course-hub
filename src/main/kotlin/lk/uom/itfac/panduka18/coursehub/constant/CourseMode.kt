package lk.uom.itfac.panduka18.coursehub.constant

enum class CourseMode(val value: String) {
    E("Elective"), C("Compulsory");

    override fun toString(): String {
        return value;
    }
}
