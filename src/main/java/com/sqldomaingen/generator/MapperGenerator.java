package com.sqldomaingen.generator;

import com.sqldomaingen.model.Relationship;
import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
public class MapperGenerator {
    private final Map<String, Table> tableMap;

    public MapperGenerator(Map<String, Table> tableMap) {
        this.tableMap = tableMap;
    }


    private void generateBaseMapper(Path mappersPath, String packageName) {
        Path baseMapperPath = mappersPath.resolve("BaseMapper.java");

        if (Files.exists(baseMapperPath)) {
            log.info("✅ BaseMapper already exists. Skipping generation.");
            return;
        }

        log.info("🔧 Creating BaseMapper interface...");

        String baseMapperContent = """
                package %s.mappers;
                
                import org.modelmapper.ModelMapper;
                import java.util.List;
                import java.util.stream.Collectors;
                
                public interface BaseMapper<E, D> {
                
                    ModelMapper modelMapper = new ModelMapper();
                
                    default D toDTO(E entity) {
                        return modelMapper.map(entity, getDtoClass());
                    }
                
                    default E toEntity(D dto) {
                        return modelMapper.map(dto, getEntityClass());
                    }
                
                    default List<D> toDTOList(List<E> entities) {
                        return entities.stream().map(this::toDTO).collect(Collectors.toList());
                    }
                
                    default List<E> toEntityList(List<D> dtos) {
                        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
                    }
                
                    Class<D> getDtoClass();
                
                    Class<E> getEntityClass();
                }
                """.formatted(packageName);

        try {
            Files.write(baseMapperPath, baseMapperContent.getBytes());
            log.info("✅ BaseMapper successfully created: {}", baseMapperPath);
        } catch (IOException e) {
            log.error("❌ Error writing BaseMapper file: {}", e.getMessage());
        }
    }


    public void generateMappers(String packageName) {
        Path mappersPath = Paths.get("output/mappers");

        try {
            Files.createDirectories(mappersPath);
            log.info("✅ Mapper directory created at: {}", mappersPath);

            if (tableMap.isEmpty()) {
                log.warn("⚠️ No tables found in tableMap. No mappers will be generated!");
                return;
            }

            // 🔧 Δημιουργούμε το BaseMapper μία φορά αν δεν υπάρχει
            generateBaseMapper(mappersPath, packageName);

            for (Table table : tableMap.values()) {
                log.info("🔍 Generating mapper for table: {}", table.getName());
                generateMapperFile(table, mappersPath, packageName);
            }
        } catch (IOException e) {
            log.error("❌ Error creating mappers directory: {}", e.getMessage());
        }
    }


    private void generateMapperFile(Table table, Path mappersPath, String packageName) {
        String entityName = NamingConverter.toPascalCase(table.getName());
        String dtoName = entityName + "DTO";
        String mapperName = entityName + "Mapper";

        log.info("📌 Creating Mapper: {} -> {}.java", table.getName(), mapperName);

        String mapperContent = generateMapperContent(table, entityName, dtoName, mapperName, packageName, tableMap);


        Path mapperFilePath = mappersPath.resolve(mapperName + ".java");
        log.debug("📂 Target file path: {}", mapperFilePath);

        try {
            log.info("✏️ Writing content to file: {}", mapperFilePath.toAbsolutePath());
            Files.write(mapperFilePath, mapperContent.getBytes());
            log.info("✅ File successfully written: {}", mapperFilePath.toAbsolutePath());
        } catch (IOException e) {
            log.error("❌ Error writing mapper file '{}': {}", mapperName, e.getMessage());
        }
    }

    private String generateMapperContent(Table table, String entityName, String dtoName, String mapperName, String packageName, Map<String, Table> tableMap) {
        log.info("📌 Generating mapper content for: {}", entityName);

        // ✅ Ανάκτηση των σχέσεων απευθείας από το tableMap
        Set<String> relatedEntities = tableMap.get(entityName.toLowerCase()).getRelationships().stream()
                .map(Relationship::getTargetTable)
                .map(NamingConverter::toPascalCase)
                .collect(Collectors.toSet());

        log.info("🔍 Found relationships for {}: {}", entityName, relatedEntities);

        StringBuilder content = new StringBuilder();
        content.append("package ").append(packageName).append(".mappers;\n\n")
                .append("import org.springframework.stereotype.Component;\n")
                .append("import ").append(packageName).append(".dto.").append(dtoName).append(";\n")
                .append("import ").append(packageName).append(".entities.").append(entityName).append(";\n")
                .append("import ").append(packageName).append(".mappers.BaseMapper;\n") // ✅ Import BaseMapper
                .append("import java.util.List;\n\n");

        // ✅ Προσθήκη imports για Nested DTOs
        for (String relatedEntity : relatedEntities) {
            content.append("import ").append(packageName).append(".dto.").append(relatedEntity).append("DTO;\n");
            content.append("import ").append(packageName).append(".mappers.").append(relatedEntity).append("Mapper;\n\n");
        }

        content.append("@Component\n")
                .append("public class ").append(mapperName).append(" implements BaseMapper<").append(entityName).append(", ").append(dtoName).append("> {\n\n");


        content.append("}\n");

        log.info("📄 Generated content for {}:\n{}", mapperName, content);

        return content.toString();
    }
}
