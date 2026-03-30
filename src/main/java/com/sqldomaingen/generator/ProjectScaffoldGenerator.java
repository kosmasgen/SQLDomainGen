package com.sqldomaingen.generator;

import lombok.extern.log4j.Log4j2;

import com.sqldomaingen.util.GeneratorSupport;
import java.nio.file.Path;
import java.util.Objects;

import static com.sqldomaingen.util.Constants.*;

/**
 * Generates a minimal, buildable Spring Boot Maven project scaffold:
 * pom.xml, Application class, and application.properties
 * Target structure:
 * {outputDir}/pom.xml
 * {outputDir}/src/main/java/{basePackagePath}/Application.java
 * {outputDir}/src/main/resources/application.properties
 * {outputDir}/src/test/java/{basePackagePath}/ (directory only)
 */
@Log4j2
public class ProjectScaffoldGenerator {

    /**
     * Generates the project scaffold under outputDir.
     *
     * @param outputDir target project root directory
     * @param basePackage Java base package (e.g. gr.knowledge.schoolmanagement)
     * @param overwrite if true, overwrites existing files
     */
    public void generateScaffold(String outputDir,
                                 String basePackage,
                                 String defaultSchemaName,
                                 boolean overwrite) {
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
        GeneratorSupport.ensureDirectory(projectRoot);

        String groupId = pkg;
        String artifactId = resolveArtifactId(pkg);

        writePom(projectRoot, groupId, artifactId, overwrite);
        writeApplication(projectRoot, pkg, overwrite);
        createApplicationProperties(projectRoot, artifactId, defaultSchemaName, overwrite);

        GeneratorSupport.ensureDirectory(resolveBaseJavaDir(projectRoot, pkg, true));

        log.info("Project scaffold created under: {}", projectRoot.toAbsolutePath());
    }



    /**
     * Resolves the Maven artifactId from the base package.
     *
     * @param basePackage base Java package
     * @return resolved artifactId, or generated-app when blank
     */
    private static String resolveArtifactId(String basePackage) {
        int lastDotIndex = basePackage.lastIndexOf('.');
        String lastSegment = lastDotIndex >= 0
                ? basePackage.substring(lastDotIndex + 1)
                : basePackage;

        String artifactId = lastSegment.trim();
        if (artifactId.isEmpty()) {
            return "generated-app";
        }

        return artifactId;
    }

    /**
     * Generates the Maven pom.xml file for the project.
     *
     * @param projectRoot target project root directory
     * @param groupId Maven groupId
     * @param artifactId Maven artifactId
     * @param overwrite true to overwrite an existing file
     */
    private void writePom(Path projectRoot, String groupId, String artifactId, boolean overwrite) {
        Path pom = projectRoot.resolve("pom.xml");

        String safeGroupId = (groupId == null || groupId.isBlank()) ? "com.generated" : groupId.trim();
        String safeArtifactId = (artifactId == null || artifactId.isBlank()) ? "generated-app" : artifactId.trim();
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
            <version>%s</version>
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
            <springdoc.version>%s</springdoc.version>
            <modelmapper.version>%s</modelmapper.version>
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
                <version>${springdoc.version}</version>
            </dependency>

            <dependency>
                <groupId>org.modelmapper</groupId>
                <artifactId>modelmapper</artifactId>
                <version>${modelmapper.version}</version>
            </dependency>

            <dependency>
                <groupId>org.hibernate.orm</groupId>
                <artifactId>hibernate-envers</artifactId>
            </dependency>

            <dependency>
                <groupId>org.postgresql</groupId>
                <artifactId>postgresql</artifactId>
                <scope>runtime</scope>
            </dependency>
            
            <dependency>
                 <groupId>org.liquibase</groupId>
                 <artifactId>liquibase-core</artifactId>
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
    """.formatted(
                SPRING_BOOT_VERSION,
                safeGroupId,
                safeArtifactId,
                projectName,
                SPRINGDOC_VERSION,
                MODELMAPPER_VERSION
        );

        GeneratorSupport.writeFile(pom, content, overwrite);
    }


    /**
     * Generates the Spring Boot application entry point class.
     *
     * @param projectRoot target project root directory
     * @param basePackage base Java package
     * @param overwrite true to overwrite an existing file
     */
    private void writeApplication(Path projectRoot, String basePackage, boolean overwrite) {
        Path baseJavaDir = resolveBaseJavaDir(projectRoot, basePackage, false);
        GeneratorSupport.ensureDirectory(baseJavaDir);

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

        GeneratorSupport.writeFile(appFile, content, overwrite);
    }


    /**
     * Resolves the Spring Boot application class name from the base package.
     *
     * @param basePackage base Java package
     * @return resolved application class name
     */
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

    /**
     * Capitalizes the first character of the given string.
     * @return value with uppercase first character, or empty string when blank
     */
    private static String capitalize(String s) {
        if (s == null || s.isBlank()) return "";
        String t = s.trim();
        return Character.toUpperCase(t.charAt(0)) + t.substring(1);
    }


    /**
     * Creates the application.properties file for the generated project.
     *
     * @param root project root directory
     * @param applicationName Spring application name
     * @param overwrite whether existing files should be overwritten
     */
    private void createApplicationProperties(Path root,
                                             String applicationName,
                                             String defaultSchemaName,
                                             boolean overwrite) {
        String name = (applicationName == null || applicationName.isBlank())
                ? "generated-app"
                : applicationName.trim();

        String resolvedSchemaName = (defaultSchemaName == null || defaultSchemaName.isBlank())
                ? "public"
                : defaultSchemaName.trim();

        String props = """
spring.application.name=%s

############################
# PostgreSQL
############################
spring.datasource.url=jdbc:postgresql://localhost:5432/schooldb
spring.datasource.username=schooluser
spring.datasource.password=Strong_Pass_123!

############################
# Liquibase
############################
spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:db/migration/changelog-master.xml
spring.liquibase.default-schema=%s

############################
# JPA
############################
spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.default_schema=%s
spring.jpa.properties.org.hibernate.envers.default_schema=audit

server.port=8081

############################
# Swagger
############################
springdoc.default-produces-media-type=application/json
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.operationsSorter=alpha
springdoc.writer-with-order-by-keys=true
""".formatted(name, resolvedSchemaName, resolvedSchemaName);

        Path file = root.resolve("src/main/resources/application.properties");
        GeneratorSupport.writeFile(file, props, overwrite);
    }




    /**
     * Resolves the base Java source directory for the given package.
     *
     * @param projectRoot target project root directory
     * @param basePackage base Java package
     * @param testSources true to resolve the test source path, false for main source path
     * @return resolved base Java directory path
     */
    private static Path resolveBaseJavaDir(Path projectRoot, String basePackage, boolean testSources) {
        String basePath = basePackage.replace('.', '/');

        if (testSources) {
            return projectRoot.resolve(Path.of("src", "test", "java", basePath));
        }

        return projectRoot.resolve(Path.of("src", "main", "java", basePath));
    }

}

