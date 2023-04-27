package cz.cvut.fel.constructa.security;

import org.springframework.security.core.Authentication;

/**
 * The interface Authentication facade.
 */
public interface AuthenticationFacade {
    /**
     * Gets authentication.
     *
     * @return the authentication
     */
    Authentication getAuthentication();
}
