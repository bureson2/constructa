package cz.cvut.fel.constructa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * The type Authentication facade.
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    /**
     * Gets authentication.
     *
     * @return the authentication
     */
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
