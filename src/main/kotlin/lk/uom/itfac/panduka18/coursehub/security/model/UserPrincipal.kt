package lk.uom.itfac.panduka18.coursehub.security.model

import com.fasterxml.jackson.annotation.JsonProperty
import lk.uom.itfac.panduka18.coursehub.constant.Role
import lombok.EqualsAndHashCode
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:35 PM
 */
data class UserPrincipal(
        @JsonProperty var id: UUID,
        @JsonProperty
        private var username: String,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private var password: String,
        @JsonProperty
        private var authorities: List<Role>,
        @JsonProperty
        private var attributes: Map<String, Any>?,
        @JsonProperty
        var name: String,
) : UserDetails {
    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username;
    }


    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getAuthorities(): List<Role> {
        return authorities
    }


    fun getFirstRole(): Role {
        return authorities[0]
    }


    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserPrincipal

        if (id != other.id) return false
        if (username != other.username) return false
        if (password != other.password) return false
        if (authorities != other.authorities) return false
        if (attributes != other.attributes) return false
        return true
    }

}
