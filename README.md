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

```sql
CREATE TABLE company (
    id uuid PRIMARY KEY,
    name varchar(255) NOT NULL
);
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
