package com.sqldomaingen;

import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.model.Column;
import com.sqldomaingen.model.Table;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class EntityGeneratorTest {

    private EntityGenerator entityGenerator;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        entityGenerator = new EntityGenerator();
        log.info(" Setting up EntityGeneratorTest...");
    }

    @Test
    void testGenerateEntityWithOneToMany() throws IOException {
        log.info("Running testGenerateEntityWithOneToMany...");

        Table customers = new Table();
        customers.setName("Customers");

        Table orders = new Table();
        orders.setName("Orders");

        Column customerId = new Column();
        customerId.setName("id");
        customerId.setSqlType("INT");
        customerId.setJavaType("Long");
        customerId.setPrimaryKey(true);
        customerId.setNullable(false);
        customers.setColumns(new ArrayList<>(List.of(customerId)));

        Column orderId = new Column();
        orderId.setName("id");
        orderId.setSqlType("INT");
        orderId.setJavaType("Long");
        orderId.setPrimaryKey(true);
        orderId.setNullable(false);

        Column customerIdFk = new Column();
        customerIdFk.setName("customer_id");
        customerIdFk.setSqlType("INT");
        customerIdFk.setJavaType("Long");
        customerIdFk.setPrimaryKey(false);
        customerIdFk.setForeignKey(true);
        customerIdFk.setNullable(false);
        customerIdFk.setReferencedTable("Customers");
        customerIdFk.setReferencedColumn("id");

        orders.setColumns(new ArrayList<>(List.of(orderId, customerIdFk)));

        entityGenerator.generate(
                List.of(customers, orders),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path customersFile = findGeneratedFile("Customers.java");
        Path ordersFile = findGeneratedFile("Orders.java");

        assertTrue(Files.exists(customersFile), "Customers.java should be generated.");
        assertTrue(Files.exists(ordersFile), "Orders.java should be generated.");

        String customersContent = Files.readString(customersFile);
        String ordersContent = Files.readString(ordersFile);

        assertFalse(customersContent.contains("@OneToMany"),
                "Customers.java should not contain inverse @OneToMany in current generator behavior.");

        assertTrue(ordersContent.contains("@ManyToOne(fetch = FetchType.LAZY)"),
                "Orders.java should contain owning @ManyToOne relation.");
        assertTrue(ordersContent.contains("@JoinColumn(name = \"customer_id\", nullable = false)"),
                "Orders.java should contain correct JoinColumn for customer_id.");
        assertTrue(ordersContent.contains("private Customers customer;"),
                "Orders.java should contain relation field 'customer'.");
    }

    @Test
    void testGenerateDepartmentEntity_WithUuidPk_AndFields() throws IOException {
        log.info("Running testGenerateDepartmentEntity_WithUuidPk_AndFields...");

        String packageName = "com.example.entities";

        Table table = new Table();
        table.setName("department");

        Column departmentUuid = new Column();
        departmentUuid.setName("department_uuid");
        departmentUuid.setFieldName("departmentUuid");
        departmentUuid.setSqlType("UUID");
        departmentUuid.setJavaType("java.util.UUID");
        departmentUuid.setPrimaryKey(true);
        departmentUuid.setNullable(false);

        Column departmentId = new Column();
        departmentId.setName("department_id");
        departmentId.setFieldName("departmentId");
        departmentId.setSqlType("SERIAL");
        departmentId.setJavaType("Integer");
        departmentId.setPrimaryKey(false);
        departmentId.setNullable(true);

        Column name = new Column();
        name.setName("name");
        name.setFieldName("name");
        name.setSqlType("VARCHAR(100)");
        name.setJavaType("String");
        name.setNullable(false);
        name.setLength(100);

        Column description = new Column();
        description.setName("description");
        description.setFieldName("description");
        description.setSqlType("TEXT");
        description.setJavaType("String");
        description.setNullable(true);

        Column parentDeptId = new Column();
        parentDeptId.setName("parent_dept_id");
        parentDeptId.setFieldName("parentDeptId");
        parentDeptId.setSqlType("INT");
        parentDeptId.setJavaType("Integer");
        parentDeptId.setNullable(true);

        Column date = new Column();
        date.setName("date");
        date.setFieldName("date");
        date.setSqlType("DATE");
        date.setJavaType("java.time.LocalDate");
        date.setNullable(true);

        Column createdAt = new Column();
        createdAt.setName("created_at");
        createdAt.setFieldName("createdAt");
        createdAt.setSqlType("TIMESTAMP");
        createdAt.setJavaType("java.time.LocalDateTime");
        createdAt.setNullable(true);
        createdAt.setDefaultValue("CURRENT_TIMESTAMP");

        Column updatedAt = new Column();
        updatedAt.setName("updated_at");
        updatedAt.setFieldName("updatedAt");
        updatedAt.setSqlType("TIMESTAMP");
        updatedAt.setJavaType("java.time.LocalDateTime");
        updatedAt.setNullable(true);
        updatedAt.setDefaultValue("CURRENT_TIMESTAMP");

        Column isActive = new Column();
        isActive.setName("is_active");
        isActive.setFieldName("isActive");
        isActive.setSqlType("BOOLEAN");
        isActive.setJavaType("Boolean");
        isActive.setNullable(false);
        isActive.setDefaultValue("TRUE");

        Column budget = new Column();
        budget.setName("budget");
        budget.setFieldName("budget");
        budget.setSqlType("NUMERIC(12,2)");
        budget.setJavaType("java.math.BigDecimal");
        budget.setNullable(true);
        budget.setPrecision(12);
        budget.setScale(2);

        Column headcount = new Column();
        headcount.setName("headcount");
        headcount.setFieldName("headcount");
        headcount.setSqlType("SMALLINT");
        headcount.setJavaType("Short");
        headcount.setNullable(true);

        Column phone = new Column();
        phone.setName("phone");
        phone.setFieldName("phone");
        phone.setSqlType("VARCHAR(20)");
        phone.setJavaType("String");
        phone.setNullable(true);
        phone.setLength(20);

        Column websiteUrl = new Column();
        websiteUrl.setName("website_url");
        websiteUrl.setFieldName("websiteUrl");
        websiteUrl.setSqlType("TEXT");
        websiteUrl.setJavaType("String");
        websiteUrl.setNullable(true);

        Column attachment = new Column();
        attachment.setName("attachment");
        attachment.setFieldName("attachment");
        attachment.setSqlType("BYTEA");
        attachment.setJavaType("byte[]");
        attachment.setNullable(true);

        Column shiftStart = new Column();
        shiftStart.setName("shift_start");
        shiftStart.setFieldName("shiftStart");
        shiftStart.setSqlType("TIME");
        shiftStart.setJavaType("java.time.LocalTime");
        shiftStart.setNullable(true);

        table.setColumns(List.of(
                departmentUuid,
                departmentId,
                name,
                description,
                parentDeptId,
                date,
                createdAt,
                updatedAt,
                isActive,
                budget,
                headcount,
                phone,
                websiteUrl,
                attachment,
                shiftStart
        ));

        entityGenerator.generate(
                List.of(table),
                tempDir.toString(),
                packageName,
                true,
                false
        );

        Path generatedFile = findGeneratedFile("Department.java");
        assertTrue(Files.exists(generatedFile), "Department.java should be generated.");

        String content = Files.readString(generatedFile);
        log.debug("Generated Department.java:\n{}", content);

        assertTrue(content.contains("public class Department"), "Class name not generated correctly");
        assertTrue(content.contains("@Entity"), "@Entity annotation missing");
        assertTrue(content.contains("private UUID departmentUuid"), "UUID PK field not generated correctly");
        assertTrue(content.contains("private Integer departmentId"), "departmentId field not generated correctly");
        assertTrue(content.contains("private String name"), "name field not generated correctly");
        assertTrue(content.contains("private String description"), "description field not generated correctly");
        assertTrue(content.contains("private Integer parentDeptId"), "parentDeptId field not generated correctly");
        assertTrue(content.contains("private LocalDate date"), "date field not generated correctly");
        assertTrue(content.contains("private LocalDateTime createdAt"), "createdAt field not generated correctly");
        assertTrue(content.contains("private LocalDateTime updatedAt"), "updatedAt field not generated correctly");
        assertTrue(content.contains("private Boolean isActive"), "isActive field not generated correctly");
        assertTrue(content.contains("private BigDecimal budget"), "budget field not generated correctly");
        assertTrue(content.contains("private Short headcount"), "headcount field not generated correctly");
        assertTrue(content.contains("private String phone"), "phone field not generated correctly");
        assertTrue(content.contains("private String websiteUrl"), "websiteUrl field not generated correctly");
        assertTrue(content.contains("private byte[] attachment"), "attachment field not generated correctly");
        assertTrue(content.contains("private LocalTime shiftStart"), "shiftStart field not generated correctly");

        assertFalse(content.contains("@Builder.Default"), "Generator must not add @Builder.Default");
        assertFalse(content.contains("optional = false"), "Generator must not add optional=false");
        assertFalse(content.contains("referencedColumnName = \"id\""),
                "Generator must not add referencedColumnName=\"id\"");
    }




    @Test
    void testNoDuplicateRelationshipFields() throws IOException {
        log.info("Running testNoDuplicateRelationshipFields...");

        Table department = new Table();
        department.setName("Department");

        Column id = new Column();
        id.setName("id");
        id.setFieldName("id");
        id.setSqlType("INT");
        id.setJavaType("Long");
        id.setPrimaryKey(true);
        id.setNullable(false);

        Column parentId = new Column();
        parentId.setName("parent_id");
        parentId.setFieldName("parentId");
        parentId.setSqlType("INT");
        parentId.setJavaType("Long");
        parentId.setForeignKey(true);
        parentId.setNullable(true);
        parentId.setReferencedTable("Department");
        parentId.setReferencedColumn("id");

        department.setColumns(new ArrayList<>(List.of(id, parentId)));

        entityGenerator.generate(
                List.of(department),
                tempDir.toString(),
                "com.example.entities",
                true,
                false
        );

        Path generatedFile = findGeneratedFile("Department.java");
        assertTrue(Files.exists(generatedFile),
                "Department.java file should be generated.");

        String content = Files.readString(generatedFile);

        long fieldCount = content.lines()
                .filter(line -> line.contains("private Department parent"))
                .count();

        assertEquals(1, fieldCount,
                "There must be exactly one 'parent' field for self-referencing relationship.");
    }

    @Test
    void testGenerateCompositePkJoinTable_GeneratesExternalPkAndMapsId() throws IOException {
        Table businessLocation = new Table();
        businessLocation.setName("pep_schema.business_location");

        Column businessLocationPk = new Column();
        businessLocationPk.setName("id");
        businessLocationPk.setFieldName("id");
        businessLocationPk.setSqlType("uuid");
        businessLocationPk.setJavaType("java.util.UUID");
        businessLocationPk.setPrimaryKey(true);
        businessLocationPk.setNullable(false);

        businessLocation.setColumns(new ArrayList<>(List.of(businessLocationPk)));

        Table languages = new Table();
        languages.setName("pep_schema.languages");

        Column languagesPk = new Column();
        languagesPk.setName("id");
        languagesPk.setFieldName("id");
        languagesPk.setSqlType("uuid");
        languagesPk.setJavaType("java.util.UUID");
        languagesPk.setPrimaryKey(true);
        languagesPk.setNullable(false);

        languages.setColumns(new ArrayList<>(List.of(languagesPk)));

        Table joinTable = new Table();
        joinTable.setName("pep_schema.business_location_i18n");

        Column description = new Column();
        description.setName("description");
        description.setFieldName("description");
        description.setSqlType("varchar");
        description.setJavaType("java.lang.String");
        description.setNullable(false);
        description.setLength(1000);

        Column code = new Column();
        code.setName("code");
        code.setFieldName("code");
        code.setSqlType("varchar");
        code.setJavaType("java.lang.String");
        code.setNullable(false);
        code.setLength(255);

        Column recdeleted = new Column();
        recdeleted.setName("recdeleted");
        recdeleted.setFieldName("recdeleted");
        recdeleted.setSqlType("bool");
        recdeleted.setJavaType("java.lang.Boolean");
        recdeleted.setNullable(false);
        recdeleted.setDefaultValue("false");

        Column businessLocationId = new Column();
        businessLocationId.setName("business_location_id");
        businessLocationId.setFieldName("businessLocationId");
        businessLocationId.setSqlType("uuid");
        businessLocationId.setJavaType("java.util.UUID");
        businessLocationId.setPrimaryKey(true);
        businessLocationId.setForeignKey(true);
        businessLocationId.setNullable(false);
        businessLocationId.setReferencedTable("pep_schema.business_location");
        businessLocationId.setReferencedColumn("id");

        Column languageId = new Column();
        languageId.setName("language_id");
        languageId.setFieldName("languageId");
        languageId.setSqlType("uuid");
        languageId.setJavaType("java.util.UUID");
        languageId.setPrimaryKey(true);
        languageId.setForeignKey(true);
        languageId.setNullable(false);
        languageId.setReferencedTable("pep_schema.languages");
        languageId.setReferencedColumn("id");

        joinTable.setColumns(new ArrayList<>(List.of(
                description, code, recdeleted, businessLocationId, languageId
        )));

        entityGenerator.generate(
                List.of(businessLocation, languages, joinTable),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("BusinessLocationI18n.java");
        Path pkFile = findGeneratedFile("BusinessLocationI18nKey.java");

        assertTrue(Files.exists(entityFile), "BusinessLocationI18n.java should be generated.");
        assertTrue(Files.exists(pkFile), "BusinessLocationI18nKey.java should be generated.");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);

        assertTrue(entityContent.contains("public class BusinessLocationI18n"),
                "Expected entity class name BusinessLocationI18n.");
        assertTrue(entityContent.contains("@Table(name = \"business_location_i18n\")"),
                "Expected correct @Table annotation.");
        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId for composite PK join entity.");
        assertTrue(entityContent.contains("private BusinessLocationI18nKey id;"),
                "Expected external PK type field.");

        assertTrue(entityContent.contains("@MapsId(\"businessLocationId\")"),
                "Expected @MapsId for business_location_id.");
        assertTrue(entityContent.contains("private BusinessLocation businessLocation;"),
                "Expected relation field to BusinessLocation.");

        assertTrue(entityContent.contains("@MapsId(\"languageId\")"),
                "Expected @MapsId for language_id.");
        assertTrue(entityContent.contains("private Languages language;"),
                "Expected relation field to Languages.");

        assertTrue(entityContent.contains("private String description;"),
                "Expected normal column field: description.");
        assertTrue(entityContent.contains("private String code;"),
                "Expected normal column field: code.");
        assertTrue(entityContent.contains("private Boolean recdeleted"),
                "Expected recdeleted field.");
        assertFalse(entityContent.contains("private Boolean recdeleted = false;"),
                "Generator should not assign inline default value to recdeleted.");

        assertFalse(entityContent.contains("public static class Id"),
                "Entity should not contain nested Id class anymore.");
        assertFalse(entityContent.contains("@Embeddable"),
                "Entity file should not contain @Embeddable; PK is external now.");
        assertFalse(entityContent.contains("@ManyToMany("),
                "Composite join entity should not generate @ManyToMany.");
        assertFalse(entityContent.contains("@OneToMany("),
                "Composite join entity should not generate inverse collections on itself.");

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable on external PK class.");
        assertTrue(pkContent.contains("public class BusinessLocationI18nKey implements Serializable"),
                "Expected external PK class name BusinessLocationI18nKey.");
        assertTrue(pkContent.contains("private UUID businessLocationId;"),
                "Expected businessLocationId in PK class.");
        assertTrue(pkContent.contains("private UUID languageId;"),
                "Expected languageId in PK class.");
    }


    @Test
    void testGenerateEntityWithOneToOne_UsesTempDirAndGeneratesBothSides() throws IOException {
        Table users = new Table();
        users.setName("Users");

        Column userId = new Column();
        userId.setName("id");
        userId.setSqlType("INT");
        userId.setJavaType("Long");
        userId.setPrimaryKey(true);
        userId.setNullable(false);
        users.setColumns(new ArrayList<>(List.of(userId)));

        Table userDetails = new Table();
        userDetails.setName("UserDetails");

        Column detailsId = new Column();
        detailsId.setName("id");
        detailsId.setSqlType("INT");
        detailsId.setJavaType("Long");
        detailsId.setPrimaryKey(true);
        detailsId.setNullable(false);

        Column userIdFk = new Column();
        userIdFk.setName("user_id");
        userIdFk.setSqlType("INT");
        userIdFk.setJavaType("Long");
        userIdFk.setForeignKey(true);
        userIdFk.setNullable(false);
        userIdFk.setUnique(true);
        userIdFk.setReferencedTable("Users");
        userIdFk.setReferencedColumn("id");

        userDetails.setColumns(new ArrayList<>(List.of(detailsId, userIdFk)));

        entityGenerator.generate(
                List.of(users, userDetails),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path userDetailsFile = findGeneratedFile("UserDetails.java");
        Path usersFile = findGeneratedFile("Users.java");

        assertTrue(Files.exists(userDetailsFile), "UserDetails.java should be generated.");
        assertTrue(Files.exists(usersFile), "Users.java should be generated.");

        String userDetailsContent = Files.readString(userDetailsFile);
        String usersContent = Files.readString(usersFile);

        assertTrue(userDetailsContent.contains("@OneToOne"),
                "Expected owning @OneToOne on UserDetails.");
        assertTrue(userDetailsContent.contains("@JoinColumn(name = \"user_id\", nullable = false, unique = true)"),
                "Expected correct @JoinColumn for unique FK.");
        assertTrue(userDetailsContent.contains("private Users user;"),
                "Expected owning relation field 'user'.");
        assertFalse(userDetailsContent.contains("mappedBy"),
                "Owning side must not contain mappedBy.");

        assertTrue(usersContent.contains("@OneToOne(mappedBy = \"user\", fetch = FetchType.LAZY)"),
                "Expected inverse @OneToOne on Users.");
        assertTrue(usersContent.contains("private UserDetails userDetails;"),
                "Expected inverse field userDetails on Users.");
    }

    @Test
    void testGenerateCompanyProfessionEntity_WithTwoManyToOneAndNoManyToMany() throws IOException {
        Table company = new Table();
        company.setName("pep_schema.company");

        Column companyId = new Column();
        companyId.setName("id");
        companyId.setFieldName("id");
        companyId.setSqlType("uuid");
        companyId.setJavaType("java.util.UUID");
        companyId.setPrimaryKey(true);
        companyId.setNullable(false);
        company.setColumns(new ArrayList<>(List.of(companyId)));

        Table profession = new Table();
        profession.setName("pep_schema.profession");

        Column professionId = new Column();
        professionId.setName("id");
        professionId.setFieldName("id");
        professionId.setSqlType("uuid");
        professionId.setJavaType("java.util.UUID");
        professionId.setPrimaryKey(true);
        professionId.setNullable(false);
        profession.setColumns(new ArrayList<>(List.of(professionId)));

        Table companyProfession = new Table();
        companyProfession.setName("pep_schema.company_profession");

        Column id = new Column();
        id.setName("id");
        id.setFieldName("id");
        id.setSqlType("uuid");
        id.setJavaType("java.util.UUID");
        id.setPrimaryKey(true);
        id.setNullable(false);

        Column companyFk = new Column();
        companyFk.setName("company_id");
        companyFk.setFieldName("companyId");
        companyFk.setSqlType("uuid");
        companyFk.setJavaType("java.util.UUID");
        companyFk.setForeignKey(true);
        companyFk.setNullable(false);
        companyFk.setReferencedTable("pep_schema.company");
        companyFk.setReferencedColumn("id");

        Column professionFk = new Column();
        professionFk.setName("profession_id");
        professionFk.setFieldName("professionId");
        professionFk.setSqlType("uuid");
        professionFk.setJavaType("java.util.UUID");
        professionFk.setForeignKey(true);
        professionFk.setNullable(false);
        professionFk.setReferencedTable("pep_schema.profession");
        professionFk.setReferencedColumn("id");

        Column notes = new Column();
        notes.setName("notes");
        notes.setFieldName("notes");
        notes.setSqlType("varchar");
        notes.setJavaType("String");
        notes.setNullable(true);

        companyProfession.setColumns(new ArrayList<>(List.of(id, companyFk, professionFk, notes)));

        entityGenerator.generate(
                List.of(company, profession, companyProfession),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("CompanyProfession.java");
        assertTrue(Files.exists(entityFile), "CompanyProfession.java should be generated.");

        String content = Files.readString(entityFile);

        assertTrue(content.contains("public class CompanyProfession"),
                "Expected entity class CompanyProfession.");
        assertTrue(content.contains("@ManyToOne(fetch = FetchType.LAZY)"),
                "Expected @ManyToOne annotations.");
        assertTrue(content.contains("@JoinColumn(name = \"company_id\", nullable = false)"),
                "Expected JoinColumn for company_id.");
        assertTrue(content.contains("private Company company;"),
                "Expected relation field to Company.");
        assertTrue(content.contains("@JoinColumn(name = \"profession_id\", nullable = false)"),
                "Expected JoinColumn for profession_id.");
        assertTrue(content.contains("private Profession profession;"),
                "Expected relation field to Profession.");
        assertTrue(content.contains("private String notes;"),
                "Expected normal payload column field.");
        assertFalse(content.contains("@ManyToMany("),
                "company_profession must remain join entity, not synthetic many-to-many.");
    }

    @Test
    void testGenerateCompanyProfileLanguageEntity_GeneratesExternalPkAndMapsId() throws IOException {
        Table companyProfile = new Table();
        companyProfile.setName("pep_schema.company_profile");

        Column companyProfileId = new Column();
        companyProfileId.setName("id");
        companyProfileId.setFieldName("id");
        companyProfileId.setSqlType("uuid");
        companyProfileId.setJavaType("java.util.UUID");
        companyProfileId.setPrimaryKey(true);
        companyProfileId.setNullable(false);
        companyProfile.setColumns(new ArrayList<>(List.of(companyProfileId)));

        Table language = new Table();
        language.setName("pep_schema.languages");

        Column languageId = new Column();
        languageId.setName("id");
        languageId.setFieldName("id");
        languageId.setSqlType("uuid");
        languageId.setJavaType("java.util.UUID");
        languageId.setPrimaryKey(true);
        languageId.setNullable(false);
        language.setColumns(new ArrayList<>(List.of(languageId)));

        Table companyProfileLanguage = new Table();
        companyProfileLanguage.setName("pep_schema.company_profile_language");

        Column companyProfileFk = new Column();
        companyProfileFk.setName("company_profile_id");
        companyProfileFk.setFieldName("companyProfileId");
        companyProfileFk.setSqlType("uuid");
        companyProfileFk.setJavaType("java.util.UUID");
        companyProfileFk.setPrimaryKey(true);
        companyProfileFk.setForeignKey(true);
        companyProfileFk.setNullable(false);
        companyProfileFk.setReferencedTable("pep_schema.company_profile");
        companyProfileFk.setReferencedColumn("id");

        Column languageFk = new Column();
        languageFk.setName("language_id");
        languageFk.setFieldName("languageId");
        languageFk.setSqlType("uuid");
        languageFk.setJavaType("java.util.UUID");
        languageFk.setPrimaryKey(true);
        languageFk.setForeignKey(true);
        languageFk.setNullable(false);
        languageFk.setReferencedTable("pep_schema.languages");
        languageFk.setReferencedColumn("id");

        companyProfileLanguage.setColumns(new ArrayList<>(List.of(companyProfileFk, languageFk)));

        entityGenerator.generate(
                List.of(companyProfile, language, companyProfileLanguage),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("CompanyProfileLanguage.java");
        Path pkFile = findGeneratedFile("CompanyProfileLanguageKey.java");

        assertTrue(Files.exists(entityFile), "CompanyProfileLanguage.java should be generated.");
        assertTrue(Files.exists(pkFile), "CompanyProfileLanguageKey.java should be generated.");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);

        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId.");
        assertTrue(entityContent.contains("private CompanyProfileLanguageKey id;"),
                "Expected external PK field.");
        assertTrue(entityContent.contains("@MapsId(\"companyProfileId\")"),
                "Expected @MapsId for company_profile_id.");
        assertTrue(entityContent.contains("private CompanyProfile companyProfile;"),
                "Expected relation field to CompanyProfile.");
        assertTrue(entityContent.contains("@MapsId(\"languageId\")"),
                "Expected @MapsId for language_id.");
        assertTrue(entityContent.contains("private Languages language;"),
                "Expected relation field to Languages.");
        assertFalse(entityContent.contains("@ManyToMany("),
                "Association entity should not be generated as @ManyToMany here.");

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable PK class.");
        assertTrue(pkContent.contains("public class CompanyProfileLanguageKey implements Serializable"),
                "Expected PK class name CompanyProfileLanguageKey.");
        assertTrue(pkContent.contains("private UUID companyProfileId;"),
                "Expected companyProfileId field in PK class.");
        assertTrue(pkContent.contains("private UUID languageId;"),
                "Expected languageId field in PK class.");
    }


    @Test
    void testGenerateCompanyStatusViewRulesEntity_GeneratesExternalPkAndMapsId() throws IOException {
        Table companyStatus = new Table();
        companyStatus.setName("pep_schema.company_status");

        Column companyStatusId = new Column();
        companyStatusId.setName("id");
        companyStatusId.setFieldName("id");
        companyStatusId.setSqlType("uuid");
        companyStatusId.setJavaType("java.util.UUID");
        companyStatusId.setPrimaryKey(true);
        companyStatusId.setNullable(false);
        companyStatus.setColumns(new ArrayList<>(List.of(companyStatusId)));

        Table companyViewRules = new Table();
        companyViewRules.setName("pep_schema.company_view_rules");

        Column companyViewRulesId = new Column();
        companyViewRulesId.setName("id");
        companyViewRulesId.setFieldName("id");
        companyViewRulesId.setSqlType("uuid");
        companyViewRulesId.setJavaType("java.util.UUID");
        companyViewRulesId.setPrimaryKey(true);
        companyViewRulesId.setNullable(false);
        companyViewRules.setColumns(new ArrayList<>(List.of(companyViewRulesId)));

        Table companyStatusViewRules = new Table();
        companyStatusViewRules.setName("pep_schema.company_status_view_rules");

        Column companyStatusFk = new Column();
        companyStatusFk.setName("company_status_id");
        companyStatusFk.setFieldName("companyStatusId");
        companyStatusFk.setSqlType("uuid");
        companyStatusFk.setJavaType("java.util.UUID");
        companyStatusFk.setPrimaryKey(true);
        companyStatusFk.setForeignKey(true);
        companyStatusFk.setNullable(false);
        companyStatusFk.setReferencedTable("pep_schema.company_status");
        companyStatusFk.setReferencedColumn("id");

        Column companyViewRulesFk = new Column();
        companyViewRulesFk.setName("company_view_rules_id");
        companyViewRulesFk.setFieldName("companyViewRulesId");
        companyViewRulesFk.setSqlType("uuid");
        companyViewRulesFk.setJavaType("java.util.UUID");
        companyViewRulesFk.setPrimaryKey(true);
        companyViewRulesFk.setForeignKey(true);
        companyViewRulesFk.setNullable(false);
        companyViewRulesFk.setReferencedTable("pep_schema.company_view_rules");
        companyViewRulesFk.setReferencedColumn("id");

        Column excludeCompanies = new Column();
        excludeCompanies.setName("exclude_companies");
        excludeCompanies.setFieldName("excludeCompanies");
        excludeCompanies.setSqlType("bool");
        excludeCompanies.setJavaType("Boolean");
        excludeCompanies.setNullable(true);

        companyStatusViewRules.setColumns(new ArrayList<>(List.of(
                companyStatusFk,
                companyViewRulesFk,
                excludeCompanies
        )));

        entityGenerator.generate(
                List.of(companyStatus, companyViewRules, companyStatusViewRules),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("CompanyStatusViewRules.java");
        Path pkFile = findGeneratedFile("CompanyStatusViewRulesKey.java");

        assertTrue(Files.exists(entityFile), "CompanyStatusViewRules.java should be generated.");
        assertTrue(Files.exists(pkFile), "CompanyStatusViewRulesKey.java should be generated.");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);

        assertTrue(entityContent.contains("public class CompanyStatusViewRules"),
                "Expected entity class CompanyStatusViewRules.");
        assertTrue(entityContent.contains("@Table(name = \"company_status_view_rules\")"),
                "Expected correct @Table annotation.");
        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId.");
        assertTrue(entityContent.contains("private CompanyStatusViewRulesKey id;"),
                "Expected external PK field.");

        assertTrue(entityContent.contains("@MapsId(\"companyStatusId\")"),
                "Expected @MapsId for company_status_id.");
        assertTrue(entityContent.contains("private CompanyStatus companyStatus;"),
                "Expected relation field to CompanyStatus.");

        assertTrue(entityContent.contains("@MapsId(\"companyViewRulesId\")"),
                "Expected @MapsId for company_view_rules_id.");
        assertTrue(entityContent.contains("private CompanyViewRules companyViewRules;"),
                "Expected relation field to CompanyViewRules.");

        assertTrue(entityContent.contains("private Boolean excludeCompanies;"),
                "Expected normal payload column field.");

        assertFalse(entityContent.contains("@ManyToMany("),
                "company_status_view_rules must not be generated as synthetic many-to-many.");
        assertFalse(entityContent.contains("public static class Id"),
                "Entity should not contain nested Id class anymore.");

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable PK class.");
        assertTrue(pkContent.contains("public class CompanyStatusViewRulesKey implements Serializable"),
                "Expected PK class name CompanyStatusViewRulesKey.");
        assertTrue(pkContent.contains("private UUID companyStatusId;"),
                "Expected companyStatusId field in PK class.");
        assertTrue(pkContent.contains("private UUID companyViewRulesId;"),
                "Expected companyViewRulesId field in PK class.");
    }


    @Test
    void testGenerateCompanyProfessionSystemLinkEntity_GeneratesExternalPkAndMapsId() throws IOException {
        Table company = new Table();
        company.setName("pep_schema.company");

        Column companyId = new Column();
        companyId.setName("id");
        companyId.setFieldName("id");
        companyId.setSqlType("uuid");
        companyId.setJavaType("java.util.UUID");
        companyId.setPrimaryKey(true);
        companyId.setNullable(false);
        company.setColumns(new ArrayList<>(List.of(companyId)));

        Table professionSystem = new Table();
        professionSystem.setName("pep_schema.profession_system");

        Column professionSystemId = new Column();
        professionSystemId.setName("id");
        professionSystemId.setFieldName("id");
        professionSystemId.setSqlType("uuid");
        professionSystemId.setJavaType("java.util.UUID");
        professionSystemId.setPrimaryKey(true);
        professionSystemId.setNullable(false);
        professionSystem.setColumns(new ArrayList<>(List.of(professionSystemId)));

        Table companyProfessionSystemLink = new Table();
        companyProfessionSystemLink.setName("pep_schema.company_profession_system_link");

        Column companyFk = new Column();
        companyFk.setName("company_id");
        companyFk.setFieldName("companyId");
        companyFk.setSqlType("uuid");
        companyFk.setJavaType("java.util.UUID");
        companyFk.setPrimaryKey(true);
        companyFk.setForeignKey(true);
        companyFk.setNullable(false);
        companyFk.setReferencedTable("pep_schema.company");
        companyFk.setReferencedColumn("id");

        Column professionSystemFk = new Column();
        professionSystemFk.setName("profession_system_id");
        professionSystemFk.setFieldName("professionSystemId");
        professionSystemFk.setSqlType("uuid");
        professionSystemFk.setJavaType("java.util.UUID");
        professionSystemFk.setPrimaryKey(true);
        professionSystemFk.setForeignKey(true);
        professionSystemFk.setNullable(false);
        professionSystemFk.setReferencedTable("pep_schema.profession_system");
        professionSystemFk.setReferencedColumn("id");

        companyProfessionSystemLink.setColumns(new ArrayList<>(List.of(companyFk, professionSystemFk)));

        entityGenerator.generate(
                List.of(company, professionSystem, companyProfessionSystemLink),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("CompanyProfessionSystemLink.java");
        Path pkFile = findGeneratedFile("CompanyProfessionSystemLinkKey.java");

        assertTrue(Files.exists(entityFile), "CompanyProfessionSystemLink.java should be generated.");
        assertTrue(Files.exists(pkFile), "CompanyProfessionSystemLinkKey.java should be generated.");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);

        assertTrue(entityContent.contains("public class CompanyProfessionSystemLink"),
                "Expected entity class CompanyProfessionSystemLink.");
        assertTrue(entityContent.contains("@Table(name = \"company_profession_system_link\")"),
                "Expected correct @Table annotation.");
        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId.");
        assertTrue(entityContent.contains("private CompanyProfessionSystemLinkKey id;"),
                "Expected external PK field.");

        assertTrue(entityContent.contains("@MapsId(\"companyId\")"),
                "Expected @MapsId for company_id.");
        assertTrue(entityContent.contains("private Company company;"),
                "Expected relation field to Company.");

        assertTrue(entityContent.contains("@MapsId(\"professionSystemId\")"),
                "Expected @MapsId for profession_system_id.");
        assertTrue(entityContent.contains("private ProfessionSystem professionSystem;"),
                "Expected relation field to ProfessionSystem.");

        assertFalse(entityContent.contains("@ManyToMany("),
                "company_profession_system_link must not be generated as synthetic many-to-many.");
        assertFalse(entityContent.contains("public static class Id"),
                "Entity should not contain nested Id class anymore.");

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable PK class.");
        assertTrue(pkContent.contains("public class CompanyProfessionSystemLinkKey implements Serializable"),
                "Expected PK class name CompanyProfessionSystemLinkKey.");
        assertTrue(pkContent.contains("private UUID companyId;"),
                "Expected companyId field in PK class.");
        assertTrue(pkContent.contains("private UUID professionSystemId;"),
                "Expected professionSystemId field in PK class.");
    }


    @Test
    void testGenerateEntity_WithNumericPrecisionAndScale_ShouldRenderColumnMetadata() throws IOException {
        Table table = new Table();
        table.setName("income_gemi_payment");

        Column id = new Column();
        id.setName("id");
        id.setFieldName("id");
        id.setSqlType("UUID");
        id.setJavaType("java.util.UUID");
        id.setPrimaryKey(true);
        id.setNullable(false);
        id.setDefaultValue("gen_random_uuid()");

        Column chamberAmount = new Column();
        chamberAmount.setName("chamber_amount");
        chamberAmount.setFieldName("chamberAmount");
        chamberAmount.setSqlType("NUMERIC(19,2)");
        chamberAmount.setJavaType("java.math.BigDecimal");
        chamberAmount.setNullable(true);
        chamberAmount.setPrecision(19);
        chamberAmount.setScale(2);

        Column totalAmountPaid = new Column();
        totalAmountPaid.setName("total_amount_paid");
        totalAmountPaid.setFieldName("totalAmountPaid");
        totalAmountPaid.setSqlType("NUMERIC(19,2)");
        totalAmountPaid.setJavaType("java.math.BigDecimal");
        totalAmountPaid.setNullable(true);
        totalAmountPaid.setPrecision(19);
        totalAmountPaid.setScale(2);

        Column remittanceAmount = new Column();
        remittanceAmount.setName("remittance_amount");
        remittanceAmount.setFieldName("remittanceAmount");
        remittanceAmount.setSqlType("NUMERIC(19,2)");
        remittanceAmount.setJavaType("java.math.BigDecimal");
        remittanceAmount.setNullable(true);
        remittanceAmount.setPrecision(19);
        remittanceAmount.setScale(2);

        table.setColumns(new ArrayList<>(List.of(
                id,
                chamberAmount,
                totalAmountPaid,
                remittanceAmount
        )));

        entityGenerator.generate(
                List.of(table),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path generatedFile = findGeneratedFile("IncomeGemiPayment.java");
        assertTrue(Files.exists(generatedFile), "IncomeGemiPayment.java should be generated.");

        String content = Files.readString(generatedFile);

        assertTrue(
                content.contains("public class IncomeGemiPayment"),
                "Expected entity class IncomeGemiPayment."
        );

        assertTrue(
                content.contains("@Column(name = \"chamber_amount\", precision = 19, scale = 2)"),
                "Expected precision/scale metadata for chamber_amount."
        );
        assertTrue(
                content.contains("private BigDecimal chamberAmount;"),
                "Expected BigDecimal field for chamber_amount."
        );

        assertTrue(
                content.contains("@Column(name = \"total_amount_paid\", precision = 19, scale = 2)"),
                "Expected precision/scale metadata for total_amount_paid."
        );
        assertTrue(
                content.contains("private BigDecimal totalAmountPaid;"),
                "Expected BigDecimal field for total_amount_paid."
        );

        assertTrue(
                content.contains("@Column(name = \"remittance_amount\", precision = 19, scale = 2)"),
                "Expected precision/scale metadata for remittance_amount."
        );
        assertTrue(
                content.contains("private BigDecimal remittanceAmount;"),
                "Expected BigDecimal field for remittance_amount."
        );
    }

    
    private Path findGeneratedFile(String fileName) throws IOException {
        try (var walk = Files.walk(tempDir)) {
            return walk
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Could not find generated file: " + fileName));
        }
    }
}
