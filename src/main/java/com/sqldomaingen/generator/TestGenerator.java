package com.sqldomaingen.generator;

import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Generates test classes:
 *
 * src/test/java/{basePackage}
 * ├─ controller
 * ├─ service
 * └─ {AppName}ApplicationTests.java
 */
@Log4j2
public class TestGenerator {

    /**
     * Main entry point used by the generation pipeline.
     *
     * @param tables      parsed SQL tables
     * @param outputDir   project root output directory
     * @param basePackage base Java package
     * @param overwrite   overwrite existing files when true
     */
    public void generateTests(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        generateApplicationTest(outputDir, basePackage, overwrite);
        generateControllerTests(tables, outputDir, basePackage, overwrite);
        generateServiceTests(tables, outputDir, basePackage, overwrite);

        log.info("✅ Test classes generated successfully.");
    }

    /**
     * Backwards-compatible overload.
     */
    public void generateAllTests(List<Table> tables, String outputDir, String basePackage) {
        generateTests(tables, outputDir, basePackage, true);
    }

    private void generateApplicationTest(String outputDir, String basePackage, boolean overwrite) {
        Path testDir = PackageResolver.resolvePath(outputDir, basePackage, "", true);

        String applicationClassName = resolveApplicationClassName(basePackage);
        String appTestName = applicationClassName + "Tests";

        String content = """
                package %s;

                import org.junit.jupiter.api.Test;
                import org.springframework.boot.test.context.SpringBootTest;

                @SpringBootTest(properties = {
                        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
                        "spring.datasource.driverClassName=org.h2.Driver",
                        "spring.datasource.username=sa",
                        "spring.datasource.password=",
                        "spring.jpa.hibernate.ddl-auto=create-drop",
                        "spring.jpa.open-in-view=false",
                        "spring.liquibase.enabled=false"
                })
                class %s {

                    @Test
                    void contextLoads() {
                    }
                }
                """.formatted(basePackage, appTestName);

        writeFile(testDir.resolve(appTestName + ".java"), content, overwrite);
    }

    private void generateControllerTests(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Path controllerTestDir = PackageResolver.resolvePath(outputDir, basePackage, "controller", true);
        String testPackage = PackageResolver.resolvePackageName(basePackage, "controller");

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));
            String testName = entityName + "ControllerTest";
            String controllerName = entityName + "Controller";
            String serviceName = entityName + "Service";
            String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

            String apiPath = "/api/" + NamingConverter.toKebabCase(entityName) + "s";

            String servicePackage = PackageResolver.resolvePackageName(basePackage, "service");
            String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");
            String dtoName = entityName + "Dto";

            String content = """
                    package %s;

                    import %s.%s;
                    import %s.%s;

                    import org.junit.jupiter.api.Test;
                    import org.springframework.beans.factory.annotation.Autowired;
                    import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
                    import org.springframework.boot.test.mock.mockito.MockBean;
                    import org.springframework.test.web.servlet.MockMvc;

                    import java.util.List;

                    import static org.mockito.BDDMockito.given;
                    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
                    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

                    @WebMvcTest(%s.class)
                    class %s {

                        @Autowired
                        private MockMvc mockMvc;

                        @MockBean
                        private %s %s;

                        @Test
                        void shouldReturnOk() throws Exception {
                            given(%s.getAll%s()).willReturn(List.of());
                            mockMvc.perform(get("%s"))
                                    .andExpect(status().isOk());
                        }
                    }
                    """.formatted(
                    testPackage,
                    servicePackage, serviceName,
                    dtoPackage, dtoName,
                    controllerName,
                    testName,
                    serviceName, serviceVar,
                    serviceVar, entityName,
                    apiPath
            );

            writeFile(controllerTestDir.resolve(testName + ".java"), content, overwrite);
        }
    }

    private void generateServiceTests(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Path serviceTestDir = PackageResolver.resolvePath(outputDir, basePackage, "service", true);
        String testPackage = PackageResolver.resolvePackageName(basePackage, "service");

        String repoPackage = PackageResolver.resolvePackageName(basePackage, "repository");
        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");
        String serviceImplPackage = PackageResolver.resolvePackageName(basePackage, "serviceImpl");

        for (Table table : tables) {
            String entityName = NamingConverter.toPascalCase(normalizeTableName(table.getName()));

            String testName = entityName + "ServiceTest";
            String repoName = entityName + "Repository";
            String mapperName = entityName + "Mapper";
            String serviceImplName = entityName + "ServiceImpl";

            String repoVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Repository";
            String mapperVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper";
            String serviceVar = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";

            String content = """
                    package %s;

                    import %s.%s;
                    import %s.%s;
                    import %s.%s;

                    import org.junit.jupiter.api.Test;
                    import org.junit.jupiter.api.extension.ExtendWith;
                    import org.mockito.InjectMocks;
                    import org.mockito.Mock;
                    import org.mockito.junit.jupiter.MockitoExtension;

                    import java.util.List;

                    import static org.mockito.ArgumentMatchers.anyList;
                    import static org.mockito.BDDMockito.given;
                    import static org.mockito.Mockito.verify;

                    @ExtendWith(MockitoExtension.class)
                    class %s {

                        @Mock
                        private %s %s;

                        @Mock
                        private %s %s;

                        @InjectMocks
                        private %s %s;

                        @Test
                        void getAllShouldDelegateToRepositoryAndMapper() {
                            given(%s.findAll()).willReturn(List.of());
                            %s.getAll%s();
                            verify(%s).findAll();
                            verify(%s).toDTO(anyList());
                        }
                    }
                    """.formatted(
                    testPackage,
                    repoPackage, repoName,
                    mapperPackage, mapperName,
                    serviceImplPackage, serviceImplName,
                    testName,
                    repoName, repoVar,
                    mapperName, mapperVar,
                    serviceImplName, serviceVar,
                    repoVar,
                    serviceVar, entityName,
                    repoVar,
                    mapperVar
            );

            writeFile(serviceTestDir.resolve(testName + ".java"), content, overwrite);
        }
    }

    private static String resolveApplicationClassName(String basePackage) {
        String leaf = basePackage.substring(basePackage.lastIndexOf('.') + 1).trim();

        String cleaned = leaf.replaceAll("[^A-Za-z0-9]+", " ").trim();
        if (cleaned.contains(" ")) {
            String[] parts = cleaned.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String p : parts) {
                sb.append(capitalize(p));
            }
            return sb.append("Application").toString();
        }

        String lower = leaf.toLowerCase();
        String[] suffixes = {"management", "service", "api", "core", "app"};
        for (String suf : suffixes) {
            if (lower.endsWith(suf) && lower.length() > suf.length()) {
                String prefix = lower.substring(0, lower.length() - suf.length());
                return capitalize(prefix) + capitalize(suf) + "Application";
            }
        }

        return capitalize(leaf) + "Application";
    }

    private static String capitalize(String s) {
        if (s == null || s.isBlank()) return "";
        String t = s.trim();
        return Character.toUpperCase(t.charAt(0)) + t.substring(1);
    }

    private static String normalizeTableName(String raw) {
        if (raw == null) return "";
        String s = raw.trim();
        int dot = s.lastIndexOf('.');
        if (dot >= 0 && dot < s.length() - 1) {
            s = s.substring(dot + 1);
        }
        return s;
    }

    private static void writeFile(Path filePath, String content, boolean overwrite) {
        try {
            Files.createDirectories(filePath.getParent());

            if (!overwrite && Files.exists(filePath)) {
                log.info("ℹ️ Test exists, skipping (overwrite=false): {}", filePath.toAbsolutePath());
                return;
            }

            Files.writeString(filePath, content, StandardCharsets.UTF_8);
            log.info("✅ Test generated: {}", filePath.toAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write file: " + filePath, e);
        }
    }
}
