# Family Evaluation Task
## Table of contents
  * [General info](#general-info)
  * [Technologies Used](#technologies-used)
  * [Installation and Setup](#installation-and-setup)
  * [Usage](#usage)
  * [Requirements](#requirements)

## General info
"Movie-Hub" is a RESTful Java Spring application incorporating such
functionalities as: browsing and retrieving movies from external IMDb API,
facilitating its users to post ratings of each movie, character, and cast
member. Furthermore, Movie-Hub includes stateless user authentication via
JWT.  
It's fully integration-tested and dockerized.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JDBC
- Spring Security
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
  a) by running .bat file (windows): ```familymvn.bat```
  b) by building each component's jar separately: ```mvnw clean install``` on each component's root folder
4. Build docker-compose using the built .jar files and postgres: ```docker-compose build```
5. Run the whole setup of family components + PostgreSQL by running ```docker-compose up```

## Usage
Once the application is running, you can access it by navigating to [localhost:8080/families](http://localhost:8080/families)

### Add new Family
1. Post new family and its members to the [localhost:8080/families](http://localhost:8080/families) POST endpoint
2. Get the family and its members by accessing [localhost:8080/families/{familyid}](http://localhost:8080/families/{familyId}) GET endpoint

## Requirements
To run this app you'll need:
- JDK 17
- Docker
- Maven 3.9.0 
