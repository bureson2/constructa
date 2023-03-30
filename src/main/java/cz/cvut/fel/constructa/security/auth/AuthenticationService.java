package cz.cvut.fel.constructa.security.auth;

import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userDao;
    private final LocationRepository locationDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public static String generateUsername(String firstName, String lastName) {
        // Odstraňte mezery v prvním a posledním jménu
        firstName = firstName.trim().replaceAll("\\s+","");
        lastName = lastName.trim().replaceAll("\\s+","");

        // Náhodné číslo mezi 1 a 100
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        // Kombinace prvního a posledního jména s náhodným číslem
        String username = firstName.substring(0, Math.min(firstName.length(), 3)) +
                lastName.substring(0, Math.min(lastName.length(), 3)) +
                randomNumber;

        // Vrátí výsledné uživatelské jméno
        return username.toLowerCase();
    }

    public AuthenticationResponse register(RegisterRequest request){
        Location address = Location.builder()
                .active(false)
                .city(request.getCity())
                .street(request.getStreet())
                .country(request.getCountry())
                .descriptiveNumber(request.getDescriptiveNumber())
                .postCode(request.getPostCode())
                .build();
        locationDao.save(address);

        User user = User.builder()
                .username(generateUsername(request.getFirstname(), request.getLastname()))
                .dateOfAcceptance(new Date())
                .birthId(request.getBirthId())
                .dateOfBirth(request.getDateOfBirth())
                .titleBeforeName(request.getTitleBeforeName())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .titleAfterName(request.getTitleAfterName())
                .bankAccount(request.getBankAccount())
                .hourRate(request.getHourRate())
                .monthSalary(request.getMonthSalary())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRoles())
                .userAddress(address)
                .build();
        userDao.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userDao.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
