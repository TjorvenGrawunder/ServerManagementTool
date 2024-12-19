package de.tjorven.servermanagmenttool.errorhandling.exceptions;

public class CouldNotStartJarException extends RuntimeException {
    public CouldNotStartJarException(long id) {
        super("Could not start jar " + id);
    }
}
