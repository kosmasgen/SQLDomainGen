# SpringForge

SpringForge is a CLI tool that parses SQL DDL and generates a fully structured, production-ready Spring Boot backend.

SpringForge generates a complete backend project, not just individual classes.

It automatically generates:

* JPA entities (with Envers auditing)
* DTOs
* Mappers (ModelMapper)
* Repositories
* Services and service implementations
* REST controllers
* Liquibase changelogs
* Unit and integration tests

---

## Why SpringForge

Building backend layers manually is repetitive and error-prone.  
SpringForge eliminates boilerplate by generating a complete backend architecture directly from your database schema.

---

## Quick Start

Example CLI command:

```bash id="7r7rxt"
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

```sql id="x9gq1z"
CREATE TABLE income_payment_method (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    chamber_id int4 NOT NULL,
    chamber_pay_method_id int4 NOT NULL,
    description varchar(255) NOT NULL,
    last_updated timestamp NOT NULL,
    recdeleted int4 NULL,
    CONSTRAINT pk_income_payment_method PRIMARY KEY (id),
    CONSTRAINT uk_in_pay_method UNIQUE (chamber_id, chamber_pay_method_id)
);

CREATE TABLE income_type (
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

CREATE TABLE income_transaction (
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
    CONSTRAINT fk_income_pay_method FOREIGN KEY (income_pay_method_id) REFERENCES income_payment_method(id),
    CONSTRAINT fk_income_type FOREIGN KEY (income_type_id) REFERENCES income_type(id)
);

CREATE INDEX idx_in_trans_chamber ON income_transaction (chamber_id);
CREATE INDEX idx_in_trans_type ON income_transaction (income_type_id);
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

* `IncomeTransaction`, `IncomeType`, and `IncomePaymentMethod` entities
* DTOs with validation annotations
* Repositories with proper key mappings
* Service and service implementation layers
* REST controllers
* Liquibase changelogs with constraints, foreign keys, and indexes
* Unit and integration tests

---

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

```
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
   * Repositories
   * Services
   * Controllers
   * Liquibase changelogs
   * Tests

4. **Project Assembly**
   All generated components are assembled into a complete Spring Boot project.

---

## Project Structure

SpringForge generates a complete Spring Boot project:

```text id="c3k6vj"
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
 ├── db/changelog/

src/test/java/com/example/project/
 ├── controller/
 ├── service/
 ├── mapper/

pom.xml
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

---

## Contributing

Contributions, ideas, and feedback are welcome.
Feel free to open an issue or submit a pull request.

---

## Contact

Email: kosmasgenaris [at] gmail [dot] com

For direct inquiries or collaboration, you can reach out via email.
