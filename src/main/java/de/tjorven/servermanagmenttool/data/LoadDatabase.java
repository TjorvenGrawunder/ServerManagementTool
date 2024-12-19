package de.tjorven.servermanagmenttool.data;

import de.tjorven.servermanagmenttool.ConstVariables;
import de.tjorven.servermanagmenttool.jars.ExecutableJar;
import de.tjorven.servermanagmenttool.jars.ExecutableJarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ExecutableJarRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new ExecutableJar()));
            log.info("Preloading " + repository.save(new ExecutableJar()));
        };
    }

    private List<ExecutableJar> readExistingJars() {
        List<ExecutableJar> jars = new ArrayList<>();
        try {
            Path jarFolderPath = ConstVariables.getJAR_PATH();
            try (Stream<Path> paths = Files.walk(jarFolderPath)) {
                List<Path> jarFiles = paths.filter(Files::isRegularFile)
                        .filter(path -> path.toString().endsWith(".bat"))
                        .toList();

                for (Path jarFile : jarFiles) {

                    ExecutableJar jar = new ExecutableJar();
                    jar.setName(jarFile.getFileName().toString());
                    jar.setPath(jarFile.toString());
                    jars.add(jar);
                }
            }
        } catch ( IOException e) {
            log.error("Error reading JAR files", e);
        }
        return jars;
    }

}
