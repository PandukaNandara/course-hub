package lk.uom.itfac.panduka18.coursehub.config

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:33 PM
 */

@Configuration
@ConfigurationProperties(prefix = "app")
class TokenProperties {
    lateinit var tokenSecret: String;
    var tokenExpiration: Long = 0;
}

