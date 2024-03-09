# Turkey Weather Hub

Turkey Weather Hub is an innovative Spring Boot application designed to offer up-to-date weather information for cities in Turkey. It seamlessly combines the power of Spring Data JPA for efficient data storage with integration capabilities for the MGM (Turkish State Meteorological Service). This integration allows the application to fetch and deliver real-time weather data, providing users with accurate and timely information.

## Table of Contents

- [Project Motivation](#project-motivation)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Configuration](#configuration)
- [Contributing](#contributing)

## Project Motivation

The motivation behind this project was to address the lack of documentation for the MGM (Turkish State Meteorological Service) API. Faced with the absence of clear guidelines, the project emerged as a solution by utilizing the available data through the service. The aim was to create a clean, readable version of the weather information, regulating and enhancing the data retrieved from the service for a better user experience.

## Features

- **Area Details:** Obtain comprehensive information about a area, encompassing its geographical location, daily weather updates, and more.
- **Real-time Data:** Utilize the MGM (Turkish State Meteorological Service) as an external API, ensuring the retrieval of current and precise weather information.
- **Data Persistence:** Seamlessly store area-specific data in a PostgreSQL database leveraging the capabilities of Spring Data JPA.
- **RESTful Endpoints:** Facilitate effortless integration with other applications by exposing RESTful endpoints.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok

## Getting Started

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Damples06/turkish-weather-hub-spring-boot.git
   cd TurkeyWeatherHub
   ```
   
2. **Build and Run:**

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   
3. **Access the Application:**

   Open your web browser and go to http://localhost:8686.

## Usage

**Area Details**
   
   To retrieve details for a specific area, use the following endpoint:
   
   ```
      GET /{city-name}
   ```

**Hourly Weather Data**

   
   To get the hourly weather data for a area, use the following endpoints:

   - Get All Hourly Data
   
   ```
      GET /{city-name}/hourly/all
   ```
   
   - Get Single Hourly Data

   ```
      GET /{city-name}/hourly/{i}
   ```

**Daily Weather Data**

   
   To get the daily weather data for a area, use the following endpoints:

   - Get All Daily Data
   
   ```
      GET /{city-name}/daily/all
   ```
   
   - Get Single Daily Data

   ```
      GET /{city-name}/daily/{i}
   ```

**Forecast Data**

   
   To get the forecast data for a area, use the following endpoint:

   
   ```
      GET /{city-name}/forecast
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
    open-in-view: false
   ```

## Contributing

   Contributions are welcome! Feel free to open issues and pull requests.
