# Universal Blogger REST Service

This is the backend code to the Universal Blogger application.

It is comprised of the following parts:
* Source code for the Spring Boot REST service
* A docker-compose file for:
  * Postgres relational database
  * pgAdmin application to manage the database should you so wish
* Database schema/table creation scripts
* Sample User and Post data.
* Documentation:
  * REST Service source JavaDoc
  * REST Service OpenAPI specification

## Usage
* Start the docker container with ```docker-compose up``` on the command line in the folder with the docker-compose.yanl file.
* Use PGAdmin to create the database tables
* Start the REST service with ```./mvnw clean spring-boot:run ``` in the root source code folder
* Use the postman collection in the ```documentation``` folder to test the setup
* Use Postman to first import the User sample data json file then the Post sample data json.
  * See the OpenAPI Document for the endpoints.
