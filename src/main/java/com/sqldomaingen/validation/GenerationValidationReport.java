package com.sqldomaingen.validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Aggregates validation sections and overall metadata for one generation run.
 */
public class GenerationValidationReport {

    private final String inputFile;
    private final String outputDir;
    private final String basePackage;
    private final LocalDateTime generatedAt;
    private final List<Section> sections;
    private final String author;

    /**
     * Creates a report for one generation run.
     *
     * @param inputFile input SQL file path
     * @param outputDir generation output directory
     * @param basePackage generated base package
     * @param author Liquibase/generation author
     */
    public GenerationValidationReport(String inputFile, String outputDir, String basePackage, String author) {
        this.inputFile = inputFile == null ? "" : inputFile;
        this.outputDir = outputDir == null ? "" : outputDir;
        this.basePackage = basePackage == null ? "" : basePackage;
        this.author = author == null ? "" : author;
        this.generatedAt = LocalDateTime.now();
        this.sections = new ArrayList<>();
    }

    /**
     * Returns the generation author.
     *
     * @return generation author
     */
    @SuppressWarnings("unused")
    public String getAuthor() {
        return author;
    }



    /**
     * Adds one validation section to the report.
     *
     * @param title section title
     * @param details informational lines
     * @param violations validation violations
     */
    public void addSection(String title, List<String> details, List<String> violations) {
        sections.add(new Section(
                Objects.requireNonNullElse(title, "Untitled Section"),
                details == null ? List.of() : List.copyOf(details),
                violations == null ? List.of() : List.copyOf(violations)
        ));
    }

    /**
     * Returns all report sections.
     *
     * @return immutable sections
     */
    public List<Section> getSections() {
        return Collections.unmodifiableList(sections);
    }

    /**
     * Returns all violations across all sections.
     *
     * @return flattened violations
     */
    public List<String> getAllViolations() {
        List<String> allViolations = new ArrayList<>();

        for (Section section : sections) {
            allViolations.addAll(section.violations());
        }

        return allViolations;
    }

    /**
     * Returns the total number of violations.
     *
     * @return total violation count
     */
    public int getTotalViolationCount() {
        return getAllViolations().size();
    }


    /**
     * Returns the input SQL file path.
     *
     * @return input file path
     */
    @SuppressWarnings("unused")
    public String getInputFile() {
        return inputFile;
    }

    /**
     * Returns the generation output directory.
     *
     * @return output directory
     */
    @SuppressWarnings("unused")
    public String getOutputDir() {
        return outputDir;
    }

    /**
     * Returns the generated base package.
     *
     * @return base package
     */
    @SuppressWarnings("unused")
    public String getBasePackage() {
        return basePackage;
    }

    /**
     * Returns the generation timestamp.
     *
     * @return generation timestamp
     */
    @SuppressWarnings("unused")
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    /**
     * One logical validation section.
     *
     * @param title section title
     * @param details informational lines
     * @param violations validation violations
     */
    public record Section(String title, List<String> details, List<String> violations) {
    }


}