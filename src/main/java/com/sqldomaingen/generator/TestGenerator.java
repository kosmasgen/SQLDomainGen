package com.sqldomaingen.generator;

import com.sqldomaingen.model.Table;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Objects;

/**
 * Coordinates test generation for controller and service implementation tests.
 */
@Log4j2
public class TestGenerator {

    private final ControllerTestGenerator controllerTestGenerator;
    private final ServiceImplTestGenerator serviceImplTestGenerator;

    /**
     * Creates a test generator with default sub-generators.
     */
    public TestGenerator() {
        this(new ControllerTestGenerator(), new ServiceImplTestGenerator());
    }

    /**
     * Creates a test generator with injected sub-generators.
     *
     * @param controllerTestGenerator controller test generator
     * @param serviceImplTestGenerator service implementation test generator
     */
    public TestGenerator(
            ControllerTestGenerator controllerTestGenerator,
            ServiceImplTestGenerator serviceImplTestGenerator
    ) {
        this.controllerTestGenerator = Objects.requireNonNull(controllerTestGenerator, "controllerTestGenerator must not be null");
        this.serviceImplTestGenerator = Objects.requireNonNull(serviceImplTestGenerator, "serviceImplTestGenerator must not be null");
    }

    /**
     * Main entry point used by the generation pipeline.
     *
     * @param tables parsed SQL tables
     * @param outputDir project root output directory
     * @param basePackage base Java package
     * @param overwrite overwrite existing files when true
     */
    public void generateTests(List<Table> tables, String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(tables, "tables must not be null");
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        controllerTestGenerator.generateControllerTests(tables, outputDir, basePackage, overwrite);
        serviceImplTestGenerator.generateServiceImplTests(tables, outputDir, basePackage, overwrite);

        log.info("Test classes generated successfully.");
    }

    /**
     * Backwards-compatible overload.
     *
     * @param tables parsed SQL tables
     * @param outputDir project root output directory
     * @param basePackage base Java package
     */
    public void generateAllTests(List<Table> tables, String outputDir, String basePackage) {
        generateTests(tables, outputDir, basePackage, true);
    }
}