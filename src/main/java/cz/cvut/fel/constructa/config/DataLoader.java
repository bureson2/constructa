package cz.cvut.fel.constructa.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The type Data loader.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final DataSource dataSource;

    public DataLoader(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Run.
     *
     * @param args the args
     */
    @Override
    public void run(String... args) {
        try {
            String[] command = {
                    "psql",
                    "-h", "localhost",
                    "-p", "5432",
                    "-U", "postgres",
                    "-d", "constructa-database",
                    "-a",
                    "-f", "src/main/resources/import_data.sql"
            };

            Process process = new ProcessBuilder(command).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Error: psql exited with code " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}