package lk.uom.itfac.panduka18.coursehub.entity

import lk.uom.itfac.panduka18.coursehub.constant.Role
import lombok.EqualsAndHashCode
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:04 PM
 */
@Entity
@EqualsAndHashCode
open class User(
        @Column(unique = true)
        open var username: String,
        open var password: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "uuid-char")
    open var id: UUID? = null;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    open lateinit var role: Role;

}
