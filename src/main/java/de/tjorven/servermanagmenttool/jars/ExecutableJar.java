package de.tjorven.servermanagmenttool.jars;

import com.fasterxml.jackson.core.JsonParser;
import de.tjorven.servermanagmenttool.ConstVariables;
import de.tjorven.servermanagmenttool.errorhandling.exceptions.CouldNotStartJarException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;

@Entity @Getter @Setter
public class ExecutableJar {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String version;
    private String path;

    private boolean running;
    private transient Process process;
    private transient InputStream in;
    private transient InputStream err;

    public ExecutableJar() {
    }

    public ExecutableJar(String name, String description, String version) {
        this(name, description, version, ConstVariables.getJAR_PATH().resolve(name).toString());
    }

    public ExecutableJar(String name, String description, String version, String path) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.path = path;
    }

    public void run() {
        try {
            this.process = new ProcessBuilder("java", "-jar", this.path).start();
            this.in = this.process.getInputStream();
            this.err = this.process.getErrorStream();
            this.running = true;
        } catch (Exception e) {
            this.running = false;
            this.in = null;
            this.err = null;
            this.process = null;
            throw new CouldNotStartJarException(this.id);
        }
    }

    public void stop() {
        if (this.process != null && this.running) {
            this.process.destroy();
            this.process = null;
            this.in = null;
            this.err = null;
            this.running = false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ExecutableJar executableJar))
            return false;
        return Objects.equals(this.id, executableJar.id) && Objects.equals(this.name, executableJar.name)
                && Objects.equals(this.description, executableJar.description)
                && Objects.equals(this.version, executableJar.version)
                && Objects.equals(this.path, executableJar.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.version, this.path);
    }

    @Override
    public String toString() {
        return "ExecutableJar{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", description='" + this.description + '\'' +
                ", version='" + this.version + '\'' +
                ", path='" + this.path + '\'' +
                '}';
    }
}
