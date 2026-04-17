# SpringForge

SpringForge is a CLI tool that generates a complete Spring Boot backend from SQL DDL.

It automates the creation of:

* JPA Entities (with Envers auditing)
* DTOs
* Mappers (ModelMapper)
* Repositories
* Services & Service Implementations
* REST Controllers
* Liquibase changelogs
* Unit & integration tests

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

Run the generator using:

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

SpringForge will generate:

* `Company` entity
* `CompanyDto`
* `CompanyRepository`
* `CompanyService` & `CompanyServiceImpl`
* `CompanyController`
* Liquibase changelog
* Unit & integration tests

---

## Features

* Parses SQL DDL and extracts schema structure
* Supports foreign keys and relationships
* Handles composite primary keys
* Generates validation annotations
* Liquibase-ready output
* Clean layered architecture
* Consistent naming conventions
* Test-ready code generation

---

## Project Structure

Generated projects follow a typical Spring Boot layered architecture:

```
entity/
dto/
mapper/
repository/
service/
serviceImpl/
controller/
config/
liquibase/
test/
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
* Custom templates support
* CLI enhancements

---

## Contributing

Contributions, ideas, and feedback are welcome.

Open an issue or submit a pull request.

---

## Contact

For questions or collaboration:

kosmas.dev [at] gmail [dot] com

Or open an issue in this repository.
