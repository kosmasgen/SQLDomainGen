# SpringForge

SpringForge is a CLI tool that parses SQL DDL and generates a Spring Boot backend.

It generates a complete backend project, not just individual classes.

It automatically generates:

* JPA entities (with Envers auditing)
* DTOs
* Mapper (ModelMapper)
* Repositories
* Services and service implementations
* REST controllers
* Liquibase changelogs
* Tests for controller, DTO, entity, mapper, and service implementation layers
  
---

## Why SpringForge

Building backend layers manually is repetitive and error-prone.

SpringForge generates a production-ready Spring Boot backend directly from your SQL schema, including entities, DTOs, mappers, repositories, services, controllers, Liquibase changelogs, and tests.

It significantly reduces boilerplate and accelerates backend development.

## Quick Start

Run the CLI:

```bash
generate-entity \
  -i "path/to/input.sql" \
  -o "path/to/output" \
  -p "com.example.project" \
  -w true \
  -b true \
  -a "your@email.com"
```
---

## Example

### Input SQL

```sql

-- -- pep_schema.income_payment definition

-- Drop table
-- DROP TABLE pep_schema.income_payment;

CREATE TABLE pep_schema.income_payment (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    chamber_id int4 NOT NULL,
    chamber_pay_method_id int4 NOT NULL,
    description varchar(255) NOT NULL,
    last_updated timestamp NOT NULL,
    recdeleted int4 NULL,
    CONSTRAINT pk_income_payment PRIMARY KEY (id),
    CONSTRAINT uk_in_pay_method UNIQUE (chamber_id, chamber_pay_method_id)
);

-- -- pep_schema.income_type definition

-- Drop table
-- DROP TABLE pep_schema.income_type;

CREATE TABLE pep_schema.income_type (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    chamber_id int4 NOT NULL,
    chamber_type_id int4 NOT NULL,
    description varchar(255) NOT NULL,
    last_updated timestamp NOT NULL,
    recdeleted bool NULL,
    date_created timestamp NULL,
    CONSTRAINT pk_income_type PRIMARY KEY (id),
    CONSTRAINT uk_income_type UNIQUE (chamber_id, chamber_type_id)
);

-- -- pep_schema.income_transaction definition

-- Drop table
-- DROP TABLE pep_schema.income_transaction;

CREATE TABLE pep_schema.income_transaction (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    chamber_id int4 NOT NULL,
    chamber_in_transd_id numeric NOT NULL,
    cd_use varchar(4) NOT NULL,
    dt timestamp NOT NULL,
    is_member int4 NULL,
    company_id uuid NULL,
    account_cd varchar(255) NULL,
    income_type_id uuid NULL,
    amount numeric(19, 2) NULL,
    last_updated timestamp NOT NULL,
    recdeleted numeric NOT NULL,
    income_pay_method_id uuid NULL,
    is_kratisi numeric NULL,
    CONSTRAINT pk_income_transaction PRIMARY KEY (id),
    CONSTRAINT uk_income_trans UNIQUE (chamber_id, chamber_in_transd_id, is_kratisi),
    CONSTRAINT fk_income_pay_method FOREIGN KEY (income_pay_method_id) REFERENCES pep_schema.income_payment(id),
    CONSTRAINT fk_income_type FOREIGN KEY (income_type_id) REFERENCES pep_schema.income_type(id)
);

-- Indexes
CREATE INDEX idx_in_trans_chamber ON pep_schema.income_transaction (chamber_id);
CREATE INDEX idx_in_trans_type ON pep_schema.income_transaction (income_type_id);
```

---

### What SpringForge extracts

From this schema, SpringForge detects:

* Table relationships (foreign keys)
* Unique and composite constraints
* Indexes for optimized queries
* Field types and nullability
* Default values and timestamps

This information is used to generate a fully structured backend.

---

### Generated Output

SpringForge generates:

* `IncomeTransaction`, `IncomeType`, and `IncomePayment` entities
* DTOs with validation annotations
* Repositories with proper key mappings
* Service and service implementation layers
* REST controllers
* Liquibase changelogs (versioned)
* Unit and integration tests
 
---

### Sample Generated Code

#### Entity
```java
@Entity
@Audited
@Table(name = "income_payment", uniqueConstraints = @UniqueConstraint(columnNames = {"chamber_id", "chamber_pay_method_id"}))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "chamber_id", nullable = false)
    private Integer chamberId;

    @Column(name = "chamber_pay_method_id", nullable = false)
    private Integer chamberPayMethodId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    @Column(name = "recdeleted")
    private Integer recdeleted;

}
```

#### DTO
```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomePaymentDto {

    private UUID id;

    @NotNull
    private Integer chamberId;

    @NotNull
    private Integer chamberPayMethodId;

    @NotNull
    @Size(max = 255)
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    private Integer recdeleted;

}
```

#### Controller
```java
@RestController
@RequiredArgsConstructor
@Tag(name = "Income Payment", description = "Income Payment API")
@RequestMapping("/api/income-payment")
public class IncomePaymentController {

    private final IncomePaymentService incomePaymentService;

    /**
     * Retrieves all income payments.
     *
     * @return list of IncomePaymentDto
     */
    @Operation(summary = "Get all income payments")
    @GetMapping
    public ResponseEntity<List<IncomePaymentDto>> getAll() {
        return ResponseEntity.ok(incomePaymentService.getAllIncomePayments());
    }
}
```

### Validation Report

SpringForge includes a built-in validation system that verifies the generated backend against the original SQL schema.

Example output (based on the above schema):

```text
Parsed tables: 3
Generated entities: 3
DTOs: 3
Mapper: 3
Base mapper: 1
Repositories: 3
Services: 3
ServicesImpl: 3
Controllers: 3
Liquibase files (excluding master): 5
Foreign keys detected: 2
Indexes detected: 2
Total validation checks: 34
Violations: 0
```
All generated components are validated for:

- Entity-to-table mapping
- Field types and constraints
- Relationships and foreign keys
- Liquibase schema consistency

## Features

* Parses SQL DDL using ANTLR
* Supports foreign keys and relationships
* Supports table-level constraints (UNIQUE, CHECK, FOREIGN KEY)
* Supports index detection and generation
* Handles composite primary keys
* Generates validation annotations
* Produces Liquibase-ready output
* Follows a clean layered architecture
* Applies consistent naming conventions
* Generates test-ready code
* Transforms SQL schema into a domain model before code generation

---

## Architecture

```text
SQL DDL → ANTLR Parser → Domain Model → Code Generator → Spring Boot Project
```
## How It Works

SpringForge follows a multi-step generation pipeline:

1. **SQL Parsing**
   The input SQL DDL is parsed using ANTLR to extract tables, columns, constraints, and relationships.

2. **Model Transformation**
   The parsed schema is converted into an internal domain model.

3. **Code Generation**
   Based on the model, SpringForge generates:

   * Entities
   * DTOs
   * Mapper
   * Repositories
   * Services and service implementations
   * Controllers
   * Liquibase changelogs
   * Tests

4. **Project Assembly**
   All generated components are assembled into a complete Spring Boot project.

---

## Project Structure

SpringForge generates a complete Spring Boot project:

```text
src/main/java/com/example/project/
 ├── entity/
 ├── dto/
 ├── mapper/
 ├── repository/
 ├── service/
 ├── serviceImpl/
 ├── controller/
 ├── config/
 ├── exception/

src/main/resources/
 ├── application.properties
 ├── db/migration/changelogs/v0.1.0/

src/test/java/com/example/project/
 ├── controller/
 ├── service/
 ├── mapper/
 ├── entity/
 ├── dto/

pom.xml
.gitignore
```

---

## Requirements

* Java 21+
* Maven
* Spring Boot 3.x

---

## Roadmap

* Improved relationship mapping
* Better handling of advanced SQL constraints
* Custom template support
* CLI improvements
* Extended validation reporting

---

## Contributing

Contributions, ideas, and feedback are welcome.
Feel free to open an issue or submit a pull request.

---

## Contact

Email: kosmasgenaris [at] gmail [dot] com

For direct inquiries or collaboration, you can reach out via email.
