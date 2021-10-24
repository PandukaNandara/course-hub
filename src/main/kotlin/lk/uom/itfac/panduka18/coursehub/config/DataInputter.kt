package lk.uom.itfac.panduka18.coursehub.config

import lk.uom.itfac.panduka18.coursehub.constant.CourseMode
import lk.uom.itfac.panduka18.coursehub.constant.Role
import lk.uom.itfac.panduka18.coursehub.entity.User
import lk.uom.itfac.panduka18.coursehub.payload.request.NewCourseRequest
import lk.uom.itfac.panduka18.coursehub.repository.UserRepository
import lk.uom.itfac.panduka18.coursehub.service.CourseService
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 10:40 AM
 */
@Component
class DataInputter(
        var courseService: CourseService,
        val passwordEncoder: PasswordEncoder,
        var userRepository: UserRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
//        val user = User("admin", passwordEncoder.encode("admin"));
//        user.role = Role.ADMIN;
//        userRepository.save(user);
//
//        addCourses();
    }

    private fun addCourses() {
        courseService.create(
                NewCourseRequest(
                        "CM 3320",
                        CourseMode.C,
                        "Logic Programming & Artificial Cognitive Systems",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1997",
                ),
                NewCourseRequest(
                        "CM 3310",
                        CourseMode.C,
                        "Artificial Intelligence",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1996",
                ),
                NewCourseRequest(
                        "CM 3120",
                        CourseMode.E,
                        "Computational Statistics",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1995",
                ),
                NewCourseRequest(
                        "CM 3210",
                        CourseMode.E,
                        "Automata Theory",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1994",
                ),
                NewCourseRequest(
                        "CM 3110",
                        CourseMode.E,
                        "Computational Mathematics",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1993",
                ),
                NewCourseRequest(
                        "CM 3330",
                        CourseMode.E,
                        "Fundamentals of Bio informatics",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1992",
                ),
                NewCourseRequest(
                        "IS 3430",
                        CourseMode.C,
                        "IT Project Management",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1985",
                ),
                NewCourseRequest(
                        "IS 3100",
                        CourseMode.E,
                        "Organizational Behavior",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1984",
                ),
                NewCourseRequest(
                        "IS 3400",
                        CourseMode.C,
                        "Management Information Systems",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1983",
                ),
                NewCourseRequest(
                        "IS 4310",
                        CourseMode.E,
                        "Business Studies",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1982",
                ),
                NewCourseRequest(
                        "IS 4320",
                        CourseMode.E,
                        "eBusiness Management",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1980",
                ),
                NewCourseRequest(
                        "IS 3010",
                        CourseMode.C,
                        "Communication Skills",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1979",
                ),
                NewCourseRequest(
                        "IN 4500",
                        CourseMode.E,
                        "Mobile & Wireless Networks",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1990",
                ),
                NewCourseRequest(
                        "IN 3100",
                        CourseMode.E,
                        "Enterprise Applications Development",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1989",
                ),
                NewCourseRequest(
                        "IN 4110",
                        CourseMode.E,
                        "Network Programming",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1988",
                ),
                NewCourseRequest(
                        "IN 3400",
                        CourseMode.E,
                        "Advanced Database Management Systems",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1987",
                ),
                NewCourseRequest(
                        "IN 3900",
                        CourseMode.C,
                        "Independent Study",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1986",
                ),
                NewCourseRequest(
                        "IS 4420",
                        CourseMode.E,
                        "Decision Management",
                        200,
                        "https://moodle.itfac.uom.lk/course/view.php?id=1981",
                ),
        );
    }

}
