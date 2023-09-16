# Retail Shop Discounts Calculator

## Description

`retail-discounts` is a discount calculator for a retail shop. This guide will walk you through the steps to run the application, its tests, and how to generate coverage reports.

## Prerequisites

- Docker
- Maven
- JDK 17 or above

## Installation

### Clone the Repository

```bash
git clone https://github.com/mjmaahs/retail-discounts.git
```

### Run Dockerized Database & Application

To run both the database and application, open your command prompt and execute:

```bash
cd path/to/retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install && docker-compose up -d app
```

## Running Tests

To execute the unit tests, use the following Maven command:

```bash
cd path/to/retail-discounts
mvn test
```

## Coverage Reports

### JaCoCo Report

Running the tests via Maven will also generate a JaCoCo coverage report. The report can be found at:

```text
path/to/retail-discounts/target/site/jacoco
```

### SonarQube Report

The SonarQube report can be located at:

```text
path/to/retail-discounts/src/sonarqubereport
```

## Additional Information

### Development and Postman Testing

If you want to use Postman for testing or go into development mode:

1. Start the database and DB express services only.
2. Start the app from your IDE.
3. Uncomment the `CommandLineRunner` in the `RetailDiscountsApplication` class to insert raw data upon startup.
4. Disable CSRF in the `SecurityConfig` class.

#### Actuator Endpoints

The following Actuator endpoints are enabled: `health`, `info`, `mappings`.  
Authentication: `username: john/test123`

#### Postman Details

- **URL**: `http://localhost:8080/api/calculate-net-amount`
- **Method**: `POST`
- **Body**: `{"productSerialNumbers": ["000001","000002","000003"],"username": "bruce"}`
- **Basic Auth**: `username: john/test123`

### Run Dockerized Database Only

To run only the database, execute:

```bash
cd path/to/retail-discounts
docker-compose up -d mongo && docker-compose up -d mongo-express && mvn clean install
```
