package cz.cvut.fel.constructa.security.auth;

import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * The type Authentication service.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    /**
     * The User dao.
     */
    private final UserRepository userDao;
    /**
     * The Location dao.
     */
    private final LocationRepository locationDao;
    /**
     * The Password encoder.
     */
    private final PasswordEncoder passwordEncoder;
    /**
     * The Jwt service.
     */
    private final JwtService jwtService;
    /**
     * The Auth manager.
     */
    private final AuthenticationManager authManager;

//    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);


    /**
     * Generate username string.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @return the string
     */
    public static String generateUsername(String firstName, String lastName) {
        // Remove spaces from the first and last name
        firstName = firstName.trim().replaceAll("\\s+","");
        lastName = lastName.trim().replaceAll("\\s+","");

        // Random number between 1 and 100
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        // Combination of first and last name with a random number
        String username = firstName.substring(0, Math.min(firstName.length(), 3)) +
                lastName.substring(0, Math.min(lastName.length(), 3)) +
                randomNumber;

        // Return the final username
        return username.toLowerCase();
    }

    /**
     * Register authentication response.
     *
     * @param request the request
     * @return the authentication response
     */
    public AuthenticationResponse register(RegisterRequest request){
        Location address = Location.builder()
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
                .role(request.getRole())
                .userAddress(address)
                .build();

        User createdUser = userDao.save(user);

        log.info("New user {} was registred.", createdUser.getUsername());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticate authentication response.
     *
     * @param request the request
     * @return the authentication response
     */
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

        log.info("User {} was logged in.", user.getUsername());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
