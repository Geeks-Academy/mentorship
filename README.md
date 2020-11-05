# Getting Started

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Programmers-Only-Group_mentorship&metric=alert_status)](https://sonarcloud.io/dashboard?id=Programmers-Only-Group_mentorship)

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
2. Create your feature branch with prefix and task number (eg. `MEHN-1` where `MEHN` define prefix for mentorship and `1` is a task number)
3. Commit and push your changes to feature branch. Make sure your branch is up to date with master branch.
4. Create pull request to master branch