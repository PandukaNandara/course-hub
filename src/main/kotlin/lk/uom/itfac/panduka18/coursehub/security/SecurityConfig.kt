package lk.uom.itfac.panduka18.coursehub.security

import lk.uom.itfac.panduka18.coursehub.service.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * Created by IntelliJ IDEA.
 * @author pandu
 * Date: 2021-10-22
 * Time: 1:28 PM
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
class SecurityConfig(
        val userDetailsService: UserDetailsService,
        val tokenProvider: TokenProvider,
) : WebSecurityConfigurerAdapter() {


    companion object {
        val APPROVED_ENDPOINTS = arrayOf("/api/auth/**", "/api/students/new", "/api/courses");
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    public override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun tokenAuthFilter(
    ): TokenAuthenticationFilter {
        return TokenAuthenticationFilter(tokenProvider, userDetailsService)
    }


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .cors()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
//                .httpBasic()
//                .disable()
                .formLogin()
                .disable()

                .exceptionHandling()
                .and()
                .authorizeRequests()
                .antMatchers(*APPROVED_ENDPOINTS)
                .permitAll()
                .antMatchers("/api/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(tokenAuthFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }


    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost:8080")
                        .allowCredentials(true)
                        .allowedHeaders("*")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS")
            }
        }
    }
}

