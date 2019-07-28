[![Build Status](https://travis-ci.com/roqmarcelo/always-vinyl.svg?branch=master)](https://travis-ci.com/roqmarcelo/always-vinyl)
[![Code Coverage](https://codecov.io/gh/roqmarcelo/always-vinyl/coverage.svg)](https://codecov.io/gh/roqmarcelo/always-vinyl)

# AlwaysVinyl API

Simple API to test my capabilities as a Back-End Engineer.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Java 8
* Maven
* Git
* Docker* 

*Docker is only necessary if you want to build and run the project through a container

### Installing

Clone the repository

```
git clone https://github.com/roqmarcelo/always-vinyl.git
```

Go to the project folder

```
cd always-vinyl
```

Build the project

```
mvn clean package
```

And then run

```
mvn spring-boot:run
```

Or if you prefer to run with Docker

```
docker build -t always-vinyl .
docker run -p 8080:8080 always-vinyl
```

## Running the tests

To run only the tests

```
mvn test
```

This will run all unit tests and produce a coverage report located at **target/jacoco-report/index.html**

## Resources

To see detailed information about the resources go to http://localhost:8080/swagger-ui.html with project running.

### Albums
Albums are identified by their ids, which are unique integers, and live under ```/albums/<id>```.

* Find Albums
  * Headers: Content-Type application/json
  * GET: http://localhost:8080/albums
  
* Find Album By Id
  * Headers: Content-Type application/json
  * GET: http://localhost:8080/albums/1

### Sales
Sales are identified by their ids, which are unique integers, and live under ```/sales/<id>```.

* Find Sales
  * Headers: Content-Type application/json
  * GET: http://localhost:8080/sales
  
* Find Sale By Id
  * Headers: Content-Type application/json
  * GET: http://localhost:8080/sales/1
  
* Create Sale
  * Headers: Content-Type application/json
  * POST: http://localhost:8080/sales
  * Body:
  ```
  {
    "items": [
        {
        	"albumId": 15
        },
        {
        	"albumId": 83
        },
        {
        	"albumId": 2
        },
        {
        	"albumId": 192
        }
    ]
  }
  ```
 

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Flyway](https://flywaydb.org/)
* [H2 Database Engine](https://www.h2database.com)
* [Spotify Web API Java](https://github.com/thelinmichael/spotify-web-api-java)
* [Spring Boot Throttling](https://github.com/weddini/spring-boot-throttling)
* [Swagger](https://swagger.io/)

## Authors

* **Roque Santos** - [LinkedIn](https://www.linkedin.com/in/roqmarcelo/)

## Notes

* All the endpoints were made public intentionally
* All the api resources have a limit of 50 calls per second (applied request throttling to make the api more resilient)
* A pre-built docker image of this project is available at [Docker Hub](https://hub.docker.com/r/roquesantosdev/always-vinyl)
