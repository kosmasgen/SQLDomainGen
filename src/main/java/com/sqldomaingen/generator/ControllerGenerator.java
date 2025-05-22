package com.sqldomaingen.generator;

import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

@Log4j2
public class ControllerGenerator {

    private static final String OUTPUT_PATH = "output/controllers/";

    public void generateControllers(List<Table> tables) {
        log.info("🔄 Starting Controller generation...");

        for (Table table : tables) {
            String controllerCode = generateControllerCode(table);
            saveToFile(table.getName(), controllerCode);
        }

        log.info("✅ Controller generation completed!");
    }

    /**
     * Generates the code for a REST controller for the given table.
     * The controller will include basic CRUD operations and logging functionality.
     *
     * @param table The table object containing metadata for the entity.
     * @return A string representing the generated controller code.
     */
    private String generateControllerCode(Table table) {

        String entityName = NamingConverter.toPascalCase(table.getName());
        String dtoName = entityName + "DTO";
        String serviceName = entityName + "Service";
        String serviceInstance = NamingConverter.decapitalizeFirstLetter(entityName) + "Service";
        String primaryKeyType = RepositoryGenerator.detectPrimaryKeyType(table);

        return "package com.sqldomaingen.controller;\n\n"
                + "import com.sqldomaingen.dto." + dtoName + ";\n"
                + "import com.sqldomaingen.service." + serviceName + ";\n"
                + "import org.slf4j.Logger;\n"
                + "import org.slf4j.LoggerFactory;\n"
                + "import org.springframework.http.ResponseEntity;\n"
                + "import org.springframework.web.bind.annotation.*;\n"
                + "import java.util.List;\n\n"
                + "/**\n"
                + " * Controller for managing " + entityName + " entities.\n"
                + " * Provides CRUD operations for the " + entityName + " entity.\n"
                + " */\n"
                + "@RestController\n"
                + "@RequestMapping(\"/api/" + NamingConverter.toKebabCase(entityName) + "s\")\n"
                + "public class " + entityName + "Controller {\n\n"
                + "    private static final Logger logger = LoggerFactory.getLogger(" + entityName + "Controller.class);\n"
                + "    private final " + serviceName + " " + serviceInstance + ";\n\n"
                + "    /**\n"
                + "     * Constructor for injecting the " + serviceName + " service.\n"
                + "     * @param " + serviceInstance + " The service for the " + entityName + " entity.\n"
                + "     */\n"
                + "    public " + entityName + "Controller(" + serviceName + " " + serviceInstance + ") {\n"
                + "        this." + serviceInstance + " = " + serviceInstance + ";\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Fetches all " + entityName + " records.\n"
                + "     * @return A list of " + dtoName + " objects.\n"
                + "     */\n"
                + "    @GetMapping\n"
                + "    public ResponseEntity<List<" + dtoName + ">> getAll() {\n"
                + "        logger.info(\"Fetching all " + entityName + " records\");\n"
                + "        return ResponseEntity.ok(" + serviceInstance + ".getAll());\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Fetches a specific " + entityName + " by its ID.\n"
                + "     * @param id The ID of the " + entityName + " entity.\n"
                + "     * @return The " + dtoName + " object corresponding to the ID.\n"
                + "     */\n"
                + "    @GetMapping(\"/{id}\")\n"
                + "    public ResponseEntity<" + dtoName + "> getById(@PathVariable " + primaryKeyType + " id) {\n"
                + "        logger.info(\"Fetching " + entityName + " with ID: {}\", id);\n"
                + "        return ResponseEntity.ok(" + serviceInstance + ".getById(id));\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Creates a new " + entityName + " record.\n"
                + "     * @param dto The " + dtoName + " object to be created.\n"
                + "     * @return The created " + dtoName + " object.\n"
                + "     */\n"
                + "    @PostMapping\n"
                + "    public ResponseEntity<" + dtoName + "> create(@RequestBody " + dtoName + " dto) {\n"
                + "        logger.info(\"Creating new " + entityName + ": {}\", dto);\n"
                + "        return ResponseEntity.ok(" + serviceInstance + ".create(dto));\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Updates an existing " + entityName + " record.\n"
                + "     * @param id The ID of the " + entityName + " to be updated.\n"
                + "     * @param dto The new data for the " + entityName + " entity.\n"
                + "     * @return The updated " + dtoName + " object.\n"
                + "     */\n"
                + "    @PutMapping(\"/{id}\")\n"
                + "    public ResponseEntity<" + dtoName + "> update(@PathVariable " + primaryKeyType + " id, @RequestBody " + dtoName + " dto) {\n"
                + "        logger.info(\"Updating " + entityName + " with ID: {}\", id);\n"
                + "        return ResponseEntity.ok(" + serviceInstance + ".update(id, dto));\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Deletes an existing " + entityName + " record by its ID.\n"
                + "     * @param id The ID of the " + entityName + " to be deleted.\n"
                + "     * @return A response with no content indicating the deletion is successful.\n"
                + "     */\n"
                + "    @DeleteMapping(\"/{id}\")\n"
                + "    public ResponseEntity<Void> deleteById(@PathVariable " + primaryKeyType + " id) {\n"
                + "        logger.info(\"Deleting " + entityName + " with ID: {}\", id);\n"
                + "        " + serviceInstance + ".deleteById(id);\n"
                + "        return ResponseEntity.noContent().build();\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Checks if the " + entityName + " exists by its ID.\n"
                + "     * @param id The ID of the " + entityName + " entity.\n"
                + "     * @return `true` if the entity exists, otherwise `false`.\n"
                + "     */\n"
                + "    @GetMapping(\"/exists/{id}\")\n"
                + "    public ResponseEntity<Boolean> existsById(@PathVariable " + primaryKeyType + " id) {\n"
                + "        logger.info(\"Checking if " + entityName + " exists with ID: {}\", id);\n"
                + "        boolean exists = " + serviceInstance + ".existsById(id);\n"
                + "        return ResponseEntity.ok(exists);\n"
                + "    }\n"
                + "    /**\n"
                + "     * Counts the number of " + entityName + " records.\n"
                + "     * @return The count of " + entityName + " records.\n"
                + "     */\n"
                + "    @GetMapping(\"/count\")\n"
                + "    public ResponseEntity<Long> count() {\n"
                + "        logger.info(\"Counting " + entityName + " records\");\n"
                + "        return ResponseEntity.ok(" + serviceInstance + ".count());\n"
                + "    }\n\n"
                + "}\n";
    }


    private void saveToFile(String entityName, String content) {
        try {
            Path outputPath = Paths.get(OUTPUT_PATH);
            Files.createDirectories(outputPath);

            Path filePath = outputPath.resolve(entityName + "Controller.java");
            Files.writeString(filePath, content);

            log.info("✅ Controller generated: {}", filePath);
        } catch (IOException e) {
            log.error("❌ Error writing file: {}Controller.java", entityName, e);
        }
    }
}
