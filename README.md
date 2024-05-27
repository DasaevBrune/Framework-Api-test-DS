# Venom API Test Project 🐍

## Description

This project consists of an automated test suite for the Swagger Petstore API. It uses Java with TestNG and RestAssured to perform tests on the API endpoints.

## Features 🎯

- Automated testing for the user and pet modules of the Swagger Petstore API.
- Use of Data Providers to perform tests with different datasets.
- Integration with TestNG to organize and execute test cases efficiently.
- Logging with Log4j to record detailed information about test execution.
- Use of Excel to store test data and record test results.

## Requirements 📦 

- Java JDK 8 or higher.
- Maven.
- Java-compatible IDE such as IntelliJ IDEA or Eclipse.

## Installation ⚙️

1. Clone this repository to your local machine:
    ```bash
    git clone git@github.com:DasaevBrune/Framework-Api-test-DS.git
    ```
2. Open the project in your IDE.
3. Configure Maven dependencies if necessary.
4. Run `mvn clean install` to build the project and download dependencies.

## Usage ⚡

Run test cases from your IDE, using Maven, or from the `testng.xml` file:
```bash
mvn test
```
Observe detailed test output in the console.
Examine generated reports to see test results.

## Project Structure  🏗️ 

```bash
swagger-petstore-api-tests
├── .idea
├── logs
│   └── all.log
├── reports
│   └── AutomationReport.html
├── src
│   ├── main
│   │   ├── java           # Source code
│   │   └── resources      # Configuration files
│   │       ├── log4j2.xml
│   │       └── routes.properties
│   ├── test
│   │   ├── java           # Automated tests
│   │   │   ├── api
│   │   │   │   ├── endpoints  # Classes to interact with API endpoints
│   │   │   │   ├── payload    # Classes for API request payloads
│   │   │   │   ├── test       # Test classes
│   │   │   │   └── utilities  # Utility classes for the project
├── target
│   └── testData              # Test data files
└── pom.xml

```
## Test Cases

### User Module

- ✨ `testPostUser`: Tests the POST request to create a new user.
- 🔍 `testGetUserByName`: Tests the GET request to retrieve a user by name.
- 🔄 `testUpdateUserByName`: Tests the PUT request to update a user by name.
- ❌ `testDeleteUserByName`: Tests the DELETE request to delete a user by name.
- ❓ `testGetUserWithInvalidName`: Tests the GET request with an invalid username.
- Additional test cases specified in DataProviders for various user-related scenarios.

### Pet Module

- ✨ `testPostPet`: Tests the POST request to add a new pet to the store.
- 🔍 `testGetPetById`: Tests the GET request to retrieve pet information by ID.
- 🔄 `testUpdatePet`: Tests the PUT request to update pet information.
- ❌ `testDeletePet`: Tests the DELETE request to delete a pet by ID.
- Additional test cases specified in DataProviders for various pet-related scenarios.

### Data-Driven Tests

- ✨ `testPostUser`: Data-driven test using different datasets to create users.
- ❌ `testDeleteUserByName`: Data-driven test using different usernames to delete users.

## Contributing 🌱

Contributions are welcome! If you wish to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make the necessary changes and commit them (`git commit -am 'Add new feature'`).
4. Push the branch (`git push origin feature/new-feature`).
5. Create a Pull Request.


