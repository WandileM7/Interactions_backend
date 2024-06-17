# Interactions Backend

This is the backend component of the Interactions Management System. It provides a RESTful API for managing user interactions.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Documentation](#api-documentation)
- [Code Quality and Static Analysis](#code-quality-and-static-analysis)
- [Deployment](#deployment)
- [Contributing](#contributing)

## Prerequisites

- Java 11 or higher
- Maven
- MongoDB (or a MongoDB-compatible database)

## Getting Started

1. Clone the repository:
   git clone https://github.com/WandileM27/interactions_backend.git
2. Build the project:
   docker build -t interactions_backend .
3. Run the application:
   docker compose up

### Project Structure

src/main/java/interactions/backend: Contains the main application code
src/main/resources: Contains configuration files and resources
src/test/java: Contains unit and integration tests

### API Documentation
The API documentation is generated using Swagger and can be accessed at http://localhost:8080/swagger-ui.html when the application is running.

### Code Quality and Static Analysis
We use SonarQube for code quality and static analysis. You can run SonarQube locally or integrate it with your CI/CD pipeline.

### Deployment
The application can be deployed to various environments, such as a local machine, a cloud platform, or a containerized environment (e.g., Docker, Kubernetes).
### Contributing
Contributions are welcome! Please read the contributing guidelines for more information.