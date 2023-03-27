package cz.cvut.fel.constructa.config;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.security.auth.AuthenticationService;
import cz.cvut.fel.constructa.security.auth.RegisterRequest;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final AuthenticationService authenticationService;
    private final TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .firstname("Ondřej")
                .lastname("Bureš")
                .password("1234")
                .email("email@email.cz")
                .phone("123456789")
                .birthId("1234567/45678")
                .dateOfBirth(new Date())
                .bankAccount("12345678/1000")
                .monthSalary(50000)
                .hourRate(300)
                .roles(Role.ROLE_ADMIN)
                .build();

        authenticationService.register(registerRequest);

        registerRequest.setFirstname("Jan");
        registerRequest.setLastname("Bořek");
        registerRequest.setEmail("Jan@Bořek.com");
        registerRequest.setRoles(Role.ROLE_EMPLOYEE);
//        authenticationService.register(registerRequest);

        registerRequest.setFirstname("Pavel");
        registerRequest.setLastname("Uvařsisám");
        registerRequest.setEmail("Pavlik@Uvarsisam.cu");
//        authenticationService.register(registerRequest);

        TaskRequest taskRequest = TaskRequest.builder()
                .name("Převezení materiálu")
                .description("Spěchá")
                .timeFrom(new Date())
                .timeTo(new Date())
                .latitude(50.073658)
                .longitude(14.418540)
                .state(TaskState.NEW.getTestState())
                .locationName("Praha Klíčany")
                .userId((long)1)
                .build();

//        taskService.create(taskRequest);
//        taskService.create(taskRequest);
//        taskService.create(taskRequest);
//        taskService.create(taskRequest);
    }
}