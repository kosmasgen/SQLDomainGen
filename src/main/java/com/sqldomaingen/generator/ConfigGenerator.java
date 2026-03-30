package com.sqldomaingen.generator;

import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;
import com.sqldomaingen.util.GeneratorSupport;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Generates configuration classes for the produced Spring Boot project.
 */
@Log4j2
public class ConfigGenerator {

    /**
     * Generates all configuration files required by the generated project.
     * @param outputDir generated project root directory
     * @param basePackage generated project's base package
     * @param overwrite overwrite existing files if true
     */
    public void generateConfigs(String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        generateModelMapperConfig(outputDir, basePackage, overwrite);
    }

    /**
     * Generates a {@code ModelMapperConfig} class that exposes a {@code ModelMapper} bean.
     * @param outputDir generated project root directory
     * @param basePackage generated project's base package
     * @param overwrite overwrite existing file if true
     */
    public void generateModelMapperConfig(String outputDir, String basePackage, boolean overwrite) {
        String out = outputDir.trim();
        String pkg = basePackage.trim();

        if (out.isEmpty()) {
            throw new IllegalArgumentException("outputDir must not be blank");
        }
        if (pkg.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }

        Path configDir = GeneratorSupport.ensureDirectory(PackageResolver.resolvePath(out, pkg, "config"));
        String configPackage = PackageResolver.resolvePackageName(pkg, "config");

        Path file = configDir.resolve("ModelMapperConfig.java");

        String content = """
            package %s;

            import org.modelmapper.ModelMapper;
            import org.springframework.context.annotation.Bean;
            import org.springframework.context.annotation.Configuration;

            /**
             * Provides the {@link ModelMapper} bean used by generated mappers.
             */
            @Configuration
            public class ModelMapperConfig {

                /**
                 * Creates a {@link ModelMapper} instance configured for PATCH support.
                 * <p>
                 * Important: null values are skipped during mapping.
                 *
                 * @return a configured ModelMapper bean
                 */
                @Bean
                public ModelMapper modelMapper() {
                    ModelMapper modelMapper = new ModelMapper();

                    // 🔥 CRITICAL FOR PATCH
                    modelMapper.getConfiguration()
                            .setSkipNullEnabled(true);

                    return modelMapper;
                }
            }
            """.formatted(configPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.info("✅ ModelMapperConfig generated: {}", file.toAbsolutePath());
    }
}