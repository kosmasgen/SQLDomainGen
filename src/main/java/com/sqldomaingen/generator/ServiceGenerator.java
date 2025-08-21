package com.sqldomaingen.generator;

import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
public class ServiceGenerator {

    private static final String SERVICE_OUTPUT_PATH = "output/services";

    /**
     * Γενική μέθοδος που παράγει όλα τα services (interfaces & implementations)
     */
    public void generateAllServices(List<Table> tables) {
        ensureOutputDirectory();

        for (Table table : tables) {
            String serviceInterfaceCode = generateServiceInterface(table);
            writeToFile(serviceInterfaceCode, table.getName() + "Service.java");

            String serviceImplCode = generateServiceImpl(table);
            writeToFile(serviceImplCode, table.getName() + "ServiceImpl.java");
        }
    }

    /**
     * Δημιουργεί δυναμικά το Service Interface για κάθε οντότητα.
     */
    private String generateServiceInterface(Table table) {
        String entityName = NamingConverter.toPascalCase(table.getName());
        String dtoName = entityName + "DTO";
        String primaryKeyType = RepositoryGenerator.detectPrimaryKeyType(table);

        // Αν το PK είναι UUID, προσθέτουμε import
        String uuidImport = primaryKeyType.equals("UUID") ? "import java.util.UUID;\n" : "";

        return "package com.sqldomaingen.service;\n\n"
                + "import com.sqldomaingen.dto." + dtoName + ";\n"
                + uuidImport
                + "import java.util.List;\n\n"
                + "public interface " + entityName + "Service {\n\n"
                + "    List<" + dtoName + "> getAll();\n"
                + "    " + dtoName + " getById(" + primaryKeyType + " id);\n"
                + "    " + dtoName + " create(" + dtoName + " dto);\n"
                + "    " + dtoName + " update(" + primaryKeyType + " id, " + dtoName + " dto);\n"
                + "    void deleteById(" + primaryKeyType + " id);\n"
                + "    boolean existsById(" + primaryKeyType + " id);\n"
                + "    long count();\n"
                + "}";
    }


    /**
     * Δημιουργεί δυναμικά το Service Implementation (σύνδεση Repository + Mapper).
     */
    private String generateServiceImpl(Table table) {
        String entityName = NamingConverter.toPascalCase(table.getName());
        String dtoName = entityName + "DTO";
        String repositoryName = entityName + "Repository";
        String mapperName = entityName + "Mapper";
        String primaryKeyType = RepositoryGenerator.detectPrimaryKeyType(table);

        String repositoryInstanceName = NamingConverter.decapitalizeFirstLetter(entityName) + "Repository";
        String mapperInstanceName = NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper";


        return "package com.sqldomaingen.service.impl;\n\n"
                + "import com.sqldomaingen.dto." + dtoName + ";\n"
                + "import com.sqldomaingen.model." + entityName + ";\n"
                + "import com.sqldomaingen.repository." + repositoryName + ";\n"
                + "import com.sqldomaingen.mapper." + mapperName + ";\n"
                + "import com.sqldomaingen.service." + entityName + "Service;\n"
                + "import org.slf4j.Logger;\n"
                + "import org.slf4j.LoggerFactory;\n"
                + "import org.springframework.stereotype.Service;\n"
                + "import org.springframework.transaction.annotation.Transactional;\n"
                + "import jakarta.persistence.EntityNotFoundException;\n"
                + "import java.util.List;\n\n"
                + "/**\n"
                + " * Υλοποίηση του service για την οντότητα " + entityName + ".\n"
                + " */\n"
                + "@Service\n"
                + "@Transactional\n"
                + "public class " + entityName + "ServiceImpl implements " + entityName + "Service {\n\n"
                + "    private static final Logger logger = LoggerFactory.getLogger(" + entityName + "ServiceImpl.class);\n"
                + "    private final " + repositoryName + " " + repositoryInstanceName + ";\n"
                + "    private final " + mapperName + " " + mapperInstanceName + ";\n\n"
                + "    /**\n"
                + "     * Δημιουργεί ένα νέο instance του " + entityName + "ServiceImpl.\n"
                + "     *\n"
                + "     * @param " + repositoryInstanceName + " Το repository για την οντότητα " + entityName + ".\n"
                + "     * @param " + mapperInstanceName + " Ο mapper για την οντότητα " + entityName + ".\n"
                + "     */\n"
                + "    public " + entityName + "ServiceImpl(" + repositoryName + " " + NamingConverter.decapitalizeFirstLetter(entityName) + "Repository, "
                + mapperName + " " + NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper) {\n"
                + "        this." + repositoryInstanceName + " = " + NamingConverter.decapitalizeFirstLetter(entityName) + "Repository;\n"
                + "        this." + mapperInstanceName + " = " + NamingConverter.decapitalizeFirstLetter(entityName) + "Mapper;\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Επιστρέφει όλα τα " + entityName + " ως λίστα.\n"
                + "     *\n"
                + "     * @return Λίστα με όλα τα " + entityName + " ως DTOs.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public List<" + dtoName + "> getAll() {\n"
                + "        return " + repositoryInstanceName + ".findAll().stream().map(" + mapperInstanceName + "::toDTO).toList();\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Επιστρέφει ένα " + entityName + " με το συγκεκριμένο ID.\n"
                + "     *\n"
                + "     * @param id Το ID του " + entityName + ".\n"
                + "     * @return Το " + entityName + " ως DTO.\n"
                + "     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public " + dtoName + " getById(" + primaryKeyType + " id) {\n"
                + "        return " + mapperInstanceName + ".toDTO(" + repositoryInstanceName + ".findById(id)\n"
                + "            .orElseThrow(() -> new EntityNotFoundException(\"Entity with ID \" + id + \" not found\")));\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Δημιουργεί ένα νέο " + entityName + " και το αποθηκεύει στη βάση.\n"
                + "     *\n"
                + "     * @param dto Τα δεδομένα του " + entityName + " που θα δημιουργηθεί.\n"
                + "     * @return Το δημιουργηθέν " + entityName + " ως DTO.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public " + dtoName + " create(" + dtoName + " dto) {\n"
                + "        logger.info(\"Creating new " + entityName + ": {}\", dto);\n"
                + "        return " + mapperInstanceName + ".toDTO(" + repositoryInstanceName + ".save(" + mapperInstanceName + ".toEntity(dto)));\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Ενημερώνει ένα υπάρχον " + entityName + " με νέα δεδομένα.\n"
                + "     *\n"
                + "     * @param id Το ID του " + entityName + " που θα ενημερωθεί.\n"
                + "     * @param dto Τα νέα δεδομένα του " + entityName + ".\n"
                + "     * @return Το ενημερωμένο " + entityName + " ως DTO.\n"
                + "     * @throws EntityNotFoundException Αν δεν βρεθεί η οντότητα με το συγκεκριμένο ID.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public " + dtoName + " update(" + primaryKeyType + " id, " + dtoName + " dto) {\n"
                + "        " + entityName + " existingEntity = " + repositoryInstanceName + ".findById(id)\n"
                + "            .orElseThrow(() -> new EntityNotFoundException(\"Entity with ID \" + id + \" not found\"));\n"
                + "        " + entityName + " updatedEntity = " + mapperInstanceName + ".updateEntityFromDTO(dto, existingEntity);\n"
                + "        return " + mapperInstanceName + ".toDTO(" + repositoryInstanceName + ".save(updatedEntity));\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Διαγράφει ένα " + entityName + " από τη βάση.\n"
                + "     *\n"
                + "     * @param id Το ID του " + entityName + " που θα διαγραφεί.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public void deleteById(" + primaryKeyType + " id) {\n"
                + "        logger.info(\"Deleting " + entityName + " with ID: {}\", id);\n"
                + "        " + repositoryInstanceName + ".deleteById(id);\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Ελέγχει αν υπάρχει το " + entityName + " με το συγκεκριμένο ID.\n"
                + "     *\n"
                + "     * @param id Το ID της οντότητας.\n"
                + "     * @return `true` αν υπάρχει, αλλιώς `false`.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public boolean existsById(" + primaryKeyType + " id) {\n"
                + "        return " + repositoryInstanceName + ".existsById(id);\n"
                + "    }\n\n"
                + "    /**\n"
                + "     * Επιστρέφει το πλήθος των εγγραφών της οντότητας " + entityName + ".\n"
                + "     *\n"
                + "     * @return Ο αριθμός των εγγραφών.\n"
                + "     */\n"
                + "    @Override\n"
                + "    public long count() {\n"
                + "        return " + repositoryInstanceName + ".count();\n"
                + "    }\n"
                + "}\n";

    }


    /**
     * Εγγράφει το παραγόμενο service στον αντίστοιχο φάκελο
     */
    private void writeToFile(String content, String fileName) {
        Path filePath = Paths.get(SERVICE_OUTPUT_PATH, fileName);
        try {
            Files.write(filePath, content.getBytes());
            log.info("✅ Service generated successfully: {}", filePath);
        } catch (IOException e) {
            log.error("❌ Failed to write service file: {}", filePath, e);
        }
    }

    /**
     * Δημιουργεί τον φάκελο αν δεν υπάρχει
     */
    private void ensureOutputDirectory() {
        Path path = Paths.get(SERVICE_OUTPUT_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                log.info("✅ Service output directory created: {}", path);
            } catch (IOException e) {
                log.error("❌ Failed to create service output directory: {}", SERVICE_OUTPUT_PATH, e);
            }
        } else {
            log.info("ℹ️ Service output directory already exists: {}", path);
        }
    }
}
