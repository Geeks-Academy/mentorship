# Getting Started

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Programmers-Only-Group_mentorship&metric=alert_status)](https://sonarcloud.io/dashboard?id=Programmers-Only-Group_mentorship) [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Programmers-Only-Group_mentorship&metric=bugs)](https://sonarcloud.io/dashboard?id=Programmers-Only-Group_mentorship) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Programmers-Only-Group_mentorship&metric=coverage)](https://sonarcloud.io/dashboard?id=Programmers-Only-Group_mentorship)

## :pushpin: Prerequisites
* java 11+
* git
* maven 3.1+ (optional)

## :hammer: Installation
```bash
git clone https://github.com/Programmers-Only-Group/mentorship
cd mentorship
```

## :fire: Running locally
You can run application from Maven directly using Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```./mvnw spring-boot:run```

or you can build a jar file and run it from command line:

```bash
./mvnw package
java -jar target/*.jar
```

You can then access application here: http://localhost:8080/

## :pencil2: Contribute guide
1. Clone project
2. Create your feature branch from `develop` with prefix and task number (eg. `MEHN-1` where `MEHN` define prefix for mentorship and `1` is a task number)
3. Commit and push your changes to feature branch. Make sure your branch is up to date with `develop` branch.
4. Create pull request to `develop` branch

## :memo: Project structure

```bash
├── pom.xml
├── src
│   ├── main.java
│   │   ├── package (com...)
│   │   │    ├── MentorshipApplication.java
│   │   │    ├── commons
│   │   │    ├── mentors
│   │   │    ├── notification
│   │   │    ├── offers
│   ├── main.resources
│   │   ├── application.yml
│   │   ├── application-prod.yml
├── mvnw
└── mvnw.cmd
```

The project was splitted into three seperated bounded contexts `mentors` , `offers` , `notification` and shared `commons` package.

- `mentors` would be simple CRUD like application to manage and verify mentors.
- `offers` the most complicated part of whole application. It's responsible for offers and will contain core functionality.
- `notifications` package should allow api to send all kind of notifications
- `commons` contain all necessary classes which should be shared between packages

 **Bear in mind**: It's just a simple sketch of our architecture vision, feel free to code by yourself and change if require.
