package com.sqldomaingen.generator;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Generates a minimal, buildable Spring Boot Maven project scaffold:
 * pom.xml, Application class, and application.yml.
 * Target structure:
 * {outputDir}/pom.xml
 * {outputDir}/src/main/java/{basePackagePath}/Application.java
 * {outputDir}/src/main/resources/application.yml
 * {outputDir}/src/test/java/{basePackagePath}/ (directory only)
 */
@Log4j2
public class ProjectScaffoldGenerator {

    /**
     * Generates the project scaffold under outputDir.
     *
     * @param outputDir   target project root directory
     * @param basePackage Java base package (e.g. gr.knowledge.schoolmanagement)
     * @param overwrite   if true, overwrites existing files
     */
    public void generateScaffold(String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String out = outputDir.trim();
        String pkg = basePackage.trim();

        if (out.isEmpty()) {
            throw new IllegalArgumentException("outputDir must not be blank");
        }
        if (pkg.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }

        Path projectRoot = Path.of(out);
        ensureDir(projectRoot);

        String groupId = pkg;
        String artifactId = resolveArtifactId(pkg);

        writePom(projectRoot, groupId, artifactId, overwrite);
        writeApplication(projectRoot, pkg, overwrite);
        createApplicationProperties(projectRoot, artifactId, overwrite);

        ensureDir(resolveBaseJavaDir(projectRoot, pkg, true));

        log.info("✅ Project scaffold created under: {}", projectRoot.toAbsolutePath());
    }



    private static String resolveArtifactId(String basePackage) {
        int lastDot = basePackage.lastIndexOf('.');
        String lastSegment = (lastDot >= 0) ? basePackage.substring(lastDot + 1) : basePackage;

        String a = lastSegment.trim();
        if (a.isEmpty()) {
            return "generated-app";
        }
        // Keep underscores as-is to avoid unexpected renames.
        return a;
    }

    private void writePom(Path projectRoot, String groupId, String artifactId, boolean overwrite) {
        Path pom = projectRoot.resolve("pom.xml");

        String safeGroupId = (groupId == null || groupId.isBlank()) ? "com.generated" : groupId.trim();
        String safeArtifactId = (artifactId == null || artifactId.isBlank()) ? "generated-app" : artifactId.trim();

        // Keep it simple: name = artifactId (can be changed later if you want a prettier display name)
        String projectName = safeArtifactId;

        String content = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                <modelVersion>4.0.0</modelVersion>

                <parent>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-parent</artifactId>
                    <version>3.4.2</version>
                    <relativePath/>
                </parent>

                <groupId>%s</groupId>
                <artifactId>%s</artifactId>
                <version>0.0.1-SNAPSHOT</version>
                <name>%s</name>
                <description>Generated Spring Boot project</description>

                <properties>
                    <java.version>21</java.version>
                    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
                    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
                </properties>

                <dependencies>
                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-web</artifactId>
                    </dependency>

                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-data-jpa</artifactId>
                    </dependency>

                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-validation</artifactId>
                    </dependency>

                    <dependency>
                        <groupId>org.springdoc</groupId>
                        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
                        <version>2.8.5</version>
                    </dependency>

                    <dependency>
                        <groupId>org.modelmapper</groupId>
                        <artifactId>modelmapper</artifactId>
                        <version>3.2.0</version>
                    </dependency>

                    <dependency>
                        <groupId>org.postgresql</groupId>
                        <artifactId>postgresql</artifactId>
                        <scope>runtime</scope>
                    </dependency>

                    <dependency>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <optional>true</optional>
                    </dependency>

                    <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-test</artifactId>
                        <scope>test</scope>
                    </dependency>

                    <dependency>
                        <groupId>com.h2database</groupId>
                        <artifactId>h2</artifactId>
                        <scope>test</scope>
                    </dependency>
                </dependencies>

                <build>
                    <plugins>
                        <plugin>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-maven-plugin</artifactId>
                        </plugin>
                    </plugins>
                </build>

            </project>
            """.formatted(safeGroupId, safeArtifactId, projectName);

        writeFile(pom, content, overwrite);
        log.info("✅ pom.xml created: {}", pom.toAbsolutePath());
    }



    private void writeApplication(Path projectRoot, String basePackage, boolean overwrite) {
        Path baseJavaDir = resolveBaseJavaDir(projectRoot, basePackage, false);
        ensureDir(baseJavaDir);

        String applicationClassName = resolveApplicationClassName(basePackage);
        Path appFile = baseJavaDir.resolve(applicationClassName + ".java");

        String content = """
            package %s;

            import org.springframework.boot.SpringApplication;
            import org.springframework.boot.autoconfigure.SpringBootApplication;

            /**
             * Spring Boot entry point for the generated project.
             */
            @SpringBootApplication
            public class %s {

                public static void main(String[] args) {
                    SpringApplication.run(%s.class, args);
                }
            }
            """.formatted(basePackage, applicationClassName, applicationClassName);

        writeFile(appFile, content, overwrite);
        log.info("✅ Application created: {}", appFile.toAbsolutePath());
    }

    private static String resolveApplicationClassName(String basePackage) {
        String leaf = basePackage.substring(basePackage.lastIndexOf('.') + 1).trim();

        // Handle snake/kebab etc: med_heritage -> MedHeritage
        String cleaned = leaf.replaceAll("[^A-Za-z0-9]+", " ").trim();
        if (cleaned.contains(" ")) {
            String[] parts = cleaned.split("\\s+");
            StringBuilder sb = new StringBuilder();
            for (String p : parts) sb.append(capitalize(p));
            return sb.append("Application").toString();
        }

        // Handle common lowercase compounds: schoolmanagement -> SchoolManagement
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


    private void createApplicationProperties(Path root, String applicationName, boolean overwrite) {
        String name = (applicationName == null || applicationName.isBlank())
                ? "generated-app"
                : applicationName.trim();

        String props = """
spring.application.name=%s

spring.datasource.url=jdbc:postgresql://localhost:5432/schooldb
spring.datasource.username=schooluser
spring.datasource.password=Strong_Pass_123!
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

server.port=8081

#########################
#  Swagger   #
#########################
springdoc.default-produces-media-type=application/json
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
""".formatted(name);

        Path file = root.resolve("src/main/resources/application.properties");
        writeFile(file, props, overwrite);
    }





    private static Path resolveBaseJavaDir(Path projectRoot, String basePackage, boolean testSources) {
        String basePath = basePackage.replace('.', '/');

        if (testSources) {
            return projectRoot.resolve(Path.of("src", "test", "java", basePath));
        }

        return projectRoot.resolve(Path.of("src", "main", "java", basePath));
    }

    private static void ensureDir(Path dir) {
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory: " + dir, e);
        }
    }

    private static void writeFile(Path filePath, String content, boolean overwrite) {
        Objects.requireNonNull(filePath, "filePath must not be null");
        Objects.requireNonNull(content, "content must not be null");

        try {
            if (Files.exists(filePath) && !overwrite) {
                log.info("ℹ️ File exists, skipping (overwrite=false): {}", filePath.toAbsolutePath());
                return;
            }

            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            Files.writeString(filePath, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write file: " + filePath.toAbsolutePath(), e);
        }
    }
}

