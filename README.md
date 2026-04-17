# SpringForge

SpringForge is a CLI tool that generates a complete Spring Boot backend from SQL DDL.

It automates the creation of:

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

Building backend layers manually is repetitive and time-consuming.
SpringForge eliminates boilerplate by generating a full backend structure directly from your database schema.

Ideal for:

* Rapid prototyping
* CRUD-heavy applications
* Internal tools
* Learning Spring Boot architecture
* Standardizing backend structure across projects

---

## Quick Start

Run the generator:

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
    is_echamber int4 NULL,
    block_ser varchar(3) NULL,
    is_kratisi numeric NULL,
    chamber_comp_id numeric NULL,
    chamber_method numeric NULL,
    chamber_type numeric NULL,
    CONSTRAINT pk_income_transaction PRIMARY KEY (id),
    CONSTRAINT uk_income_trans UNIQUE (chamber_id, chamber_in_transd_id, is_kratisi),
    CONSTRAINT fk_income_pay_method FOREIGN KEY (income_pay_method_id) REFERENCES income_payment_method(id),
    CONSTRAINT fk_income_type FOREIGN KEY (income_type_id) REFERENCES income_type(id)
);

CREATE INDEX idx_in_trans_chamber ON income_transaction (chamber_id);
CREATE INDEX idx_in_trans_comp ON income_transaction (company_id);
CREATE INDEX idx_in_trans_dt ON income_transaction (dt);
CREATE INDEX idx_in_trans_member ON income_transaction (is_member);
CREATE INDEX idx_in_trans_pay ON income_transaction (income_pay_method_id);
CREATE INDEX idx_in_trans_type ON income_transaction (income_type_id);

```

### Generated Output

SpringForge generates:

* `Company` entity
* `CompanyDto`
* `CompanyRepository`
* `CompanyService` and `CompanyServiceImpl`
* `CompanyController`
* Liquibase changelog
* Unit and integration tests

---

## Features

* Parses SQL DDL and extracts schema structure
* Supports foreign keys and relationships
* Handles composite primary keys
* Generates validation annotations
* Produces Liquibase-ready output
* Follows a clean layered architecture
* Applies consistent naming conventions
* Generates test-ready code

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

Open an issue or submit a pull request.

---

## Contact

For questions or collaboration:

kosmas.dev [at] gmail [dot] com

Or open an issue in this repository.
