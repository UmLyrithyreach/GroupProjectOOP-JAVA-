```markdown
# Clothes Store Management System
Group Project for Java. First step to Application Development.

Repository: UmLyrithyreach/GroupProjectOOP-JAVA- (ID: 920928767)  
Description: Group Project for Java. First step to Application Development  
Primary language: Java (100%)

A small Java-based Clothes Store Management System implemented as an object-oriented project for a Year 2 Java course. The system models customers, orders, clothes inventory, suppliers and staff and includes simple persistence and I/O utilities.

Author's note:
"I am honored with greatest gratitude to be part of the team. Thus, I feel honored and would love to you my greatest gratitude to be part of the contributor."
— Reach

---

## What this README improves
This README focuses on a clear, actionable description of the project's structure and responsibilities so contributors can quickly find and extend functionality.

## Features
- Manage customers: add, update, view customer details
- Create and track orders, statuses and payments
- Inventory management: add, update and categorize clothes
- Supplier management: store supplier details and supplied products
- Staff management: store employee information and roles
- Shop-level operations and utilities

---

## Recommended project structure
Below is a recommended layout that matches the classes and responsibilities used in this project. If your current layout differs, adapt the descriptions to match your actual package names and file locations.

Project root
- README.md
- database/
  - schema.sql            # SQL scripts to create tables and sample data
  - seed.sql              # Optional fixture data
  - README.md             # Notes about DB setup
- lib/
  - mariadb-java-client-*.jar   # JDBC driver used by the project (if not using Maven/Gradle)
- src/
  - main/
    - java/
      - com.yourorg.clothingstore/   (or simply start in default package)
        - model/
          - Customer.java
          - Order.java
          - OrderItem.java (optional; models order lines)
          - Clothes.java
          - Supplier.java
          - Staff.java
        - dao/
          - DatabaseConnection.java   # central JDBC connection & resource helpers
          - CustomerDAO.java
          - OrderDAO.java
          - ClothesDAO.java
          - SupplierDAO.java
          - StaffDAO.java
        - service/
          - CustomerService.java      # business logic, validation, transaction coordination
          - OrderService.java
          - InventoryService.java
          - SupplierService.java
          - StaffService.java
        - ui/
          - Main.java                 # program entry point
          - ConsoleUI.java / AppUI.java  # simple console or Swing/JavaFX UI
        - util/
          - Validation.java
          - FileUtils.java
          - Config.java               # loads config from properties or env
- test/                          # unit / integration tests (if present)
- .vscode/                       # optional IDE settings (update referencedLibraries here)

Notes:
- If you are using Maven or Gradle, prefer standard Maven layout (src/main/java, src/test/java) and add the MariaDB/MySQL driver as a dependency.
- If not using a build tool, keep the JDBC jar in `lib/` and reference it in your IDE settings or the classpath when compiling/running.

---

## Class & package responsibilities (short descriptions)
- model/* — Plain Old Java Objects (POJOs) representing domain entities (Customer, Order, Clothes, Supplier, Staff). Keep these classes focused on state and simple validation.
- dao/* — Data Access Objects encapsulate JDBC code: SQL statements, ResultSet parsing, CRUD methods. DAO methods should throw or translate SQL exceptions to service-level exceptions.
- service/* — Business logic layer that coordinates DAO calls, enforces business rules, and performs higher-level operations (e.g., creating an order: check stock, create order and order items, update inventory, record payment).
- ui/* — Entry point and UI components (console, GUI, or API). Keep UI code thin — delegate logic to services.
- util/* — Helpers (configuration loading, input validation, file I/O, logging helpers).
- database/* — SQL scripts and documentation for setting up the schema and seed data.

---

## Data model overview (entities & relations)
- Customer (id, name, contact, address, email, ...)  
- Staff (id, name, role, contact)  
- Supplier (id, name, contact, suppliedItems)  
- Clothes (id, sku, name, size, color, price, quantity, category, supplier_id)  
- Order (id, customer_id, staff_id, date, status, total)  
- OrderItem (order_id, clothes_id, quantity, price_each)

Typical relationships:
- Order -> Customer (many-to-one)
- Order -> Staff (many-to-one, optional)
- Order -> OrderItem (one-to-many)
- OrderItem -> Clothes (many-to-one)
- Clothes -> Supplier (many-to-one)

---

## Database & configuration
- DB scripts: look in the `database/` folder for schema and seed scripts.
- DatabaseConnection: update the JDBC URL, username and password in DatabaseConnection.java, or better: read those values from a config file or environment variables.
- Example (DatabaseConnection snippet):
```java
private static final String URL = "jdbc:mariadb://localhost:3306/clothing_store";
private static final String USERNAME = "root";
private static final String PASSWORD = "1234";
```
Security note: do not commit production credentials. Use .env, system environment variables, or a local config file excluded from version control.

If you use VS Code, update .vscode/settings.json to reference the JDBC driver jar:
```json
"java.project.referencedLibraries": [
  "lib/mariadb-java-client-3.5.2.jar"
]
```
Or add the driver as a dependency in Maven/Gradle:
- Maven:
```xml
<dependency>
  <groupId>org.mariadb.jdbc</groupId>
  <artifactId>mariadb-java-client</artifactId>
  <version>3.5.2</version>
</dependency>
```

---

## How to build & run (simple)
1. Create the database and tables using `database/schema.sql`.
2. Place JDBC driver in `lib/` or add dependency to your build tool.
3. Update DB credentials in DatabaseConnection or configuration.
4. Compile and run from your IDE, or:
   - Compile: javac -d out src/main/java/**/*.java
   - Run: java -cp "out:lib/mariadb-java-client-3.5.2.jar" com.yourorg.clothingstore.ui.Main

If you use Maven or Gradle, use their build/run tasks instead.

---

## Development tips & next improvements
- Move DB credentials into environment variables or a config file (Config.java).
- Add unit tests for services and integration tests for DAOs (in-memory DB or test container).
- Add schema migration (Flyway or Liquibase) if the project grows.
- Add clear CONTRIBUTING.md and a LICENSE file.

---

## Team
Someth, Huyty, Reach, ChhunHour

---

If anything in the structure above does not match your repository layout, tell me which package names/paths you use and I will update this README to exactly reflect the real structure. If you want, I can commit this README to a branch and open a pull request for you.
```
