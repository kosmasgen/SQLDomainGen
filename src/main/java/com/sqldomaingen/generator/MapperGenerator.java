package com.sqldomaingen.generator;

import com.sqldomaingen.model.Table;
import com.sqldomaingen.util.NamingConverter;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;

@Log4j2
@Component
public class MapperGenerator {

    private final Map<String, Table> tableMap;

    public MapperGenerator(Map<String, Table> tableMap) {
        this.tableMap = Objects.requireNonNull(tableMap, "tableMap must not be null");
    }

    /**
     * Generates BaseMapper + one Mapper per table.
     *
     * Output:
     * {outputDir}/src/main/java/{basePackage path}/mapper/*.java
     *
     * @param outputDir   generation root (project root output)
     * @param basePackage base package (e.g. gr.knowledge.schoolmanagement)
     */
    public void generateMappers(String outputDir, String basePackage) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        Path mapperDir = ensureDirectory(PackageResolver.resolvePath(outputDir, basePackage, "mapper"));
        log.info("✅ Mappers output directory: {}", mapperDir.toAbsolutePath());

        generateBaseMapper(mapperDir, basePackage);

        for (Table table : tableMap.values()) {
            generateMapper(table, mapperDir, basePackage);
        }

        log.info("✅ Mapper generation completed under: {}", mapperDir.toAbsolutePath());
    }

    private void generateBaseMapper(Path mapperDir, String basePackage) {
        Path file = mapperDir.resolve("BaseMapper.java");

        if (Files.exists(file)) {
            log.info("ℹ️ BaseMapper already exists. Skipping: {}", file.toAbsolutePath());
            return;
        }

        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");

        String content = """
                package %s;

                import org.modelmapper.ModelMapper;

                import java.util.List;

                /**
                 * Base mapper for converting between Entity and DTO using {@link ModelMapper}.
                 *
                 * @param <E> entity type
                 * @param <D> dto type
                 */
                public abstract class BaseMapper<E, D> {

                    protected final ModelMapper modelMapper;
                    private final Class<E> entityClass;
                    private final Class<D> dtoClass;

                    protected BaseMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
                        this.modelMapper = modelMapper;
                        this.entityClass = entityClass;
                        this.dtoClass = dtoClass;
                    }

                    /**
                     * Converts an entity to a DTO.
                     *
                     * @param entity source entity
                     * @return mapped dto
                     */
                    public D toDTO(E entity) {
                        return modelMapper.map(entity, dtoClass);
                    }

                    /**
                     * Converts a list of entities to DTOs.
                     *
                     * @param entities source entities
                     * @return mapped dto list
                     */
                    public List<D> toDTO(List<E> entities) {
                        return entities.stream().map(this::toDTO).toList();
                    }

                    /**
                     * Converts a DTO to an entity.
                     *
                     * @param dto source dto
                     * @return mapped entity
                     */
                    public E toEntity(D dto) {
                        return modelMapper.map(dto, entityClass);
                    }

                    /**
                     * Converts a list of DTOs to entities.
                     *
                     * @param dtos source dtos
                     * @return mapped entity list
                     */
                    public List<E> toEntity(List<D> dtos) {
                        return dtos.stream().map(this::toEntity).toList();
                    }
                }
                """.formatted(mapperPackage);

        writeFile(file, content, "BaseMapper");
    }

    private void generateMapper(Table table, Path mapperDir, String basePackage) {
        Objects.requireNonNull(table, "table must not be null");

        String normalizedTableName = normalizeTableName(table.getName());
        String entityName = NamingConverter.toPascalCase(normalizedTableName);
        String dtoName = entityName + "Dto";
        String mapperName = entityName + "Mapper";

        String mapperPackage = PackageResolver.resolvePackageName(basePackage, "mapper");
        String entityPackage = PackageResolver.resolvePackageName(basePackage, "entity");
        String dtoPackage = PackageResolver.resolvePackageName(basePackage, "dto");

        String content = """
                package %s;

                import org.modelmapper.ModelMapper;
                import org.springframework.stereotype.Component;

                import %s.%s;
                import %s.%s;

                /**
                 * Mapper for {@link %s} and {@link %s}.
                 */
                @Component
                public class %s extends BaseMapper<%s, %s> {

                    public %s(ModelMapper modelMapper) {
                        super(modelMapper, %s.class, %s.class);
                    }
                }
                """.formatted(
                mapperPackage,
                entityPackage, entityName,
                dtoPackage, dtoName,
                entityName, dtoName,
                mapperName, entityName, dtoName,
                mapperName, entityName, dtoName
        );

        Path file = mapperDir.resolve(mapperName + ".java");
        writeFile(file, content, mapperName);
    }

    private static String normalizeTableName(String raw) {
        if (raw == null) return "";
        String s = raw.trim();
        int dot = s.lastIndexOf('.');
        if (dot >= 0 && dot < s.length() - 1) {
            s = s.substring(dot + 1);
        }
        return s;
    }

    private static Path ensureDirectory(Path path) {
        try {
            Files.createDirectories(path);
            return path;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory: " + path, e);
        }
    }

    private static void writeFile(Path file, String content, String logicalName) {
        try {
            Files.writeString(file, content, StandardCharsets.UTF_8);
            log.info("✅ Generated {}: {}", logicalName, file.toAbsolutePath());
        } catch (IOException e) {
            log.error("❌ Failed to write {} file: {}", logicalName, file.toAbsolutePath(), e);
        }
    }
}
