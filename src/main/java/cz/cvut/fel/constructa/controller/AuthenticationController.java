package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.config.JwtUtils;
import cz.cvut.fel.constructa.dao.UserDao;
import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.role.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private String jwtSigningKey = "secret";

    @PostMapping(value = "/auth/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
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
        return ResponseEntity.status(400).body(Collections.singletonMap("error", "Some error has occured"));
    }

    @GetMapping(value ="/user")
    public User getUser(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String jwt = authorizationHeader.substring(7); // remove "Bearer " from the header value

        Claims claims = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(jwtSigningKey.getBytes()))
                .parseClaimsJws(jwt).getBody();

        String username = claims.getSubject();
        String role = (String) claims.get("role");
        User toReturn = new User();
        toReturn.setUsername(username);
        Role returnedRole = Role.valueOf(role);
        toReturn.setRoles(List.of(returnedRole));

        return toReturn;
    }
}
