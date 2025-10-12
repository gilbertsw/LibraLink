# LibraLink API Developer's Guide

--- 

## Introduction

**LibraLink Back End** is the server-side API for LibraLink, an online library management platform.
It provides endpoints to manage books, users, borrowings, and library resources efficiently.
The API is built with **Java 21** and **Spring Framework**, using **MariaDB** as the primary database and **Redis** for caching.

This project is intended for developers who want to integrate with LibraLink or contribute to its back-end development.

## Technologies / Tools

- Java SE 21
- Maven 4.0.0
- MariaDB (10.1)
- Redis (latest version)
- Docker & Docker Compose
- Swagger v3 (for API documentation)

---

## Getting Started

These instructions will help you set up LibraLink API on your local machine for development purposes.

### Prerequisites

#### 1. Install Oracle Java SE Development Kit 21 (LTS)

Download [page](https://www.oracle.com/java/technologies/downloads/#java21).
Or you could use the command
`sudo apt-get install oracle-java21-installer`.
Typing `java -version` should output the following

```
java version "21.0.3" 2024-04-16 LTS
Java(TM) SE Runtime Environment (build 21.0.3+7-LTS-152)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.3+7-LTS-152, mixed mode, sharing)
```

#### 2. Install MariaDB 10.1

```
You can skip this step and use docker to install development dependencies. Go to section Setup Local Dependencies with Docker
```

Download [page](https://downloads.mariadb.org/mariadb/10.1/). Please follow the instruction based on OS.


### Setup Local Dependencies with Docker

Notes: If you need to change default value of the configuration, create a file `.env` based on [example.env](example.env)

1. Install Docker based on your OS. [Guide](https://docs.docker.com/get-docker/)
2. Execute `docker-compose -f docker-compose.yml up --build -d` in the root of the project.
3. Go to your local [adminer](http://localhost:10001) to check your database
    ```
    Username: root
    Password: root
    Database: LibraLinkDB
   ```

### How to Build
#### Local Environment

You could build it with you favorite IDE, or by using command-line 
``mvn clean install``. To build without running UnitTest, just add argument `-DskipTests`

### How to Run

#### Local Environment

You could run it with your favorite IDE (just run it), or by using command-line ``java -jar {jar_name}``
  
---

## Discipline Dependency

These documents contain all coding conventions to develop LibraLink backend API neatly.
Here is the list of the documents:

1. [Git convention](docs/git-convention.md)
2. [Package management](docs/package-management.md)
3. [Class management](docs/class-management.md)

--- 

## Contribution

We welcome contributions!
Please follow these steps:

1. Fork the repository
2. Create a feature branch, e.g. ``git checkout -b feature/YourFeature``
3. Commit your changes, e.g. ``git commit -m 'feat: Add new feature'``
4. Push to the branch, e.g. ``git push origin feature/YourFeature``
5. Open a Pull Request

