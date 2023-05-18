package cz.cvut.fel.constructa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Security configuration.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    /**
     * The Jwt auth filter.
     */
    private final JwtAuthenticationFilter jwtAuthFilter;
    /**
     * The Authentication provider.
     */
    private final AuthenticationProvider authenticationProvider;

    /**
     * Security filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeHttpRequests()

                .requestMatchers(
                        "/api/v1/permissions",
                        "/api/v1/auth/authenticate",
                        // swagger enables
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                ).permitAll()
                .requestMatchers(
                        "/api/v1/auth/register"
                ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                .requestMatchers(
                        "/api/v1/companies/**",
                        "/api/v1/construction-reports/**",
                        "/api/v1/projects/**",
                        "/api/v1/tasks/**",
                        "/api/v1/users/**",
                        "/api/v1/vehicles/**",
                        "/api/v1/work-reports/**"
                ).authenticated()
                .requestMatchers(
                        "/api/v1/work-reports/user/**"
                ).hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_REPORTER")

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
