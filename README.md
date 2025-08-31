````markdown
# Bajaj Finserv Health Qualifier Project

## Overview

This project is a Spring Boot application developed as part of the Bajaj Finserv Health Qualifier challenge. It involves generating a webhook, solving a SQL problem, and submitting the solution to a specified endpoint.

## Project Details

*   **Current Date and Time (UTC)**: 2025-08-31 12:58:01
*   **User's GitHub Login**: Mangun10

## Problem Statement

The application addresses the following problem:

1.  On startup, it sends a POST request to generate a webhook.
2.  Based on the response, it solves a SQL problem and stores the result.
3.  It sends the solution (a final SQL query) to the returned webhook URL using a JWT token.

## Technologies Used

*   Java 17
*   Spring Boot
*   Spring WebFlux
*   Lombok
*   Maven

## Project Structure

The project structure is as follows:

```
bajaj-finserv-health-qualifier/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/bajajfinservhealthqualifier/
│   │   │       ├── dto/
│   │   │   │   ├── SolutionRequest.java
│   │   │   │   ├── WebhookRequest.java
│   │   │   │   └── WebhookResponse.java
│   │   │   ├── entity/
│   │   │   │   ├── Department.java
│   │   │   │   ├── Employee.java
│   │   │   │   └── Payments.java
│   │   │   ├── repository/
│   │   │   │   └── PaymentsRepository.java
│   │   │   ├── BajajFinservHealthQualifierApplication.java
│   │   │   ├── runner/
│   │   │   │   └── AppRunner.java
│   │   │   └── service/
│   │   │       └── ChallengeService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/example/bajajfinservhealthqualifier/
│               └── BajajFinservHealthQualifierApplicationTests.java
├── pom.xml
└── mvnw
└── mvnw.cmd
```

*   `src/main/java`: Contains the source code for the application.
*   `src/main/resources`: Contains configuration files and static resources.
*   `pom.xml`: The Maven project file.
*   `mvnw` and `mvnw.cmd`: The Maven Wrapper scripts.

## Setup Instructions

1.  **Clone the Repository**:

    ```bash
    git clone https://github.com/<your_github_username>/<your_repository_name>.git
    ```

2.  **Configure the Database**:
    *   Create a MySQL database.
    *   Update the `application.properties` file with your database credentials.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?serverTimezone=UTC
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3.  **Build the Project**:
    Use the Maven Wrapper to build the project:

    ```bash
    ./mvnw clean package
    ```

4.  **Run the Application**:
    Run the JAR file:

    ```bash
    java -jar target/bajaj-finserv-health-qualifier-0.0.1-SNAPSHOT.jar
    ```

## Code Explanation

*   **`dto` Package**: Contains Data Transfer Objects for request and response payloads.
*   **`service` Package**: Contains the `ChallengeService` class, which implements the core logic of the application.
*   **`runner` Package**: Contains the `AppRunner` class, which executes the challenge on application startup.
*   **`application.properties`**: Configures the application properties, including the database connection details.

## Submission Details

*   **GitHub Repository Link**:
    `https://github.com/<your_github_username>/<your_repository_name>`
*   **RAW Downloadable JAR Link**:
    `https://raw.githubusercontent.com/<your_github_username>/<your_repository_name>/main/target/bajaj-finserv-health-qualifier-0.0.1-SNAPSHOT.jar`

## Notes

*   This project was developed to solve the Bajaj Finserv Health Qualifier challenge.
*   The application connects to a MySQL database to execute the SQL query.
*   Ensure that the database is properly configured before running the application.
