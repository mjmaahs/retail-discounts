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

Open your command prompt and run:

```bash
cd path/to/retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install && docker-compose up -d app
```

## How to Run Tests

To run the unit tests, use the following Maven command:

```bash
cd path/to/retail-discounts
mvn test
```

## How to Generate Coverage Reports

### JaCoCo report

Running the tests via Maven will also generate a JaCoCo report. You can find the report at:

```text
path/to/retail-discounts/target/site/jacoco
```

### SonarQube Report

You can find the SonarQube report at:

```text
path/to/retail-discounts/src/sonarqubereport
```

## Additional Information

### Development and Postman Testing

In case you want to test using Postman and go into development mode, you can start the database and DB express services only. Please uncomment the CommandLineRunner in the `RetailDiscountsApplication` class. This will insert raw data upon starting the app. Additionally, make sure to disable CSRF in the `SecurityConfig` class. You can run the application using IntelliJ or Eclipse.


### Run Dockerized Database Only

To run only the database, execute the following:

```bash
cd path/to/retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install
```

---