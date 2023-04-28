package cz.cvut.fel.constructa.config;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * The type Environment config.
 */
public class EnvironmentConfig {
    /**
     * The Dotenv.
     */
    private final Dotenv dotenv;

    /**
     * Instantiates a new Environment config.
     */
    public EnvironmentConfig() {
        dotenv = Dotenv.configure().ignoreIfMissing().load();
    }

    /**
     * Get string.
     *
     * @param key the key
     * @return the string
     */
    public String get(String key) {
        return dotenv.get(key);
    }
}