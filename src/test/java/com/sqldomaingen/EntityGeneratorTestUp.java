package com.sqldomaingen;

import com.sqldomaingen.generator.EntityGenerator;
import com.sqldomaingen.generator.RelationshipResolver;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class EntityGeneratorTestUp {

    private EntityGenerator entityGenerator;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        entityGenerator = new EntityGenerator();
        log.info("Setting up EntityGeneratorTest...");
    }

    @Test
    void testGenerateEntityWithOneToManyFromBothSides() {
        Table customers = new Table();
        customers.setName("Customers");
        customers.setColumns(new ArrayList<>(List.of(createLongPrimaryKeyColumn("id"))));

        Table orders = new Table();
        orders.setName("Orders");
        orders.setColumns(new ArrayList<>(List.of(
                createLongPrimaryKeyColumn("id"),
                createRequiredLongForeignKeyColumn("customer_id", "Customers", "id")
        )));

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Customers", customers);
        tableMap.put("Orders", orders);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        String customersContent = entityGenerator.createEntityContent(customers, "com.example.entities", false);
        String ordersContent = entityGenerator.createEntityContent(orders, "com.example.entities", false);

        assertTrue(customersContent.contains("@OneToMany(mappedBy = \"customer\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse @OneToMany on Customers.");
        assertTrue(customersContent.contains("private List<Orders>"),
                "Expected inverse collection of Orders on Customers.");

        assertTrue(ordersContent.contains("@ManyToOne(fetch = FetchType.LAZY)"),
                "Expected owning @ManyToOne on Orders.");
        assertTrue(ordersContent.contains("@JoinColumn(name = \"customer_id\", nullable = false)"),
                "Expected owning JoinColumn on Orders.");
        assertTrue(ordersContent.contains("private Customers customer;"),
                "Expected owning relation field on Orders.");
        assertFalse(ordersContent.contains("@OneToMany("),
                "Orders must not expose inverse @OneToMany for the same relationship.");
    }

    @Test
    void testGenerateDepartmentEntity_WithUuidPk_AndFields() throws IOException {
        String packageName = "com.example.entities";

        Table table = new Table();
        table.setName("department");

        Column departmentUuid = new Column();
        departmentUuid.setName("department_uuid");
        departmentUuid.setSqlType("UUID");
        departmentUuid.setJavaType("java.util.UUID");
        departmentUuid.setPrimaryKey(true);
        departmentUuid.setNullable(false);

        Column departmentId = new Column();
        departmentId.setName("department_id");
        departmentId.setSqlType("SERIAL");
        departmentId.setJavaType("Integer");
        departmentId.setPrimaryKey(false);
        departmentId.setNullable(true);

        Column name = new Column();
        name.setName("name");
        name.setSqlType("VARCHAR(100)");
        name.setJavaType("String");
        name.setNullable(false);

        Column description = new Column();
        description.setName("description");
        description.setSqlType("TEXT");
        description.setJavaType("String");
        description.setNullable(true);

        Column parentDeptId = new Column();
        parentDeptId.setName("parent_dept_id");
        parentDeptId.setSqlType("INT");
        parentDeptId.setJavaType("Integer");
        parentDeptId.setNullable(true);

        Column date = new Column();
        date.setName("date");
        date.setSqlType("DATE");
        date.setJavaType("java.time.LocalDate");
        date.setNullable(true);

        Column createdAt = new Column();
        createdAt.setName("created_at");
        createdAt.setSqlType("TIMESTAMP");
        createdAt.setJavaType("java.time.LocalDateTime");
        createdAt.setNullable(true);
        createdAt.setDefaultValue("CURRENT_TIMESTAMP");

        Column updatedAt = new Column();
        updatedAt.setName("updated_at");
        updatedAt.setSqlType("TIMESTAMP");
        updatedAt.setJavaType("java.time.LocalDateTime");
        updatedAt.setNullable(true);
        updatedAt.setDefaultValue("CURRENT_TIMESTAMP");

        Column isActive = new Column();
        isActive.setName("is_active");
        isActive.setSqlType("BOOLEAN");
        isActive.setJavaType("Boolean");
        isActive.setNullable(false);
        isActive.setDefaultValue("TRUE");

        Column budget = new Column();
        budget.setName("budget");
        budget.setSqlType("NUMERIC(12,2)");
        budget.setJavaType("java.math.BigDecimal");
        budget.setNullable(true);

        Column headcount = new Column();
        headcount.setName("headcount");
        headcount.setSqlType("SMALLINT");
        headcount.setJavaType("Short");
        headcount.setNullable(true);

        Column phone = new Column();
        phone.setName("phone");
        phone.setSqlType("VARCHAR(20)");
        phone.setJavaType("String");
        phone.setNullable(true);

        Column websiteUrl = new Column();
        websiteUrl.setName("website_url");
        websiteUrl.setSqlType("TEXT");
        websiteUrl.setJavaType("String");
        websiteUrl.setNullable(true);

        Column attachment = new Column();
        attachment.setName("attachment");
        attachment.setSqlType("BYTEA");
        attachment.setJavaType("byte[]");
        attachment.setNullable(true);

        Column shiftStart = new Column();
        shiftStart.setName("shift_start");
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

        entityGenerator.generate(List.of(table), tempDir.toString(), packageName, true, false);

        Path expectedFile = tempDir
                .resolve(packageName.replace('.', java.io.File.separatorChar))
                .resolve("Department.java");

        Path generatedFile;
        try (java.util.stream.Stream<Path> walk = Files.walk(tempDir)) {
            generatedFile = walk
                    .filter(path -> path.getFileName().toString().equals("Department.java"))
                    .findFirst()
                    .orElse(expectedFile);
        }

        assertTrue(Files.exists(generatedFile),
                "Department.java should be generated under the package folder. Expected: " + expectedFile);

        String content = Files.readString(generatedFile);

        assertTrue(content.contains("public class Department"), "Class name not generated correctly");
        assertTrue(content.contains("@Entity"), "@Entity annotation missing");
        assertTrue(content.contains("private UUID departmentUuid"), "UUID PK field not generated correctly");

        assertFalse(content.contains("@Builder.Default"), "Generator must not add @Builder.Default");
        assertFalse(content.contains("optional = false"), "Generator must not add optional=false");
        assertFalse(content.contains("referencedColumnName"), "Generator must not add referencedColumnName=\"id\"");
    }

    @Test
    void testGenerateDepartmentSelfReferenceFromBothSidesAndWithoutDuplicates() {
        Table department = new Table();
        department.setName("Department");
        department.setColumns(new ArrayList<>(List.of(
                createLongPrimaryKeyColumn("id"),
                createNullableLongForeignKeyColumn("parent_id", "Department", "id")
        )));

        Map<String, Table> tableMap = new HashMap<>();
        tableMap.put("Department", department);

        RelationshipResolver resolver = new RelationshipResolver(tableMap);
        resolver.resolveRelationshipsForAllTables();

        String content = entityGenerator.createEntityContent(department, "com.example.entities", false);

        assertTrue(content.contains("@ManyToOne(fetch = FetchType.LAZY)"),
                "Expected self-referencing owning @ManyToOne.");
        assertTrue(content.contains("private Department parent;"),
                "Expected self parent field.");
        assertTrue(content.contains("@OneToMany(mappedBy = \"parent\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse self @OneToMany with mappedBy='parent'.");
        assertTrue(content.contains("private List<Department>"),
                "Expected inverse self collection field.");

        long parentFieldCount = content.lines()
                .filter(line -> line.contains("private Department parent"))
                .count();

        assertEquals(1, parentFieldCount,
                "There must be exactly one self parent field.");
    }

    @Test
    void testGenerateCompositePkJoinTable_GeneratesExternalPkAndInverseCollectionsOnParents() throws IOException {
        Table businessLocation = createUuidPkTable("pep_schema.business_location");
        Table languages = createUuidPkTable("pep_schema.languages");

        Table joinTable = new Table();
        joinTable.setName("pep_schema.business_location_i18n");

        Column description = new Column();
        description.setName("description");
        description.setSqlType("varchar");
        description.setJavaType("java.lang.String");
        description.setNullable(false);
        description.setLength(1000);

        Column code = new Column();
        code.setName("code");
        code.setSqlType("varchar");
        code.setJavaType("java.lang.String");
        code.setNullable(false);
        code.setLength(255);

        Column recdeleted = new Column();
        recdeleted.setName("recdeleted");
        recdeleted.setSqlType("bool");
        recdeleted.setJavaType("java.lang.Boolean");
        recdeleted.setNullable(false);
        recdeleted.setDefaultValue("false");

        Column businessLocationId = createRequiredUuidForeignKeyColumn("business_location_id", "pep_schema.business_location", "id");
        businessLocationId.setPrimaryKey(true);

        Column languageId = createRequiredUuidForeignKeyColumn("language_id", "pep_schema.languages", "id");
        languageId.setPrimaryKey(true);

        joinTable.setColumns(new ArrayList<>(List.of(
                description,
                code,
                recdeleted,
                businessLocationId,
                languageId
        )));

        entityGenerator.generate(
                List.of(businessLocation, languages, joinTable),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("BusinessLocationI18n.java");
        Path pkFile = findGeneratedFile("BusinessLocationI18nPK.java");
        Path businessLocationFile = findGeneratedFile("BusinessLocation.java");
        Path languagesFile = findGeneratedFile("Languages.java");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);
        String businessLocationContent = Files.readString(businessLocationFile);
        String languagesContent = Files.readString(languagesFile);

        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId for composite PK join entity.");
        assertTrue(entityContent.contains("private BusinessLocationI18nPK id;"),
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
        assertTrue(entityContent.contains("private Boolean recdeleted = false;"),
                "Expected boolean default handling.");
        assertFalse(entityContent.contains("@ManyToMany("),
                "Composite join entity should not generate @ManyToMany.");
        assertFalse(entityContent.contains("@OneToMany("),
                "Join entity itself must not generate inverse parent collections.");

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable on external PK class.");
        assertTrue(pkContent.contains("public class BusinessLocationI18nPK implements Serializable"),
                "Expected external PK class name.");
        assertTrue(pkContent.contains("private UUID businessLocationId;"),
                "Expected businessLocationId in PK class.");
        assertTrue(pkContent.contains("private UUID languageId;"),
                "Expected languageId in PK class.");

        assertTrue(businessLocationContent.contains("@OneToMany(mappedBy = \"businessLocation\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on BusinessLocation.");
        assertTrue(businessLocationContent.contains("private List<BusinessLocationI18n>"),
                "Expected BusinessLocation inverse collection field.");

        assertTrue(languagesContent.contains("@OneToMany(mappedBy = \"language\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on Languages.");
        assertTrue(languagesContent.contains("private List<BusinessLocationI18n>"),
                "Expected Languages inverse collection field.");
    }

    @Test
    void testGenerateEntityWithOneToOne_UsesTempDirAndGeneratesBothSides() throws IOException {
        Table users = new Table();
        users.setName("Users");
        users.setColumns(new ArrayList<>(List.of(createLongPrimaryKeyColumn("id"))));

        Table userDetails = new Table();
        userDetails.setName("UserDetails");

        Column detailsId = createLongPrimaryKeyColumn("id");
        Column userIdFk = createRequiredLongForeignKeyColumn("user_id", "Users", "id");
        userIdFk.setUnique(true);

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
    void testGenerateCompanyProfessionEntity_WithTwoManyToOneAndInverseOneToManyOnParents() throws IOException {
        Table company = createUuidPkTable("pep_schema.company");
        Table profession = createUuidPkTable("pep_schema.profession");

        Table companyProfession = new Table();
        companyProfession.setName("pep_schema.company_profession");

        Column id = createUuidPrimaryKeyColumn("id");
        Column companyFk = createRequiredUuidForeignKeyColumn("company_id", "pep_schema.company", "id");
        Column professionFk = createRequiredUuidForeignKeyColumn("profession_id", "pep_schema.profession", "id");

        Column notes = new Column();
        notes.setName("notes");
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
        Path companyFile = findGeneratedFile("Company.java");
        Path professionFile = findGeneratedFile("Profession.java");

        String entityContent = Files.readString(entityFile);
        String companyContent = Files.readString(companyFile);
        String professionContent = Files.readString(professionFile);

        assertTrue(entityContent.contains("@ManyToOne(fetch = FetchType.LAZY)"),
                "Expected @ManyToOne annotations.");
        assertTrue(entityContent.contains("@JoinColumn(name = \"company_id\", nullable = false)"),
                "Expected JoinColumn for company_id.");
        assertTrue(entityContent.contains("private Company company;"),
                "Expected relation field to Company.");
        assertTrue(entityContent.contains("@JoinColumn(name = \"profession_id\", nullable = false)"),
                "Expected JoinColumn for profession_id.");
        assertTrue(entityContent.contains("private Profession profession;"),
                "Expected relation field to Profession.");
        assertTrue(entityContent.contains("private String notes;"),
                "Expected normal payload column field.");
        assertFalse(entityContent.contains("@ManyToMany("),
                "company_profession must remain join entity, not synthetic many-to-many.");

        assertTrue(companyContent.contains("@OneToMany(mappedBy = \"company\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse OneToMany on Company.");
        assertTrue(companyContent.contains("private List<CompanyProfession>"),
                "Expected Company inverse collection field.");

        assertTrue(professionContent.contains("@OneToMany(mappedBy = \"profession\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse OneToMany on Profession.");
        assertTrue(professionContent.contains("private List<CompanyProfession>"),
                "Expected Profession inverse collection field.");
    }

    @Test
    void testGenerateCompanyProfileLanguageEntity_GeneratesExternalPkAndInverseCollectionsOnParents() throws IOException {
        Table companyProfile = createUuidPkTable("pep_schema.company_profile");
        Table language = createUuidPkTable("pep_schema.languages");

        Table companyProfileLanguage = new Table();
        companyProfileLanguage.setName("pep_schema.company_profile_language");

        Column companyProfileFk = createRequiredUuidForeignKeyColumn("company_profile_id", "pep_schema.company_profile", "id");
        companyProfileFk.setPrimaryKey(true);

        Column languageFk = createRequiredUuidForeignKeyColumn("language_id", "pep_schema.languages", "id");
        languageFk.setPrimaryKey(true);

        companyProfileLanguage.setColumns(new ArrayList<>(List.of(companyProfileFk, languageFk)));

        entityGenerator.generate(
                List.of(companyProfile, language, companyProfileLanguage),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("CompanyProfileLanguage.java");
        Path pkFile = findGeneratedFile("CompanyProfileLanguagePK.java");
        Path companyProfileFile = findGeneratedFile("CompanyProfile.java");
        Path languagesFile = findGeneratedFile("Languages.java");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);
        String companyProfileContent = Files.readString(companyProfileFile);
        String languagesContent = Files.readString(languagesFile);

        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId.");
        assertTrue(entityContent.contains("private CompanyProfileLanguagePK id;"),
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
        assertTrue(pkContent.contains("public class CompanyProfileLanguagePK implements Serializable"),
                "Expected PK class name CompanyProfileLanguagePK.");
        assertTrue(pkContent.contains("private UUID companyProfileId;"),
                "Expected companyProfileId field in PK class.");
        assertTrue(pkContent.contains("private UUID languageId;"),
                "Expected languageId field in PK class.");

        assertTrue(companyProfileContent.contains("@OneToMany(mappedBy = \"companyProfile\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on CompanyProfile.");
        assertTrue(companyProfileContent.contains("private List<CompanyProfileLanguage>"),
                "Expected CompanyProfile inverse collection field.");

        assertTrue(languagesContent.contains("@OneToMany(mappedBy = \"language\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on Languages.");
        assertTrue(languagesContent.contains("private List<CompanyProfileLanguage>"),
                "Expected Languages inverse collection field.");
    }

    @Test
    void testGenerateCompanyStatusViewRulesEntity_GeneratesExternalPkAndInverseCollectionsOnParents() throws IOException {
        Table companyStatus = createUuidPkTable("pep_schema.company_status");
        Table companyViewRules = createUuidPkTable("pep_schema.company_view_rules");

        Table companyStatusViewRules = new Table();
        companyStatusViewRules.setName("pep_schema.company_status_view_rules");

        Column companyStatusFk = createRequiredUuidForeignKeyColumn("company_status_id", "pep_schema.company_status", "id");
        companyStatusFk.setPrimaryKey(true);

        Column companyViewRulesFk = createRequiredUuidForeignKeyColumn("company_view_rules_id", "pep_schema.company_view_rules", "id");
        companyViewRulesFk.setPrimaryKey(true);

        Column excludeCompanies = new Column();
        excludeCompanies.setName("exclude_companies");
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
        Path pkFile = findGeneratedFile("CompanyStatusViewRulesPK.java");
        Path companyStatusFile = findGeneratedFile("CompanyStatus.java");
        Path companyViewRulesFile = findGeneratedFile("CompanyViewRules.java");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);
        String companyStatusContent = Files.readString(companyStatusFile);
        String companyViewRulesContent = Files.readString(companyViewRulesFile);

        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId.");
        assertTrue(entityContent.contains("private CompanyStatusViewRulesPK id;"),
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

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable PK class.");
        assertTrue(pkContent.contains("public class CompanyStatusViewRulesPK implements Serializable"),
                "Expected PK class name CompanyStatusViewRulesPK.");
        assertTrue(pkContent.contains("private UUID companyStatusId;"),
                "Expected companyStatusId field in PK class.");
        assertTrue(pkContent.contains("private UUID companyViewRulesId;"),
                "Expected companyViewRulesId field in PK class.");

        assertTrue(companyStatusContent.contains("@OneToMany(mappedBy = \"companyStatus\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on CompanyStatus.");
        assertTrue(companyStatusContent.contains("private List<CompanyStatusViewRules>"),
                "Expected CompanyStatus inverse collection field.");

        assertTrue(companyViewRulesContent.contains("@OneToMany(mappedBy = \"companyViewRules\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on CompanyViewRules.");
        assertTrue(companyViewRulesContent.contains("private List<CompanyStatusViewRules>"),
                "Expected CompanyViewRules inverse collection field.");
    }

    @Test
    void testGenerateCompanyProfessionSystemLinkEntity_GeneratesExternalPkAndInverseCollectionsOnParents() throws IOException {
        Table company = createUuidPkTable("pep_schema.company");
        Table professionSystem = createUuidPkTable("pep_schema.profession_system");

        Table companyProfessionSystemLink = new Table();
        companyProfessionSystemLink.setName("pep_schema.company_profession_system_link");

        Column companyFk = createRequiredUuidForeignKeyColumn("company_id", "pep_schema.company", "id");
        companyFk.setPrimaryKey(true);

        Column professionSystemFk = createRequiredUuidForeignKeyColumn("profession_system_id", "pep_schema.profession_system", "id");
        professionSystemFk.setPrimaryKey(true);

        companyProfessionSystemLink.setColumns(new ArrayList<>(List.of(companyFk, professionSystemFk)));

        entityGenerator.generate(
                List.of(company, professionSystem, companyProfessionSystemLink),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path entityFile = findGeneratedFile("CompanyProfessionSystemLink.java");
        Path pkFile = findGeneratedFile("CompanyProfessionSystemLinkPK.java");
        Path companyFile = findGeneratedFile("Company.java");
        Path professionSystemFile = findGeneratedFile("ProfessionSystem.java");

        String entityContent = Files.readString(entityFile);
        String pkContent = Files.readString(pkFile);
        String companyContent = Files.readString(companyFile);
        String professionSystemContent = Files.readString(professionSystemFile);

        assertTrue(entityContent.contains("@EmbeddedId"),
                "Expected @EmbeddedId.");
        assertTrue(entityContent.contains("private CompanyProfessionSystemLinkPK id;"),
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

        assertTrue(pkContent.contains("@Embeddable"),
                "Expected @Embeddable PK class.");
        assertTrue(pkContent.contains("public class CompanyProfessionSystemLinkPK implements Serializable"),
                "Expected PK class name CompanyProfessionSystemLinkPK.");
        assertTrue(pkContent.contains("private UUID companyId;"),
                "Expected companyId field in PK class.");
        assertTrue(pkContent.contains("private UUID professionSystemId;"),
                "Expected professionSystemId field in PK class.");

        assertTrue(companyContent.contains("@OneToMany(mappedBy = \"company\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on Company.");
        assertTrue(companyContent.contains("private List<CompanyProfessionSystemLink>"),
                "Expected Company inverse collection field.");

        assertTrue(professionSystemContent.contains("@OneToMany(mappedBy = \"professionSystem\", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)"),
                "Expected inverse collection on ProfessionSystem.");
        assertTrue(professionSystemContent.contains("private List<CompanyProfessionSystemLink>"),
                "Expected ProfessionSystem inverse collection field.");
    }

    @Test
    void testGeneratePureManyToManyFromBothSides() throws IOException {
        Table company = createUuidPkTable("pep_schema.company");
        Table language = createUuidPkTable("pep_schema.languages");

        Table companyLanguage = new Table();
        companyLanguage.setName("pep_schema.company_language");

        Column companyFk = createRequiredUuidForeignKeyColumn("company_id", "pep_schema.company", "id");
        companyFk.setPrimaryKey(true);

        Column languageFk = createRequiredUuidForeignKeyColumn("language_id", "pep_schema.languages", "id");
        languageFk.setPrimaryKey(true);

        companyLanguage.setColumns(new ArrayList<>(List.of(companyFk, languageFk)));

        entityGenerator.generate(
                List.of(company, language, companyLanguage),
                tempDir.toString(),
                "com.example",
                true,
                false
        );

        Path companyFile = findGeneratedFile("Company.java");
        Path languagesFile = findGeneratedFile("Languages.java");

        String companyContent = Files.readString(companyFile);
        String languagesContent = Files.readString(languagesFile);

        assertTrue(companyContent.contains("@ManyToMany(fetch = FetchType.LAZY)"),
                "Expected owning @ManyToMany on Company.");
        assertTrue(companyContent.contains("@JoinTable(name = \"company_language\""),
                "Expected @JoinTable on Company.");
        assertTrue(companyContent.contains("private List<Languages>"),
                "Expected Company collection of Languages.");

        assertTrue(languagesContent.contains("@ManyToMany(mappedBy = \"languages\", fetch = FetchType.LAZY)"),
                "Expected inverse @ManyToMany on Languages.");
        assertTrue(languagesContent.contains("private List<Company>"),
                "Expected Languages collection of Company.");
    }

    private Table createUuidPkTable(String tableName) {
        Table table = new Table();
        table.setName(tableName);
        table.setColumns(new ArrayList<>(List.of(createUuidPrimaryKeyColumn("id"))));
        return table;
    }

    private Column createLongPrimaryKeyColumn(String columnName) {
        Column column = new Column();
        column.setName(columnName);
        column.setSqlType("INT");
        column.setJavaType("Long");
        column.setPrimaryKey(true);
        column.setNullable(false);
        return column;
    }

    private Column createUuidPrimaryKeyColumn(String columnName) {
        Column column = new Column();
        column.setName(columnName);
        column.setSqlType("uuid");
        column.setJavaType("java.util.UUID");
        column.setPrimaryKey(true);
        column.setNullable(false);
        return column;
    }

    private Column createRequiredLongForeignKeyColumn(String columnName, String referencedTable, String referencedColumn) {
        Column column = new Column();
        column.setName(columnName);
        column.setSqlType("INT");
        column.setJavaType("Long");
        column.setPrimaryKey(false);
        column.setForeignKey(true);
        column.setNullable(false);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        return column;
    }

    private Column createNullableLongForeignKeyColumn(String columnName, String referencedTable, String referencedColumn) {
        Column column = new Column();
        column.setName(columnName);
        column.setSqlType("INT");
        column.setJavaType("Long");
        column.setPrimaryKey(false);
        column.setForeignKey(true);
        column.setNullable(true);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        return column;
    }

    private Column createRequiredUuidForeignKeyColumn(String columnName, String referencedTable, String referencedColumn) {
        Column column = new Column();
        column.setName(columnName);
        column.setSqlType("uuid");
        column.setJavaType("java.util.UUID");
        column.setPrimaryKey(false);
        column.setForeignKey(true);
        column.setNullable(false);
        column.setReferencedTable(referencedTable);
        column.setReferencedColumn(referencedColumn);
        return column;
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
