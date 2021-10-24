package lk.uom.itfac.panduka18.coursehub.security.permisson

import org.springframework.security.access.prepost.PreAuthorize
import java.lang.annotation.Inherited

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 5:51 PM
 */
@Inherited
@PreAuthorize("hasAuthority(T(lk.uom.itfac.panduka18.coursehub.constant.Role).ADMIN)")
annotation class AdminPermission
