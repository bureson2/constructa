package cz.cvut.fel.constructa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConstructaApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(ConstructaApplication.class, args);
//    }

    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(ConstructaApplication.class)
                .properties("spring.config.location=classpath:application.properties,classpath:application.properties");
        app.run(args);
    }

}
