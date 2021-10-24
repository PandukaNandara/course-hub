package lk.uom.itfac.panduka18.coursehub.service

import lk.uom.itfac.panduka18.coursehub.payload.request.StudentCreationRequest
import lk.uom.itfac.panduka18.coursehub.payload.request.StudentUpdateRequest
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:10 PM
 */
@Service
interface StudentService {
    fun create(studentCreationRequest: StudentCreationRequest);
    fun delete(id: UUID)
    fun update(id: UUID, studentUpdateRequest: StudentUpdateRequest, currentUser: UserPrincipal)
}
