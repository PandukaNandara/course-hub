package lk.uom.itfac.panduka18.coursehub.security

import io.jsonwebtoken.JwtException
import lk.uom.itfac.panduka18.coursehub.service.UserDetailsService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.AntPathMatcher
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:30 PM
 */
@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
class TokenAuthenticationFilter(
        var tokenProvider: TokenProvider,
        var userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if (SecurityContextHolder.getContext().authentication == null) {
            try {
                val jwt = getJwtFromRequest(request)
                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    val userId: UUID = tokenProvider.getUserIdFromToken(jwt)
                    val userDetails: UserDetails = userDetailsService.loadUserById(userId)
                    val authentication = UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            Objects.requireNonNull(userDetails).authorities
                    )
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            } catch (ex: JwtException) {

                logger.error("Could not set user authentication in security context", ex)
                throw ex
            }
        }
        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    private val antPathMatcher = AntPathMatcher();

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val hasAuthHeader = request.getHeader(HttpHeaders.AUTHORIZATION) != null;

        val isNotProtected = antPathMatcher.match("/api/auth/**", request.requestURI) ||
                (antPathMatcher.match("/api/students/new", request.requestURI));
        return !hasAuthHeader || isNotProtected
    }
}

