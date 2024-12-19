package de.tjorven.servermanagmenttool;

import lombok.Getter;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConstVariables {
    @Getter private static final Path JAR_PATH;

    static {
        try {
            JAR_PATH = Paths.get(ConstVariables.class.getClassLoader().getResource("jars/batfiles").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
