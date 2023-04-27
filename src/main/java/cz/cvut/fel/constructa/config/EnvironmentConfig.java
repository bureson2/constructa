package cz.cvut.fel.constructa.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvironmentConfig {
    private final Dotenv dotenv;

    public EnvironmentConfig() {
        dotenv = Dotenv.configure().ignoreIfMissing().load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }
}