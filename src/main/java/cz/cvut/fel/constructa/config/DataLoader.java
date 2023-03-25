package cz.cvut.fel.constructa.config;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.security.auth.AuthenticationService;
import cz.cvut.fel.constructa.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final AuthenticationService authenticationService;

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setFirstname("Ondřej");
        request.setLastname("Bureš");
        request.setPassword("1234");
        request.setEmail("email@email.cz");
        request.setPhone("123456789");
        request.setBirthId("1234567/45678");
        request.setBankAccount("12345678/1000");
        request.setMonthSalary(50000);
        request.setHourRate(300);
        request.setDateOfBirth(new Date());
        request.setRoles(Role.ROLE_ADMIN);
        authenticationService.register(request);
    }
}