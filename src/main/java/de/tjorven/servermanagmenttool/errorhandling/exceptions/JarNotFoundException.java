package de.tjorven.servermanagmenttool.errorhandling.exceptions;

public class JarNotFoundException extends RuntimeException {
    public JarNotFoundException(Long id) {
        super("Could not find jar " + id);
    }
}
