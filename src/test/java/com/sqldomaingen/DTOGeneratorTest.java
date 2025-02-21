package com.sqldomaingen;

import com.sqldomaingen.generator.DTOGenerator;
import com.sqldomaingen.model.Entity;
import com.sqldomaingen.parseroutputfiles.EntityParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DTOGeneratorTest {

    @Test
    void testDTOGenerationFromEntities() throws IOException {
        EntityParser parser = new EntityParser();
        List<Entity> entities = parser.parseEntities("output/entities");

        DTOGenerator generator = new DTOGenerator();
        generator.generateDTOs(entities, "output/DTO");

        for (Entity entity : entities) {
            String dtoFileName = "output/DTO/" + entity.getName() + "DTO.java";
            Path dtoFilePath = Path.of(dtoFileName);
            assertTrue(Files.exists(dtoFilePath), "Το DTO αρχείο δεν δημιουργήθηκε: " + dtoFileName);

            String content = Files.readString(dtoFilePath);
            assertTrue(content.contains("public class " + entity.getName() + "DTO"), "Το DTO αρχείο δεν περιέχει τη σωστή κλάση: " + entity.getName());

            for (var field : entity.getFields()) {
                if (!field.isRelationship()) {
                    // Απλά πεδία: String title, Long id
                    assertTrue(content.contains("private " + field.getType() + " " + field.getName() + ";"),
                            "Το DTO αρχείο δεν περιέχει το πεδίο: " + field.getName() + " για την κλάση " + entity.getName());
                } else {
                    // Σχέσεις
                    if (field.getType().startsWith("List<")) {
                        String relatedEntityName = field.getType().substring(5, field.getType().length() - 1);
                        assertTrue(content.contains("private List<" + relatedEntityName + "DTO> " + field.getName() + ";"),
                                "Το DTO αρχείο δεν περιέχει το List<DTO> πεδίο: " + field.getName() + " για την κλάση " + entity.getName());
                    } else {
                        assertTrue(content.contains("private " + field.getType() + "DTO " + field.getName() + ";"),
                                "Το DTO αρχείο δεν περιέχει το DTO πεδίο: " + field.getName() + " για την κλάση " + entity.getName());
                    }
                }
            }
        }
    }


}
