# Word Search Game

This is a Word Search game application built using Java and Spring Boot. It allows users to create a grid and provides an API endpoint to retrieve the grid as a string.

## Features

- Generate a grid for the Word Search game.
- API endpoint to retrieve the grid as a string.

## Technologies Used

- Java
- Spring Boot

## Getting Started

Follow the instructions below to get started with the Word Search game application.

### 1. Clone the Repository

```shell
https://github.com/Sriramb0304/WordSearchGame.git
```

### 3. Build and Run the Application
 - Ensure that Java (JDK) and Maven are installed on your system.
 - Open the project in your preferred Java IDE.
 - Build the application using Maven.
 - Run the application.

## Endpoints
 - The application only provides REST API endpoint, so use an API testing application like Postman.
 - The application runs on port 8080. Visit ```http://localhost:8080/api/v1/grid``` to access the below api endpoints.
 - The application takes in the grid length and list of words seperated by commas as parameter and returns the grid as a String.
 - Sample GET request - ```http://localhost:8080/grid?gridSize=10&wordList=ONE,TWO,THREE```
   
![image](https://github.com/Sriramb0304/WordSearchGame/assets/72428614/99a93fcb-30d7-4298-86a5-a6cda76fc94c)
