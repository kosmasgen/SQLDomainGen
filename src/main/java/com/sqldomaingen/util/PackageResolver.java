package com.sqldomaingen.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Resolves filesystem paths and Java package names for generated sources.
 */
public final class PackageResolver {

    private PackageResolver() {
    }

    /**
     * Builds a target folder like:
     * {outputDir}/src/main/java/{basePackage as path}/{subPackage as path}
     * subPackage may include dots, e.g. "service.impl".
     */
    public static Path resolvePath(String outputDir, String basePackage, String subPackage) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");
        Objects.requireNonNull(subPackage, "subPackage must not be null");

        String out = outputDir.trim();
        String base = basePackage.trim();
        String sub = subPackage.trim();

        if (out.isEmpty()) {
            throw new IllegalArgumentException("outputDir must not be blank");
        }
        if (base.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }
        if (sub.isEmpty()) {
            throw new IllegalArgumentException("subPackage must not be blank");
        }

        String basePath = base.replace('.', '/');
        String subPath = sub.replace('.', '/');

        return Paths.get(out, "src", "main", "java", basePath, subPath);
    }

    /**
     * Builds a target folder like:
     * {outputDir}/src/{main|test}/java/{basePackage as path}/{subPackage as path}
     * If subPackage is blank, returns the base package folder.
     */
    public static Path resolvePath(String outputDir, String basePackage, String subPackage, boolean testSources) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");
        Objects.requireNonNull(subPackage, "subPackage must not be null");

        String out = outputDir.trim();
        String base = basePackage.trim();
        String sub = subPackage.trim();

        if (out.isEmpty()) {
            throw new IllegalArgumentException("outputDir must not be blank");
        }
        if (base.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }

        String basePath = base.replace('.', '/');
        String srcRoot = testSources ? "test" : "main";

        if (sub.isEmpty()) {
            return Paths.get(out, "src", srcRoot, "java", basePath);
        }

        String subPath = sub.replace('.', '/');
        return Paths.get(out, "src", srcRoot, "java", basePath, subPath);
    }

    /**
     * Builds a Java package name like: {basePackage}.{subPackage}
     */
    public static String resolvePackageName(String basePackage, String subPackage) {
        Objects.requireNonNull(basePackage, "basePackage must not be null");
        Objects.requireNonNull(subPackage, "subPackage must not be null");

        String base = basePackage.trim();
        String sub = subPackage.trim();

        if (base.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }
        if (sub.isEmpty()) {
            throw new IllegalArgumentException("subPackage must not be blank");
        }

        return base + "." + sub;
    }
}
