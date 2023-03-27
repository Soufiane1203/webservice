Movie List Web Service
This is a simple web service that allows you to manage a list of movies and actors.

Requirements
Java 8+
Maven
Installation
Clone this repository
Open a terminal and navigate to the project directory
Run mvn clean install to build the project
Usage
Start the web service by running mvn spring-boot:run in the terminal
Open your web browser and navigate to http://localhost:8080/swagger-ui.html to access the Swagger UI documentation
Use the Swagger UI to test the different endpoints
Endpoints
POST /movies: Add a new movie to the list
POST /actors: Add a new actor to the list
POST /movies/{movieId}/actors/{actorId}: Link an actor to a movie
GET /movies/{title}: Retrieve information about a movie by title
GET /actors/{name}: Retrieve information about an actor by name
