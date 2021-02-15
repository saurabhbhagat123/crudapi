# CRUD API

[![Build Status](https://travis-ci.org/miguno/java-docker-build-tutorial.svg?branch=master)](https://travis-ci.org/miguno/java-docker-build-tutorial)


This project performs CRUD(create,retrieve,update,delete) operation on Person enity.


## Features
- Exposed CRUD api which performs insertion,updation,deletion and retrieval operation.
- This application is secured by OAUTH2.You need access token to hit the resource api.



# Requirements

Docker must be installed. That's it. You do not need a Java JDK or Maven installed.


# Usage and Demo

**Step 1:** Create the Docker image according to [Dockerfile](Dockerfile).
This step uses Maven to build, test, and package the [Java application](src/main/java/com/miguno/App.java)
according to [pom.xml](pom.xml).  

```shell
# This may take a few minutes.
# go to project home directory
$ docker build -t rest-crud .
```

**Step 2:** Start a container for the Docker image.

```shell
$ docker run -p 8080:8080 rest-crud
```

**Step 3:** Test this application


- Open url http://localhost:8080/swagger-ui.html<br>
- As this application is secured, you need to authorize this application
Click on Authorize button on right.<br>
- Enter client id and secret,<br>
client id: client<br>
secret: secret<br>
Select all scopes<br>
Click on Authorize<br><br>
- Expand person-controller and run the API.<br>

You can also test/consume this api using POSTMAN<br>
Steps:
- Fetch Access Token

    **Token URL**: http://localhost:8080/oauth/token<br>
    **Method Type**: POST<br>
    **Body**:<br>
        grant_type: client_credentials<br>
        client_id: client<br>
        client_secret: secret<br>
**Content-Type**: application/x-www-form-urlencoded<br>

- Pass access token in Authorization header to access resource API<br>
For example,<br>
Resource API: http://localhost:8080/api/persons<br>
Header:<br>
  Authorization: Bearer bda55976-9d48-4b32-b164-b787effc80be<br>



# Notes



You can also build, test, package, and run the Java application locally (without Docker)
if you have JDK 8+ and Maven installed.

```shell
# Build, test, package the application locally
$ mvn package

# Run the application locally
$ java -jar target/restproject-0.0.1-SNAPSHOT.jar
```


## Tech/framework used

<b>Server Side Technologies:</b>
- Java 8
- Spring Boot
- Spring Security
- Build Tool: Maven


<b>Code Repository:</b>
- Github using Git

