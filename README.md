# Universal Blogger

This is the source code to a simple blogging application.

It comprises the following parts:
* A Docker container for a persistence provider:
	* PostGres relational Database
	* pgAdmin application to manage the database
* A Spring Boot REST service to as the main interface for data operations on the datasbase.
* An iOS native App as one front end for blogging