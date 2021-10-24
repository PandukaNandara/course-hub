package lk.uom.itfac.panduka18.coursehub.payload.request

import javax.validation.constraints.NotEmpty

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:18 PM
 */
data class LoginRequest(
        @NotEmpty
        val username: String,
        @NotEmpty
        val password: String,
) {
}
