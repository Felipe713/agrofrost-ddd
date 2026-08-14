package cl.felipe.agrofrost;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ArchitectureRulesTest {

    @Test
    void domainAndApplicationRespectDependencyDirection() throws IOException {
        assertNoForbiddenImports(
                Path.of("src/main/java/cl/felipe/agrofrost/domain"),
                "cl.felipe.agrofrost.application",
                "cl.felipe.agrofrost.infrastructure",
                "org.springframework",
                "jakarta.persistence",
                "javax.persistence",
                "com.fasterxml.jackson");
        assertNoForbiddenImports(
                Path.of("src/main/java/cl/felipe/agrofrost/application"),
                "cl.felipe.agrofrost.infrastructure",
                "org.springframework",
                "jakarta.persistence",
                "javax.persistence",
                "com.fasterxml.jackson");
    }

    private void assertNoForbiddenImports(Path directory, String... forbiddenImports)
            throws IOException {
        try (Stream<Path> files = Files.walk(directory)) {
            for (Path file : files.filter(path -> path.toString().endsWith(".java")).toList()) {
                String source = Files.readString(file);
                for (String forbiddenImport : forbiddenImports) {
                    assertFalse(source.contains("import " + forbiddenImport),
                            () -> file + " imports forbidden dependency " + forbiddenImport);
                }
            }
        }
    }
}
