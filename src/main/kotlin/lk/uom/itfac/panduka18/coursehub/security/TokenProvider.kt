package lk.uom.itfac.panduka18.coursehub.security

import io.jsonwebtoken.*
import lk.uom.itfac.panduka18.coursehub.config.TokenProperties
import lk.uom.itfac.panduka18.coursehub.security.model.UserPrincipal
import lombok.RequiredArgsConstructor
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:31 PM
 */
@Service
@RequiredArgsConstructor
class TokenProvider(
        var tokenProperties: TokenProperties,
) {
    fun createToken(authentication: Authentication): String {
        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal
        return createToken(userPrincipal)
    }

    fun createToken(userPrincipal: UserPrincipal): String {
        val now = Date()
        val expiryDate: Date = Date(now.time + tokenProperties.tokenExpiration)
        return Jwts.builder()
                .setSubject(userPrincipal.id.toString())
                .setIssuedAt(Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, tokenProperties.tokenSecret)
                .compact()
    }

    fun getUserIdFromToken(token: String?): UUID {
        val claims: Claims = Jwts.parser()
                .setSigningKey(tokenProperties.tokenSecret)
                .parseClaimsJws(token)
                .body
        return UUID.fromString(claims.subject.toString());
    }

    fun validateToken(authToken: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(tokenProperties.tokenSecret).parseClaimsJws(authToken)
            true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
            throw ex
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
            throw ex
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
            throw ex
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
            throw ex
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
            throw ex
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TokenProvider::class.java)
    }
}
