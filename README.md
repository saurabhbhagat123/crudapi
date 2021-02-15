# CRUD API

[![Build Status](https://travis-ci.org/miguno/java-docker-build-tutorial.svg?branch=master)](https://travis-ci.org/miguno/java-docker-build-tutorial)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This project performs CRUD(create,retrieve,update,delete) operation on Person enity.

The Docker build uses a [multi-stage build setup](https://docs.docker.com/develop/develop-images/multistage-build/)
to minimize the size of the generated Docker image.

> **Golang developer?** Check out https://github.com/miguno/golang-docker-build-tutorial

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

```shell
1.Open url http://localhost:8080/swagger-ui.html
2.As this application is secured, you need to authorize this application
Click on Authorize button on right,
3.Enter client id and secret,
client id: client
secret: secret
Select all scopes
Click on Authorize
4.Expand person-controller and run the API.

You can also test/consume this api using POSTMAN
Steps:
1.Fetch Access Token

URL: http://localhost:8080/oauth/token
Method Type: POST
Body:
grant_type: client_credentials
client_id: client
client_secret: secret
Content-Type: application/x-www-form-urlencoded

2. Pass access token in Authorization header to access resource API
For example,
Resource API: http://localhost:8080/api/persons
Header:
  Authorization: Bearer bda55976-9d48-4b32-b164-b787effc80be
```


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

