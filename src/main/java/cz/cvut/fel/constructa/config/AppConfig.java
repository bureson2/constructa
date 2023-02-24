package cz.cvut.fel.constructa.config;

import cz.cvut.fel.constructa.service.impl.TaskServiceImpl;
import cz.cvut.fel.constructa.service.impl.UserServiceImpl;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public TaskService taskService() {
        return new TaskServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
