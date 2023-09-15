# Retail Shop Discounts Calculator

## Description

`retail-discounts` is a discount calculator for a retail shop. This guide will walk you through the steps to run the application, its tests, and how to generate coverage reports.

## Prerequisites

- Docker
- Maven
- JDK 17 or above

## How to Run the Code

### Clone the Repository

```
git clone https://github.com/mjmaahs/retail-discounts.git
```

### Run Dockerized Database & Application

On Windows, open your command prompt and run:

```bash
D: && cd D:\projects\Apps\retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install && docker-compose up -d app
```

### Run Dockerized Database Only

To run only the database, execute the following:

```bash
D: && cd D:\projects\Apps\retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install
```

## How to Run Tests

To run the unit tests, use the following Maven command:

```bash
mvn test
```

## How to Generate Coverage Reports

### Run JaCoCo for Test Coverage

Running the tests via Maven will also generate a JaCoCo report. You can find the report at:

```text
D:\projects\Apps\retail-discounts\target\site\jacoco
```

## Additional Information

### SonarQube Analysis

To run a SonarQube analysis of the code:

```bash
mvn sonar:sonar -Dsonar.java.binaries=target/classes -Dsonar.login=sqa_1c3d332a32e5c580ec43dd08fd28c1af9479b6aa
```

---
