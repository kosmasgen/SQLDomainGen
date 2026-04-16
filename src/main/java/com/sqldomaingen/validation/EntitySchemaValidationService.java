package com.sqldomaingen.validation;

import com.sqldomaingen.util.Constants;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
 * Core validation service extracted from test logic.
 */
public class EntitySchemaValidationService {

    /**
     * Executes validation logic.
     *
     * @return list of violations
     */
    public List<String> validate() {
        List<String> violations = new ArrayList<>();

        try {
            if (!Files.exists(Constants.SCHEMA_PATH)) {
                violations.add("Missing schema file: " + Constants.SCHEMA_PATH.toAbsolutePath());
                return violations;
            }

            if (!Files.exists(Constants.GENERATED_JAVA_ROOT)) {
                violations.add("Missing generated Java root: " + Constants.GENERATED_JAVA_ROOT.toAbsolutePath());
                return violations;
            }

            EntitySchemaValidator validator = new EntitySchemaValidator();
            return validator.validate();
        } catch (Exception exception) {
            violations.add("Validation execution failed: " + exception.getMessage());
            return violations;
        }
    }
}