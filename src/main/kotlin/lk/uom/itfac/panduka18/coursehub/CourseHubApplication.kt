package lk.uom.itfac.panduka18.coursehub

import lk.uom.itfac.panduka18.coursehub.config.TokenProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication

@EnableConfigurationProperties(TokenProperties::class)
class CourseHubApplication

fun main(args: Array<String>) {
	runApplication<CourseHubApplication>(*args)
}
