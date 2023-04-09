package cz.cvut.fel.constructa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "exp://localhost:19000") // allow web and mobile localhost origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // allow request methods
                .allowedHeaders("*") // allow headers
                .allowCredentials(true) // allow connection with cookie
                .maxAge(36000); // caching
    }
}
