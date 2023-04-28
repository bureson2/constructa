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

/**
 * The type Data loader.
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    /**
     * The Authentication service.
     */
    private final AuthenticationService authenticationService;

    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    @Override
    public void run(String... args) throws Exception {

//        TODO


    }
}