package lk.uom.itfac.panduka18.coursehub.constant

import org.springframework.security.core.GrantedAuthority

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:05 PM
 */
enum class Role : GrantedAuthority {
    ADMIN,
    STUDENT;

    override fun getAuthority(): String? {
        return name
    }
}
