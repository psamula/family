# Family Evaluation Task
## Table of contents
  * [General info](#general-info)
  * [Technologies Used](#technologies-used)
  * [Installation and Setup](#installation-and-setup)
  * [Usage](#usage)
  * [Requirements](#requirements)

## General info
This project consists of two Java Spring app components that communicate with each other using RESTful APIs. The first component is responsible for processing both GET and POST requests from the client via createFamily and getFamily endpoints, second component is responsible for managing family members. Components use RestTemplate to provide RESTful communication.

The project uses Spring Data JDBC to provide a simple and consistent way to interact with the database, and Flyway for database migration management. PostgreSQL is used as the database engine. The project is built using Maven and requires Java 17.

Family Evaluation Task includes integration tests written with Mockito and JUnit to ensure the proper functioning of each component.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JDBC
- PostgreSQL
- Flyway
- JUnit
- Mockito
- Lombok
- Docker

## Installation and Setup
1. Clone the repository: ```git clone https://github.com/psamula/family```
2. Navigate to the project's root directory
3. Build both of the java spring app components jar files; you can do that in 2 ways:  
  a) by running .bat file (windows): ```familymvn.bat```<br>
  b) by building each component's jar separately: ```mvn clean install -DskipTests=true``` on each component's root folder
4. Build docker-compose using the built .jar files and postgres: ```docker-compose build```
5. Run the whole setup of family components + PostgreSQL by running ```docker-compose up```

## Usage
Once the application is running, you can access it by navigating to [localhost:8080/families](http://localhost:8080/families)

### Add and get family and family members
1. Post new family and its members to the [localhost:8080/families](http://localhost:8080/families) POST endpoint using requested Json body
2. Get the family and its members by accessing [localhost:8080/families/{familyid}](http://localhost:8080/families/{familyId}) GET endpoint

## Requirements
To run this app you'll need:
- JDK 17
- Docker
- Maven 3.9.0 
