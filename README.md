# Turkey Weather Hub

Turkey Weather Hub is a Spring Boot application that provides weather information for cities. It leverages Spring Data JPA for data persistence and integrates with external weather APIs to fetch real-time weather data.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)

## Features

- **City Details:** Retrieve detailed information about a city, including its location, daily weather, and more.
- **Real-time Data:** Utilize external weather APIs to provide up-to-date weather information.
- **Data Persistence:** Store city information in a PostgreSQL database using Spring Data JPA.
- **RESTful Endpoints:** Expose RESTful endpoints for easy integration with other applications.

## Technologies Used

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Java 17

## Getting Started

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Damples06/WeatherWebApp.git
   cd WeatherWebApp
   ```
   
2. **Build and Run:**

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   
3. **Access the Application:**

   Open your web browser and go to http://localhost:8686.

## Usage

**City Details**
   
   To retrieve details for a specific city, use the following endpoint:
   
   ```
      GET /{city-name}
   ```
   
   Example:
   
   ```bash
      curl http://localhost:8686/Istanbul
   ```
**Daily Weather Data**

   
   To get the daily weather data for a city, use the following endpoint:
   
   ```
      GET /daily/{city-name}
   ```
   
   Example:
   
   ```bash
      curl http://localhost:8686/daily/Istanbul
   ```

 ## Configuration

   Configure the application properties in application.yml for database connection and external API integration.
   
   ```yaml
      server:
        port: 8686
      spring:
        datasource:
          url: jdbc:postgresql://localhost:5432/city
          username: postgres
          password: postgres
          driver-class-name: org.postgresql.Driver
        jpa:
          hibernate:
            ddl-auto: update
          show-sql: true
          properties:
            hibernate:
              dialect: org.hibernate.dialect.PostgreSQLDialect
   ```

## Contributing

   Contributions are welcome! Feel free to open issues and pull requests.
