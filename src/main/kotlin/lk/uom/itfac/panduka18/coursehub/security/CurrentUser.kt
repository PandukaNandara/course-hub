package lk.uom.itfac.panduka18.coursehub.security

import org.springframework.security.core.annotation.AuthenticationPrincipal

/**
 * Indicates the current user
 *
 * @author pandu
 * Date: 2020-10-26
 * Time: 3:37 PM
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@AuthenticationPrincipal
annotation class CurrentUser
