package de.tjorven.servermanagmenttool.controller;

import de.tjorven.servermanagmenttool.ConstVariables;
import de.tjorven.servermanagmenttool.errorhandling.exceptions.JarNotFoundException;
import de.tjorven.servermanagmenttool.jars.ExecutableJar;
import de.tjorven.servermanagmenttool.jars.ExecutableJarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JarController {
    private final ExecutableJarRepository repository;

    public JarController(ExecutableJarRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/jars")
    List<ExecutableJar> all() {
        return repository.findAll();
    }

    // end::get-aggregate-root[]

    @PostMapping("/jars")
    ExecutableJar newExecutableJar(@RequestBody ExecutableJar newJar) {
        return repository.save(newJar);
    }

    @PostMapping("/jars/run/{id}")
    void runExecutableJar(@PathVariable Long id) {
        ExecutableJar jar = repository.findById(id)
                .orElseThrow(() -> new JarNotFoundException(id));
        jar.run();
    }

    @PostMapping("/jars/stop/{id}")
    void stopExecutableJar(@PathVariable Long id) {
        ExecutableJar jar = repository.findById(id)
                .orElseThrow(() -> new JarNotFoundException(id));
        if (jar.isRunning()) {
            jar.stop();
        }
    }

    // Single item

    @GetMapping("/jars/{id}")
    ExecutableJar one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new JarNotFoundException(id));
    }

    @PutMapping("/jars/{id}")
    ExecutableJar replaceExecutableJar(@RequestBody ExecutableJar newJar, @PathVariable Long id) {

        return repository.findById(id)
                .map(jar -> {
                    jar.setName(newJar.getName());
                    jar.setDescription(newJar.getDescription());
                    jar.setVersion(newJar.getVersion());
                    jar.setPath(ConstVariables.getJAR_PATH().resolve(newJar.getName()).toString());
                    return repository.save(jar);
                })
                .orElseGet(() -> repository.save(newJar));
    }

    @DeleteMapping("/jars/{id}")
    void deleteExecutableJar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
