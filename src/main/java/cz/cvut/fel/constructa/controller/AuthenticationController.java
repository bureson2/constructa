package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.config.JwtUtils;
import cz.cvut.fel.constructa.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.println("login try");
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        System.out.println(user);
        if (user != null) {
            String token = jwtUtils.generateToken(user);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            System.out.println(token);
            return ResponseEntity.ok(response);
        }
        System.out.println("error");
        return ResponseEntity.status(400).body(Collections.singletonMap("error", "Some error has occured"));
    }

//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<String> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        final UserDetails user = userDao.findUserByEmail(request.getEmail());
//        if (user != null) {
//            return ResponseEntity.ok(jwtUtils.generateToken(user));
//        }
//        return ResponseEntity.status(400).body("Some error has occured");
//    }
}
