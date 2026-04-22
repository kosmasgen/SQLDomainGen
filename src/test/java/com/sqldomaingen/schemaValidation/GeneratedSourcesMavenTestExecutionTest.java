package com.sqldomaingen.schemaValidation;

import com.sqldomaingen.util.Constants;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Executes the generated project's Maven tests to ensure all tests pass.
 */
class GeneratedSourcesMavenTestExecutionTest {

    /**
     * Runs "mvnw.cmd test" on the generated project and fails if any test fails.
     *
     * @throws Exception if process execution fails
     */
    @Test
    void shouldExecuteGeneratedProjectTestsSuccessfully() throws Exception {
        Path projectRoot = resolveGeneratedProjectRoot();
        Path mvnw = projectRoot.resolve("mvnw.cmd");

        if (!mvnw.toFile().exists()) {
            fail("Missing Maven wrapper: " + mvnw.toAbsolutePath());
        }

        Process process = new ProcessBuilder(
                mvnw.toAbsolutePath().toString(),
                "clean",
                "test",
                "-q"
        )
                .directory(projectRoot.toFile())
                .redirectErrorStream(true)
                .start();

        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        int exitCode = process.waitFor();

        if (exitCode != 0) {
            fail("Generated project tests FAILED.\n\n" + output);
        }
    }

    /**
     * Resolves the generated project root directory.
     *
     * @return generated project root
     */
    private Path resolveGeneratedProjectRoot() {
        return Constants.GENERATED_JAVA_ROOT.getParent().getParent().getParent();
    }
}