package cz.cvut.fel.constructa.security.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Authentication controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentization")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * The Auth service.
     */
    private final AuthenticationService authService;

    /**
     * Register response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Authenticate response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
