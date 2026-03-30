package com.sqldomaingen;

import com.sqldomaingen.generator.LiquibaseGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LiquibaseGeneratorTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldGenerateMainXmlWithParentsBeforeChildren() throws Exception {
        Table languages = table("pep_schema.languages");
        Table companyProfile = table("pep_schema.company_profile");
        Table companyProfileI18n = table(
                "pep_schema.company_profile_i18n",
                foreignKeyColumn("company_profile_id", "pep_schema.company_profile", "id"),
                foreignKeyColumn("language_id", "pep_schema.languages", "id")
        );

        LiquibaseGenerator generator = new LiquibaseGenerator();

        generator.generateLiquibaseFiles(
                tempDir.toString(),
                List.of(companyProfileI18n, companyProfile, languages),
                true
        );

        Path mainXml = tempDir.resolve("src/main/resources/db/migration/changelogs/v0.1.0/main.xml");
        String content = Files.readString(mainXml);

        int languagesIndex = content.indexOf("languages.xml");
        int companyProfileIndex = content.indexOf("companyProfile.xml");
        int companyProfileI18nIndex = content.indexOf("companyProfileI18n.xml");

        assertTrue(languagesIndex >= 0, "languages include missing");
        assertTrue(companyProfileIndex >= 0, "companyProfile include missing");
        assertTrue(companyProfileI18nIndex >= 0, "companyProfileI18n include missing");

        assertTrue(
                languagesIndex < companyProfileI18nIndex,
                "languages must come before companyProfileI18n"
        );
        assertTrue(
                companyProfileIndex < companyProfileI18nIndex,
                "companyProfile must come before companyProfileI18n"
        );
    }

    @Test
    void shouldGenerateSchemaFreeCamelCaseIncludeNames() throws Exception {
        Table auditTrail = table("pep_schema.audit_trail");

        LiquibaseGenerator generator = new LiquibaseGenerator();

        generator.generateLiquibaseFiles(
                tempDir.toString(),
                List.of(auditTrail),
                true
        );

        Path mainXml = tempDir.resolve("src/main/resources/db/migration/changelogs/v0.1.0/main.xml");
        String content = Files.readString(mainXml);

        assertTrue(
                content.contains("<include file=\"auditTrail.xml\" relativeToChangelogFile=\"true\" />"),
                "Expected schema-free camelCase include file name"
        );
        assertFalse(
                content.contains("pep_schema.audit_trail.xml"),
                "Schema-qualified snake_case include file name must not appear"
        );
    }

    @Test
    void shouldGenerateMasterXmlPointingToVersionMain() throws Exception {
        LiquibaseGenerator generator = new LiquibaseGenerator();

        generator.generateLiquibaseFiles(tempDir.toString(), List.of(), true);

        Path masterXml = tempDir.resolve("src/main/resources/db/migration/changelog-master.xml");
        String content = Files.readString(masterXml);

        assertTrue(
                content.contains("<include file=\"changelogs/v0.1.0/main.xml\" relativeToChangelogFile=\"true\" />")
        );
    }

    private Table table(String tableName, Column... columns) {
        Table table = new Table();
        table.setName(tableName);
        table.setColumns(new ArrayList<>(List.of(columns)));
        return table;
    }

    private Column foreignKeyColumn(String columnName, String referencedTable, String referencedColumn) {
        Column column = new Column();
        column.setName(columnName);
        column.setForeignKey(true);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        return column;
    }
}