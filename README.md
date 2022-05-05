# Gandalf API

Gandalf API is a REST API service to manage all authentication needs in the project scope. It was developed during IT Bootcamp - Wave 5 as a part of the last group project.

## Endpoints

See also the OpenAPI Specification (/swagger-ui.html while running).

- Base API Port: 8080
- Base Database Port: 5432

| Method   | URI       | Description    |
| :---------- | :--------- | :----------------------- |
| `POST` | `/user/v1` | Register a new User |
| `GET` | `/user/v1/{id}` | Find User by ID |
| `POST` | `/user/v1/signin` | Performs a Sign In |

## Requirements

- Java 11 and later

## Installation and Usage

Use the given Maven Wrapper and Docker to build a new service container

```bash
## 1. Clone project to local 
git clone https://github.com/Grupo9-ITBootcampMeli/pi-ml-warehouse

## 2. Use Maven Wrapper to generate a new build  
./mvnw clean package

## 2.1. Optionally, build without tests 
./mvnw clean package -DskipTests

## 3. Start service via Docker 
docker-compose up

## 3.1. If you are recreating a container, build a new Docker image and delete the previous
docker-compose build --no-cache && docker-compose up -d && docker rmi -f $(docker images -f "dangling=true" -q)

```

This service is required for full operation of the [Products](https://github.com/Grupo9-ITBootcampMeli/pi-ml-products), [Cart](https://github.com/Grupo9-ITBootcampMeli/pi-ml-cart) and [Warehouse](https://github.com/Grupo9-ITBootcampMeli/pi-ml-warehouse) services.

## Authors
- [Amanda Zara](https://github.com/azfernandes)
- [André Veziane](https://github.com/andrevezi)
- [Antônio Schappo](https://github.com/antonio-schappo)
- [Guilherme Pereira](https://github.com/GuiSilva23)
- [Joan Silva](https://github.com/joanmeli)
- [Vinicius Brito](https://github.com/ViniCBrito)
