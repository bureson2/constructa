package cz.cvut.fel.constructa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Povolení přístupu z React FE běžícího na portu 3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Povolení metod
                .allowedHeaders("*") // Povolení všech hlaviček
                .allowCredentials(true) // Povolení cookie přenosu
                .maxAge(3600); // Caching
    }
}
