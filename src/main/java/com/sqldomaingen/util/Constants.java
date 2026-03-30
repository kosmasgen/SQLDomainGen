package com.sqldomaingen.util;

import java.nio.file.Path;
import java.util.Set;

public class Constants {


    public static final String SPRING_BOOT_VERSION = "3.4.2";
    public static final String SPRINGDOC_VERSION = "2.8.5";
    public static final String MODELMAPPER_VERSION = "3.2.0";

    public static final String DEFAULT_VERSION = "v0.1.0";
    public static final Path MAIN_XML_RELATIVE_PATH = Path.of(
            "src", "main", "resources", "db", "migration", "changelogs", DEFAULT_VERSION, "main.xml"
    );

    public static final Path SCHEMA_PATH = Path.of("input", "test_script.sql");
    public static final Path GENERATED_JAVA_ROOT = Path.of("output", "PepTest", "src", "main", "java");

    public static final Set<String> JAVA_EXCLUDED_TABLES = Set.of(
            "audit",
            "syncruns_error_log"

    );

}
