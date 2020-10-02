## Getting Started

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Programmers-Only-Group_mentorship&metric=alert_status)](https://sonarcloud.io/dashboard?id=Programmers-Only-Group_mentorship)

### Prerequisites
* java 11+
* git
* maven 3.1+ (optional)

### Installation
```bash
git clone https://github.com/Programmers-Only-Group/mentorship
cd mentorship
```

## Running locally
You can run application from Maven directly using Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```./mvnw spring-boot:run```

or you can build a jar file and run it from command line:

```bash
./mvnw package
java -jar target/*.jar
```

You can then access application here: http://localhost:8080/

# Package structure

```bash
├── pom.xml
├── src
│   ├── main.java
│   │   ├── package (com...)
│   │   │    ├── MentorshipApplication.java
│   │   │    ├── api
│   │   │    │    ├── SomeController.java
│   │   │    │    ├── data
│   │   │    │    ├── mapper
│   │   │    ├── client
│   │   │    │    ├── SomeClient.java
│   │   │    │    ├── data
│   │   │    │    ├── config
│   │   │    ├── config
│   │   │    ├── repository
│   │   │    │    ├── SomeRepository.java
│   │   │    │    ├── enitity
│   │   │    │    ├── impl
│   │   │    ├── service
│   │   │    │    ├── SomeService.java
│   │   │    │    ├── data
│   │   │    │    ├── mapper
│   │   │    ├── utils
│   ├── main.resources
│   │   ├── application.yml
│   │   ├── application-prod.yml
├── mvnw
└── mvnw.cmd
```

## Api Layer (Api)
 This layer is responsible for handling incoming requests from frontend application or another internal services.
 
 - **root** - root api package should contain all available controller (eg.``MentorshipController.java``, ``SkillController.java``)
 - **data** - should contain Data Transfer Objects between api layer <--> client layer
 - **mapper** - should contain implementation of mappers responsible for mapping object to and from next layer (business layer)
 
 
 **Bear in mind**: api layer isn't correct place for business logic, it should only handle incoming request and delegate to proper service.
 
 ## Business Layer (Service)
 One of the most important place in application. Contain all complicated logic involved with business process.
 
 - **root** - root service package should contain all services responsible for implement business logic (eg.``MentorshipService.java``, ``ReservationService.java``)
 - **data** - package keep basic objects related with domain
 - **mapper** - should contain implementation of mappers responsible for mapping object to and from next layer (client/data layer)
 
 **Bear in mind**: keep business layer as secure as possible, so please write tests! It's only layer which certainly requires it!
 
 ## Client / Database Layer (client, repository)  
 This layer is responsible for using external tools like database or  services (like Google Calendar API).
 
 - **root** (repository) - root repository package should contain main repositories (eg.``MentorshipRepository.java``)
 - **entity** (repository) - should contain ORM classes (eg.``MentorshipEntity.java``)
 - **root** (client) - root client package should contain external services(eg.``GoogleAPI.java``)
 - **config** (client) - package which loads and prepare all necessary configuration to use external clients (like. keeping secrets, set connection pool, connection timeout etc)
  - **data** (client)  - should contain Data Transfer Objects between service layer <--> client layer (DTO's)